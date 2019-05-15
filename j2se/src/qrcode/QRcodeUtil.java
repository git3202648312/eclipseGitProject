package qrcode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

import jp.sourceforge.qrcode.QRCodeDecoder;

public class QRcodeUtil {
	
	/*
	 * ����ͼƬ��·��         src/��ά��.png
	 * ������Ϣ����ַ��Ϣ��"helloworld"
	 */
	
	//���ܣ� ������Ϣ->��ά��
	public void encoderQRcode(String content,String imgPath,String imgType,int size) throws IOException {
		
		BufferedImage bufImg = qRcodeCommon(content, imgType, size);
		
		File file = new File(imgPath);//src/��ά��.png	-->��ά��.png
		
		//API
		ImageIO.write(bufImg, imgType, file);
	}

	private BufferedImage qRcodeCommon(String content,String imgType,int size) throws IOException {
		BufferedImage bufImg = null;
		
		//Qrcode���ַ���->boolean[][]
		Qrcode qrCodeHandler = new Qrcode();
		//���ö�ά����Ŵ��� 		7% L��M��Q��H 30% �Ŵ���Խ�ߣ��ɴ洢��ϢԽ�٣����ǶԶ�ά��������Ҫ��ԽС
		qrCodeHandler.setQrcodeErrorCorrect('M');
		//�ɴ洢��Ϣ���ͣ�N:���֡�A������+A-Z��B������
		qrCodeHandler.setQrcodeEncodeMode('B');
		//�ߴ磺ȡֵ��Χ��1-40
		qrCodeHandler.setQrcodeVersion(size);
		//"hello world"���һ���ֽ�����
		byte[] contentBytes = content.getBytes("utf-8");
		//-->boolean[][]
		boolean[][] codeOut = qrCodeHandler.calQrcode(contentBytes);
		
		int imgSize = 67 + 12*(size-1);
		
		bufImg = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
		Graphics2D gs = bufImg.createGraphics();
		gs.setBackground(Color.WHITE);//�����屳����ɫ����Ϊ��ɫ
		gs.clearRect(0, 0, imgSize, imgSize);//��ʼ��
		gs.setColor(Color.BLACK);//���û�����ͼ�����ɫ����ά�����ɫ��
		
		int pixoff = 2;
		
		for(int j=0;j<codeOut.length;j++) {
			for(int i=0;i<codeOut.length;i++) {
				if(codeOut[j][i]) {
					gs.fillRect(j*3+pixoff, i*3+pixoff, 3, 3);;
				}
			}
		}
		
		//����logo
		Image logo = ImageIO.read(new File("src/logo.png"));
		
		//��ȡ�߶ȺͿ��
		int maxHeight = bufImg.getHeight();
		int maxWidth = bufImg.getWidth();
		//�������ɵĶ�ά���ϻ�Logo
		gs.drawImage(logo, imgSize/5*2, imgSize/5*2, maxWidth/5,maxHeight/5,null);
		
		gs.dispose();//�ͷſռ�
		bufImg.flush();//����
		return bufImg;
	}
	
	//���ܣ���ά��(ͼƬ·��)->������Ϣ
	public String decoderQRCode(String imgPath) throws IOException {
		
		//BufferedImage�ڴ��е�ͼƬ��Ӳ���е�imgPathͼƬ-> �ڴ�BufferedImage
		BufferedImage bufImg = ImageIO.read(new File(imgPath));
		//����
		QRCodeDecoder decoder = new QRCodeDecoder();
		TwoDimensionCodeImage tdcImage = new TwoDimensionCodeImage(bufImg);
		byte[] bs = decoder.decode(tdcImage);//bufImg
		//byte[]->String
		String content = new String(bs,"utf-8");
		return content;
	}
}
