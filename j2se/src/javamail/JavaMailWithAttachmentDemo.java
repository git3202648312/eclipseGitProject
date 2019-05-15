package javamail;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class JavaMailWithAttachmentDemo {
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
		transport.connect("3202648312@qq.com","fciofluqfynodeic");//建立连接，其中密码以授权码的形式体现
		Address[] addresses = message.getAllRecipients();
		
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	}
	
	//MimeMessage:邮件
	public static MimeMessage createMimeMessage(Session session,String send,String receive, String cReceive, String mReceive) throws Exception {
		MimeMessage message = new MimeMessage(session);
		//邮件：标题、正文、收件人、发件人		{附件、图片}
		Address address = new InternetAddress(send,"发件人名字","utf-8");
		message.setFrom(address);
		message.setSubject("这是标题(还有图片+附件)","utf-8");
		
		
		//创建图片节点
		MimeBodyPart imagePart = new MimeBodyPart();
		DataHandler imageDataHandler = new DataHandler(new FileDataSource("src/logo.png"));
		imagePart.setDataHandler(imageDataHandler);
		imagePart.setContentID("myLogoImage");
		
		//创建文本节点，目的是为了加载图片节点
		MimeBodyPart textPart = new MimeBodyPart();
		textPart.setContent("image:<img src='cid:myLogoImage'/>","text/html; charset=UTF-8");
		
		
		//将文本节点，图片节点-->组装
		MimeMultipart mm_text_image = new MimeMultipart();
		mm_text_image.addBodyPart(imagePart);
		mm_text_image.addBodyPart(textPart);
		mm_text_image.setSubType("related");//关联关系
		
		//正文中只能出现MimeBodyPart普通节点不能出现复合节点MimeMultipart
		//MimeMultipart-->MimeBodyPart
		MimeBodyPart text_image_bodyPart = new MimeBodyPart();
		text_image_bodyPart.setContent(mm_text_image);
		
		//图片->文本节点->复合节点->根据要求变成普通节点
		
		//附件
		MimeBodyPart attachment = new MimeBodyPart();
		DataHandler fileDataHandler = new DataHandler(new FileDataSource("src/logo.png"));
		attachment.setDataHandler(fileDataHandler);
		//给附件设置文件名
		attachment.setFileName(MimeUtility.encodeText(fileDataHandler.getName()));
		
		//将刚才处理好的"文本+图片"节点与附件设置成一个新的复合节点
		MimeMultipart mm = new MimeMultipart();
		mm.addBodyPart(text_image_bodyPart);
		mm.addBodyPart(attachment);
		mm.setSubType("mixed");//混合关系
		
		message.setContent(mm,"text/html; charset=UTF-8");
		
		//收件人类型：TO普通收件人、CC抄送、BCC密送
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receive,"收件人A","utf-8"));
		message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress(cReceive,"抄送人B","utf-8"));
		message.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress(mReceive,"密送人C","utf-8"));
		
		message.setSentDate(new Date());//邮件发送时间
		message.saveChanges();//保存邮件
		return message;
	}
}
