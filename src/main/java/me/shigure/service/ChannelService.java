package me.shigure.service;

import com.alibaba.fastjson.JSONObject;
import me.shigure.beans.ChannelBean;
import me.shigure.beans.VideoInfoBean;
import me.shigure.dao.ChannelDao;
import me.shigure.utils.PageUtil;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author zhengsiyuan
 * @date 2019-04-07 16:19
 */
public class ChannelService {

	public static ArrayList<ChannelBean> getChannelList() throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
		return ChannelDao.getChannelList();
	}


	public static JSONObject getPageNewVideoByChannelId(int channelId, int page, int limit) {
		JSONObject jsonObject = new JSONObject();

		try {
			int videoNum = VideoManageService.getVideoNumByChannelId(channelId);
			PageUtil pageObject = new PageUtil(page, videoNum, limit);
			jsonObject.put("videoList", JSONObject.toJSONString(ChannelDao.getPageNewVideoByChannelId(channelId, pageObject.getStartIndex(), pageObject.getPageSize())));
			jsonObject.put("pageInfo",JSONObject.toJSONString(pageObject));
			jsonObject.put("msg", "ok");
		} catch (SQLException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
			jsonObject.put("msg", "error");
		}
		return jsonObject;
	}

	public static JSONObject getPageHotVideoByChannelId(int channelId, int page, int limit) {
		JSONObject jsonObject = new JSONObject();

		try {
			int videoNum = VideoManageService.getVideoNumByChannelId(channelId);
			PageUtil pageObject = new PageUtil(page, videoNum, limit);
			jsonObject.put("videoList", JSONObject.toJSONString(ChannelDao.getPageHotVideoByChannelId(channelId, pageObject.getStartIndex(), pageObject.getPageSize())));
			jsonObject.put("pageInfo",JSONObject.toJSONString(pageObject));
			jsonObject.put("msg", "ok");
		} catch (SQLException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
			jsonObject.put("msg", "error");
		}
		return jsonObject;
	}
}
