package me.shigure.beans;

import lombok.*;

import java.io.Serializable;

/**
 * @author zhengsiyuan
 * @date 2019-02-18 17:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentBean implements Serializable {

	private Integer commentId;
	private String commentContext;
	private Integer userId;
	private String commentTime;
	private Integer videoId;
	private Integer likeNum;
	private Integer commentStatus;
	private Integer commentType;

	private String userName;
	private String imagePath;
	private Integer levelId;

	private Double rowNum;

	public CommentBean(String commentContext, Integer userId, String commentTime, Integer videoId, Integer likeNum, Integer commentType, Integer commentStatus) {
		this.commentContext = commentContext;
		this.userId = userId;
		this.commentTime = commentTime;
		this.videoId = videoId;
		this.likeNum = likeNum;
		this.commentType = commentType;
		this.commentStatus = commentStatus;
	}
}
