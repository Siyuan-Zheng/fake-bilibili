package me.shigure.service;

import com.alibaba.fastjson.JSONObject;
import me.shigure.beans.BannerBean;
import me.shigure.dao.BannerManageDao;
import me.shigure.dao.SearchDao;
import me.shigure.utils.PageUtil;

import java.sql.SQLException;

/**
 * @author zhengsiyuan
 * @date 2019-05-16 16:21
 */
public class BannerManageService {

	public static JSONObject getBannerList(int page, int limit) {

		JSONObject jsonObject = new JSONObject();

		try {
			int bannerNum = BannerManageDao.getBannerNum();
			PageUtil pageObject = new PageUtil(page, bannerNum, limit);
			jsonObject.put("msg", "ok");
			jsonObject.put("code", 0);
			jsonObject.put("count", bannerNum);
			jsonObject.put("data", JSONObject.toJSONString(BannerManageDao.getBannerList( pageObject.getStartIndex(), pageObject.getPageSize())));
		} catch (SQLException e) {
			e.printStackTrace();
			jsonObject.put("msg", "error");
		}
		return jsonObject;
	}

	public static JSONObject getBannerInfo(int bannerId){

		JSONObject jsonObject = new JSONObject();

		try {
			jsonObject.put("bannerInfo",BannerManageDao.getBannerInfo(bannerId));
			jsonObject.put("msg", "ok");
		} catch (SQLException e) {
			e.printStackTrace();
			jsonObject.put("msg", "error");
		}

		return jsonObject;

	}

}
