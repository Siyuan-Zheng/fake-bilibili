package me.shigure.dao;

import me.shigure.beans.HistoryBean;
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
 * @date 2019-04-03 10:38
 */
public class HistoryDao {

	public static ArrayList<HistoryBean> getHistoryList(int userId) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
		ArrayList<HistoryBean> arrayList = new ArrayList<>();

		Connection conn = C3P0Util.getConnection();

		String sql = "select t1.videoName, t1.videoPointNum, t2.*, t3.imagePath " +
				"from t_video t1, t_history t2, t_image t3 " +
				"where t1.videoId = t2.videoId " +
				"and videoStatus = 1 " +
				"and t1.videoPicId = t3.imageId " +
				"and t2.userId = ? " +
				"order by t2.historyTime desc";


		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, userId);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			HistoryBean historyBean;
			historyBean = (HistoryBean) new BeanUtil().autoBean(HistoryBean.class, resultSet);
			arrayList.add(historyBean);
		}

		C3P0Util.release(conn, statement, resultSet);

		return arrayList;

	}

	public static int deleteHistory(int userId) throws SQLException {

		Connection conn = C3P0Util.getConnection();

		String sql = "delete from t_history where userId = ?";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setInt(1, userId);

		int result = statement.executeUpdate();

		C3P0Util.release(conn,statement);

		return result;
	}

	public static int addHistory(int videoId, int userId, String historyTime) throws SQLException {
		Connection conn = C3P0Util.getConnection();

		String sql = "insert into t_history values (null,?,?,?)";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setInt(1, videoId);
		statement.setInt(2, userId);
		statement.setString(3, historyTime);

		int result = statement.executeUpdate();

		C3P0Util.release(conn,statement);

		return result;
	}

	public static int updateHistory(int videoId, int userId, String historyTime) throws SQLException {
		Connection conn = C3P0Util.getConnection();

		String sql = "update t_history set historyTime = ? where videoId = ? and userId = ?";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setString(1, historyTime);
		statement.setInt(2, videoId);
		statement.setInt(3, userId);

		int result = statement.executeUpdate();

		C3P0Util.release(conn,statement);

		return result;
	}

}
