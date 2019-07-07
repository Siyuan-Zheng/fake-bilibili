package me.shigure.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author zhengsiyuan
 * @date 2019-03-04 10:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LevelBean implements Serializable {

	private Integer levelId;
	private String levelName;
	private String levelDesc;

}
