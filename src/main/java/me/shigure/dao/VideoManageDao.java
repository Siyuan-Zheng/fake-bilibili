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
 * @date 2019-04-11 22:50
 */
public class VideoManageDao {

	public static ArrayList<VideoInfoBean> getVideoList(int start, int end) throws SQLException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {

		ArrayList<VideoInfoBean> arrayList = new ArrayList<>();

		Connection conn = C3P0Util.getConnection();

		String sql = "select t1.videoId, t1.channelId, videoPicId, videoPath, t1.userId, videoName, videoLikeNum, videoFavoriteNum, videoPointNum, videoDesc, operateTime, videoCommentNum, videoStatus, t2.userName, t2.userDesc, t3.channelName, date_format(from_unixtime(ROUND(t1.videoUpTime/1000)),'%Y-%m-%d %H:%s:%i') videoUpTime, case t1.videoStatus when 1 then '正常' when 2 then '已封禁' when 4 then '被举报' end as videoTag " +
		"from t_video t1, t_user t2, t_channel t3, t_image t4 " +
				"where t1.userId = t2.userId " +
				"and t1.channelId = t3.channelId " +
				"and t1.videoPicId = t4.imageId " +
				"and t1.videoStatus != 3 " +
				"order by t1.videoId " +
				"limit ?,?";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1,start);
		statement.setInt(2,end);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()){
			VideoInfoBean videoInfoBean;
			videoInfoBean = (VideoInfoBean) new BeanUtil().autoBean(VideoInfoBean.class,resultSet);
			arrayList.add(videoInfoBean);
		}

		C3P0Util.release(conn,statement,resultSet);

		return arrayList;
	}

	public static int getVideoNum() throws SQLException{

		Connection conn = C3P0Util.getConnection();

		String sql = "select count(videoId) videoNum from t_video";

		PreparedStatement statement = conn.prepareStatement(sql);

		ResultSet resultSet = statement.executeQuery();

		int videoNum = 0;

		while (resultSet.next()){
			videoNum = resultSet.getInt("videoNum");
		}

		C3P0Util.release(conn,statement,resultSet);

		return videoNum;

	}

	public static int setVideoStatus(int videoId, int videoStatus) throws SQLException {
		Connection conn = C3P0Util.getConnection();

		String sql = "update t_video set videoStatus = ? where videoId = ?";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setInt(1, videoStatus);
		statement.setInt(2, videoId);

		int result = statement.executeUpdate();

		C3P0Util.release(conn,statement);

		return result;
	}


	public static int getVideoNumByChannelId(int channelId) throws SQLException {
		Connection conn = C3P0Util.getConnection();

		String sql = "select count(videoId) videoNum from t_video where channelId = ?";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, channelId);

		ResultSet resultSet = statement.executeQuery();

		int videoNum = 0;

		while (resultSet.next()){
			videoNum = resultSet.getInt("videoNum");
		}

		C3P0Util.release(conn,statement,resultSet);

		return videoNum;
	}
}
