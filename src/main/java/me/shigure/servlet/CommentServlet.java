package me.shigure.servlet;

import com.alibaba.fastjson.JSONObject;
import me.shigure.beans.CommentBean;
import me.shigure.service.CommentService;
import me.shigure.utils.ServletUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author shigure
 * @date 2019-04-24 14:52
 */
@WebServlet(name = "CommentServlet", urlPatterns = "/commentData")
public class CommentServlet extends BaseServlet{

	public void getCommentList(HttpServletRequest request, HttpServletResponse response){
		System.out.println("请求----getCommentList");
		int videoId = Integer.parseInt(request.getParameter("videoId"));
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		JSONObject jsonObject = CommentService.getCommentList(videoId, page, limit);
		ServletUtil.writeResponse(response, jsonObject.toJSONString());
	}

	public void getDynamicCommentList(HttpServletRequest request, HttpServletResponse response){
		System.out.println("请求----getDynamicCommentList");
		int videoId = Integer.parseInt(request.getParameter("videoId"));
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		JSONObject jsonObject = CommentService.getDynamicCommentList(videoId, page, limit);
		ServletUtil.writeResponse(response, jsonObject.toJSONString());
	}

	public void addDynamicComment(HttpServletRequest request, HttpServletResponse response){
		System.out.println("请求----addDynamicComment");
		String commentContent = request.getParameter("commentContent");
		int dynamicId = Integer.parseInt(request.getParameter("dynamicId"));
		String commentTime = request.getParameter("commentTime");
		int userId = Integer.parseInt(request.getParameter("userId"));
		JSONObject jsonObject = CommentService.addDynamicComment(new CommentBean(commentContent,userId,commentTime,dynamicId,0,2,1));
		ServletUtil.writeResponse(response, jsonObject.toJSONString());
	}

	public void addComment(HttpServletRequest request, HttpServletResponse response){
		System.out.println("请求----addComment");
		String commentContent = request.getParameter("commentContent");
		int videoId = Integer.parseInt(request.getParameter("videoId"));
		String commentTime = request.getParameter("commentTime");
		int userId = Integer.parseInt(request.getParameter("userId"));
		JSONObject jsonObject = CommentService.addComment(new CommentBean(commentContent,userId,commentTime,videoId,0,1,1));
		ServletUtil.writeResponse(response, jsonObject.toJSONString());
	}

	public void likeComment(HttpServletRequest request, HttpServletResponse response){
		System.out.println("请求----likeComment");
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		JSONObject jsonObject = CommentService.likeComment(commentId);
		ServletUtil.writeResponse(response, jsonObject.toJSONString());
	}

}
