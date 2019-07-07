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
public class ChannelBean implements Serializable {

	private Integer channelId;
	private String channelName;
	private String channelDesc;
	private Integer channelPicId;
	private String imagePath;
	private Integer channelStatus;

	public ChannelBean(Integer channelId, String channelName, String channelDesc, Integer channelPicId) {
		this.channelId = channelId;
		this.channelName = channelName;
		this.channelDesc = channelDesc;
		this.channelPicId = channelPicId;
	}

	public ChannelBean(String channelName, String channelDesc, Integer channelPicId) {
		this.channelName = channelName;
		this.channelDesc = channelDesc;
		this.channelPicId = channelPicId;
	}
}
