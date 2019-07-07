package me.shigure.utils.validate;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author zhengsiyuan
 */

public class Streams {
	/**
	 * 关闭输入流
	 * 
	 * @param in 输入流
	 */
	public static void close(InputStream in) {
		if (in != null) {
			try {
				in.close();
			} catch (IOException ioException) {
				// ignore
			}
		}
	}

	/**
	 * 关闭输出流
	 * 
	 * @param out 输出流
	 */
	public static void close(OutputStream out) {
		if (out != null) {
			try {
				out.flush();
			} catch (IOException ioException) {
				// ignore
			}
			try {
				out.close();
			} catch (IOException ioException) {
				// ignore
			}
		}
	}
}
