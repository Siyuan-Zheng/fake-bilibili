//package me.shigure.dao;
//
//import me.shigure.utils.C3P0Util;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
///**
// * @author zhengsiyuan
// * @date 2019-05-18 10:26
// */
//public class ReportDao {
//
//	public static int videoReport(int videoId) throws SQLException {
//
//		Connection conn = C3P0Util.getConnection();
//
//		String sql = "update t_video ";
//
//		PreparedStatement statement = conn.prepareStatement(sql);
//
//		statement.setInt(1, videoId);
//
//		int result = statement.executeUpdate();
//
//		C3P0Util.release(conn,statement);
//
//		return result;
//
//	}
//
//}
