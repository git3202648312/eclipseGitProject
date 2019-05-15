package file.split;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfReader {
	public static void main(String[] args) throws Exception {
		File confFile = new File("C:\\Users\\¹Ëº£\\Desktop\\split\\9.config");
		readConfig(confFile);
	}

	private static void readConfig(File confFile) throws Exception {
		BufferedReader bReader = new BufferedReader(new FileReader(confFile)) ;
		String line = null;
		while((line=bReader.readLine())!=null) {
			String[] arr = line.split("=");
			if(line.startsWith("filename")) {
				System.out.println("filename,"+arr[1]);
			}else if(line.startsWith("partcount")) {
				System.out.println("partcount,"+arr[1]);
			}
		}
		bReader.close();
	}
}
