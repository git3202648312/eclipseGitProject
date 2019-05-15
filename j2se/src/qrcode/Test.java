package qrcode;

import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException {
		//���ɶ�ά��
		/*
		 * ����ͼƬ��·��         src/��ά��.png
		 * ������Ϣ����ַ��Ϣ��""https://www.baidu.com"
		 */
		String imgPath = "src/��ά��.png";
		String content = "https://www.baidu.com";
		
		/*
		 * ���ܣ� ������Ϣ->��ά��
		 * ���ܣ���ά��->������Ϣ
		 */
		
		//���ܣ� ������Ϣ->��ά��
		QRcodeUtil qrUtil = new QRcodeUtil();
		qrUtil.encoderQRcode(content, imgPath, "png", 15);
		
		//���ܣ���ά��->������Ϣ
		String imgContent = qrUtil.decoderQRCode(imgPath);
		System.out.println(imgContent);
	}
}
