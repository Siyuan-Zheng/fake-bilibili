package me.shigure.dao;

import me.shigure.beans.VideoInfoBean;
import me.shigure.beans.ChannelBean;
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
 * @date 2019-03-04 17:35
 */
public class IndexDao {
	/**
	 * 获取有新动态的前八个视频
	 * @param channelId 视频类别id
	 * @return ArrayList<BaseVideoBean>
	 */
	public static ArrayList<VideoInfoBean> getNewVideo(int channelId) throws SQLException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {

		ArrayList<VideoInfoBean> arrayList = new ArrayList<>();

		Connection conn = C3P0Util.getConnection();

		String sql = "select t1.*, t2.userName, t3.channelName, t2.userDesc, t4.imagePath " +
				"from t_video t1, t_user t2, t_channel t3, t_image t4 " +
				"where t1.channelId = ? " +
				"and t1.userId = t2.userId " +
				"and t1.channelId = t3.channelId " +
				"and t1.videoPicId = t4.imageId " +
				"and videoStatus = 1 " +
				"order by t1.operateTime desc limit 8";

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

	/**
	 * 获取最新上传的前八个视频
	 * @param channelId 视频类别id
	 * @return ArrayList<BaseVideoBean>
	 */
	public static ArrayList<VideoInfoBean> getNewUpdateVideo(int channelId) throws SQLException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {

		ArrayList<VideoInfoBean> arrayList = new ArrayList<>();

		Connection conn = C3P0Util.getConnection();

		String sql = "select t1.*, t2.userName, t2.userDesc, t3.channelName, t4.imagePath " +
				"from t_video t1, t_user t2, t_channel t3, t_image t4 " +
				"where t1.channelId = ? " +
				"and t1.userId = t2.userId " +
				"and t1.channelId = t3.channelId " +
				"and videoStatus = 1 " +
				"and t1.videoPicId = t4.imageId " +
				"order by t1.videoUpTime desc limit 8";

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

	/**
	 * 获取点击量最高的前七个视频
	 * @param channelId 视频类别id
	 * @return ArrayList<BaseVideoBean>
	 */
	public static ArrayList<VideoInfoBean> getTopVideo(int channelId) throws SQLException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {

		ArrayList<VideoInfoBean> arrayList = new ArrayList<>();

		Connection conn = C3P0Util.getConnection();

		String sql = "select t1.*, t2.userName, t2.userDesc, t3.channelName, t4.imagePath " +
				"from t_video t1, t_user t2, t_channel t3, t_image t4 " +
				"where t1.channelId = ? " +
				"and t1.userId = t2.userId " +
				"and t1.channelId = t3.channelId " +
				"and videoStatus = 1 " +
				"and t1.videoPicId = t4.imageId " +
				"order by t1.videoPointNum desc limit 10";

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

	/**
	 * 随机获取六个视频
	 * @return ArrayList<BaseVideoBean>
	 */
	public static ArrayList<VideoInfoBean> getRandomVideo() throws SQLException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {

		ArrayList<VideoInfoBean> arrayList = new ArrayList<>();

		Connection conn = C3P0Util.getConnection();

		String sql = "select t1.*, t2.userName, t2.userDesc, t3.channelName, t4.imagePath " +
				"from t_video t1, t_user t2, t_channel t3, t_image t4 " +
				"JOIN (SELECT ROUND(RAND() * (SELECT MAX(videoId) FROM t_video)) AS id) AS t2 WHERE t1.videoId >= t2.id " +
				"and t1.userId = t2.userId " +
				"and t1.channelId = t3.channelId " +
				"and videoStatus = 1 " +
				"and t1.videoPicId = t4.imageId " +
				"order by videoId limit 6";

		PreparedStatement statement = conn.prepareStatement(sql);

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
