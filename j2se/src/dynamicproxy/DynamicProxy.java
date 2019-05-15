package dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import staticproxy.RealSubject;
import staticproxy.Subject;

//动态代理 不能实现某一具体的接口，因为动态代理是为"任何"真实角色服务的，而不是某一个具体角色服务
//动态代理需要实现一个已存在的接口InvocationHandler，该接口表是"任何"
public class DynamicProxy implements InvocationHandler{

	//用Object接收任何对象的真实角色
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
