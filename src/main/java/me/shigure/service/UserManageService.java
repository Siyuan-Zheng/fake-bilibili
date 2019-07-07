package me.shigure.service;

import me.shigure.beans.UserInfoBean;
import me.shigure.dao.UserManageDao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author zhengsiyuan
 * @date 2019-04-11 14:16
 */
public class UserManageService {

	public static int getUserNum() throws SQLException{
		return UserManageDao.getUserNum();
	}

	public static ArrayList<UserInfoBean> getUserList(int start, int end) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
		return UserManageDao.getUserList(start,end);
	}

	public static int addManager(UserInfoBean userInfoBean) throws SQLException {
		return UserManageDao.addManager(userInfoBean);
	}

	public static int setUserStatus(int userId, int userStatus) throws SQLException {
		return UserManageDao.setUserStatus(userId, userStatus);
	}

	public static int deleteUser(int userId) throws SQLException{
		return UserManageDao.deleteUser(userId);
	}

}
