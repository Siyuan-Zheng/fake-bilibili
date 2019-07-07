package me.shigure.dao;

import me.shigure.beans.UserInfoBean;
import me.shigure.utils.C3P0Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author zhengsiyuan
 * @date 2019-04-14 16:53
 */
public class UserDao {

	public static int editUser(UserInfoBean userInfoBean) throws SQLException {
		Connection conn = C3P0Util.getConnection();

		String sql = "update t_user set userName = ?, userDesc = ?, sex = ? where userId = ?";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setString(1, userInfoBean.getUserName());
		statement.setString(1, userInfoBean.getUserDesc());
		statement.setInt(1, userInfoBean.getSex());
		statement.setInt(1, userInfoBean.getUserId());

		int result = statement.executeUpdate();

		C3P0Util.release(conn,statement);

		return result;
	}

	public static int changePassword(String password, int userId) throws SQLException {
		Connection conn = C3P0Util.getConnection();

		String sql = "update t_user set password = ? where userId = ?";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setString(1, password);
		statement.setInt(2, userId);

		int result = statement.executeUpdate();

		C3P0Util.release(conn,statement);

		return result;
	}

	public static int changeMail(String mail, int userId) throws SQLException {

		Connection conn = C3P0Util.getConnection();

		String sql = "update t_user set mail = ? where userId = ?";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setString(1, mail);
		statement.setInt(2, userId);

		int result = statement.executeUpdate();

		C3P0Util.release(conn,statement);

		return result;

	}

	public static int changePhone(String phone, int userId) throws SQLException {

		Connection conn = C3P0Util.getConnection();

		String sql = "update t_user set phoneNum = ? where userId = ?";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setString(1, phone);
		statement.setInt(2, userId);

		int result = statement.executeUpdate();

		C3P0Util.release(conn,statement);

		return result;

	}
}
