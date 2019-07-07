package me.shigure.mapping.user;

import me.shigure.utils.MappingUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhengsiyuan
 * @date 2019-04-14 01:16
 */
@WebServlet(name = "PersonalCenterMapping", urlPatterns = "/personalCenter")
public class PersonalCenterMapping extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MappingUtil.mapping(request,response,"user/personalCenter.html");
	}
}
