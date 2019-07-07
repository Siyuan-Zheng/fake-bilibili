package me.shigure.service;

import com.alibaba.fastjson.JSONObject;
import me.shigure.beans.ChannelBean;
import me.shigure.dao.BannerManageDao;
import me.shigure.dao.ChannelManageDao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author zhengsiyuan
 * @date 2019-04-12 15:47
 */
public class ChannelManageService {

	public static ArrayList<ChannelBean> getChannelList(int start, int end) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
		return ChannelManageDao.getChannelList(start, end);
	}

	public static int getChannelNum() throws SQLException{
		return ChannelManageDao.getChannelNum();
	}

	public static ChannelBean getChannelInfoById(int channelId) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
		return ChannelManageDao.getChannelInfoById(channelId);
	}

	public static int editChannelWithImage(ChannelBean channelBean) throws SQLException {
		return ChannelManageDao.editChannelWithImage(channelBean);
	}

	public static int editChannelNoneImage(ChannelBean channelBean) throws SQLException{
		return ChannelManageDao.editChannelNoneImage(channelBean);
	}

	public static int deleteChannel(int channelId) throws SQLException {
		return ChannelManageDao.deleteChannel(channelId);
	}

	public static int addChannel(ChannelBean channelBean) throws SQLException {
		return ChannelManageDao.addChannel(channelBean);
	}

	public static JSONObject isHaveVideo(int channelId){
		JSONObject jsonObject = new JSONObject();

		try {
			jsonObject.put("videoNum", ChannelManageDao.isHaveVideo(channelId));
			jsonObject.put("msg", "ok");
		} catch (SQLException e) {
			e.printStackTrace();
			jsonObject.put("msg", "error");
		}

		return jsonObject;
	}

}
