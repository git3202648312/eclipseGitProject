package security;

import java.util.Scanner;

public class SecurityDemo {
		
	String uname;
	String pwd;
	Scanner input = new Scanner(System.in);
	
	public boolean register() {
		Boolean flag = false;
		try {
			System.out.println("�������û�����");
			uname = input.next();
			
			System.out.println("���������룺");
			pwd = input.next();
			pwd = SecurityUtil.md5Encode(pwd.getBytes());
			System.out.println("ע����Ϣ���£��û���:"+uname+",����:"+pwd);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean login() {
		Boolean result = false;
		try {
			System.out.println("�������û�����");
			String loginUname = input.next();
			System.out.println("���������룺");
			String loginPwd = input.next();
			loginPwd = SecurityUtil.md5Encode(loginPwd.getBytes());
			if(loginUname.equals(uname) && loginPwd.equals(pwd)) {
				System.out.println("��¼��Ϣ���£��û���:"+loginUname+",����:"+loginPwd);
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
			System.out.println("��½�ɹ�");
		}else {
			System.out.println("��½ʧ��");
		}
	}
}
