package me.shigure.utils;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * @author shigure
 * @date 2019-04-22 15:15
 */
public class MailUtil {

	public static void sendMail(String email, String emailMsg, int code)
			throws AddressException, MessagingException, UnsupportedEncodingException {
		// 1.创建一个程序与邮件服务器会话对象 Session

		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "SMTP");
		props.setProperty("mail.host", "smtp.ym.163.com");
		// 指定验证为true
		props.setProperty("mail.smtp.auth", "true");

		// 创建验证器
		Authenticator auth = new Authenticator() {
			@Override
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("admin@shigure.me", "zhengsiyuan4399");
			}
		};

		Session session = Session.getInstance(props, auth);

		// 2.创建一个Message，它相当于是邮件内容
		Message message = new MimeMessage(session);
		// 设置发送者
		message.setFrom(new InternetAddress("admin@shigure.me","枯木短视频","UTF-8"));
		// 设置发送方式与接收者
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email));

		message.setSubject(code+" 是你的验证码");

		message.setContent(emailMsg, "text/html;charset=utf-8");

		// 3.创建 Transport用于将邮件发送

		Transport.send(message);
	}
}
