package me.shigure.servlet;

import com.alibaba.fastjson.JSONObject;
import me.shigure.beans.HistoryBean;
import me.shigure.service.HistroyService;
import me.shigure.utils.ServletUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author zhengsiyuan
 * @date 2019-04-03 10:43
 */

@WebServlet(name = "HistoryServlet", urlPatterns = "/history")
public class HistoryServlet extends BaseServlet{

	public void getHistoryList(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("请求----getHistoryList");

		File f = new File(this.getClass().getResource("/").getPath());

		int userId = Integer.parseInt(request.getParameter("userId"));

		JSONObject jsonObject = new JSONObject();
		ArrayList<HistoryBean> arrayList = null;
		try {
			request.setCharacterEncoding("UTF-8");
			arrayList = HistroyService.getHistoryList(userId);

		} catch (UnsupportedEncodingException | SQLException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
		}

		jsonObject.put("historyList",JSONObject.toJSONString(arrayList));
		jsonObject.put("msg","ok");
		ServletUtil.writeResponse(response,jsonObject.toJSONString());

	}

	public void deleteHistory(HttpServletRequest request, HttpServletResponse response){
		System.out.println("请求----deleteHistory");

		int userId = Integer.parseInt(request.getParameter("userId"));

		JSONObject jsonObject = new JSONObject();
		int result = 0;

		try {
			request.setCharacterEncoding("UTF-8");
			result = HistroyService.deleteHistory(userId);
		} catch (SQLException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		if (result != 0){
			jsonObject.put("msg","ok");
			ServletUtil.writeResponse(response, jsonObject.toJSONString());
		}else {
			jsonObject.put("msg","error");
			ServletUtil.writeResponse(response, jsonObject.toJSONString());
		}
	}

	public void addHistory(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("请求----addHistory");

		int userId = Integer.parseInt(request.getParameter("userId"));
		int videoId = Integer.parseInt(request.getParameter("videoId"));
		String historyTime = request.getParameter("historyTime");

		JSONObject jsonObject = new JSONObject();
		int result = 0;
		int result1 = 0;

		try {
			request.setCharacterEncoding("UTF-8");
			result = HistroyService.updateHistory(videoId, userId, historyTime);
		} catch (SQLException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		if (result != 0) {
			jsonObject.put("msg", "ok");
			ServletUtil.writeResponse(response, jsonObject.toJSONString());
		} else {
			try {
				result1 = HistroyService.addHistory(videoId, userId, historyTime);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (result1 != 0) {
				jsonObject.put("msg", "ok");
				ServletUtil.writeResponse(response, jsonObject.toJSONString());
			} else {
				jsonObject.put("msg", "error");
				ServletUtil.writeResponse(response, jsonObject.toJSONString());
			}
		}
	}
}
