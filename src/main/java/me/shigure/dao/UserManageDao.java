package me.shigure.dao;

import me.shigure.beans.UserInfoBean;
import me.shigure.utils.BeanUtil;
import me.shigure.utils.C3P0Util;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author zhengsiyuan
 * @date 2019-04-11 14:10
 */
public class UserManageDao {

	public static ArrayList<UserInfoBean> getUserList(int start, int end) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

		ArrayList<UserInfoBean> arrayList = new ArrayList<>();

		Connection conn = C3P0Util.getConnection();

		String sql = "select t1.*, t2.imagePath, t3.levelName, t3.levelDesc, t4.rightName," +
				"case t1.userStatus when 1 then '正常' when 2 then '已封禁' end as rightDesc " +
				"from t_user t1, t_image t2, t_level t3, t_right t4 " +
				"where t1.userPicId = t2.imageId " +
				"and t1.levelId = t3.levelId " +
				"and t1.userRightId = t4.rightId " +
				"and t1.userStatus != 3 " +
				"order by t1.userId " +
				"limit ?,?";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1,start);
		statement.setInt(2,end);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()){
			UserInfoBean userInfoBean;
			userInfoBean = (UserInfoBean) new BeanUtil().autoBean(UserInfoBean.class,resultSet);
			arrayList.add(userInfoBean);
		}

		C3P0Util.release(conn,statement,resultSet);

		return arrayList;
	}

	public static int getUserNum() throws SQLException{

		Connection conn = C3P0Util.getConnection();

		String sql = "select count(userId) userNum from t_user";

		PreparedStatement statement = conn.prepareStatement(sql);

		ResultSet resultSet = statement.executeQuery();

		int userNum = 0;

		while (resultSet.next()){
			userNum = resultSet.getInt("userNum");
		}

		C3P0Util.release(conn,statement,resultSet);

		return userNum;

	}

	public static int addManager(UserInfoBean userInfoBean) throws SQLException {
		Connection conn = C3P0Util.getConnection();

		String sql = "insert into t_user values (null,?,?,?,?,null,?,0,?,null,?,null,null)";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setString(1, userInfoBean.getUserName());
		statement.setString(2, userInfoBean.getPassword());
		statement.setString(3, userInfoBean.getMail());
		statement.setString(4, userInfoBean.getPhoneNum());
		statement.setInt(5, userInfoBean.getUserRightId());
		statement.setInt(6, userInfoBean.getUserStatus());
		statement.setInt(7, userInfoBean.getLevelId());

		int result = statement.executeUpdate();

		C3P0Util.release(conn,statement);

		return result;
	}

	public static int setUserStatus(int userId, int userStatus) throws SQLException {
		Connection conn = C3P0Util.getConnection();

		String sql = "update t_user set userStatus = ? where userId = ?";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setInt(1, userStatus);
		statement.setInt(2, userId);

		int result = statement.executeUpdate();

		C3P0Util.release(conn,statement);

		return result;
	}

	public static int deleteUser(int userId) throws SQLException {
		Connection conn = C3P0Util.getConnection();

		String sql = "delete from t_user where userId = ?";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setInt(1, userId);

		int result = statement.executeUpdate();

		C3P0Util.release(conn,statement);

		return result;
	}
}
