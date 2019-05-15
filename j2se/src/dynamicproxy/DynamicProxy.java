package dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import staticproxy.RealSubject;
import staticproxy.Subject;

//��̬���� ����ʵ��ĳһ����Ľӿڣ���Ϊ��̬������Ϊ"�κ�"��ʵ��ɫ����ģ�������ĳһ�������ɫ����
//��̬������Ҫʵ��һ���Ѵ��ڵĽӿ�InvocationHandler���ýӿڱ���"�κ�"
public class DynamicProxy implements InvocationHandler{

	//��Object�����κζ������ʵ��ɫ
	Object realSubject ;
	public DynamicProxy(Object realSubject) {
		this.realSubject = realSubject;
	}
	
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		this.before();
		Object obj = method.invoke(realSubject, args);
		this.after();
		return obj;
	}
	
	private void before() {
		System.out.println("before....");
	}
	
	private void after() {
		System.out.println("after....");
	}

}
