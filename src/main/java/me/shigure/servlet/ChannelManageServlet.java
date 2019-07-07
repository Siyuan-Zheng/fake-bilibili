package me.shigure.servlet;

import com.alibaba.fastjson.JSONObject;
import me.shigure.beans.ChannelBean;
import me.shigure.service.BannerManageService;
import me.shigure.service.ChannelManageService;
import me.shigure.service.ImageService;
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
 * @date 2019-04-12 15:49
 */
@WebServlet(name = "ChannelManageServlet", urlPatterns = "/channelManageData")
public class ChannelManageServlet extends BaseServlet {

	public void getChannelList(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("请求----getChannelList");

		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));

		JSONObject jsonObject = new JSONObject();
		ArrayList<ChannelBean> arrayList = null;
		int channelNum = 0;
		try {
			request.setCharacterEncoding("UTF-8");
			channelNum = ChannelManageService.getChannelNum();
			PageUtil pageObject = new PageUtil(page, channelNum, limit);
			arrayList = ChannelManageService.getChannelList(pageObject.getStartIndex(), pageObject.getPageSize());

		} catch (UnsupportedEncodingException | SQLException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
		}

		jsonObject.put("code", 0);
		jsonObject.put("count", channelNum);
		jsonObject.put("data", JSONObject.toJSONString(arrayList));
		jsonObject.put("msg", "ok");
		ServletUtil.writeResponse(response, jsonObject.toJSONString());
	}

	public void getChannelInfo(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("请求----getChannelInfo");

		int channelId = Integer.parseInt(request.getParameter("channelId"));

		JSONObject jsonObject = new JSONObject();
		ChannelBean channelBean = null;
		try {
			request.setCharacterEncoding("UTF-8");
			channelBean = ChannelManageService.getChannelInfoById(channelId);

		} catch (UnsupportedEncodingException | SQLException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

		jsonObject.put("channelInfo", JSONObject.toJSONString(channelBean));
		jsonObject.put("msg", "ok");
		ServletUtil.writeResponse(response, jsonObject.toJSONString());
	}

	public void editChannelNoneImage(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("请求----editChannelNoneImage");

		String channelName = request.getParameter("channelName");
		String channelDesc = request.getParameter("channelDesc");
		int channelId = Integer.parseInt(request.getParameter("channelId"));

		JSONObject jsonObject = new JSONObject();
		int result = 0;

		try {
			result = ChannelManageService.editChannelNoneImage(new ChannelBean(channelId, channelName, channelDesc, 0));
		} catch (SQLException e) {
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

	public void editChannelWithImage(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("请求----editChannelWithImage");

		String channelName = request.getParameter("channelName");
		String channelDesc = request.getParameter("channelDesc");
		String imagePath = request.getParameter("imagePath");
		int channelId = Integer.parseInt(request.getParameter("channelId"));

		JSONObject jsonObject = new JSONObject();
		int result = 0;
		int result2 = 0;

		try {
			request.setCharacterEncoding("UTF-8");
			result = ImageService.addImage(imagePath);
		} catch (SQLException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		if (result != 0) {
			try {
				int imageId = ImageService.getImageId();
				result2 = ChannelManageService.editChannelWithImage(new ChannelBean(channelId, channelName, channelDesc, imageId));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (result2 != 0) {
				jsonObject.put("msg", "ok");
				ServletUtil.writeResponse(response, jsonObject.toJSONString());
			} else {
				jsonObject.put("msg", "error");
				ServletUtil.writeResponse(response, jsonObject.toJSONString());
			}
		}
	}

	public void deleteChannel(HttpServletRequest request, HttpServletResponse response){
		System.out.println("请求----deleteChannel");

		int channelId = Integer.parseInt(request.getParameter("channelId"));

		JSONObject jsonObject = new JSONObject();
		int result = 0;

		try {
			request.setCharacterEncoding("UTF-8");
			result = ChannelManageService.deleteChannel(channelId);
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

	public void addChannel(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("请求----addChannel");

		String channelName = request.getParameter("channelName");
		String channelDesc = request.getParameter("channelDesc");
		String imagePath = request.getParameter("imagePath");

		JSONObject jsonObject = new JSONObject();
		int result = 0;
		int result2 = 0;

		try {
			request.setCharacterEncoding("UTF-8");
			result = ImageService.addImage(imagePath);
		} catch (SQLException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		if (result != 0) {
			try {
				int imageId = ImageService.getImageId();
				result2 = ChannelManageService.addChannel(new ChannelBean(channelName, channelDesc, imageId));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (result2 != 0) {
				jsonObject.put("msg", "ok");
				ServletUtil.writeResponse(response, jsonObject.toJSONString());
			} else {
				jsonObject.put("msg", "error");
				ServletUtil.writeResponse(response, jsonObject.toJSONString());
			}
		}
	}

	public void isHaveVideo(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("请求----isHaveVideo");
		int channelId = Integer.parseInt(request.getParameter("channelId"));
		JSONObject jsonObject = ChannelManageService.isHaveVideo(channelId);
		ServletUtil.writeResponse(response, jsonObject.toJSONString());
	}
}
