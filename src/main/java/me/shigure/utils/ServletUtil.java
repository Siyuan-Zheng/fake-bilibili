package me.shigure.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author zhengsiyuan
 * @date 2019-02-19 14:46
 */
public class ServletUtil {

	public static void printRequest(HttpServletRequest request){
		Map<String, String[]> map = request.getParameterMap();
		Set<Map.Entry<String, String[]>> keys = map.entrySet();
		Iterator<Map.Entry<String, String[]>> i = keys.iterator();
		System.out.print("+__+");
		while (i.hasNext()){
			Map.Entry<String, String[]> it = i.next();
			System.out.println(it.getKey()+":"+ Arrays.toString(it.getValue()));
		}
	}

	public static void writeResponse(HttpServletResponse response, String message){
		response.setCharacterEncoding("UTF-8");
		PrintWriter printWriter;
		try {
			printWriter = response.getWriter();
			printWriter.append(message);
			printWriter.flush();
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
