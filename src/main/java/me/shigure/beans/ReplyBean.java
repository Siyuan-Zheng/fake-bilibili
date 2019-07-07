package me.shigure.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author shigure
 * @date 2019-04-24 14:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReplyBean implements Serializable {

	private Integer replyId;
	private Integer toCommentId;
	private String replyContent;
	private Integer replyType;
	private Integer fromUserId;
	private Integer toUserId;
	private String replyTime;
	private Integer replyLikeNum;
	private Integer replyStatus;

	private String userName;
	private String imagePath;
	private Integer levelId;

	private String toUserName;

	public ReplyBean(Integer toCommentId, String replyContent, Integer replyType, Integer fromUserId, Integer toUserId, String replyTime, Integer replyLikeNum) {
		this.toCommentId = toCommentId;
		this.replyContent = replyContent;
		this.replyType = replyType;
		this.fromUserId = fromUserId;
		this.toUserId = toUserId;
		this.replyTime = replyTime;
		this.replyLikeNum = replyLikeNum;
	}
}
