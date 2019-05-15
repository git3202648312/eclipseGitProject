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
	 * 生成图片的路径         src/二维码.png
	 * 文字信息、网址信息："helloworld"
	 */
	
	//加密： 文字信息->二维码
	public void encoderQRcode(String content,String imgPath,String imgType,int size) throws IOException {
		
		BufferedImage bufImg = qRcodeCommon(content, imgType, size);
		
		File file = new File(imgPath);//src/二维码.png	-->二维码.png
		
		//API
		ImageIO.write(bufImg, imgType, file);
	}

	private BufferedImage qRcodeCommon(String content,String imgType,int size) throws IOException {
		BufferedImage bufImg = null;
		
		//Qrcode：字符串->boolean[][]
		Qrcode qrCodeHandler = new Qrcode();
		//设置二维码的排错率 		7% L、M、Q、H 30% 排错率越高，可存储信息越少，但是对二维码清晰度要求越小
		qrCodeHandler.setQrcodeErrorCorrect('M');
		//可存储信息类型：N:数字、A：数字+A-Z、B：所有
		qrCodeHandler.setQrcodeEncodeMode('B');
		//尺寸：取值范围：1-40
		qrCodeHandler.setQrcodeVersion(size);
		//"hello world"变成一个字节数组
		byte[] contentBytes = content.getBytes("utf-8");
		//-->boolean[][]
		boolean[][] codeOut = qrCodeHandler.calQrcode(contentBytes);
		
		int imgSize = 67 + 12*(size-1);
		
		bufImg = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
		Graphics2D gs = bufImg.createGraphics();
		gs.setBackground(Color.WHITE);//将画板背景颜色设置为白色
		gs.clearRect(0, 0, imgSize, imgSize);//初始化
		gs.setColor(Color.BLACK);//设置画板上图像的颜色（二维码的颜色）
		
		int pixoff = 2;
		
		for(int j=0;j<codeOut.length;j++) {
			for(int i=0;i<codeOut.length;i++) {
				if(codeOut[j][i]) {
					gs.fillRect(j*3+pixoff, i*3+pixoff, 3, 3);;
				}
			}
		}
		
		//增加logo
		Image logo = ImageIO.read(new File("src/logo.png"));
		
		//获取高度和宽度
		int maxHeight = bufImg.getHeight();
		int maxWidth = bufImg.getWidth();
		//在已生成的二维码上画Logo
		gs.drawImage(logo, imgSize/5*2, imgSize/5*2, maxWidth/5,maxHeight/5,null);
		
		gs.dispose();//释放空间
		bufImg.flush();//清理
		return bufImg;
	}
	
	//解密：二维码(图片路径)->文字信息
	public String decoderQRCode(String imgPath) throws IOException {
		
		//BufferedImage内存中的图片，硬盘中的imgPath图片-> 内存BufferedImage
		BufferedImage bufImg = ImageIO.read(new File(imgPath));
		//解密
		QRCodeDecoder decoder = new QRCodeDecoder();
		TwoDimensionCodeImage tdcImage = new TwoDimensionCodeImage(bufImg);
		byte[] bs = decoder.decode(tdcImage);//bufImg
		//byte[]->String
		String content = new String(bs,"utf-8");
		return content;
	}
}
