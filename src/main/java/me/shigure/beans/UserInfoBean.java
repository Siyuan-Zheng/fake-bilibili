package me.shigure.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author zhengsiyuan
 * @date 2019-03-06 14:52
 */
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoBean implements Serializable {

	private Integer userId;
	private String userName;
	private String password;
	private String mail;
	private String phoneNum;
	private String userDesc;
	private Integer userPicId;
	private Integer userRightId;
	private Integer userStatus;
	private Integer sex;
	private Integer levelId;
	private String imagePath;
	private String levelName;
	private String levelDesc;
	private String rightName;
	private String rightDesc;


	public UserInfoBean(String password, String mail, String phoneNum) {
		this.password = password;
		this.mail = mail;
		this.phoneNum = phoneNum;
	}

	public UserInfoBean(Integer userId, String userName, String userDesc, Integer sex) {
		this.userId = userId;
		this.userName = userName;
		this.userDesc = userDesc;
		this.sex = sex;
	}

	public UserInfoBean(String userName, String password, String mail, String phoneNum, Integer userRightId, Integer userStatus, Integer levelId) {
		this.userName = userName;
		this.password = password;
		this.mail = mail;
		this.phoneNum = phoneNum;
		this.userRightId = userRightId;
		this.userStatus = userStatus;
		this.levelId = levelId;
	}
}
