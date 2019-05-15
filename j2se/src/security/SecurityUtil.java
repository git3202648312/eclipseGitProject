package security;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.codec.digest.DigestUtils;

public class SecurityUtil {
	//异或 实现加密与解密：	传入String("abc") -->String("xyz")
	public static String xor(String input) {
		//可逆加密
		char[] chs = input.toCharArray();//"abc" -->{'a','b','c'}-->{'x','y','z'}
		for(int i=0;i<chs.length;i++) {
			chs[i] = (char) (chs[i]^3000);//对每一个字符进行加密 a ->x	char<int
		}
		return new String(chs);//{'x','y','z'}-->"xyz"
	}
	
	//md5加密 不可逆 速度较快
	public static String md5Encode(byte[] input) {//String("abc") -->String("12144abc")
		return DigestUtils.md5Hex(input);
	}
	
	//sha256 加密 不可逆 安全性较高
	public static String sha256Encode(byte[] input) {
		return DigestUtils.sha256Hex(input);
	}
	
	//Base64加密
	public static String base64Encode(byte[] input) {
		String result = null;
		try {
			Class clazz = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
			Method method = clazz.getMethod("encode", byte[].class);
			result = (String) method.invoke(null, input);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	//Base64解密
	public static byte[] base64Decode(String input) {
		byte[] result = null;
		try {
			Class clazz = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
			Method method = clazz.getMethod("decode", String.class);
			result = (byte[]) method.invoke(null, input);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) {
		String str = "hello";
//		str =xor(str);//第一次异或：加密
//		System.out.println(str);
//		str =xor(str);//第二次异或：解密
//		System.out.println(str);
		
//		str = md5Encode(str.getBytes());
//		System.out.println(str);
		
//		str = sha256Encode(str.getBytes());
//		System.out.println(str);
		
		str = base64Encode(str.getBytes());
		System.out.println("加密："+str);
		byte[] result = base64Decode(str);
		System.out.println("解密："+new String(result));
	}
}
