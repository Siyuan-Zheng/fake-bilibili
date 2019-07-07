package me.shigure.mapping.login;

import me.shigure.utils.MappingUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhengsiyuan
 * @date 2019-05-18 08:28
 */
@WebServlet(name = "ForgetPasswordSecMapping", urlPatterns = "/forgetPasswordSec")
public class ForgetPasswordSecMapping extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MappingUtil.mapping(request, response, "login/forgetPasswordSec.html");
	}
}
