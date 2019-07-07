package me.shigure.dao;

import me.shigure.beans.VideoInfoBean;
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
 * @date 2019-03-27 20:33
 */
public class VideoDao {

	public static VideoInfoBean getVideoInfo(int video) throws SQLException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {

		VideoInfoBean videoInfoBean = null;

		Connection conn = C3P0Util.getConnection();

		String sql = "select t1.*, t2.channelName, t3.userName, t3.userDesc, t4.imagePath " +
				"from t_video t1, t_channel t2, t_user t3, t_image t4 " +
				"where videoId = ? " +
				"and t1.userId = t3.userId " +
				"and t1.videoPicId = t4.imageId " +
				"and t1.channelId = t2.channelId";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1,video);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()){
			videoInfoBean = (VideoInfoBean) new BeanUtil().autoBean(VideoInfoBean.class,resultSet);
		}

		C3P0Util.release(conn,statement, resultSet);

		return videoInfoBean;
	}

	public static ArrayList<VideoInfoBean> getRecommendVideo(int channelId) throws SQLException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {

		ArrayList<VideoInfoBean> arrayList = new ArrayList<>();

		Connection conn = C3P0Util.getConnection();

		String sql = "select t1.*, t2.userName, t2.userDesc, t3.channelName, t4.imagePath " +
				"from t_video t1, t_user t2, t_channel t3, t_image t4 " +
				"where t1.channelId = ? " +
				"and t1.userId = t2.userId " +
				"and t1.channelId = t3.channelId " +
				"and t1.videoPicId = t4.imageId " +
				"and videoStatus = 1 " +
				"limit 20";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1,channelId);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()){
			VideoInfoBean videoInfoBean;
			videoInfoBean = (VideoInfoBean) new BeanUtil().autoBean(VideoInfoBean.class,resultSet);
			arrayList.add(videoInfoBean);
		}

		C3P0Util.release(conn,statement,resultSet);

		return arrayList;
	}

	public static int setFavoriteNum(int videoId, String symbol) throws SQLException {
		Connection conn = C3P0Util.getConnection();

		String sql = "update t_video set videoFavoriteNum = videoFavoriteNum "+ symbol +" 1 where videoId = ?";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setInt(1, videoId);

		int result = statement.executeUpdate();

		C3P0Util.release(conn,statement);

		return result;
	}

	public static int addLikeNum(int videoId) throws SQLException {
		Connection conn = C3P0Util.getConnection();

		String sql = "update t_video set videoLikeNum = videoLikeNum + 1 where videoId = ?";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setInt(1, videoId);

		int result = statement.executeUpdate();

		C3P0Util.release(conn,statement);

		return result;
	}

	public static int addCommentNum(int videoId) throws SQLException {
		Connection conn = C3P0Util.getConnection();

		String sql = "update t_video set videoCommentNum = videoCommentNum + 1 where videoId = ?";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setInt(1, videoId);

		int result = statement.executeUpdate();

		C3P0Util.release(conn,statement);

		return result;
	}

	public static int addVideoPointNum(int videoId) throws SQLException {
		Connection conn = C3P0Util.getConnection();

		String sql = "update t_video set videoPointNum = videoPointNum + 1 where videoId = ?";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setInt(1, videoId);

		int result = statement.executeUpdate();

		C3P0Util.release(conn,statement);

		return result;
	}

	public static int addVideo(VideoInfoBean videoInfoBean) throws SQLException {
		Connection conn = C3P0Util.getConnection();

		String sql = "insert into t_video (videoPicId, videoPath, userId, videoName, videoLikeNum, videoFavoriteNum, videoPointNum, videoUpTime, videoDesc, videoTag, operateTime, videoCommentNum, channelId,videoStatus) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setInt(1, videoInfoBean.getVideoPicId());
		statement.setString(2, videoInfoBean.getVideoPath());
		statement.setInt(3, videoInfoBean.getUserId());
		statement.setString(4, videoInfoBean.getVideoName());
		statement.setInt(5, videoInfoBean.getVideoLikeNum());
		statement.setInt(6, videoInfoBean.getVideoFavoriteNum());
		statement.setInt(7, videoInfoBean.getVideoPointNum());
		statement.setString(8, videoInfoBean.getVideoUpTime());
		statement.setString(9, videoInfoBean.getVideoDesc());
		statement.setString(10, videoInfoBean.getVideoTag());
		statement.setString(11, videoInfoBean.getOperateTime());
		statement.setInt(12, videoInfoBean.getVideoCommentNum());
		statement.setInt(13, videoInfoBean.getChannelId());
		statement.setInt(14, videoInfoBean.getVideoStatus());

		int result = statement.executeUpdate();

		C3P0Util.release(conn,statement);

		return result;
	}

	public static ArrayList<VideoInfoBean> getVideoByUserId(int userId) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

		ArrayList<VideoInfoBean> arrayList = new ArrayList<>();

		Connection conn = C3P0Util.getConnection();

		String sql = "select t1.*, t2.userName, t2.userDesc, t3.channelName, t4.imagePath " +
				"from t_video t1, t_user t2, t_channel t3, t_image t4 " +
				"where t1.userId = ? " +
				"and t1.userId = t2.userId " +
				"and t1.channelId = t3.channelId " +
				"and videoStatus = 1 " +
				"and t1.videoPicId = t4.imageId " +
				"and t1.videoStatus != 3";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1,userId);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()){
			VideoInfoBean videoInfoBean;
			videoInfoBean = (VideoInfoBean) new BeanUtil().autoBean(VideoInfoBean.class,resultSet);
			arrayList.add(videoInfoBean);
		}

		C3P0Util.release(conn,statement,resultSet);

		return arrayList;
	}

}
