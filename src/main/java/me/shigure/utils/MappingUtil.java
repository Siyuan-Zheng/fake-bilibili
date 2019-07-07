package me.shigure.utils;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhengsiyuan
 * @date 2019-04-13 16:08
 */
public class MappingUtil {

	public static void mapping(HttpServletRequest request, HttpServletResponse response, String url){
		TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
		WebContext context = new WebContext(request, response, request.getServletContext());
		response.setCharacterEncoding("utf-8");
		try {
			engine.process(url, context, response.getWriter());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
