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
		props.setProperty("mail.transport.protocol", "smtp");//ʵ��Э�飺smtp
		props.setProperty("mail.smtp.host", "smtp.qq.com");//Э���ַ
		props.setProperty("mail.smtp.port", "465");//Э��˿�
		props.setProperty("mail.smtp.auth", "true");//��Ҫ��Ȩ
		//QQ:SSL��֤
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		
		Session session = Session.getInstance(props);
		//session.setDebug(true);
		
		//�����ʼ�
		MimeMessage message = createMimeMessage(session, "3202648312@qq.com","3202648312@qq.com", "3202648312@qq.com", "3202648312@qq.com");
		//MimeMessage message =null;
		Transport transport = session.getTransport();//�������Ӷ���
		transport.connect("3202648312@qq.com","hkuojbzcojfhdggg");//�������ӣ�������������Ȩ�����ʽ����
		transport.sendMessage(message, message.getAllRecipients());
		
		transport.close();
	}
	
	//MimeMessage:�ʼ�
	public static MimeMessage createMimeMessage(Session session,String send,String receive, String cReceive, String mReceive) throws Exception {
		MimeMessage message = new MimeMessage(session);
		//�ʼ������⡢���ġ��ռ��ˡ�������		{������ͼƬ}
		Address address = new InternetAddress(send,"����������","utf-8");
		message.setFrom(address);
		message.setSubject("���Ǳ���","utf-8");
		message.setContent("����....����....","text/html; charset=UTF-8");
		//�ռ������ͣ�TO��ͨ�ռ��ˡ�CC���͡�BCC����
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receive,"�ռ���A","utf-8"));
		message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress(cReceive,"������B","utf-8"));
		message.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress(mReceive,"������C","utf-8"));
		
		message.setSentDate(new Date());//�ʼ�����ʱ��
		message.saveChanges();//�����ʼ�
		return message;
	}
}
