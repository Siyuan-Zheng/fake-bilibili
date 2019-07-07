package me.shigure.service;

import me.shigure.beans.VideoInfoBean;
import me.shigure.dao.VideoManageDao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author zhengsiyuan
 * @date 2019-04-11 23:27
 */
public class VideoManageService {

	public static ArrayList<VideoInfoBean> getVideoList(int start, int end) throws SQLException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
		return VideoManageDao.getVideoList(start, end);
	}

	public static int getVideoNum() throws SQLException{
		return VideoManageDao.getVideoNum();
	}

	public static int getVideoNumByChannelId(int channelId) throws SQLException{
		return VideoManageDao.getVideoNumByChannelId(channelId);
	}

	public static int setVideoStatus(int videoId, int videoStatus) throws SQLException {
		return VideoManageDao.setVideoStatus(videoId, videoStatus);
	}

}
