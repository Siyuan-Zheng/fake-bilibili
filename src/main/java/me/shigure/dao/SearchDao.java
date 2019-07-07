package me.shigure.dao;

import me.shigure.beans.VideoInfoBean;
import me.shigure.utils.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author zhengsiyuan
 * @date 2019-05-15 15:58
 */
public class SearchDao {

	public static ArrayList<VideoInfoBean> searchNewVideo(String videoName, int start, int end) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Util.getDataSource());

		String sql = "select t1.*, t2.userName, t3.channelName, t2.userDesc, t4.imagePath " +
				"from t_video t1, t_user t2, t_channel t3, t_image t4 " +
				"where t1.videoName like ? " +
				"and t1.userId = t2.userId " +
				"and t1.channelId = t3.channelId " +
				"and t1.videoPicId = t4.imageId " +
				"and videoStatus = 1 " +
				"order by t1.operateTime desc limit ?,?";

		return (ArrayList<VideoInfoBean>) runner.query(sql, new BeanListHandler<VideoInfoBean>(VideoInfoBean.class),videoName,start,end);
	}

	public static ArrayList<VideoInfoBean> searchTopVideo(String videoName, int start, int end) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Util.getDataSource());

		String sql = "select t1.*, t2.userName, t3.channelName, t2.userDesc, t4.imagePath " +
				"from t_video t1, t_user t2, t_channel t3, t_image t4 " +
				"where t1.videoName like ? " +
				"and t1.userId = t2.userId " +
				"and t1.channelId = t3.channelId " +
				"and t1.videoPicId = t4.imageId " +
				"and videoStatus = 1 " +
				"order by t1.operateTime desc limit ?,?";

		return (ArrayList<VideoInfoBean>) runner.query(sql, new BeanListHandler<VideoInfoBean>(VideoInfoBean.class),videoName,start,end);
	}

	public static int getVideoNumByVideoName(String videoName) throws SQLException {
		Connection conn = C3P0Util.getConnection();

		String sql = "select count(videoId) videoNum from t_video where videoName like ?";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, videoName);

		ResultSet resultSet = statement.executeQuery();

		int videoNum = 0;

		while (resultSet.next()){
			videoNum = resultSet.getInt("videoNum");
		}

		C3P0Util.release(conn,statement,resultSet);

		return videoNum;
	}
}
