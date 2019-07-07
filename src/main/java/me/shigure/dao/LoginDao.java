package me.shigure.dao;

import me.shigure.beans.UserInfoBean;
import me.shigure.utils.BeanUtil;
import me.shigure.utils.C3P0Util;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhengsiyuan
 * @date 2019-02-15 17:24
 */
public class LoginDao {

	private static final String MAIL = "mail";
	private static final String PHONE = "phone";

	public static UserInfoBean login(UserInfoBean userInfoBean, String accountType) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
		UserInfoBean currentUserInfoBean = new UserInfoBean();
		Connection conn = C3P0Util.getConnection();
		String sql = null;

		if (MAIL.equals(accountType)) {
			sql = "select t1.*, t2.imagePath, t3.levelName, t3.levelDesc, t4.rightName, t4.rightDesc " +
					"from t_user t1, t_image t2, t_level t3, t_right t4 " +
					"where t1.mail = ? " +
					"and t1.userPicId = t2.imageId " +
					"and t1.levelId = t3.levelId " +
					"and t1.userRightId = t4.rightId";
			System.out.println("mail");
		} else if (PHONE.equals(accountType)) {
			sql = "select t1.*, t2.imagePath, t3.levelName, t3.levelDesc, t4.rightName, t4.rightDesc " +
					"from t_user t1, t_image t2, t_level t3, t_right t4 " +
					"where t1.phoneNum = ? " +
					"and t1.userPicId = t2.imageId " +
					"and t1.levelId = t3.levelId " +
					"and t1.userRightId = t4.rightId";
			System.out.println("phone");
		}

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setString(1, userInfoBean.getMail());

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()){
			currentUserInfoBean = (UserInfoBean) new BeanUtil().autoBean(UserInfoBean.class,resultSet);
		}

		C3P0Util.release(conn,statement,resultSet);

		return currentUserInfoBean;
	}

	public static int register(UserInfoBean userInfoBean, String accountType) throws SQLException {

		Connection conn = C3P0Util.getConnection();

		String sql = "insert into t_user values (null,?,?,?,?,?,?,?,?,?,?,0,0)";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setString(1, userInfoBean.getUserName());
		statement.setString(2, userInfoBean.getPassword());
		statement.setString(5, userInfoBean.getUserDesc());
		statement.setInt(6, userInfoBean.getUserRightId());
		statement.setInt(7, userInfoBean.getUserPicId());
		statement.setInt(8, userInfoBean.getUserStatus());
		statement.setInt(9, userInfoBean.getSex());
		statement.setInt(10, userInfoBean.getLevelId());

		System.out.println(accountType);

		if (MAIL.equals(accountType)){
			System.out.println(1);
			System.out.println(userInfoBean.getMail());
			statement.setString(3, userInfoBean.getMail());
			statement.setString(4, null);
			System.out.println("mail");
		}else if (PHONE.equals(accountType)){
			System.out.println(2);
			System.out.println(userInfoBean.getPhoneNum());
			statement.setString(3, null);
			statement.setString(4, userInfoBean.getPhoneNum());
			System.out.println("phone");
		}

		int result = statement.executeUpdate();

		C3P0Util.release(conn,statement);

		return result;
	}

	public static boolean isDuplicate(String userName, String account, String accountType) {
		if (MAIL.equals(accountType)){
			System.out.println("mail");
		}else if (PHONE.equals(accountType)){
			System.out.println("phone");
		}
		return true;
	}
}
