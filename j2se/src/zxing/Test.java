package zxing;

import java.io.File;
import java.io.IOException;

import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;

public class Test {
	public static void main(String[] args) throws IOException, WriterException, NotFoundException {
		
		//生成二维码
		/*
		 * 生成图片的路径         src/二维码.png
		 * 文字信息、网址信息：""https://www.baidu.com"
		 */
		
		String imgPath = "src/二维码.gif";
		String content = "https://www.baidu.com";
		String logo = "src/logo.png";
		
		/*
		 * 加密： 文字信息->二维码
		 * 解密：二维码->文字信息
		 */
		
		//加密： 文字信息->二维码
		ZXingUtil.encodeImg(imgPath, "gif", content, 430, 430,logo);
		
		
		//解密：二维码->文字信息
		ZXingUtil.decodeImg(new File(imgPath));
	}
}
