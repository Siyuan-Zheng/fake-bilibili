package me.shigure.beans;

import lombok.*;

import java.io.Serializable;

/**
 * @author zhengsiyuan
 * @date 2019-02-18 17:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MessageBean implements Serializable {

	private Integer messageId;
	private Integer sendUserId;
	private Integer receiveUserId;
	private String messageContext;
	private Integer messageStatus;
	private Long messageTime;

}
