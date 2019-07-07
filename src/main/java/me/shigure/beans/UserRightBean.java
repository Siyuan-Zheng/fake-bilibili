package me.shigure.beans;

import lombok.*;

import java.io.Serializable;

/**
 * @author zhengsiyuan
 * @date 2019-02-18 17:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRightBean implements Serializable {

	private Integer rightId;
	private String rightName;
	private String rightDesc;

}
