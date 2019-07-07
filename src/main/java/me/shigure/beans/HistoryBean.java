package me.shigure.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhengsiyuan
 * @date 2019-03-07 09:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryBean {

	private Integer historyId;
	private Integer videoId;
	private Integer userId;
	private String historyTime;
	private String videoName;
	private String imagePath;
	private Integer videoPointNum;

}
