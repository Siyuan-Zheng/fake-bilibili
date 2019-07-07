package me.shigure.utils;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author zhengsiyuan
 * @date 2019-04-13 15:20
 */
@WebListener
public class ThymeleafConfig implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		TemplateEngine engine = templateEngine(sce.getServletContext());
		TemplateEngineUtil.storeTemplateEngine(sce.getServletContext(), engine);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

	private TemplateEngine templateEngine(ServletContext servletContext) {
		TemplateEngine engine = new TemplateEngine();

		engine.setTemplateResolver(templateResolver(servletContext));
		return engine;
	}

	private ITemplateResolver templateResolver(ServletContext servletContext) {
		ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(servletContext);
		resolver.setCacheable(false);
		resolver.setPrefix("/");
		resolver.setCharacterEncoding("UTF-8");
		resolver.setTemplateMode(TemplateMode.HTML);
		return resolver;
	}

}
