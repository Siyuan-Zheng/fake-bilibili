package me.shigure.dao;

import me.shigure.utils.C3P0Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhengsiyuan
 * @date 2019-04-08 14:28
 */
public class ImageDao {

	public static int addImage(String imagePath) throws SQLException {

		Connection conn = C3P0Util.getConnection();

		String sql = "insert into t_image (imageId, imagePath) VALUES (null,?)";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setString(1, imagePath);

		int result = statement.executeUpdate();

		C3P0Util.release(conn,statement);

		return result;
	}

	public static int getImageId() throws SQLException {

		Connection conn = C3P0Util.getConnection();

		String sql = "select max(imageId) imageId from t_image";

		PreparedStatement statement = conn.prepareStatement(sql);

		ResultSet resultSet = statement.executeQuery();

		int imageId = 0;

		while (resultSet.next()){
			imageId = resultSet.getInt("imageId");
		}

		C3P0Util.release(conn,statement,resultSet);

		return imageId;

	}

}
