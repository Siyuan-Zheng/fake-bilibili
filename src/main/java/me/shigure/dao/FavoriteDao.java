package me.shigure.dao;

import me.shigure.beans.FavoriteBean;
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
 * @date 2019-04-05 14:04
 */
public class FavoriteDao {

	public static ArrayList<FavoriteBean> getFavoriteList(int userId) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
		ArrayList<FavoriteBean> arrayList = new ArrayList<>();

		Connection conn = C3P0Util.getConnection();

		String sql = "select t1.videoName, t1.videoPointNum, t2.*, t3.imagePath " +
				"from t_video t1, t_favorite t2, t_image t3 " +
				"where t1.videoId = t2.videoId " +
				"and videoStatus = 1 " +
				"and t1.videoPicId = t3.imageId " +
				"and t2.userId = ? " +
				"order by t2.favoriteTime";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1,userId);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()){
			FavoriteBean favoriteBean;
			favoriteBean = (FavoriteBean) new BeanUtil().autoBean(FavoriteBean.class,resultSet);
			arrayList.add(favoriteBean);
		}

		C3P0Util.release(conn,statement,resultSet);

		return arrayList;

	}

	public static int addFavorite(int videoId, int userId, String favoriteTime) throws SQLException {
		Connection conn = C3P0Util.getConnection();

		String sql = "insert into t_favorite values (null,?,?,?)";


		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setInt(1, userId);
		statement.setInt(2, videoId);
		statement.setString(3, favoriteTime);

		int result = statement.executeUpdate();

		C3P0Util.release(conn,statement);

		return result;
	}

	public static int deleteFavorite(int videoId, int userId) throws SQLException {

		Connection conn = C3P0Util.getConnection();

		String sql = "delete from t_favorite where userId = ? and videoId = ?";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setInt(1, userId);
		statement.setInt(2, videoId);

		int result = statement.executeUpdate();

		C3P0Util.release(conn,statement);

		return result;
	}

}
