package me.shigure.servlet;

import com.alibaba.fastjson.JSONObject;
import me.shigure.beans.FollowBean;
import me.shigure.service.FollowService;
import me.shigure.utils.ServletUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * @author zhengsiyuan
 * @date 2019-04-02 16:41
 */

@WebServlet(name = "FollowServlet", urlPatterns = "/follow")
public class FollowServlet extends BaseServlet{

	public void getFollow(HttpServletRequest request, HttpServletResponse response){
		System.out.println("请求----getVideoInfo");

		int userId = Integer.parseInt(request.getParameter("userId"));

		JSONObject jsonObject = new JSONObject();
		FollowBean followBean = null;
		try {
			request.setCharacterEncoding("UTF-8");
			followBean = FollowService.getFollowed(userId);

		} catch (UnsupportedEncodingException | InvocationTargetException | SQLException | InstantiationException | NoSuchMethodException | IllegalAccessException e) {
			e.printStackTrace();
		}

		jsonObject.put("followInfo",JSONObject.toJSONString(followBean));
		jsonObject.put("msg","ok");

		ServletUtil.writeResponse(response,jsonObject.toJSONString());
	}

	public void setFollowed(HttpServletRequest request, HttpServletResponse response){
		System.out.println("请求----setFollowed");


		int userId = Integer.parseInt(request.getParameter("userId"));
		String followedStr = request.getParameter("followedStr");
		JSONObject jsonObject = new JSONObject();
		int result = 0;

		try {
			request.setCharacterEncoding("UTF-8");
			result = FollowService.setFollowed(userId,followedStr);
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

	public void setFollower(HttpServletRequest request, HttpServletResponse response){
		System.out.println("请求----setFollower");

		int userId = Integer.parseInt(request.getParameter("userId"));
		String followerStr = request.getParameter("followerStr");

		JSONObject jsonObject = new JSONObject();
		int result = 0;

		try {
			request.setCharacterEncoding("UTF-8");
			result = FollowService.setFollower(userId,followerStr);
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
}
