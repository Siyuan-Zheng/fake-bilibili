package me.shigure.servlet;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author zhengsiyuan
 * @date 2019-02-15 16:52
 */
public class BaseServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) {
		// 获得方法名称
		String methodName = request.getParameter("method");
		Method method;

		// 通过方法名和方法所需要的参数获得Method对象
		try {
			method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("调用的方法：" + methodName + "不存在", e);
		}

		// 通过Method对象调用方法
		try {
			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
