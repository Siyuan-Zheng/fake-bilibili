package me.shigure.filter;

import me.shigure.beans.UserInfoBean;
import me.shigure.utils.GlobalConst;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhengsiyuan
 * @date 2019-04-14 15:36
 */
@WebFilter(filterName = "Filter3", urlPatterns = {"/channelManageData","/userManageData","/videoManageData","/userAddWin","/userBanWin","/userDelWin","/userManage","/videoBanWin","/videoDelWin","/videoManage","/channelAdd", "/channelDel", "/channelEdit", "/channelManage", "/channelView"})
public class Filter3 implements Filter {
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		Object userInfo = request.getSession().getAttribute(GlobalConst.USER_SESSION_KEY);

		UserInfoBean userInfoBean = (UserInfoBean) userInfo;

		int userRight = userInfoBean.getUserRightId();

		if (userRight != 0){
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().println("<span>此页面仅限管理员用户,是管理员？<a href='login' style='text-decoration:none;'>去登陆></a></span>");
		}else {
			chain.doFilter(req, resp);
		}

	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

}
