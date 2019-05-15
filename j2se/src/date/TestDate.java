package date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {
	public static void main(String[] args) throws Exception {
		
		//456
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String format = sdf.format(new Date());
		System.out.println(format);
		System.out.println(sdf.parse("2002-09-09"));
//		System.out.println(date.getTime());
//		System.out.println(date.getDate());
//		System.out.println(date.getDay());
//		System.out.println(date.getHours());
//		System.out.println(date.getMinutes());
//		System.out.println(date.getMonth()+1);
	}
}
