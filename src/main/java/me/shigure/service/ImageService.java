package me.shigure.service;

import me.shigure.dao.ImageDao;

import java.sql.SQLException;

/**
 * @author zhengsiyuan
 * @date 2019-04-08 14:36
 */
public class ImageService {

	public static int addImage(String imagePath) throws SQLException {
		return ImageDao.addImage(imagePath);
	}

	public static int getImageId() throws SQLException {
		return ImageDao.getImageId();
	}

}
