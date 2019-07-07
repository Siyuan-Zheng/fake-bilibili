package me.shigure.servlet;

import com.alibaba.fastjson.JSONObject;
import me.shigure.beans.CommentBean;
import me.shigure.beans.ReplyBean;
import me.shigure.service.CommentService;
import me.shigure.service.ReplyService;
import me.shigure.utils.ServletUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author shigure
 * @date 2019-04-24 16:40
 */
@WebServlet(name = "ReplyServlet", urlPatterns = "/replyData")
public class ReplyServlet extends BaseServlet {

	public void getReplyList(HttpServletRequest request, HttpServletResponse response){
		System.out.println("请求----getReplyList");
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		JSONObject jsonObject = ReplyService.getReplyList(commentId, page, limit);
		ServletUtil.writeResponse(response, jsonObject.toJSONString());
	}

	public void addReply(HttpServletRequest request, HttpServletResponse response){
		System.out.println("请求----addReply");
		String replyContent = request.getParameter("replyContent");
		int toCommentId = Integer.parseInt(request.getParameter("toCommentId"));
		int replyType = Integer.parseInt(request.getParameter("replyType"));
		int fromUserId = Integer.parseInt(request.getParameter("fromUserId"));
		String replyTime = request.getParameter("replyTime");
		int toUserId = Integer.parseInt(request.getParameter("toUserId"));

		JSONObject jsonObject = ReplyService.addReply(new ReplyBean(toCommentId,replyContent,replyType,fromUserId,toUserId,replyTime,0));
		ServletUtil.writeResponse(response, jsonObject.toJSONString());
	}

	public void likeReply(HttpServletRequest request, HttpServletResponse response){
		System.out.println("请求----likeReply");
		int replyId = Integer.parseInt(request.getParameter("replyId"));
		JSONObject jsonObject = ReplyService.likeReply(replyId);
		ServletUtil.writeResponse(response, jsonObject.toJSONString());
	}

}
