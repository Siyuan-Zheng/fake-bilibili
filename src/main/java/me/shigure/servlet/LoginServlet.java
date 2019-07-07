package me.shigure.servlet;

import com.alibaba.fastjson.JSONObject;
import me.shigure.beans.UserInfoBean;
import me.shigure.service.LoginService;
import me.shigure.utils.GlobalConst;
import me.shigure.utils.Md5Util;
import me.shigure.utils.ServletUtil;
import me.shigure.utils.validate.BaseCaptcha;
import me.shigure.utils.validate.GifBaseCaptcha;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Objects;


/**
 * @author zhengsiyuan
 * @date 2019-02-15 16:55
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/loginData")
public class LoginServlet extends BaseServlet {

	/**
	 * 登陆
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	public void login(HttpServletRequest request, HttpServletResponse response) {

		JSONObject jsonObject = new JSONObject();

		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		System.out.println("请求----login");

		//获取用户用户信息
		String accountType = request.getParameter("accountType");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String code = request.getParameter("code");

		//获取保存在session中的验证码
		HttpSession session = request.getSession();
		Object object = session.getAttribute("L_V_CODE");
		//验证码错误
		if (object == null || !object.toString().toUpperCase().equals(code.toUpperCase())) {
			jsonObject.put("msg","invalid");
			ServletUtil.writeResponse(response, jsonObject.toJSONString());
		}
		//删除session中的验证码
		session.setAttribute("L_V_CODE", null);

		UserInfoBean userBean = new UserInfoBean(password, account, account);
		UserInfoBean currentUserBean = null;
		try {
			currentUserBean = LoginService.login(userBean, accountType);
		} catch (SQLException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
		}

		//当用户ID为空时，则不存在当前用户
		if (currentUserBean != null) {
			if (currentUserBean.getUserId() == null) {
				jsonObject.put("msg","blank");
				System.out.println("blank");
				ServletUtil.writeResponse(response, jsonObject.toJSONString());
				//验证密码是否一致
			} else if (Objects.equals(Md5Util.md5(currentUserBean.getPassword() + code.toUpperCase()), password)) {
				//判断用户是否被封禁
				if (currentUserBean.getUserStatus() != 0) {
					//将用户信息存入session
					request.getSession().setAttribute(GlobalConst.USER_SESSION_KEY, currentUserBean);
					jsonObject.put("user",JSONObject.toJSONString(currentUserBean));
					jsonObject.put("msg","ok");
					System.out.println("ok");
					ServletUtil.writeResponse(response, jsonObject.toJSONString());
					//若用户被封禁
				} else {
					jsonObject.put("msg","block");
					System.out.println("block");
					ServletUtil.writeResponse(response, jsonObject.toJSONString());
				}
				//密码错误
			} else {
				jsonObject.put("msg","error");
				System.out.println("error");
				ServletUtil.writeResponse(response, jsonObject.toJSONString());
			}
		}
	}

	/**
	 * 用户注册
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	public void register(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("请求----register");

		JSONObject jsonObject = new JSONObject();

		String accountType = request.getParameter("accountType");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String userName = request.getParameter("userName");

		UserInfoBean userInfoBean = new UserInfoBean(null,userName,password,account,account,null,0,1,1,1,1,"/static/upload/image/4a6b4dcb-d1e7-4f60-9b31-32b19f95e3a0.png",null,null,null,null);

		int result = 0;
		try {
			result = LoginService.register(userInfoBean, accountType);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (result != 0) {
			request.getSession().setAttribute(GlobalConst.USER_SESSION_KEY, userInfoBean);
			jsonObject = JSONObject.parseObject(JSONObject.toJSONString(userInfoBean));
			jsonObject.put("msg","ok");
			ServletUtil.writeResponse(response, jsonObject.toJSONString());
		} else {
			jsonObject.put("msg","error");
			ServletUtil.writeResponse(response, jsonObject.toJSONString());
		}
	}

	/**
	 * 判断用户名及账号是否重复
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	public boolean isDuplicate(HttpServletRequest request, HttpServletResponse response) {

		JSONObject jsonObject = new JSONObject();

		String account = request.getParameter("account");
		String accountType = request.getParameter("userAccountType");
		String userName = request.getParameter("userName");

		boolean isDuplicate = LoginService.isDuplicate(userName,account,accountType);

		if (isDuplicate){
			jsonObject.put("msg","duplicate");
			ServletUtil.writeResponse(response, jsonObject.toJSONString());
			return true;
		}else {
			jsonObject.put("msg","ok");
			ServletUtil.writeResponse(response, jsonObject.toJSONString());
			return false;
		}
	}

	/**
	 * 生成验证码
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	public void validateCode(HttpServletRequest request, HttpServletResponse response) {
		// 设置相应类型,告诉浏览器输出的内容为图片
		response.setContentType("image/jpeg");
		// 设置响应头信息，告诉浏览器不要缓存此内容
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		//设置HttpOnly属性,防止Xss攻击
		response.setHeader("Set-Cookie", "name=value; HttpOnly");
		response.setDateHeader("Expire", 0);
		BaseCaptcha baseCaptcha = new GifBaseCaptcha(110, 30, 5);
		ByteArrayOutputStream tmp = new ByteArrayOutputStream();
		baseCaptcha.out(tmp);
		request.getSession().setAttribute("L_V_CODE", baseCaptcha.text());
		response.setHeader("content-length", tmp.size() + "");
		try {
			response.getOutputStream().write(tmp.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void logout(HttpServletRequest request, HttpServletResponse response) {

		request.getSession().setAttribute(GlobalConst.USER_SESSION_KEY, null);


	}


}
