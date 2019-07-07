package me.shigure.servlet;

import com.alibaba.fastjson.JSONObject;
import me.shigure.beans.UserInfoBean;
import me.shigure.service.UserService;
import me.shigure.utils.CodeUtil;
import me.shigure.utils.ServletUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

/**
 * @author zhengsiyuan
 * @date 2019-04-14 16:52
 */
@WebServlet(name = "UserServlet", urlPatterns = "/userData")
public class UserServlet extends BaseServlet{

	public void editUser(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("请求----editUser");

		int userId = Integer.parseInt(request.getParameter("userId"));
		int sex = Integer.parseInt(request.getParameter("sex"));
		String userNama = request.getParameter("userName");
		String userDesc = request.getParameter("userDecs");

		JSONObject jsonObject = new JSONObject();
		int result = 0;

		try {
			request.setCharacterEncoding("UTF-8");
			result = UserService.editUser(new UserInfoBean(userId, userNama, userDesc, sex));
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

	public void getCode(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("请求----getCode");

		String type = request.getParameter("type");
		String target = request.getParameter("target");

		JSONObject jsonObject = new JSONObject();

		int code = CodeUtil.createCode();

		boolean result = false;

		if ("mail".equals(type)){
			result = CodeUtil.sendCodeByMail(code,target);
		}else if ("phone".equals(type)){
			result = CodeUtil.sendCodeByPhone(code,target);
		}
		request.getSession().setAttribute("CODE", code);

		System.out.println(code);

		if (result){
			jsonObject.put("msg", "ok");
			ServletUtil.writeResponse(response, jsonObject.toJSONString());
		}else {
			jsonObject.put("msg", "error");
			ServletUtil.writeResponse(response, jsonObject.toJSONString());
		}
	}

	public void checkCode(HttpServletRequest request, HttpServletResponse response){
		System.out.println("请求----checkCode");

		String code = request.getParameter("code");
		JSONObject jsonObject = new JSONObject();

		HttpSession session = request.getSession();
		Object object = session.getAttribute("CODE");

		String originalCode = object.toString();

		if (originalCode.equals(code)){
			jsonObject.put("msg", "ok");
			ServletUtil.writeResponse(response, jsonObject.toJSONString());
		}else {
			jsonObject.put("msg", "error");
			ServletUtil.writeResponse(response, jsonObject.toJSONString());
		}

	}

	public void changePassword(HttpServletRequest request, HttpServletResponse response){
		System.out.println("请求----changePassword");

		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		int userId = Integer.parseInt(request.getParameter("userId"));

		JSONObject jsonObject = new JSONObject();
		int result = 0;

		if (password.equals(repassword)){
			try {
				request.setCharacterEncoding("UTF-8");
				result = UserService.changePassword(password.toUpperCase(),userId);
			} catch (SQLException | UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}else {
			jsonObject.put("msg", "error");
			ServletUtil.writeResponse(response, jsonObject.toJSONString());
		}

		if (result != 0) {
			jsonObject.put("msg", "ok");
			ServletUtil.writeResponse(response, jsonObject.toJSONString());
		} else {
			jsonObject.put("msg", "error");
			ServletUtil.writeResponse(response, jsonObject.toJSONString());
		}
	}

	public void changeMail(HttpServletRequest request, HttpServletResponse response){

		System.out.println("请求----changeMail");

		String code = request.getParameter("code");
		String mail = request.getParameter("mail");
		int userId = Integer.parseInt(request.getParameter("userId"));

		JSONObject jsonObject = new JSONObject();

		HttpSession session = request.getSession();
		Object object = session.getAttribute("CODE");

		String originalCode = object.toString();

		session.setAttribute("CODE",null);
		int result = 0;

		if (originalCode.equals(code)){
			try {
				request.setCharacterEncoding("UTF-8");
				result = UserService.changeMail(mail,userId);
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
		}else {
			jsonObject.put("msg", "error");
			ServletUtil.writeResponse(response, jsonObject.toJSONString());
		}
	}

	public void changePhone(HttpServletRequest request, HttpServletResponse response){

		System.out.println("请求----changePhone");

		String code = request.getParameter("code");
		String phone = request.getParameter("phone");
		int userId = Integer.parseInt(request.getParameter("userId"));

		JSONObject jsonObject = new JSONObject();

		HttpSession session = request.getSession();
		Object object = session.getAttribute("CODE");

		String originalCode = object.toString();

		session.setAttribute("CODE",null);
		int result = 0;

		if (originalCode.equals(code)){
			try {
				request.setCharacterEncoding("UTF-8");
				result = UserService.changePhone(phone,userId);
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
		}else {
			jsonObject.put("msg", "error");
			ServletUtil.writeResponse(response, jsonObject.toJSONString());
		}
	}
}
