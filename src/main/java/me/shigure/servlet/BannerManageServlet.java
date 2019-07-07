package me.shigure.servlet;

import com.alibaba.fastjson.JSONObject;
import me.shigure.service.BannerManageService;
import me.shigure.utils.ServletUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhengsiyuan
 * @date 2019-05-16 16:22
 */
@WebServlet(name = "BannerManageServlet", urlPatterns = "/bannerManageData")
public class BannerManageServlet extends BaseServlet{

	public void getBannerList(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("请求----getBannerList");
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		JSONObject jsonObject = BannerManageService.getBannerList(page,limit);
		ServletUtil.writeResponse(response, jsonObject.toJSONString());
	}

	public void getBannerInfo(HttpServletRequest request, HttpServletResponse response){
		System.out.println("请求----getBannerInfo");
		int bannerId = Integer.parseInt(request.getParameter("bannerId"));
		JSONObject jsonObject = BannerManageService.getBannerInfo(bannerId);
		ServletUtil.writeResponse(response, jsonObject.toJSONString());
	}

}
