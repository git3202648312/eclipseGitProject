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
		
		/*方法一:
		 * List<FileInputStream> inputs = new ArrayList();
		for(int i=1;i<9;i++) {
			inputs.add(new FileInputStream("C:\\Users\\顾海\\Desktop\\split\\"+i+".mp4"));
		}
		//指定合并后的文件输出流
		OutputStream out = new FileOutputStream("C:\\Users\\顾海\\Desktop\\split\\study.mp4");
		
		//将多个输入流依次读入内存，最后在一次性输出到study.mp4
		byte[] buf = new byte[1024*1024*10];
		for (FileInputStream in:inputs) {
			int len = in.read(buf);
			out.write(buf, 0, len);
		}
		out.close();
		for(FileInputStream in:inputs) {
			in.close();
		}*/
		
		//方法二：
		//指定拆分后的文件位置
		File splitDir = new File("C:\\Users\\顾海\\Desktop\\split");
		
		mergeFile(splitDir);
	}

	private static Properties getProperties() throws FileNotFoundException, IOException {
		String configFileName = "C:\\Users\\顾海\\Desktop\\split\\conf.properties";
		Properties prop = new Properties();
		prop.load(new FileInputStream(configFileName));
		return prop;
	}
	
	private static void mergeFile(File splitDir) throws Exception {
		//合并之前，读取配置信息
		Properties prop = getProperties();
		String fileName = prop.getProperty("filename");
		int partCount = Integer.parseInt(prop.getProperty("partcount"));
		
		List<FileInputStream> inputs = new ArrayList();
		for(int i=1;i<=partCount;i++) {
			inputs.add(new FileInputStream("C:\\Users\\顾海\\Desktop\\split\\"+i+".mp4"));
			
		}
		Enumeration<FileInputStream> en = Collections.enumeration(inputs);
		//多个流-> 1个流
		SequenceInputStream sin = new SequenceInputStream(en);
		//指定合并后的文件输出流
		OutputStream out = new FileOutputStream("C:\\Users\\顾海\\Desktop\\split\\"+fileName);
		byte[] buf = new byte[1024*1024*10];
		int len = -1;
		while((len=sin.read(buf))!=-1) {
			out.write(buf, 0, len);
		}
		out.close();
		sin.close();
	}

}
