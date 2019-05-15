package qrcode;

import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException {
		//生成二维码
		/*
		 * 生成图片的路径         src/二维码.png
		 * 文字信息、网址信息：""https://www.baidu.com"
		 */
		String imgPath = "src/二维码.png";
		String content = "https://www.baidu.com";
		
		/*
		 * 加密： 文字信息->二维码
		 * 解密：二维码->文字信息
		 */
		
		//加密： 文字信息->二维码
		QRcodeUtil qrUtil = new QRcodeUtil();
		qrUtil.encoderQRcode(content, imgPath, "png", 15);
		
		//解密：二维码->文字信息
		String imgContent = qrUtil.decoderQRCode(imgPath);
		System.out.println(imgContent);
	}
}
