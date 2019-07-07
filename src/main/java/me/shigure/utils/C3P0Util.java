package me.shigure.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @author zhengsiyuan
 */
public class C3P0Util {

	private static ComboPooledDataSource comboPooleDataSource = new ComboPooledDataSource();
	
	/**
	 * 获取连接
	 * 
	 * @return 返回数据库连接对象
	 */
	public static Connection getConnection(){
		
		try {
			return comboPooleDataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * 获取数据源对象
	 */
	public static DataSource getDataSource(){
		return comboPooleDataSource;
	}
	
	/**
	 * 释放连接
	 * @param conn 数据库连接对象
	 * @param stmt Statement对象
	 * @param rs Result对象
	 */
	public static void release(Connection conn, Statement stmt, ResultSet rs) {

		release(conn,stmt);

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rs = null;
		}

	}
	/**
	 * 释放连接
	 * @param conn 数据库连接对象
	 * @param stmt Statement对象
	 */
	public static void release(Connection conn, Statement stmt) {

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = null;
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stmt = null;
		}
	}
}
