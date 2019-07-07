package me.shigure.servlet;

import com.alibaba.fastjson.JSONObject;
import me.shigure.beans.ChannelBean;
import me.shigure.beans.VideoInfoBean;
import me.shigure.service.ChannelService;
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
 * @date 2019-04-07 16:19
 */
@WebServlet(name = "ChannelServlet", urlPatterns = "/channelData")
public class ChannelServlet extends BaseServlet {

	public void getChannelList(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("请求----getChannelList");

		JSONObject jsonObject = new JSONObject();
		ArrayList<ChannelBean> arrayList = null;
		try {
			request.setCharacterEncoding("UTF-8");
			arrayList = ChannelService.getChannelList();

		} catch (UnsupportedEncodingException | SQLException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

		jsonObject.put("channelList", JSONObject.toJSONString(arrayList));
		jsonObject.put("msg", "ok");
		ServletUtil.writeResponse(response, jsonObject.toJSONString());
	}

	public void getPageNewVideoByChannelId(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("请求----getPageNewVideoByChannelId");

		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		int channelId = Integer.parseInt(request.getParameter("channelId"));

		JSONObject jsonObject = ChannelService.getPageNewVideoByChannelId(channelId,page,limit);

		ServletUtil.writeResponse(response, jsonObject.toJSONString());
	}

	public void getPageHotVideoByChannelId(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("请求----getPageNewVideoByChannelId");

		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		int channelId = Integer.parseInt(request.getParameter("channelId"));

		JSONObject jsonObject = ChannelService.getPageHotVideoByChannelId(channelId,page,limit);

		ServletUtil.writeResponse(response, jsonObject.toJSONString());
	}
}
