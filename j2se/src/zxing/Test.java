package zxing;

import java.io.File;
import java.io.IOException;

import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;

public class Test {
	public static void main(String[] args) throws IOException, WriterException, NotFoundException {
		
		//���ɶ�ά��
		/*
		 * ����ͼƬ��·��         src/��ά��.png
		 * ������Ϣ����ַ��Ϣ��""https://www.baidu.com"
		 */
		
		String imgPath = "src/��ά��.gif";
		String content = "https://www.baidu.com";
		String logo = "src/logo.png";
		
		/*
		 * ���ܣ� ������Ϣ->��ά��
		 * ���ܣ���ά��->������Ϣ
		 */
		
		//���ܣ� ������Ϣ->��ά��
		ZXingUtil.encodeImg(imgPath, "gif", content, 430, 430,logo);
		
		
		//���ܣ���ά��->������Ϣ
		ZXingUtil.decodeImg(new File(imgPath));
	}
}
