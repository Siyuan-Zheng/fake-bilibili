package me.shigure.servlet;

import com.alibaba.fastjson.JSONObject;
import me.shigure.beans.VideoInfoBean;
import me.shigure.service.ImageService;
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
 * @date 2019-03-27 20:33
 */
@WebServlet(name = "VideoServlet", urlPatterns = "/videoData")
public class VideoServlet extends BaseServlet{

	public void getVideoInfo(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("请求----getVideoInfo");

		int videoId = Integer.parseInt(request.getParameter("videoId"));

		JSONObject jsonObject = new JSONObject();
		VideoInfoBean videoInfo = null;
		try {
			request.setCharacterEncoding("UTF-8");
			videoInfo = VideoService.getVideoInfo(videoId);

		} catch (UnsupportedEncodingException | InvocationTargetException | SQLException | InstantiationException | NoSuchMethodException | IllegalAccessException e) {
			e.printStackTrace();
		}

		jsonObject.put("videoInfo",JSONObject.toJSONString(videoInfo));
		jsonObject.put("msg","ok");

		ServletUtil.writeResponse(response,jsonObject.toJSONString());

	}

	public void getRecommendVideo(HttpServletRequest request, HttpServletResponse response){
		System.out.println("请求----getRecommendVideo");

		int channelId = Integer.parseInt(request.getParameter("channelId"));

		JSONObject jsonObject = new JSONObject();
		ArrayList arrayList = null;
		try {
			request.setCharacterEncoding("UTF-8");
			arrayList = VideoService.getRecommendVideo(channelId);
		} catch (UnsupportedEncodingException | InvocationTargetException | SQLException | InstantiationException | NoSuchMethodException | IllegalAccessException e) {
			e.printStackTrace();
		}

		jsonObject.put("recommendVideo",JSONObject.toJSONString(arrayList));
		jsonObject.put("msg","ok");
		ServletUtil.writeResponse(response,jsonObject.toJSONString());
	}

	public void addVideoLikeNum(HttpServletRequest request, HttpServletResponse response){

		System.out.println("请求----addVideoLikeNum");

		int videoId = Integer.parseInt(request.getParameter("videoId"));

		JSONObject jsonObject = new JSONObject();
		int result = 0;

		try {
			request.setCharacterEncoding("UTF-8");
			result = VideoService.addLikeNum(videoId);
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

	public void addVideoPointNum(HttpServletRequest request, HttpServletResponse response){

		System.out.println("请求----addVideoLikeNum");

		int videoId = Integer.parseInt(request.getParameter("videoId"));

		JSONObject jsonObject = new JSONObject();
		int result = 0;

		try {
			request.setCharacterEncoding("UTF-8");
			result = VideoService.addVideoPointNum(videoId);
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

	public void addVideo(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("请求----addVideo");

		String videoPath = request.getParameter("videoPath");
		String videoName = request.getParameter("videoName");
		String videoUpTime = request.getParameter("videoUpTime");
		String videoDesc = request.getParameter("videoDesc");
		String videoTag = request.getParameter("videoTag");
		String operateTime = request.getParameter("operateTime");
		String imagePath = request.getParameter("imagePath");
		int userId = Integer.parseInt(request.getParameter("userId"));
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
				result2 = VideoService.addVideo(new VideoInfoBean(channelId, imageId, videoPath, userId, videoName, 0, 0, 0, videoUpTime, videoDesc, videoTag, operateTime, 0,1));
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

	public void getVideoByUserId(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("请求----getVideoByUserId");

		int userId = Integer.parseInt(request.getParameter("userId"));

		JSONObject jsonObject = new JSONObject();
		ArrayList<VideoInfoBean> arrayList = null;
		try {
			request.setCharacterEncoding("UTF-8");
			arrayList = VideoService.getVideoByUserId(userId);

		} catch (UnsupportedEncodingException | InvocationTargetException | SQLException | InstantiationException | NoSuchMethodException | IllegalAccessException e) {
			e.printStackTrace();
		}

		jsonObject.put("videoList",JSONObject.toJSONString(arrayList));
		jsonObject.put("msg","ok");

		ServletUtil.writeResponse(response,jsonObject.toJSONString());

	}

}
