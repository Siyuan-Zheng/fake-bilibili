package me.shigure.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author zhengsiyuan
 * @date 2019-05-16 16:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BannerBean {

	private Integer bannerId;
	private Integer bannerImageId;
	private String bannerTitle;
	private String bannerUrl;

	private String bannerImagePath;

}
