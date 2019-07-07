package me.shigure.service;

import me.shigure.beans.FollowBean;
import me.shigure.dao.FollowDao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * @author zhengsiyuan
 * @date 2019-04-02 16:40
 */
public class FollowService {
	public static FollowBean getFollowed(int userId) throws SQLException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
		return FollowDao.getFollowed(userId);
	}

	public static int setFollowed(int userId, String followedStr) throws SQLException {
		return FollowDao.setFollowed(userId,followedStr);
	}

	public static int setFollower(int userId, String followerStr) throws SQLException {
		return FollowDao.setFollower(userId,followerStr);
	}
}
