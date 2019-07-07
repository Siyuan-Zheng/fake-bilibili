package me.shigure.service;

import com.alibaba.fastjson.JSONObject;
import me.shigure.dao.SearchDao;
import me.shigure.utils.PageUtil;

import java.sql.SQLException;

/**
 * @author zhengsiyuan
 * @date 2019-05-15 15:57
 */
public class SearchService {

	public static JSONObject searchNewVideo(String videoName, int page, int limit){
		JSONObject jsonObject = new JSONObject();

		try {
			int videoNum = SearchDao.getVideoNumByVideoName(videoName);
			PageUtil pageObject = new PageUtil(page, videoNum, limit);
			jsonObject.put("videoList", JSONObject.toJSONString(SearchDao.searchNewVideo(videoName, pageObject.getStartIndex(), pageObject.getPageSize())));
			jsonObject.put("pageInfo",JSONObject.toJSONString(pageObject));
			jsonObject.put("msg", "ok");
		} catch (SQLException e) {
			e.printStackTrace();
			jsonObject.put("msg", "error");
		}
		return jsonObject;
	}

	public static JSONObject searchTopVideo(String videoName, int page, int limit){
		JSONObject jsonObject = new JSONObject();

		try {
			int videoNum = SearchDao.getVideoNumByVideoName(videoName);
			PageUtil pageObject = new PageUtil(page, videoNum, limit);
			jsonObject.put("videoList", JSONObject.toJSONString(SearchDao.searchTopVideo(videoName, pageObject.getStartIndex(), pageObject.getPageSize())));
			jsonObject.put("pageInfo",JSONObject.toJSONString(pageObject));
			jsonObject.put("msg", "ok");
		} catch (SQLException e) {
			e.printStackTrace();
			jsonObject.put("msg", "error");
		}
		return jsonObject;
	}

}
