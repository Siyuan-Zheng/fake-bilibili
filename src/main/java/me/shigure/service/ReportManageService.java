package me.shigure.service;

import com.alibaba.fastjson.JSONObject;
import me.shigure.dao.BannerManageDao;
import me.shigure.dao.ReportManageDao;
import me.shigure.utils.PageUtil;

import java.sql.SQLException;

/**
 * @author zhengsiyuan
 * @date 2019-05-17 16:54
 */
public class ReportManageService {

	public static JSONObject getVideoReportList(int page, int limit) {

		JSONObject jsonObject = new JSONObject();

		try {
			int bannerNum = BannerManageDao.getBannerNum();
			PageUtil pageObject = new PageUtil(page, bannerNum, limit);
			jsonObject.put("msg", "ok");
			jsonObject.put("code", 0);
			jsonObject.put("count", bannerNum);
			jsonObject.put("data", JSONObject.toJSONString(ReportManageDao.getVideoReportList( pageObject.getStartIndex(), pageObject.getPageSize())));
		} catch (SQLException e) {
			e.printStackTrace();
			jsonObject.put("msg", "error");
		}
		return jsonObject;
	}

	public static JSONObject getCommentReportList(int page, int limit) {

		JSONObject jsonObject = new JSONObject();

		try {
			int bannerNum = BannerManageDao.getBannerNum();
			PageUtil pageObject = new PageUtil(page, bannerNum, limit);
			jsonObject.put("msg", "ok");
			jsonObject.put("code", 0);
			jsonObject.put("count", bannerNum);
			jsonObject.put("data", JSONObject.toJSONString(ReportManageDao.getCommentReportList( pageObject.getStartIndex(), pageObject.getPageSize())));
		} catch (SQLException e) {
			e.printStackTrace();
			jsonObject.put("msg", "error");
		}
		return jsonObject;
	}

}
