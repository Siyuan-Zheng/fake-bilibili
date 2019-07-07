package me.shigure.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author zhengsiyuan
 * @date 2019-04-26 15:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DynamicBean implements Serializable {

	private Integer dynamicId;
	private String dynamicContent;
	private Integer dynamicType;
	private Integer videoId;
	private Integer userId;
	private String dynamicTime;
	private Integer dynamicLikeNum;
	private Integer dynamicCommentNum;
	private Integer dynamicStatus;

	private String videoImagePath;
	private String userImagePath;
	private String videoUserImagePath;
	private String userName;
	private String videoName;
	private String videoDesc;
	private Integer videoUserId;
	private String videoUserName;
	private Integer videoPointNum;
	private Integer videoCommentNum;


	public DynamicBean(String dynamicContent, Integer dynamicType, Integer videoId, Integer userId, String dynamicTime, Integer dynamicLikeNum, Integer dynamicCommentNum, Integer dynamicStatus) {
		this.dynamicContent = dynamicContent;
		this.dynamicType = dynamicType;
		this.videoId = videoId;
		this.userId = userId;
		this.dynamicTime = dynamicTime;
		this.dynamicLikeNum = dynamicLikeNum;
		this.dynamicCommentNum = dynamicCommentNum;
		this.dynamicStatus = dynamicStatus;
	}
}
