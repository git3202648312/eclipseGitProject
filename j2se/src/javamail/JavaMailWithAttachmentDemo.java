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
		transport.connect("3202648312@qq.com","fciofluqfynodeic");//�������ӣ�������������Ȩ�����ʽ����
		Address[] addresses = message.getAllRecipients();
		
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	}
	
	//MimeMessage:�ʼ�
	public static MimeMessage createMimeMessage(Session session,String send,String receive, String cReceive, String mReceive) throws Exception {
		MimeMessage message = new MimeMessage(session);
		//�ʼ������⡢���ġ��ռ��ˡ�������		{������ͼƬ}
		Address address = new InternetAddress(send,"����������","utf-8");
		message.setFrom(address);
		message.setSubject("���Ǳ���(����ͼƬ+����)","utf-8");
		
		
		//����ͼƬ�ڵ�
		MimeBodyPart imagePart = new MimeBodyPart();
		DataHandler imageDataHandler = new DataHandler(new FileDataSource("src/logo.png"));
		imagePart.setDataHandler(imageDataHandler);
		imagePart.setContentID("myLogoImage");
		
		//�����ı��ڵ㣬Ŀ����Ϊ�˼���ͼƬ�ڵ�
		MimeBodyPart textPart = new MimeBodyPart();
		textPart.setContent("image:<img src='cid:myLogoImage'/>","text/html; charset=UTF-8");
		
		
		//���ı��ڵ㣬ͼƬ�ڵ�-->��װ
		MimeMultipart mm_text_image = new MimeMultipart();
		mm_text_image.addBodyPart(imagePart);
		mm_text_image.addBodyPart(textPart);
		mm_text_image.setSubType("related");//������ϵ
		
		//������ֻ�ܳ���MimeBodyPart��ͨ�ڵ㲻�ܳ��ָ��Ͻڵ�MimeMultipart
		//MimeMultipart-->MimeBodyPart
		MimeBodyPart text_image_bodyPart = new MimeBodyPart();
		text_image_bodyPart.setContent(mm_text_image);
		
		//ͼƬ->�ı��ڵ�->���Ͻڵ�->����Ҫ������ͨ�ڵ�
		
		//����
		MimeBodyPart attachment = new MimeBodyPart();
		DataHandler fileDataHandler = new DataHandler(new FileDataSource("src/logo.png"));
		attachment.setDataHandler(fileDataHandler);
		//�����������ļ���
		attachment.setFileName(MimeUtility.encodeText(fileDataHandler.getName()));
		
		//���ղŴ���õ�"�ı�+ͼƬ"�ڵ��븽�����ó�һ���µĸ��Ͻڵ�
		MimeMultipart mm = new MimeMultipart();
		mm.addBodyPart(text_image_bodyPart);
		mm.addBodyPart(attachment);
		mm.setSubType("mixed");//��Ϲ�ϵ
		
		message.setContent(mm,"text/html; charset=UTF-8");
		
		//�ռ������ͣ�TO��ͨ�ռ��ˡ�CC���͡�BCC����
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receive,"�ռ���A","utf-8"));
		message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress(cReceive,"������B","utf-8"));
		message.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress(mReceive,"������C","utf-8"));
		
		message.setSentDate(new Date());//�ʼ�����ʱ��
		message.saveChanges();//�����ʼ�
		return message;
	}
}
