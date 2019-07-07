package me.shigure.mapping.channel;

import me.shigure.utils.MappingUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @author zhengsiyuan
 * @date 2019-04-22 16:49
 */
@WebServlet(name = "ChannelMapping", urlPatterns = "/channel")
public class ChannelMapping extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MappingUtil.mapping(request,response,"channel/channel.html");
	}
}
