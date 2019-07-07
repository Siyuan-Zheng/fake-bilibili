package me.shigure.servlet;

import com.alibaba.fastjson.JSONObject;
import me.shigure.beans.VideoInfoBean;
import me.shigure.service.VideoManageService;
import me.shigure.utils.PageUtil;
import me.shigure.utils.ServletUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author zhengsiyuan
 * @date 2019-04-11 23:28
 */
@WebServlet(name = "VideoManageServlet", urlPatterns = "/videoManageData")
public class VideoManageServlet extends BaseServlet{

	public void getVideoList(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("请求----getVideoList");

		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));

		JSONObject jsonObject = new JSONObject();
		ArrayList<VideoInfoBean> arrayList = null;
		int videoNum = 0;
		try {
			request.setCharacterEncoding("UTF-8");
			videoNum = VideoManageService.getVideoNum();
			PageUtil pageObject = new PageUtil(page, videoNum, limit);
			arrayList = VideoManageService.getVideoList(pageObject.getStartIndex(), pageObject.getPageSize());

		} catch (UnsupportedEncodingException | SQLException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
		}

		jsonObject.put("code", 0);
		jsonObject.put("count", videoNum);
		jsonObject.put("data", JSONObject.toJSONString(arrayList));
		jsonObject.put("msg", "ok");
		ServletUtil.writeResponse(response, jsonObject.toJSONString());
	}

	public void setVideoStatus(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("请求----setVideoStatus");

		int videoId = Integer.parseInt(request.getParameter("videoId"));
		int videoStatus = Integer.parseInt(request.getParameter("videoStatus"));

		JSONObject jsonObject = new JSONObject();
		int result = 0;

		try {
			request.setCharacterEncoding("UTF-8");
			result = VideoManageService.setVideoStatus(videoId,videoStatus);
		} catch (SQLException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		if (result != 0) {
			jsonObject.put("msg", "ok");
			ServletUtil.writeResponse(response, jsonObject.toJSONString());
		} else {
			jsonObject.put("msg", "error");
			ServletUtil.writeResponse(response, jsonObject.toJSONString());
		}

	}


}
