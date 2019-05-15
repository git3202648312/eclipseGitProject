package file.split;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;


public class MergeFile {
	public static void main(String[] args) throws Exception {
		
		/*����һ:
		 * List<FileInputStream> inputs = new ArrayList();
		for(int i=1;i<9;i++) {
			inputs.add(new FileInputStream("C:\\Users\\�˺�\\Desktop\\split\\"+i+".mp4"));
		}
		//ָ���ϲ�����ļ������
		OutputStream out = new FileOutputStream("C:\\Users\\�˺�\\Desktop\\split\\study.mp4");
		
		//��������������ζ����ڴ棬�����һ���������study.mp4
		byte[] buf = new byte[1024*1024*10];
		for (FileInputStream in:inputs) {
			int len = in.read(buf);
			out.write(buf, 0, len);
		}
		out.close();
		for(FileInputStream in:inputs) {
			in.close();
		}*/
		
		//��������
		//ָ����ֺ���ļ�λ��
		File splitDir = new File("C:\\Users\\�˺�\\Desktop\\split");
		
		mergeFile(splitDir);
	}

	private static Properties getProperties() throws FileNotFoundException, IOException {
		String configFileName = "C:\\Users\\�˺�\\Desktop\\split\\conf.properties";
		Properties prop = new Properties();
		prop.load(new FileInputStream(configFileName));
		return prop;
	}
	
	private static void mergeFile(File splitDir) throws Exception {
		//�ϲ�֮ǰ����ȡ������Ϣ
		Properties prop = getProperties();
		String fileName = prop.getProperty("filename");
		int partCount = Integer.parseInt(prop.getProperty("partcount"));
		
		List<FileInputStream> inputs = new ArrayList();
		for(int i=1;i<=partCount;i++) {
			inputs.add(new FileInputStream("C:\\Users\\�˺�\\Desktop\\split\\"+i+".mp4"));
			
		}
		Enumeration<FileInputStream> en = Collections.enumeration(inputs);
		//�����-> 1����
		SequenceInputStream sin = new SequenceInputStream(en);
		//ָ���ϲ�����ļ������
		OutputStream out = new FileOutputStream("C:\\Users\\�˺�\\Desktop\\split\\"+fileName);
		byte[] buf = new byte[1024*1024*10];
		int len = -1;
		while((len=sin.read(buf))!=-1) {
			out.write(buf, 0, len);
		}
		out.close();
		sin.close();
	}

}
