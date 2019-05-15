package file.split;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class FileSplit {
	public static void main(String[] args) throws Exception {
		File resFile = new File("D:\\workspace\\major\\1609302�� �˺�.mp4");
		File splitDir = new File("C:/Users/�˺�/Desktop/split");
		//���ʹ�ô����� 5
		if(hasRemainingTries()) {
			//����ļ�
			splitFile(resFile,splitDir);
		}else {
			System.out.println("���ô����ѵ�");
		}
	}

	//�ж��Ƿ������ô���
	//˼·�� ����ǰʹ�õĴ���������Ӳ��
	private static boolean hasRemainingTries() throws FileNotFoundException, IOException {
		Properties prop =new Properties();
		int count = 0;
		//ÿʹ��һ�Σ�1.�Ȼ�ȡ֮ǰ���˼���   2.�ٽ�֮ǰ��������1
		
		//��ѯ����֮ǰ�Ѿ����˼���
		prop.load(new FileInputStream("C:/Users/�˺�/Desktop/split/tries.properties"));
		String times = prop.getProperty("times");
		//
		if(times==null) {
			count = 1;
			prop.setProperty("times", count+"");
		}else {
			int timeCount = Integer.parseInt(times);
			timeCount++;
			prop.setProperty("times", timeCount+"");
			if(timeCount>5) {
				return false;
			}
		}
		prop.store(new FileOutputStream("C:/Users/�˺�/Desktop/split/tries.properties"), "try time....");
		
		return true;
	}

	private static void splitFile(File resFile, File splitDir) throws Exception {
		if(!splitDir.exists()) {
			splitDir.mkdirs();
		}
		
		//˼·����֣�1��������   n�������
		//�ϲ���n��������   1�������(ע��˳��)
		InputStream in = new FileInputStream(resFile);
		
		OutputStream out = null;
		
		byte[] buf = new byte[1024*1024*10];
		int len = -1;
		int count = 1;
		while((len=in.read(buf))!=-1) {
			out = new FileOutputStream(new File(splitDir,count++ +".mp4"));
			out.write(buf, 0, len);
			out.close();
			//out.flush();
		}
		
		//��ֵ�ʱ����ν��ļ������ָ���������棬Ϊ�����ϲ���׼��
		//������һ�������ļ�9.config ��������������Ϣ
		out = new FileOutputStream(new File(splitDir,"conf.properties"));
		
//		//��ʽһ��
//		//��ѯ��ǰ����ϵͳ�Ļ��з�
//		String lineSeparator = System.getProperty("line.separator");
//		out.write(("filename="+resFile.getName()).getBytes());
//		out.write(lineSeparator.getBytes());
//		out.write(("partcount="+(count-1)).getBytes());
//		out.close();
		
		//��ʽ����Properties,���ڴ��ж��������key=value����ʽд��Ӳ����
		Properties prop = new Properties();
		prop.setProperty("filename", resFile.getName());
		prop.setProperty("partcount", (count-1)+"");
		//д��Ӳ��(���棺�־û�)
		prop.store(out, "file configuration...");
		in.close();
	}
}
