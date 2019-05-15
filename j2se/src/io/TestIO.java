package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public class TestIO {
	public static void main(String[] args) throws Exception {
//		File file = new File("C:/Users/顾海/Desktop/aaa");
//		boolean b = file.mkdir();
//		System.out.println(b);
		String str = "年好啊";
		char[] cs = new char[10];
		char[] cs2 = new char[10];
		byte[] bs = new byte[32];
		str.getChars(0, str.length(), cs, 0);
//		for (char c : cs) {
//			System.out.print(c+"-");
//		}
//		System.out.println("\n"+cs.length);
		File outFile = new File("C:/Users/顾海/Desktop/aaa","a.png");
		//File inFile = new File("D:\\workspace\\major\\Java高级编程\\J2SE.txt");
		//File inFile = new File("D:\\workspace\\major\\html\\beijing.html");
		File inFile = new File("D:\\workspace\\major\\images\\lxwm.png");
//		try {
//			Reader reader = new FileReader(inFile);
//			//System.out.println(reader);
//			int length = reader.read(cs2, 0, 10);
//			System.out.println((String)length);
//			reader.close();
//			Writer writer = new FileWriter(outFile);
//			writer.write(str);
//			writer.close();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		InputStream is = new FileInputStream(inFile);
		OutputStream os = new FileOutputStream(outFile);
		int ch=-1;
		while((ch=is.read(bs))!=-1) {
			//System.out.println(ch+"-"+(char)ch);
			os.write(bs,0,ch);
		}
		is.close();
		os.close();
//		Reader r = new FileReader(inFile);
//		Writer w = new FileWriter(outFile);
//		int ch = -1;
//		while((ch=r.read())!=-1) {
//			System.out.println(ch+"-"+(char)ch);
//			w.write(ch);
//		}
//		r.close();
//		w.close();

//		Reader r = new InputStreamReader(new FileInputStream(inFile) ,"utf-8");
//		Writer w = new OutputStreamWriter(new FileOutputStream(outFile),"utf-8");
//		int ch = -1;
//		while((ch=r.read())!=-1) {
//			System.out.println(ch+"-"+(char)ch);
//			w.write(ch);
//		}
//		r.close();
//		w.close();
	}
}
