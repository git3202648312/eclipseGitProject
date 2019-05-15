package dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Test {
	public static void main(String[] args) {
//		//真实角色
//		Subject realSubject = new RealSubject();
//		//初步代理角色
//		InvocationHandler handler = new DynamicProxy(realSubject);
//		//最终代理角色
//		
//		/*
//		 * newProxyInstance(loader, interfaces, h)
//		 * loader:累加器对象
//		 * interfaces:要代理的真实角色  可以拥有哪些方法
//		 * h:hanlder,初步代理角色->真实代理角色	通过h可以知道要代理的真实角色是什么
//		 */
//		Subject subProxy = (Subject)Proxy.newProxyInstance(handler.getClass().getClassLoader(), realSubject.getClass().getInterfaces(), handler);
//		subProxy.rent();//自动执行InvokeactionHandler(DynamicProxy)中的invoke()方法
		
		
		Object realSubject = new Door();
		InvocationHandler handler = new DynamicProxy(realSubject);
		Lock lockProxy = (Lock)Proxy.newProxyInstance(handler.getClass().getClassLoader(), realSubject.getClass().getInterfaces(), handler);
		lockProxy.locked();
	}
}
