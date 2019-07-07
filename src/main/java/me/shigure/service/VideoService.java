package me.shigure.service;

import me.shigure.beans.FollowBean;
import me.shigure.beans.VideoInfoBean;
import me.shigure.dao.VideoDao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author zhengsiyuan
 * @date 2019-03-27 20:33
 */
public class VideoService {

	public static VideoInfoBean getVideoInfo(int videoId) throws InvocationTargetException, SQLException, InstantiationException, NoSuchMethodException, IllegalAccessException {
		return VideoDao.getVideoInfo(videoId);
	}

	public static ArrayList<VideoInfoBean> getRecommendVideo(int channelId) throws InvocationTargetException, SQLException, InstantiationException, NoSuchMethodException, IllegalAccessException {
		return VideoDao.getRecommendVideo(channelId);
	}

	public static int setFavoriteNum(int videoId, String symbol) throws SQLException{
		return VideoDao.setFavoriteNum(videoId,symbol);
	}

	public static int addLikeNum(int videoId) throws SQLException {
		return VideoDao.addLikeNum(videoId);
	}

	public static int addVideoPointNum(int videoId) throws SQLException{
		return VideoDao.addVideoPointNum(videoId);
	}

	public static int addVideo(VideoInfoBean videoInfoBean) throws SQLException {
		return VideoDao.addVideo(videoInfoBean);
	}

	public static ArrayList<VideoInfoBean> getVideoByUserId(int userId) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
		return VideoDao.getVideoByUserId(userId);
	}
}
