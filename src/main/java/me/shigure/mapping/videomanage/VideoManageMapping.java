package me.shigure.mapping.videomanage;

import me.shigure.utils.MappingUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhengsiyuan
 * @date 2019-04-14 14:24
 */
@WebServlet(name = "VideoManageMapping", urlPatterns = "/videoManage")
public class VideoManageMapping extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MappingUtil.mapping(request,response,"videoManage/videoManage.html");
	}
}
