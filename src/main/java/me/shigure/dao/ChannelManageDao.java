package me.shigure.dao;

import me.shigure.beans.ChannelBean;
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
 * @date 2019-04-12 15:43
 */
public class ChannelManageDao {

	public static ArrayList<ChannelBean> getChannelList(int start, int end) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

		ArrayList<ChannelBean> arrayList = new ArrayList<>();

		Connection conn = C3P0Util.getConnection();

		String sql = "select t1.*, t2.imagePath " +
				"from t_channel t1, t_image t2 " +
				"where t1.channelPicId = t2.imageId " +
				"and t1.channelStatus != 3 " +
				"order by t1.channelId " +
				"limit ?,?";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1,start);
		statement.setInt(2,end);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()){
			ChannelBean channelBean;
			channelBean = (ChannelBean) new BeanUtil().autoBean(ChannelBean.class,resultSet);
			arrayList.add(channelBean);
		}

		C3P0Util.release(conn,statement,resultSet);

		return arrayList;
	}

	public static int getChannelNum() throws SQLException{

		Connection conn = C3P0Util.getConnection();

		String sql = "select count(channelId) channelNum from t_channel";

		PreparedStatement statement = conn.prepareStatement(sql);

		ResultSet resultSet = statement.executeQuery();

		int channelNum = 0;

		while (resultSet.next()){
			channelNum = resultSet.getInt("channelNum");
		}

		C3P0Util.release(conn,statement,resultSet);

		return channelNum;

	}

	public static ChannelBean getChannelInfoById(int channelId) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

		ChannelBean channelBean = null;

		Connection conn = C3P0Util.getConnection();

		String sql = "select t1.*, t2.imagePath " +
				"from t_channel t1, t_image t2 " +
				"where t1.channelId= ? " +
				"and t1.channelPicId = t2.imageId";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1,channelId);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()){
			channelBean = (ChannelBean) new BeanUtil().autoBean(ChannelBean.class,resultSet);
		}

		C3P0Util.release(conn,statement,resultSet);

		return channelBean;

	}

	public static int editChannelWithImage(ChannelBean channelBean) throws SQLException {
		Connection conn = C3P0Util.getConnection();

		String sql = "update t_channel set channelName = ?, channelDesc = ?, channelPicId = ? where channelId = ?";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setString(1, channelBean.getChannelName());
		statement.setString(2, channelBean.getChannelDesc());
		statement.setInt(3, channelBean.getChannelPicId());
		statement.setInt(4, channelBean.getChannelId());

		int result = statement.executeUpdate();

		C3P0Util.release(conn,statement);

		return result;
	}

	public static int editChannelNoneImage(ChannelBean channelBean) throws SQLException {
		Connection conn = C3P0Util.getConnection();

		String sql = "update t_channel set channelName = ?, channelDesc = ? where channelId = ?";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setString(1, channelBean.getChannelName());
		statement.setString(2, channelBean.getChannelDesc());
		statement.setInt(3, channelBean.getChannelId());

		int result = statement.executeUpdate();

		C3P0Util.release(conn,statement);

		return result;
	}

	public static int deleteChannel(int channelId) throws SQLException {
		Connection conn = C3P0Util.getConnection();

		String sql = "update t_channel set channelStatus = 3 where channelId = ?";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setInt(1, channelId);

		int result = statement.executeUpdate();

		C3P0Util.release(conn,statement);

		return result;
	}

	public static int addChannel(ChannelBean channelBean) throws SQLException {

		Connection conn = C3P0Util.getConnection();

		String sql = "insert into t_channel (channelId, channelName, channelDesc, channelPicId, channelStatus) VALUES (null,?,?,?,1)";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setString(1, channelBean.getChannelName());
		statement.setString(2, channelBean.getChannelDesc());
		statement.setInt(3, channelBean.getChannelPicId());

		int result = statement.executeUpdate();

		C3P0Util.release(conn,statement);

		return result;

	}

	public static int isHaveVideo(int channelId) throws SQLException {

		Connection conn = C3P0Util.getConnection();

		String sql = "select count(videoId) imageId from t_video where channelId = ?";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setInt(1, channelId);

		ResultSet resultSet = statement.executeQuery();

		int imageId = 0;

		while (resultSet.next()){
			imageId = resultSet.getInt("imageId");
		}

		C3P0Util.release(conn,statement,resultSet);

		return imageId;

	}

}
