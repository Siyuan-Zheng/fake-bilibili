package me.shigure.dao;

import me.shigure.beans.CommentBean;
import me.shigure.beans.ReplyBean;
import me.shigure.utils.BeanUtil;
import me.shigure.utils.C3P0Util;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author zhengsiyuan
 * @date 2019-04-24 16:09
 */
public class ReplyDao {

	public static ArrayList<ReplyBean> getReplyList(int commentId, int start, int end) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
		ArrayList<ReplyBean> arrayList = new ArrayList<>();

		Connection conn = C3P0Util.getConnection();

		String sql = "select t1.*, t2.userName, t3.imagePath, t2.levelId, c.toUserName, (@i:=@i+1) as replyFloor " +
				"from (t_comment_reply t1, t_user t2, t_image t3, (select @i:=0) t4 ) " +
				"left join (select userName as toUserName,userId from t_user) c on c.userId = t1.toUserId " +
				"where t1.toCommentId= ? " +
				"and t2.userId = t1.fromUserId " +
				"and t2.userPicId = t3.imageId " +
				"order by t1.replyTime desc " +
				"limit ?,?";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, commentId);
		statement.setInt(2, start);
		statement.setInt(3, end);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			ReplyBean replyBean;
			replyBean = (ReplyBean) new BeanUtil().autoBean(ReplyBean.class, resultSet);
			arrayList.add(replyBean);
		}

		C3P0Util.release(conn, statement, resultSet);

		return arrayList;

	}

	public static int getReplyNum(int commentId) throws SQLException{

		Connection conn = C3P0Util.getConnection();

		String sql = "select count(replyId) channelNum from t_comment_reply where toCommentId = ?";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, commentId);

		ResultSet resultSet = statement.executeQuery();

		int replyNum = 0;

		while (resultSet.next()){
			replyNum = resultSet.getInt("channelNum");
		}

		C3P0Util.release(conn,statement,resultSet);

		return replyNum;

	}

	public static int addReply(ReplyBean replyBean) throws SQLException {
		Connection conn = C3P0Util.getConnection();

		String sql = "insert into t_comment_reply(toCommentId, replyContent, replyType, fromUserId, toUserId, replyTime, replyLikeNum) VALUES (?,?,?,?,?,?,?)";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setInt(1, replyBean.getToCommentId());
		statement.setString(2, replyBean.getReplyContent());
		statement.setInt(3, replyBean.getReplyType());
		statement.setInt(4, replyBean.getFromUserId());
		statement.setInt(5, replyBean.getToUserId());
		statement.setString(6, replyBean.getReplyTime());
		statement.setInt(7, replyBean.getReplyLikeNum());

		int result = statement.executeUpdate();

		C3P0Util.release(conn,statement);

		return result;
	}

	public static int getReplyId() throws SQLException {

		Connection conn = C3P0Util.getConnection();

		String sql = "select max(replyId) replyId from t_comment_reply";

		PreparedStatement statement = conn.prepareStatement(sql);

		ResultSet resultSet = statement.executeQuery();

		int replyId = 0;

		while (resultSet.next()) {
			replyId = resultSet.getInt("replyId");
		}

		C3P0Util.release(conn,statement,resultSet);

		return replyId;
	}

	public static int likeReply(int replyId) throws SQLException {
		Connection conn = C3P0Util.getConnection();

		String sql = "update t_comment_reply set replyLikeNum = replyLikeNum + 1 where replyId =?";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, replyId);

		int result = statement.executeUpdate();

		C3P0Util.release(conn,statement);

		return result;
	}
}
