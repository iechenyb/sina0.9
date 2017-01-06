package com.cyb.javamail;

import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;
//http://blog.csdn.net/frankcheng5143/article/details/50436207
public class JavaMailTest {
	public static void main(String[] args) throws MessagingException, GeneralSecurityException {
		Properties props = new Properties();

		// 开启debug调试
		props.setProperty("mail.debug", "true");
		// 发送服务器需要身份验证
		props.setProperty("mail.smtp.auth", "true");
		// 设置邮件服务器主机名
		props.setProperty("mail.host", "smtp.qq.com");//smtp.163.com 
		props.setProperty("mail.smtp.port", "465");
		// 发送邮件协议名称
		props.setProperty("mail.transport.protocol", "smtp");
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.socketFactory", sf);
		// 设置环境信息
		Session session = Session.getInstance(props);

		// 创建邮件对象
		Message msg = new MimeMessage(session);
		msg.setSubject("JavaMail测试");
		// 设置邮件内容
		msg.setText("这是一封由JavaMail发送的邮件！");
		// 设置发件人
		msg.setFrom(new InternetAddress("1048417686@qq.com"));

		Transport transport = session.getTransport();
		// 连接邮件服务器
		transport.connect("1048417686", "zhpojqcdqzjfbdjc");
		// 发送邮件
		transport.sendMessage(msg, new Address[] { new InternetAddress(
				"383065059@qq.com") });
		// 关闭连接
		transport.close();
	}
}
