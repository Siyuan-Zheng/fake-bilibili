package me.shigure.dao;

import me.shigure.beans.ChannelBean;
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
 * @date 2019-04-07 16:15
 */
public class ChannelDao {

	public static ArrayList<ChannelBean> getChannelList() throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
		ArrayList<ChannelBean> arrayList = new ArrayList<>();

		Connection conn = C3P0Util.getConnection();

		String sql = "select t1.*, t2.imagePath " +
				"from t_channel t1, t_image t2 " +
				"where t1.channelPicId = t2.imageId " +
				"and t1.channelStatus = 1";

		PreparedStatement statement = conn.prepareStatement(sql);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()){
			ChannelBean channelBean;
			channelBean = (ChannelBean) new BeanUtil().autoBean(ChannelBean.class,resultSet);
			arrayList.add(channelBean);
		}

		C3P0Util.release(conn,statement,resultSet);

		return arrayList;
	}

	public static ArrayList<VideoInfoBean> getPageNewVideoByChannelId(int channelId,int start, int end) throws SQLException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {

		ArrayList<VideoInfoBean> arrayList = new ArrayList<>();

		Connection conn = C3P0Util.getConnection();

		String sql = "select t1.*, t2.userName, t3.channelName, t2.userDesc, t4.imagePath " +
				"from t_video t1, t_user t2, t_channel t3, t_image t4 " +
				"where t1.channelId = ? " +
				"and t1.userId = t2.userId " +
				"and t1.channelId = t3.channelId " +
				"and t1.videoPicId = t4.imageId " +
				"and videoStatus = 1 " +
				"order by t1.operateTime desc limit ?,?";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setInt(1,channelId);
		statement.setInt(2,start);
		statement.setInt(3,end);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()){
			VideoInfoBean videoInfoBean;
			videoInfoBean = (VideoInfoBean) new BeanUtil().autoBean(VideoInfoBean.class,resultSet);
			arrayList.add(videoInfoBean);
		}

		C3P0Util.release(conn,statement,resultSet);

		return arrayList;

	}

	public static ArrayList<VideoInfoBean> getPageHotVideoByChannelId(int channelId,int start, int end) throws SQLException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {

		ArrayList<VideoInfoBean> arrayList = new ArrayList<>();

		Connection conn = C3P0Util.getConnection();

		String sql = "select t1.*, t2.userName, t3.channelName, t2.userDesc, t4.imagePath " +
				"from t_video t1, t_user t2, t_channel t3, t_image t4 " +
				"where t1.channelId = ? " +
				"and t1.userId = t2.userId " +
				"and t1.channelId = t3.channelId " +
				"and t1.videoPicId = t4.imageId " +
				"and videoStatus = 1 " +
				"order by t1.videoPointNum desc limit ?,?";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setInt(1,channelId);
		statement.setInt(2,start);
		statement.setInt(3,end);

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
