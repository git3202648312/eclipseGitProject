package dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Test {
	public static void main(String[] args) {
//		//��ʵ��ɫ
//		Subject realSubject = new RealSubject();
//		//���������ɫ
//		InvocationHandler handler = new DynamicProxy(realSubject);
//		//���մ����ɫ
//		
//		/*
//		 * newProxyInstance(loader, interfaces, h)
//		 * loader:�ۼ�������
//		 * interfaces:Ҫ�������ʵ��ɫ  ����ӵ����Щ����
//		 * h:hanlder,���������ɫ->��ʵ�����ɫ	ͨ��h����֪��Ҫ�������ʵ��ɫ��ʲô
//		 */
//		Subject subProxy = (Subject)Proxy.newProxyInstance(handler.getClass().getClassLoader(), realSubject.getClass().getInterfaces(), handler);
//		subProxy.rent();//�Զ�ִ��InvokeactionHandler(DynamicProxy)�е�invoke()����
		
		
		Object realSubject = new Door();
		InvocationHandler handler = new DynamicProxy(realSubject);
		Lock lockProxy = (Lock)Proxy.newProxyInstance(handler.getClass().getClassLoader(), realSubject.getClass().getInterfaces(), handler);
		lockProxy.locked();
	}
}
