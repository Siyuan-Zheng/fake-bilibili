package me.shigure.servlet;

import com.alibaba.fastjson.JSONObject;
import me.shigure.service.IndexService;
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
 * @date 2019-03-04 17:31
 */
@WebServlet(name = "IndexServlet", urlPatterns = "/indexData")
public class IndexServlet extends BaseServlet {

	/**
	 * 获取最新动态的视频
	 */
	public void getNewVideo(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("请求----getNewVideo");

		int channelId = Integer.parseInt(request.getParameter("channelId"));
		JSONObject jsonObject = new JSONObject();
		ArrayList arrayList = null;

		try {
			request.setCharacterEncoding("UTF-8");
			arrayList = IndexService.getNewVideo(channelId);
		} catch (UnsupportedEncodingException | InvocationTargetException | SQLException | InstantiationException | NoSuchMethodException | IllegalAccessException e) {
			e.printStackTrace();
		}

		jsonObject.put("newVideo",JSONObject.toJSONString(arrayList));
		jsonObject.put("msg","ok");

		ServletUtil.writeResponse(response,jsonObject.toJSONString());
	}

	/**
	 * 获取排名靠前的视频
	 */
	public void getTopVideo(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("请求----getTopVideo");

		int channelId = Integer.parseInt(request.getParameter("channelId"));
		JSONObject jsonObject = new JSONObject();

		ArrayList arrayList = null;

		try {
			request.setCharacterEncoding("UTF-8");
			arrayList = IndexService.getTopVideo(channelId);
		} catch (UnsupportedEncodingException | InstantiationException | InvocationTargetException | NoSuchMethodException | SQLException | IllegalAccessException e) {
			e.printStackTrace();
		}


		jsonObject.put("topVideo",JSONObject.toJSONString(arrayList));
		jsonObject.put("msg","ok");
		ServletUtil.writeResponse(response,jsonObject.toJSONString());
	}

	/**
	 * 获取随机视频
	 */
	public void getRandomVideo(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("请求----getRandomVideo");

		JSONObject jsonObject = new JSONObject();
		ArrayList arrayList = null;
		try {
			request.setCharacterEncoding("UTF-8");
			arrayList = IndexService.getRandomVideo();

		} catch (UnsupportedEncodingException | SQLException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}

		jsonObject.put("randomVideo",JSONObject.toJSONString(arrayList));
		jsonObject.put("msg","ok");
		ServletUtil.writeResponse(response,jsonObject.toJSONString());
	}
}
