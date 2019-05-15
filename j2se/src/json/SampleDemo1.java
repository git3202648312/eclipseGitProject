package json;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class SampleDemo1 {
	//1.Map集合、JavaBean、字符串-->JSon对象
	//a.Map集合JSon对象
	public static void demo1() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("s01", "zs");
		map.put("s02", "ls");
		map.put("s03", "ww");
		//map->JSon
		JSONObject json = new JSONObject(map);
		System.out.println(json);
	}
	
	//b.JavaBean
	public static void demo2() {
		Address addr = new Address("yz","yy");
		Person per = new Person("zs",23,addr);
//		JSONObject json = new JSONObject(per);
		net.sf.json.JSONObject json = new net.sf.json.JSONObject();
		json = json.fromObject(per);
		System.out.println(json);
	}
	
	//String
	public static void demo3() {
		String str = "{\"address\":{\"schoolAddress\":\"yy\",\"homeAddress\":\"yz\"},\"name\":\"zs\",\"age\":23}";
//		JSONObject json = new JSONObject(str);
		net.sf.json.JSONObject json = new net.sf.json.JSONObject();
		json = json.fromObject(str);
		System.out.println(json);
	}
	
	public void demo4() {
//		InputStream in = super.getClass().getClassLoader().getResourceAsStream("json/per.json");
//		byte[] bs = new byte[10];
//		StringBuffer sb = new StringBuffer();
//		int len = -1 ;
//		try {
//			while((len=in.read(bs))!=-1) {
//				String str = new String(bs,0,len);
//				sb.append(str);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		JSONObject json = new JSONObject(sb.toString());
		String str = null;
		try {
			str = FileUtils.readFileToString(new File("D:\\study\\eclippse-workspace\\j2se\\src\\json\\per.json"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject json = new JSONObject(str);
		System.out.println(json);
	}
	
	public static void demo5() {
		Map<String,Person> map = new HashMap<String,Person>();
		Person person1 = new Person("zs",23,new Address("yz1","yy1"));
		Person person2 = new Person("ls",23,new Address("yz2","yy2"));
		Person person3 = new Person("ww",23,new Address("yz3","yy3"));
		map.put("zs", person1);
		map.put("ls", person2);
		map.put("ww", person3);
		JSONObject json = new JSONObject(map);
		//生成json文件
		Writer writer = null;
		try {
			writer = new FileWriter("F:\\per.json");
			json.write(writer);
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void demo6() {
		String str = "[{\"name\":\"zs\",\"age\":23},{\"name\":\"ls\",\"age\":24},{\"name\":\"ww\",\"age\":25}]";
		JSONArray jArray = new JSONArray(str);
		System.out.println(jArray);
	}
	
	public static void demo7() {
		Map<String,String> map = new HashMap();
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key3", "value3");
		net.sf.json.JSONArray jArray = new net.sf.json.JSONArray();
		jArray = jArray.fromObject(map);
		System.out.println(jArray);
	}
	
	public static void demo8() {
		String jStr = "[{\"name\":\"zs\",\"age\":23},{\"name\":\"ls\",\"age\":24},{\"name\":\"ww\",\"age\":25}]";
		net.sf.json.JSONArray jArray = new net.sf.json.JSONArray();
		jArray = jArray.fromObject(jStr);
		Map<String,Object> map = new HashMap();
		for (int i=0;i<jArray.size();i++) {
			Object object = jArray.get(i);
			net.sf.json.JSONObject json = (net.sf.json.JSONObject)object;
			Set<String> keys = json.keySet();
			for(String key:keys) {
				Object value = json.get(key);
				map.put(key, value);
			}
		}
		System.out.println(map);
	}
	
	public static void main(String[] args) {
//		SampleDemo1 sample = new SampleDemo1();
//		demo1();
//		demo2();
//		demo3();
//		sample.demo4();
//		demo5();
//		demo6();
//		demo7();
		demo8();
	}
}
