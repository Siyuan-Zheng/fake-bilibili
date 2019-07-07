package me.shigure.servlet;

import com.alibaba.fastjson.JSONObject;
import me.shigure.beans.UserInfoBean;
import me.shigure.service.UserManageService;
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
 * @date 2019-04-11 14:17
 */

@WebServlet(name = "UserManageServlet", urlPatterns = "/userManageData")
public class UserManageServlet extends BaseServlet {

	public void getUserList(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("请求----getUserInfo");

		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));

		JSONObject jsonObject = new JSONObject();
		ArrayList<UserInfoBean> arrayList = null;
		int userNum = 0;
		try {
			request.setCharacterEncoding("UTF-8");
			userNum = UserManageService.getUserNum();
			PageUtil pageObject = new PageUtil(page, userNum, limit);
			arrayList = UserManageService.getUserList(pageObject.getStartIndex(), pageObject.getPageSize());

		} catch (UnsupportedEncodingException | SQLException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
		}

		jsonObject.put("code", 0);
		jsonObject.put("count", userNum);
		jsonObject.put("data", JSONObject.toJSONString(arrayList));
		jsonObject.put("msg", "ok");
		ServletUtil.writeResponse(response, jsonObject.toJSONString());
	}

	public void addManager(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("请求----addManager");

		String userName = request.getParameter("userName");
		String mail = request.getParameter("mail");
		String phoneNum = request.getParameter("phoneNum");
		String password = request.getParameter("password");

		int userRightId = Integer.parseInt(request.getParameter("userRightId"));
		int userStatus = Integer.parseInt(request.getParameter("userStatus"));
		int userLevelId = Integer.parseInt(request.getParameter("userLevelId"));

		JSONObject jsonObject = new JSONObject();
		int result = 0;

		UserInfoBean userInfoBean = new UserInfoBean(userName, password, mail, phoneNum, userRightId, userStatus, userLevelId);

		try {
			request.setCharacterEncoding("UTF-8");
			result = UserManageService.addManager(userInfoBean);
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

	public void setUserStatus(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("请求----setUserStatus");

		int userId = Integer.parseInt(request.getParameter("userId"));
		int userStatus = Integer.parseInt(request.getParameter("userStatus"));

		JSONObject jsonObject = new JSONObject();
		int result = 0;

		try {
			request.setCharacterEncoding("UTF-8");
			result = UserManageService.setUserStatus(userId,userStatus);
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

	public void deleteUser(HttpServletRequest request, HttpServletResponse response){
		System.out.println("请求----deleteUser");

		int userId = Integer.parseInt(request.getParameter("userId"));

		JSONObject jsonObject = new JSONObject();
		int result = 0;

		try {
			request.setCharacterEncoding("UTF-8");
			result = UserManageService.deleteUser(userId);
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
