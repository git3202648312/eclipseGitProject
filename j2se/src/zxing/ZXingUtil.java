package zxing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import jp.sourceforge.qrcode.util.Color;

public class ZXingUtil {

	//加密： 文字信息->二维码
	public static void encodeImg(String imgPath,String format, String content, int width, int height,String logo) throws IOException, WriterException {
		
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType,Object>();
		//排错率	L<M<Q<H
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
		//编码
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		//外边距：margin
		hints.put(EncodeHintType.MARGIN, 1);
		/*
		 *content：需要加密的内容
		 * BarcodeFormat.QR_CODE：要解析的类型（二维码）
		 */
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height,hints);
		
		//内存中的一张图片：此时需要的图片是二维码--> 需要一个boolean[][] -->BitMatrix
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for(int x=0;x<width;x++) {
			for(int y=0;y<height;y++) {
				//int a = bitMatrix.get(x, y)?Color.BLACK:Color.WHITE;
				img.setRGB(x, y, (bitMatrix.get(x, y)?Color.BLACK:Color.WHITE));
			}
		}
		
		
		File file = new File(imgPath);
		//画logo
		img = LogoUtil.logoMatrix(img, logo);
		//生成图片
		ImageIO.write(img, format, file);
		
	}
	
	//解密：二维码->文字信息
	public static void decodeImg(File file) throws NotFoundException, IOException {
		if(!file.exists()) {
			return ;
		}
		//file->内存中的一张图片
		BufferedImage img = ImageIO.read(file);
		MultiFormatReader formatReader = new MultiFormatReader();
		BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(img);
		Binarizer binarizer = new HybridBinarizer(source);
		BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
		//图片->result
		Map map = new HashMap();
		map.put(EncodeHintType.CHARACTER_SET, "utf-8");
		Result result = formatReader.decode(binaryBitmap,map);
		System.out.println("解析结果"+result.toString());
	}
}
