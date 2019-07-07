package me.shigure.utils;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

/**
 * @author shigure
 * @date 2019-04-22 14:00
 */
public class CodeUtil {

	public static int createCode(){
		return (int)((Math.random()*9+1)*10000);
	}

	public static boolean sendCodeByMail(int code, String target){
		StringBuilder content = new StringBuilder();
		content.append("<html><head></head>");
		content.append("<body><div><h2>确认你的邮箱地址</h2>亲爱的用户:您好! 您正在进行邮箱验证，请在验证码输入框中输入此次验证码以完成验证（5分钟内有效)：<br><h2>").append(code).append("</h2> <br>如非本人操作，请忽略此邮件,由此给您带来的不便请谅解!</div>");
		content.append("<div><span style ='float: right;'>枯木短视频</span></div>");
		content.append("</body></html>");
		try {
			MailUtil.sendMail(target, content.toString(), code);
		} catch (MessagingException | UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean sendCodeByPhone(int code, String target){

		return true;
	}

}
