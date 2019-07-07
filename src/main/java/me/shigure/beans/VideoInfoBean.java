package me.shigure.beans;

import lombok.*;

import java.io.Serializable;

/**
 * @author zhengsiyuan
 * @date 2019-03-06 14:28
 */
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoInfoBean implements Serializable {
	private Integer videoId;
	private Integer channelId;
	private Integer videoPicId;
	private String videoPath;
	private Integer userId;
	private String videoName;
	private Integer videoLikeNum;
	private Integer videoFavoriteNum;
	private Integer videoPointNum;
	private String videoUpTime;
	private String videoDesc;
	private String videoTag;
	private String operateTime;
	private String imagePath;
	private String userName;
	private String channelName;
	private Integer videoCommentNum;
	private String userDesc;
	private Integer videoStatus;

	public VideoInfoBean(Integer channelId, Integer videoPicId, String videoPath, Integer userId, String videoName, Integer videoLikeNum, Integer videoFavoriteNum, Integer videoPointNum, String videoUpTime, String videoDesc, String videoTag, String operateTime, Integer videoCommentNum, Integer videoStatus) {
		this.channelId = channelId;
		this.videoPicId = videoPicId;
		this.videoPath = videoPath;
		this.userId = userId;
		this.videoName = videoName;
		this.videoLikeNum = videoLikeNum;
		this.videoFavoriteNum = videoFavoriteNum;
		this.videoPointNum = videoPointNum;
		this.videoUpTime = videoUpTime;
		this.videoDesc = videoDesc;
		this.videoTag = videoTag;
		this.operateTime = operateTime;
		this.videoCommentNum = videoCommentNum;
		this.videoStatus = videoStatus;
	}
}
