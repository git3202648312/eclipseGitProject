package zxing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.RoundRectangle2D.Float;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LogoUtil {
	//����logo����ά��-->����ά���logo
	public static BufferedImage logoMatrix(BufferedImage matrixImage,String logo) throws IOException {
		//�ڶ�ά���ϻ�logo������һ�� ��ά�뻭��
		Graphics2D g2 = matrixImage.createGraphics();
		//��logo��String->BufferedImage(�ڴ�)
		BufferedImage logoImg = ImageIO.read(new File(logo));
		int height = matrixImage.getHeight();
		int width = matrixImage.getWidth();
		
		g2.drawImage(logoImg, width*2/5, height*2/5, width/5, height/5, null);
		//����һ������ɫԲ�������εĻ���
		BasicStroke stroke = new BasicStroke(5,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
		//������-���ʹ���
		g2.setStroke(stroke);
		//����һ��������
		RoundRectangle2D.Float round = new RoundRectangle2D.Float(width*2/5, height*2/5, width/5, height/5,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
		g2.setColor(Color.WHITE);
		g2.draw(round);
		
		//����ɫ�߿�
		//����һ������ɫԲ�������εĻ���
		BasicStroke stroke1 = new BasicStroke(1,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
		//������-���ʹ���
		g2.setStroke(stroke1);
		//����һ��������
		RoundRectangle2D.Float round1 = new RoundRectangle2D.Float(width*2/5+2, height*2/5+2, width/5-4, height/5-4,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
		g2.setColor(Color.GRAY);
		g2.draw(round1);
		
		g2.dispose();
		matrixImage.flush();
		return matrixImage;
	}
}
