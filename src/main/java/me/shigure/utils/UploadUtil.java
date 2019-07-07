package me.shigure.utils;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author zhengsiyuan
 * @date 2019-04-08 14:37
 */
public class UploadUtil {

	public static JSONObject uploadVideo(Part videoPart, Part imagePart, String realPath) throws IOException {

		String fileName = UUID.randomUUID().toString();

		JSONObject jsonObject = new JSONObject();

		String videoName = videoPart.getSubmittedFileName();
		String imageName = imagePart.getSubmittedFileName();

		String videoSuffix = videoName.substring(videoName.lastIndexOf(".")).toLowerCase();
		String imageSuffix = imageName.substring(imageName.lastIndexOf(".")).toLowerCase();

		if (videoPart == null || imagePart == null) {
			jsonObject.put("msg", "blank");
			return jsonObject;
		}

		String videoPath = realPath + "video/";
		String imagePath = realPath + "image/";

		File dir = new File(videoPath);
		File dir1 = new File(imagePath);
		if(!dir.exists()){
			if (!dir.mkdir()){
				jsonObject.put("msg", "error");
				return jsonObject;
			}
		}
		if(!dir1.exists()){
			if (!dir.mkdir()){
				jsonObject.put("msg", "error");
				return jsonObject;
			}
		}
		videoPart.write(videoPath + fileName + videoSuffix);
		imagePart.write(imagePath + fileName + imageSuffix);

		jsonObject.put("msg", "ok");
		jsonObject.put("videoPath", "/static/upload/video/" + fileName + "mp4");
		jsonObject.put("imagePath", "/static/upload/image/" + fileName + imageSuffix);
		return jsonObject;
	}

	public static JSONObject uploadImage(Part imagePart, String realPath) throws IOException {

		String fileName = UUID.randomUUID().toString();

		JSONObject jsonObject = new JSONObject();

		String imageName = imagePart.getSubmittedFileName();

		String imageSuffix = imageName.substring(imageName.lastIndexOf(".")).toLowerCase();

		if (imagePart == null) {
			jsonObject.put("msg", "blank");
			return jsonObject;
		}

		String imagePath = realPath + "image/";

		File dir = new File(imagePath);

		if(!dir.exists()){
			if (!dir.mkdir()){
				jsonObject.put("msg", "error");
				return jsonObject;
			}
		}
		imagePart.write(imagePath + fileName + imageSuffix);

		jsonObject.put("msg", "ok");
		jsonObject.put("imagePath", "/static/upload/image/" + fileName + imageSuffix);
		return jsonObject;
	}

}
