package me.shigure.utils;

import org.thymeleaf.TemplateEngine;

import javax.servlet.ServletContext;

/**
 * @author zhengsiyuan
 * @date 2019-04-13 15:20
 */
class TemplateEngineUtil {
	private static final String TEMPLATE_ENGINE_ATTR = "thymeleaf3.TemplateEngineInstance";
	static void storeTemplateEngine(ServletContext context, TemplateEngine engine) {
		context.setAttribute(TEMPLATE_ENGINE_ATTR, engine);
	}
	static TemplateEngine getTemplateEngine(ServletContext context) {
		return (TemplateEngine) context.getAttribute(TEMPLATE_ENGINE_ATTR);
	}
}
