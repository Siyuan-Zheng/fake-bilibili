package me.shigure.service;

import me.shigure.beans.UserInfoBean;
import me.shigure.dao.UserDao;

import java.sql.SQLException;

/**
 * @author zhengsiyuan
 * @date 2019-04-14 17:11
 */
public class UserService {

	public static int editUser(UserInfoBean userInfoBean) throws SQLException {
		return UserDao.editUser(userInfoBean);
	}

	public static int changePassword(String password, int userId) throws SQLException {
		return UserDao.changePassword(password,userId);
	}

	public static int changeMail(String mail, int userId) throws SQLException {
		return UserDao.changeMail(mail,userId);
	}

	public static int changePhone(String phone, int userId) throws SQLException {
		return UserDao.changePhone(phone,userId);
	}
}
