package me.shigure.service;

import me.shigure.beans.FavoriteBean;
import me.shigure.dao.FavoriteDao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author zhengsiyuan
 * @date 2019-04-05 15:09
 */
public class FavoriteService {

	public static ArrayList<FavoriteBean> getFavoriteList(int userId) throws SQLException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
		return FavoriteDao.getFavoriteList(userId);
	}

	public static int addFavorite(int videoId, int userId, String favoriteTime) throws SQLException {
		return FavoriteDao.addFavorite(videoId,userId,favoriteTime);
	}

	public static int deleteFavorite(int videoId, int userId) throws SQLException{
		return FavoriteDao.deleteFavorite(videoId, userId);
	}

}
