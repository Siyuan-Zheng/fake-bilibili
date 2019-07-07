package me.shigure.service;

import me.shigure.beans.UserInfoBean;
import me.shigure.dao.LoginDao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * @author zhengsiyuan
 * @date 2019-02-15 17:14
 */
public class LoginService {

	public static UserInfoBean login(UserInfoBean userInfoBean, String accountType) throws SQLException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
		return LoginDao.login(userInfoBean, accountType);
	}

	public static int register(UserInfoBean userInfoBean, String accountType) throws SQLException {
		return LoginDao.register(userInfoBean, accountType);
	}

	public static boolean isDuplicate(String userName, String account, String accountType){
		return LoginDao.isDuplicate(userName, account, accountType);
	}

}
