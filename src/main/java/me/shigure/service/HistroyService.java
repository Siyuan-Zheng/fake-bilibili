package me.shigure.service;

import me.shigure.beans.HistoryBean;
import me.shigure.dao.HistoryDao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author zhengsiyuan
 * @date 2019-04-03 10:40
 */
public class HistroyService {

	public static ArrayList<HistoryBean> getHistoryList(int userId) throws SQLException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
		return HistoryDao.getHistoryList(userId);
	}

	public static int deleteHistory(int userId) throws SQLException {
		return HistoryDao.deleteHistory(userId);
	}

	public static int addHistory(int videoId, int userId, String historyTime) throws SQLException {
		return HistoryDao.addHistory(videoId,userId,historyTime);
	}

	public static int updateHistory(int videoId, int userId, String historyTime) throws SQLException {
		return HistoryDao.updateHistory(videoId,userId,historyTime);
	}
}
