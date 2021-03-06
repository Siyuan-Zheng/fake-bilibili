package me.shigure.mapping.channelmanage;

import me.shigure.utils.MappingUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhengsiyuan
 * @date 2019-04-13 17:08
 */
@WebServlet(name = "ChannelManageMapping", urlPatterns = "/channelManage")
public class ChannelManageMapping extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MappingUtil.mapping(request,response,"channelManage/channelManage.html");
	}
}
