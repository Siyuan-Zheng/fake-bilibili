package me.shigure.service;

import me.shigure.beans.ChannelBean;
import me.shigure.beans.VideoInfoBean;
import me.shigure.dao.IndexDao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author zhengsiyuan
 * @date 2019-03-04 17:34
 */
public class IndexService {

	public static ArrayList<VideoInfoBean> getNewVideo(int channelId) throws InvocationTargetException, SQLException, InstantiationException, NoSuchMethodException, IllegalAccessException {
		return IndexDao.getNewVideo(channelId);
	}

	public static ArrayList<VideoInfoBean> getNewUpdateVideo(int channelId) throws InvocationTargetException, SQLException, InstantiationException, NoSuchMethodException, IllegalAccessException {
		return IndexDao.getNewUpdateVideo(channelId);
	}

	public static ArrayList<VideoInfoBean> getTopVideo(int channelId) throws InvocationTargetException, SQLException, InstantiationException, NoSuchMethodException, IllegalAccessException {
		return IndexDao.getTopVideo(channelId);
	}

	public static ArrayList<VideoInfoBean> getRandomVideo() throws SQLException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
		return IndexDao.getRandomVideo();
	}

}
