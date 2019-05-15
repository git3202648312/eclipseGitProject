package security;

import java.util.Scanner;

public class SecurityDemo {
		
	String uname;
	String pwd;
	Scanner input = new Scanner(System.in);
	
	public boolean register() {
		Boolean flag = false;
		try {
			System.out.println("请输入用户名：");
			uname = input.next();
			
			System.out.println("请输入密码：");
			pwd = input.next();
			pwd = SecurityUtil.md5Encode(pwd.getBytes());
			System.out.println("注册信息如下：用户名:"+uname+",密码:"+pwd);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean login() {
		Boolean result = false;
		try {
			System.out.println("请输入用户名：");
			String loginUname = input.next();
			System.out.println("请输入密码：");
			String loginPwd = input.next();
			loginPwd = SecurityUtil.md5Encode(loginPwd.getBytes());
			if(loginUname.equals(uname) && loginPwd.equals(pwd)) {
				System.out.println("登录信息如下：用户名:"+loginUname+",密码:"+loginPwd);
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) {
		SecurityDemo demo = new SecurityDemo();
		demo.register();
		
		boolean result = demo.login();
		if(result) {
			System.out.println("登陆成功");
		}else {
			System.out.println("登陆失败");
		}
	}
}
