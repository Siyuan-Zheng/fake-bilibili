package me.shigure.filter;

import io.leopard.web.servlet.CookieUtil;
import me.shigure.utils.GlobalConst;
import me.shigure.utils.MappingUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhengsiyuan
 * @date 2019-04-13 15:05
 */
@WebFilter(filterName = "Filter2", urlPatterns = {"/home", "/setting", "/channelAdd", "/channelDel", "/channelEdit", "/channelManage", "/channelView","/doUpload","/uploadFrame","/personalCenter","/personalFavorite","/personalHistory","/userAddWin","/userBanWin","/userDelWin","/userManage","/videoBanWin","/videoDelWin","/videoManage"})
public class Filter2 implements Filter {
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

		HttpServletRequest request = (HttpServletRequest) req;

		Object userInfo = request.getSession().getAttribute(GlobalConst.USER_SESSION_KEY);

		Cookie[] cookies = ((HttpServletRequest) req).getCookies();
		String cookie = CookieUtil.getCookie("user_cookie",request);

		if (userInfo == null){
			System.out.println("filter2");
			MappingUtil.mapping(request, (HttpServletResponse) resp,"login/login.html");
		}else {
			chain.doFilter(req, resp);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

}
