package me.shigure.service;

import com.alibaba.fastjson.JSONObject;
import me.shigure.beans.CommentBean;
import me.shigure.dao.CommentDao;
import me.shigure.dao.DynamicDao;
import me.shigure.dao.VideoDao;
import me.shigure.utils.PageUtil;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * @author shigure
 * @date 2019-04-24 14:50
 */
public class CommentService {

	public static JSONObject getCommentList(int videoId, int page, int limit){
		JSONObject jsonObject = new JSONObject();
		try {
			int videoNum = CommentDao.getCommentNum(videoId);
			PageUtil pageObject = new PageUtil(page, videoNum, limit);
			jsonObject.put("commentList", JSONObject.toJSONString(CommentDao.getCommentList(videoId, pageObject.getStartIndex(), pageObject.getPageSize())));
			jsonObject.put("pageInfo",JSONObject.toJSONString(pageObject));
			jsonObject.put("msg", "ok");
		} catch (SQLException e) {
			jsonObject.put("msg","error");
			e.printStackTrace();
		}
		return jsonObject;
	}

	public static JSONObject getDynamicCommentList(int videoId, int page, int limit){
		JSONObject jsonObject = new JSONObject();
		try {
			int videoNum = CommentDao.getDynamicCommentNum(videoId);
			PageUtil pageObject = new PageUtil(page, videoNum, limit);
			jsonObject.put("commentList", JSONObject.toJSONString(CommentDao.getDynamicCommentList(videoId, pageObject.getStartIndex(), pageObject.getPageSize())));
			jsonObject.put("pageInfo",JSONObject.toJSONString(pageObject));
			jsonObject.put("msg", "ok");
		} catch (SQLException e) {
			jsonObject.put("msg","error");
			e.printStackTrace();
		}
		return jsonObject;
	}

	public static JSONObject addComment(CommentBean commentBean){

		JSONObject jsonObject = new JSONObject();

		try {
			int flag = CommentDao.addComment(commentBean);
			int maxFloor = CommentDao.getMaxFloor(commentBean.getVideoId(),1);
			int flag2 = VideoDao.addCommentNum(commentBean.getVideoId());
			if (flag != 0 && flag2 != 0){
				jsonObject.put("msg","ok");
				jsonObject.put("maxFloor",maxFloor);
			}else {
				jsonObject.put("msg","error");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			jsonObject.put("msg","error");
		}
		return jsonObject;
	}

	public static JSONObject addDynamicComment(CommentBean commentBean){

		JSONObject jsonObject = new JSONObject();

		try {
			int flag = CommentDao.addComment(commentBean);
			int maxFloor = CommentDao.getMaxFloor(commentBean.getVideoId(),2);
			int flag2 = DynamicDao.addCommentNum(commentBean.getVideoId());
			if (flag != 0 && flag2 != 0){
				jsonObject.put("msg","ok");
				jsonObject.put("maxFloor",maxFloor);
			}else {
				jsonObject.put("msg","error");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			jsonObject.put("msg","error");
		}
		return jsonObject;
	}

	public static JSONObject likeComment(int commentId){

		JSONObject jsonObject = new JSONObject();

		try {
			int flag = CommentDao.likeComment(commentId);
			if (flag!=0){
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
