package me.shigure.beans;

import lombok.*;

import java.io.Serializable;

/**
 * @author zhengsiyuan
 * @date 2019-02-18 17:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FavoriteBean implements Serializable {

	private Integer favoriteId;
	private Integer userId;
	private Integer videoId;
	private String favoriteTime;
	private String videoName;
	private String imagePath;
	private Integer videoPointNum;

}
