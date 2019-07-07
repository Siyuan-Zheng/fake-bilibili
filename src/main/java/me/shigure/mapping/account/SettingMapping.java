package me.shigure.mapping.account;

import me.shigure.utils.MappingUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhengsiyuan
 * @date 2019-04-13 16:52
 */
@WebServlet(name = "SettingMapping", urlPatterns = "/setting")
public class SettingMapping extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MappingUtil.mapping(request,response,"account/setting.html");
	}
}
