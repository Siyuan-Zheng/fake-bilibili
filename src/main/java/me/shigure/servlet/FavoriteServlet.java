package me.shigure.servlet;

import com.alibaba.fastjson.JSONObject;
import me.shigure.beans.FavoriteBean;
import me.shigure.service.FavoriteService;
import me.shigure.service.VideoService;
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
 * @date 2019-04-05 15:11
 */

@WebServlet(name = "FavoriteServlet", urlPatterns = "/favorite")
public class FavoriteServlet extends BaseServlet{

	public void getFavoriteList(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("请求----getFavoriteList");

		int userId = Integer.parseInt(request.getParameter("userId"));

		JSONObject jsonObject = new JSONObject();
		ArrayList<FavoriteBean> arrayList = null;
		try {
			request.setCharacterEncoding("UTF-8");
			arrayList = FavoriteService.getFavoriteList(userId);

		} catch (UnsupportedEncodingException | SQLException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

		jsonObject.put("favoriteList", JSONObject.toJSONString(arrayList));
		jsonObject.put("msg", "ok");
		ServletUtil.writeResponse(response, jsonObject.toJSONString());
	}

	public void addFavorite(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("请求----addFavorite");

		int userId = Integer.parseInt(request.getParameter("userId"));
		int videoId = Integer.parseInt(request.getParameter("videoId"));
		String favoriteTime = request.getParameter("favoriteTime");
		String symbol = request.getParameter("symbol");

		JSONObject jsonObject = new JSONObject();
		int result = 0;
		int result1 = 0;

		try {
			request.setCharacterEncoding("UTF-8");
			result = FavoriteService.addFavorite(videoId, userId, favoriteTime);
			result1 = VideoService.setFavoriteNum(videoId, symbol);
		} catch (SQLException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		if (result != 0 && result1 != 0) {
			jsonObject.put("msg", "ok");
			ServletUtil.writeResponse(response, jsonObject.toJSONString());
		} else {
			jsonObject.put("msg", "error");
			ServletUtil.writeResponse(response, jsonObject.toJSONString());
		}

	}

	public void deleteFavorite(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("请求----deleteFavorite");

		int userId = Integer.parseInt(request.getParameter("userId"));
		int videoId = Integer.parseInt(request.getParameter("videoId"));
		String symbol = request.getParameter("symbol");

		JSONObject jsonObject = new JSONObject();
		int result = 0;
		int result1 = 0;

		try {
			request.setCharacterEncoding("UTF-8");
			result = FavoriteService.deleteFavorite(videoId, userId);
			result1 = VideoService.setFavoriteNum(videoId, symbol);
		} catch (SQLException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		if (result != 0 && result1 != 0) {
			jsonObject.put("msg", "ok");
			ServletUtil.writeResponse(response, jsonObject.toJSONString());
		} else {
			jsonObject.put("msg", "error");
			ServletUtil.writeResponse(response, jsonObject.toJSONString());
		}

	}


}
