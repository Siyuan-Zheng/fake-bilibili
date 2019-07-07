package me.shigure.servlet;

import com.alibaba.fastjson.JSONObject;
import me.shigure.service.BannerManageService;
import me.shigure.service.ReportManageService;
import me.shigure.utils.ServletUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhengsiyuan
 * @date 2019-05-17 16:51
 */
@WebServlet(name = "ReportManageServlet", urlPatterns = "/reportManageData")
public class ReportManageServlet extends BaseServlet {

	public void getVideoReportList(HttpServletRequest request, HttpServletResponse response){
		System.out.println("请求----getVideoReportList");
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		JSONObject jsonObject = ReportManageService.getVideoReportList(page,limit);
		ServletUtil.writeResponse(response, jsonObject.toJSONString());
	}

	public void getCommentReportList(HttpServletRequest request, HttpServletResponse response){
		System.out.println("请求----getCommentReportList");
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		JSONObject jsonObject = ReportManageService.getCommentReportList(page,limit);
		ServletUtil.writeResponse(response, jsonObject.toJSONString());
	}
}
