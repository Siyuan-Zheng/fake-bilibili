package me.shigure.mapping.reportmanage;

import me.shigure.utils.MappingUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhengsiyuan
 * @date 2019-05-17 16:32
 */
@WebServlet(name = "ReportManageMapping", urlPatterns = "/reportManage")
public class ReportManageMapping extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MappingUtil.mapping(request, response, "reportManage/reportManage.html");
	}
}
