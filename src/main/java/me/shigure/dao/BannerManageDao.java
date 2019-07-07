package me.shigure.dao;

import me.shigure.beans.BannerBean;
import me.shigure.utils.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author zhengsiyuan
 * @date 2019-05-16 16:21
 */
public class BannerManageDao {

	public static ArrayList<BannerBean> getBannerList(int start, int end) throws SQLException {

		QueryRunner runner = new QueryRunner(C3P0Util.getDataSource());

		String sql = "select t1.*, t2.imagePath as bannerImagePath " +
				"from t_banner t1, t_image t2 " +
				"where t1.bannerImageId = t2.imageId " +
				"order by t1.bannerId " +
				"limit ?,?";

		return (ArrayList<BannerBean>) runner.query(sql, new BeanListHandler<>(BannerBean.class),start,end);
	}

	public static int getBannerNum() throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Util.getDataSource());

		String sql = "select count(bannerId) videoNum from t_banner";
		int re = (int) (long) runner.query(sql, new ScalarHandler());
		System.out.println(re);
		return re;
	}

	public static BannerBean getBannerInfo(int bannerId) throws SQLException {

		QueryRunner runner = new QueryRunner(C3P0Util.getDataSource());

		String sql = "select t1.*, t2.imagePath as bannerImagePath " +
				"from t_banner t1, t_image t2 " +
				"where t1.bannerImageId = t2.imageId " +
				"and t1.bannerId = ?";

		return runner.query(sql, new BeanHandler<>(BannerBean.class),bannerId);
	}

}
