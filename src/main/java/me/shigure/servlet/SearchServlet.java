package me.shigure.servlet;

import com.alibaba.fastjson.JSONObject;
import me.shigure.service.SearchService;
import me.shigure.utils.ServletUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhengsiyuan
 * @date 2019-05-15 15:55
 */
@WebServlet(name = "SearchServlet", urlPatterns = "/searchData")
public class SearchServlet extends BaseServlet{

	public void searchNewVideo(HttpServletRequest request, HttpServletResponse response){
		System.out.println("请求----searchNewVideo");

		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		String videoName = request.getParameter("videoName");

		JSONObject jsonObject = SearchService.searchNewVideo(videoName,page,limit);

		ServletUtil.writeResponse(response, jsonObject.toJSONString());
	}

	public void searchTopVideo(HttpServletRequest request, HttpServletResponse response){
		System.out.println("请求----searchTopVideo");

		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		String videoName = request.getParameter("videoName");

		JSONObject jsonObject = SearchService.searchTopVideo(videoName,page,limit);

		ServletUtil.writeResponse(response, jsonObject.toJSONString());
	}

}
