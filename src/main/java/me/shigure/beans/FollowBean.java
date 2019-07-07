package me.shigure.beans;

import lombok.*;

import java.io.Serializable;

/**
 * @author zhengsiyuan
 * @date 2019-02-18 17:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FollowBean implements Serializable {

	private Integer followId;
	private Integer userId;
	private String followedUser;
	private String follower;

}
