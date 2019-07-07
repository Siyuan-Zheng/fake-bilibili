package me.shigure.dao;

import me.shigure.beans.DynamicBean;
import me.shigure.beans.VideoInfoBean;
import me.shigure.utils.BeanUtil;
import me.shigure.utils.C3P0Util;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author zhengsiyuan
 * @date 2019-03-06 15:44
 */
public class DynamicDao {

	public static ArrayList<VideoInfoBean> getDynamicVideoList(String[] userID) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
		ArrayList<VideoInfoBean> arrayList = new ArrayList<>();

		Connection conn = C3P0Util.getConnection();

		StringBuffer sql = new StringBuffer("select t1.*, t2.userName, t2.userDesc, t3.channelName, t4.imagePath " +
				"from t_video t1, t_user t2, t_channel t3, t_image t4 " +
				"where t1.userId = t2.userId " +
				"and t1.channelId = t3.channelId " +
				"and t1.videoPicId = t4.imageId " +
				"and videoStatus = 1 " +
				"and (t1.userId = ?");

		for (int i = 0; i < userID.length-1; i++){
			sql.append(" or t1.userId = ?");
		}

		sql.append(") order by t1.videoUpTime limit 50");

		PreparedStatement statement = conn.prepareStatement(String.valueOf(sql));

		for (int i = 0; i < userID.length; i++){
			statement.setInt(i+1, Integer.parseInt(userID[i]));
		}

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()){
			VideoInfoBean videoInfoBean;
			videoInfoBean = (VideoInfoBean) new BeanUtil().autoBean(VideoInfoBean.class,resultSet);
			arrayList.add(videoInfoBean);
		}

		C3P0Util.release(conn,statement,resultSet);

		return arrayList;
	}

	public static ArrayList<DynamicBean> getDynamicList(String[] userId, int start, int end) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

		ArrayList<DynamicBean> arrayList = new ArrayList<>();

		Connection conn = C3P0Util.getConnection();

		StringBuffer sql = new StringBuffer("select t1.*, t2.videoName, t2.videoDesc,t2.videoPointNum, t2.videoCommentNum, t2.userId as videoUserId, t3.userName, t4.imagePath as videoImagePath, c.userImagePath, c2.videoUserImagePath, c3.videoUserName " +
				"from (t_dynamic t1, t_video t2, t_user t3, t_image t4) " +
				"left join (select imagePath as userImagePath,imageId from t_image) c on c.imageId = t3.userPicId " +
				"left join (select userPicId,userId from t_user) c1 on c1.userId = t1.userId " +
				"left join (select imagePath as videoUserImagePath, imageId  from t_image) c2 on c2.imageId = c1.userPicId " +
				"left join (select userId,userName as videoUserName from t_user) c3 on c3.userId = t2.userId " +
				"where t1.videoId = t2.videoId " +
				"and t1.userId = t3.userId " +
				"and t2.videoPicId = t4.imageId " +
				"and t1.dynamicStatus != 2 && t1.dynamicStatus != 3 " +
				"and (t1.userId = ?");

		for (int i = 0; i < userId.length-1; i++){
			sql.append(" or t1.userId = ?");
		}

		sql.append(") order by t1.dynamicTime desc limit ?,?");

		PreparedStatement statement = conn.prepareStatement(String.valueOf(sql));

		for (int i = 0; i < userId.length; i++){
			statement.setInt(i+1, Integer.parseInt(userId[i]));
		}
		statement.setInt(userId.length+1, start);
		statement.setInt(userId.length+2, end);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()){
			DynamicBean dynamicBean;
			dynamicBean = (DynamicBean) new BeanUtil().autoBean(DynamicBean.class,resultSet);
			arrayList.add(dynamicBean);
		}

		C3P0Util.release(conn,statement,resultSet);

		return arrayList;

	}

	public static int getDynamicNum(String[] userId) throws SQLException{

		Connection conn = C3P0Util.getConnection();

		StringBuffer sql = new StringBuffer("select count(dynamicId) channelNum from t_dynamic where (userId = ?");

		for (int i = 0; i < userId.length-1; i++){
			sql.append(" or userId = ?");
		}

		sql.append(")");

		PreparedStatement statement = conn.prepareStatement(String.valueOf(sql));

		for (int i = 0; i < userId.length; i++){
			statement.setInt(i+1, Integer.parseInt(userId[i]));
		}
		ResultSet resultSet = statement.executeQuery();

		int commentNum = 0;

		while (resultSet.next()){
			commentNum = resultSet.getInt("channelNum");
		}

		C3P0Util.release(conn,statement,resultSet);

		return commentNum;

	}

	public static int addCommentNum(int dynamicId) throws SQLException {
		Connection conn = C3P0Util.getConnection();

		String sql = "update t_dynamic set dynamicCommentNum = dynamicCommentNum + 1 where dynamicId = ?";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setInt(1, dynamicId);

		int result = statement.executeUpdate();

		C3P0Util.release(conn,statement);

		return result;
	}

	public static int addDynamic(DynamicBean dynamicBean) throws SQLException {
		Connection conn = C3P0Util.getConnection();

		String sql = "insert into t_dynamic(dynamicContent, dynamicType, videoId, userId, dynamicTime, dynamicLikeNum, dynamicCommentNum, dynamicStatus) values (?,?,?,?,?,?,?,?)";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setString(1, dynamicBean.getDynamicContent());
		statement.setInt(2, dynamicBean.getDynamicType());
		statement.setInt(3, dynamicBean.getVideoId());
		statement.setInt(4, dynamicBean.getUserId());
		statement.setString(5, dynamicBean.getDynamicTime());
		statement.setInt(6, dynamicBean.getDynamicLikeNum());
		statement.setInt(7, dynamicBean.getDynamicCommentNum());
		statement.setInt(8, dynamicBean.getDynamicStatus());

		int result = statement.executeUpdate();

		C3P0Util.release(conn,statement);

		return result;
	}

}
