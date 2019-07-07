package me.shigure.servlet;

import com.alibaba.fastjson.JSONObject;
import me.shigure.beans.DynamicBean;
import me.shigure.beans.FollowBean;
import me.shigure.beans.VideoInfoBean;
import me.shigure.service.CommentService;
import me.shigure.service.DynamicService;
import me.shigure.service.FollowService;
import me.shigure.utils.ServletUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author zhengsiyuan
 * @date 2019-03-06 15:44
 */
@WebServlet(name = "DynamicServlet", urlPatterns = "/dynamicData")
public class DynamicServlet extends BaseServlet{


	public void getDynamicVideoList(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("请求----getFollowList");

		int userId = Integer.parseInt(request.getParameter("userId"));

		JSONObject jsonObject = new JSONObject();
		FollowBean followBean = new FollowBean();
		try {
			request.setCharacterEncoding("UTF-8");
			followBean = FollowService.getFollowed(userId);
		} catch (UnsupportedEncodingException | SQLException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

		String followedStr = followBean.getFollowedUser();
		String[] followed = followedStr.split(",");


		ArrayList<VideoInfoBean> arrayList1 = null;
		try {
			arrayList1 = DynamicService.getDynamicVideoList(followed);
		} catch (SQLException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
		}

		jsonObject.put("dynamicList",JSONObject.toJSONString(arrayList1));
		jsonObject.put("msg","ok");
		ServletUtil.writeResponse(response,jsonObject.toJSONString());
	}

	public void getDynamicList(HttpServletRequest request, HttpServletResponse response){
		System.out.println("请求----getDynamicList");
		int userId = Integer.parseInt(request.getParameter("userId"));
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));

		JSONObject jsonObject = DynamicService.getDynamicList(userId, page, limit);
		ServletUtil.writeResponse(response, jsonObject.toJSONString());

	}

	public void addDynamic(HttpServletRequest request, HttpServletResponse response){
		System.out.println("请求----addDynamic");
		int dynamicType = Integer.parseInt(request.getParameter("dynamicType"));
		int videoId = Integer.parseInt(request.getParameter("videoId"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		String dynamicContent = request.getParameter("dynamicContent");
		String dynamicTime = request.getParameter("dynamicTime");

		JSONObject jsonObject = DynamicService.addDynamic(new DynamicBean(dynamicContent,dynamicType,videoId,userId,dynamicTime,0,0,1));
		ServletUtil.writeResponse(response, jsonObject.toJSONString());

	}

}
