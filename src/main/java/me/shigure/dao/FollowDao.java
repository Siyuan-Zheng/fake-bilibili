package me.shigure.dao;

import me.shigure.beans.FollowBean;
import me.shigure.utils.BeanUtil;
import me.shigure.utils.C3P0Util;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhengsiyuan
 * @date 2019-04-02 16:42
 */
public class FollowDao {
	public static FollowBean getFollowed(int userId) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

		FollowBean followBean = null;

		Connection conn = C3P0Util.getConnection();

		String sql = "select * from t_follow where userId = ?";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1,userId);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()){
			followBean = (FollowBean) new BeanUtil().autoBean(FollowBean.class,resultSet);
		}

		C3P0Util.release(conn,statement,resultSet);

		return followBean;

	}

	public static int setFollowed(int userId, String followedStr) throws SQLException {

		Connection conn = C3P0Util.getConnection();

		String sql = "update t_follow set followedUser = ? where userId = ?";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setString(1, followedStr);
		statement.setInt(2, userId);

		int result = statement.executeUpdate();

		C3P0Util.release(conn,statement);

		return result;

	}

	public static int setFollower(int userId, String followerStr) throws SQLException {

		Connection conn = C3P0Util.getConnection();

		String sql = "update t_follow set follower = ? where userId = ?";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setString(1, followerStr);
		statement.setInt(2, userId);

		int result = statement.executeUpdate();

		C3P0Util.release(conn,statement);

		return result;

	}

}
