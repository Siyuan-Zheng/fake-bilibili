package me.shigure.filter;

import me.shigure.utils.GlobalConst;
import me.shigure.utils.MappingUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhengsiyuan
 * @date 2019-04-14 15:16
 */
@WebFilter(filterName = "Filter1", urlPatterns = {"/channelManageData","/dataUpload","/userManageData","/videoManageData"})
public class Filter1 implements Filter {
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		Object userInfo = request.getSession().getAttribute(GlobalConst.USER_SESSION_KEY);

		if (userInfo == null){
			System.out.println("filter1");

			response.setHeader("status", "noLogin");
			response.sendError(518, "permission denied");

			MappingUtil.mapping(request, response,"login/login.html");
		}else {
			chain.doFilter(req, resp);
		}	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

}
