package me.shigure.dao;

import me.shigure.beans.CommentBean;
import me.shigure.beans.HistoryBean;
import me.shigure.utils.BeanUtil;
import me.shigure.utils.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shigure
 * @date 2019-04-24 14:43
 */
public class CommentDao {

	public static ArrayList<CommentBean> getCommentList(int videoId, int start, int end) throws SQLException {

		QueryRunner runner = new QueryRunner(C3P0Util.getDataSource());

		String sql = "select t1.*, t2.userName, t3.imagePath, t2.levelId, (@i:=@i+1) as rowNum " +
				"from t_comment t1, t_user t2, t_image t3, (select @i:=0) t4 " +
				"where t1.videoId = ? " +
				"and t2.userId = t1.userId " +
				"and t2.userPicId = t3.imageId " +
				"and t1.commentType = 1 " +
				"and t1.commentStatus != 3 " +
				"order by t1.commentTime desc " +
				"limit ?,?";


		return (ArrayList<CommentBean>) runner.query(sql, new BeanListHandler<CommentBean>(CommentBean.class),videoId,start,end);

	}

	public static ArrayList<CommentBean> getDynamicCommentList(int videoId, int start, int end) throws SQLException {

		QueryRunner runner = new QueryRunner(C3P0Util.getDataSource());

		String sql = "select t1.*, t2.userName, t3.imagePath, t2.levelId, (@i:=@i+1) as rowNum " +
				"from t_comment t1, t_user t2, t_image t3, (select @i:=0) t4 " +
				"where t1.videoId = ? " +
				"and t2.userId = t1.userId " +
				"and t2.userPicId = t3.imageId " +
				"and t1.commentType = 2 " +
				"and t1.commentStatus != 3 " +
				"order by t1.commentTime desc " +
				"limit ?,?";

		return (ArrayList<CommentBean>) runner.query(sql, new BeanListHandler<CommentBean>(CommentBean.class),videoId,start,end);

	}

	public static int getCommentNum(int videoId) throws SQLException{

		Connection conn = C3P0Util.getConnection();

		String sql = "select count(commentId) channelNum from t_comment where videoId = ? and commentType = 1";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, videoId);

		ResultSet resultSet = statement.executeQuery();

		int commentNum = 0;

		while (resultSet.next()){
			commentNum = resultSet.getInt("channelNum");
		}

		C3P0Util.release(conn,statement,resultSet);

		return commentNum;

	}

	public static int getDynamicCommentNum(int videoId) throws SQLException{

		Connection conn = C3P0Util.getConnection();

		String sql = "select count(commentId) channelNum from t_comment where videoId = ? and commentType = 2";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, videoId);

		ResultSet resultSet = statement.executeQuery();

		int commentNum = 0;

		while (resultSet.next()){
			commentNum = resultSet.getInt("channelNum");
		}

		C3P0Util.release(conn,statement,resultSet);

		return commentNum;

	}

	public static int getMaxFloor(int videoId, int commentType) throws SQLException {

		Connection conn = C3P0Util.getConnection();

		String sql = "select max(@i:=@i+1) as rowNum from t_comment, (select @i:=0) t1 where videoId = ? and commentType = ?";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, videoId);
		statement.setInt(2, commentType);

		ResultSet resultSet = statement.executeQuery();

		int maxFloor = 0;

		while (resultSet.next()){
			maxFloor = resultSet.getInt("rowNum");
		}

		C3P0Util.release(conn,statement,resultSet);

		return maxFloor;
	}

	public static int addComment(CommentBean commentBean) throws SQLException {
		Connection conn = C3P0Util.getConnection();

		String sql = "insert into t_comment(commentContext, userId, commentTime, videoId, likeNum,commentType,commentStatus) VALUES (?,?,?,?,?,?,?)";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setString(1, commentBean.getCommentContext());
		statement.setInt(2, commentBean.getUserId());
		statement.setString(3, commentBean.getCommentTime());
		statement.setInt(4, commentBean.getVideoId());
		statement.setInt(5, commentBean.getLikeNum());
		statement.setInt(6, commentBean.getCommentType());
		statement.setInt(7, commentBean.getCommentStatus());

		int result = statement.executeUpdate();

		C3P0Util.release(conn,statement);

		return result;
	}

	public static int likeComment(int commentId) throws SQLException {
		Connection conn = C3P0Util.getConnection();

		String sql = "update t_comment set likeNum = likeNum + 1 where commentId = ?";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, commentId);

		int result = statement.executeUpdate();

		C3P0Util.release(conn,statement);

		return result;
	}
}
