package me.shigure.servlet;

import com.alibaba.fastjson.JSONObject;
import me.shigure.utils.ServletUtil;
import me.shigure.utils.UploadUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * @author zhengsiyuan
 * @date 2019-04-07 16:52
 */
@MultipartConfig
@WebServlet(name = "UploadServlet", urlPatterns = "/dataUpload")
public class UploadServlet extends BaseServlet{

	public void uploadVideo(HttpServletRequest request, HttpServletResponse response){
		System.out.println("请求----uploadVideo");

		JSONObject jsonObject = new JSONObject();

		try {
			final Part videoPart = request.getPart("video");
			final Part imagePart = request.getPart("image");
			String realPath = this.getServletContext().getRealPath("/static/upload/");

			ServletUtil.writeResponse(response, UploadUtil.uploadVideo(videoPart,imagePart,realPath).toJSONString());

		} catch (IOException | ServletException e) {
			e.printStackTrace();
			jsonObject.put("msg", "error");
			ServletUtil.writeResponse(response, jsonObject.toJSONString());
		}
	}

	public void uploadImage(HttpServletRequest request, HttpServletResponse response){
		System.out.println("请求----uploadImage");
		JSONObject jsonObject = new JSONObject();

		try {
			final Part imagePart = request.getPart("image");
			String realPath = this.getServletContext().getRealPath("/static/upload/");
			ServletUtil.writeResponse(response, UploadUtil.uploadImage(imagePart,realPath).toJSONString());

		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
	}

}
