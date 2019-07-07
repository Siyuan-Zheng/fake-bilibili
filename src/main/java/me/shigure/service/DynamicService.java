package me.shigure.service;

import com.alibaba.fastjson.JSONObject;
import me.shigure.beans.DynamicBean;
import me.shigure.beans.FollowBean;
import me.shigure.beans.VideoInfoBean;
import me.shigure.dao.CommentDao;
import me.shigure.dao.DynamicDao;
import me.shigure.utils.PageUtil;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author zhengsiyuan
 * @date 2019-03-06 15:44
 */
public class DynamicService {

	public static ArrayList<VideoInfoBean> getDynamicVideoList(String[] userId) throws SQLException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
		return DynamicDao.getDynamicVideoList(userId);
	}

	public static JSONObject getDynamicList(int userIds, int page, int limit){

		JSONObject jsonObject = new JSONObject();
		try {
			FollowBean followBean = FollowService.getFollowed(userIds);
			String followed = followBean.getFollowedUser();
			String[] userId = followed.split(",");
			int dynamicNum = DynamicDao.getDynamicNum(userId);
			PageUtil pageObject = new PageUtil(page, dynamicNum, limit);
			jsonObject.put("dynamicList", JSONObject.toJSONString(DynamicDao.getDynamicList(userId, pageObject.getStartIndex(), pageObject.getPageSize())));
			jsonObject.put("pageInfo",JSONObject.toJSONString(pageObject));
			jsonObject.put("msg", "ok");
		} catch (SQLException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
			jsonObject.put("msg","error");
			e.printStackTrace();
		}
		return jsonObject;

	}

	public static JSONObject addDynamic(DynamicBean dynamicBean){
		JSONObject jsonObject = new JSONObject();

		try {
			int flag = DynamicDao.addDynamic(dynamicBean);
			if (flag != 0){
				jsonObject.put("msg","ok");
			}else {
				jsonObject.put("msg","error");
			}
		} catch (SQLException e) {
			jsonObject.put("msg","error");
			e.printStackTrace();
		}
		return jsonObject;
	}

}
