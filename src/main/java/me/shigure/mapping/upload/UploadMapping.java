package me.shigure.mapping.upload;

import me.shigure.utils.MappingUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhengsiyuan
 * @date 2019-04-13 15:21
 */
@WebServlet(name = "UploadMapping", urlPatterns = "/doUpload")
public class UploadMapping extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MappingUtil.mapping(request,response,"upload/upload.html");
	}

}
