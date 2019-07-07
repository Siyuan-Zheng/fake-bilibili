package me.shigure.dao;

import me.shigure.beans.CommentBean;
import me.shigure.beans.VideoInfoBean;
import me.shigure.utils.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author zhengsiyuan
 * @date 2019-05-17 16:54
 */
public class ReportManageDao {

	public static ArrayList<VideoInfoBean> getVideoReportList(int start, int end) throws SQLException {

		QueryRunner runner = new QueryRunner(C3P0Util.getDataSource());

		String sql = "select t1.videoId, t1.channelId, videoPicId, videoPath, t1.userId, videoName, videoLikeNum, videoFavoriteNum, videoPointNum, videoDesc, operateTime, videoCommentNum, videoStatus, t2.userName, t2.userDesc, t3.channelName, date_format(from_unixtime(ROUND(t1.videoUpTime/1000)),'%Y-%m-%d %H:%s:%i') videoUpTime, case t1.videoStatus when 1 then '正常' when 2 then '已封禁' end as videoTag " +
				"from t_video t1, t_user t2, t_channel t3, t_image t4 " +
				"where t1.userId = t2.userId " +
				"and t1.channelId = t3.channelId " +
				"and t1.videoPicId = t4.imageId " +
				"and t1.videoStatus = 4 " +
				"order by t1.videoId " +
				"limit ?,?";

		return (ArrayList<VideoInfoBean>) runner.query(sql, new BeanListHandler<>(VideoInfoBean.class),start,end);
	}

	public static ArrayList<CommentBean> getCommentReportList(int start, int end) throws SQLException {

		QueryRunner runner = new QueryRunner(C3P0Util.getDataSource());

		String sql = "select * " +
				"from t_comment t1, t_video t2, t_user t3 " +
				"where t1.videoId = t2.videoId " +
				"and t1.userId = t2.userId " +
				"and t1.commentStatus = 4 " +
				"order by t1.commentId " +
				"limit ?,?";

		return (ArrayList<CommentBean>) runner.query(sql, new BeanListHandler<>(CommentBean.class),start,end);
	}

}
