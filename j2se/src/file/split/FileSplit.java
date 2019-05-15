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
		File resFile = new File("D:\\workspace\\major\\1609302班 顾海.mp4");
		File splitDir = new File("C:/Users/顾海/Desktop/split");
		//软件使用次数： 5
		if(hasRemainingTries()) {
			//拆分文件
			splitFile(resFile,splitDir);
		}else {
			System.out.println("试用次数已到");
		}
	}

	//判断是否还有试用次数
	//思路： 将当前使用的次数保存在硬盘
	private static boolean hasRemainingTries() throws FileNotFoundException, IOException {
		Properties prop =new Properties();
		int count = 0;
		//每使用一次：1.先获取之前用了几次   2.再将之前次数机加1
		
		//查询本次之前已经用了几次
		prop.load(new FileInputStream("C:/Users/顾海/Desktop/split/tries.properties"));
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
		prop.store(new FileOutputStream("C:/Users/顾海/Desktop/split/tries.properties"), "try time....");
		
		return true;
	}

	private static void splitFile(File resFile, File splitDir) throws Exception {
		if(!splitDir.exists()) {
			splitDir.mkdirs();
		}
		
		//思路：拆分：1个输入流   n个输出流
		//合并：n个输入流   1个输出流(注意顺序)
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
		
		//拆分的时候：如何将文件名、分割的数量保存，为后续合并做准备
		//在生成一个配置文件9.config 保存上述描述信息
		out = new FileOutputStream(new File(splitDir,"conf.properties"));
		
//		//方式一：
//		//查询当前操作系统的换行符
//		String lineSeparator = System.getProperty("line.separator");
//		out.write(("filename="+resFile.getName()).getBytes());
//		out.write(lineSeparator.getBytes());
//		out.write(("partcount="+(count-1)).getBytes());
//		out.close();
		
		//方式二：Properties,将内存中多个属性以key=value的形式写到硬盘中
		Properties prop = new Properties();
		prop.setProperty("filename", resFile.getName());
		prop.setProperty("partcount", (count-1)+"");
		//写入硬盘(保存：持久化)
		prop.store(out, "file configuration...");
		in.close();
	}
}
