package me.shigure.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author zhengsiyuan
 * @date 2019-03-04 11:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ImageBean implements Serializable {

	private Integer imageId;
	private String imagePath;

}
