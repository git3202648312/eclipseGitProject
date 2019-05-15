package javamail;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailDemo {
	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");//实用协议：smtp
		props.setProperty("mail.smtp.host", "smtp.qq.com");//协议地址
		props.setProperty("mail.smtp.port", "465");//协议端口
		props.setProperty("mail.smtp.auth", "true");//需要授权
		//QQ:SSL认证
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		
		Session session = Session.getInstance(props);
		//session.setDebug(true);
		
		//创建邮件
		MimeMessage message = createMimeMessage(session, "3202648312@qq.com","3202648312@qq.com", "3202648312@qq.com", "3202648312@qq.com");
		//MimeMessage message =null;
		Transport transport = session.getTransport();//建立连接对象
		transport.connect("3202648312@qq.com","hkuojbzcojfhdggg");//建立连接，其中密码以授权码的形式体现
		transport.sendMessage(message, message.getAllRecipients());
		
		transport.close();
	}
	
	//MimeMessage:邮件
	public static MimeMessage createMimeMessage(Session session,String send,String receive, String cReceive, String mReceive) throws Exception {
		MimeMessage message = new MimeMessage(session);
		//邮件：标题、正文、收件人、发件人		{附件、图片}
		Address address = new InternetAddress(send,"发件人名字","utf-8");
		message.setFrom(address);
		message.setSubject("这是标题","utf-8");
		message.setContent("正文....内容....","text/html; charset=UTF-8");
		//收件人类型：TO普通收件人、CC抄送、BCC密送
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receive,"收件人A","utf-8"));
		message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress(cReceive,"抄送人B","utf-8"));
		message.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress(mReceive,"密送人C","utf-8"));
		
		message.setSentDate(new Date());//邮件发送时间
		message.saveChanges();//保存邮件
		return message;
	}
}
