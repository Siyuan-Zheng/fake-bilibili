package me.shigure.service;

import com.alibaba.fastjson.JSONObject;
import me.shigure.beans.CommentBean;
import me.shigure.beans.ReplyBean;
import me.shigure.dao.CommentDao;
import me.shigure.dao.ReplyDao;
import me.shigure.utils.C3P0Util;
import me.shigure.utils.PageUtil;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhengsiyuan
 * @date 2019-04-24 16:39
 */
public class ReplyService {
	public static JSONObject getReplyList(int commentId, int page, int limit){
		JSONObject jsonObject = new JSONObject();
		try {
			int videoNum = ReplyDao.getReplyNum(commentId);
			PageUtil pageObject = new PageUtil(page, videoNum, limit);
			jsonObject.put("replyList", JSONObject.toJSONString(ReplyDao.getReplyList(commentId, pageObject.getStartIndex(), pageObject.getPageSize())));
			jsonObject.put("pageInfo",JSONObject.toJSONString(pageObject));
			jsonObject.put("msg", "ok");
		} catch (SQLException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
			jsonObject.put("msg","error");
			e.printStackTrace();
		}
		return jsonObject;
	}

	public static JSONObject addReply(ReplyBean replyBean){
		JSONObject jsonObject = new JSONObject();
		try {
			int maxReplyId;
			int flag = ReplyDao.addReply(replyBean);
			maxReplyId = ReplyDao.getReplyId();
			if (flag != 0){
				jsonObject.put("msg","ok");
				jsonObject.put("maxReplyId",maxReplyId);
			}else {
				jsonObject.put("msg","error");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			jsonObject.put("msg","error");
		}
		return jsonObject;
	}

	public static JSONObject likeReply(int replyId){

		JSONObject jsonObject = new JSONObject();

		try {
			int flag = ReplyDao.likeReply(replyId);

			if (flag != 0){
				jsonObject.put("msg","ok");
			}else {
				jsonObject.put("msg","error");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			jsonObject.put("msg","error");
		}
		return jsonObject;
	}


}
