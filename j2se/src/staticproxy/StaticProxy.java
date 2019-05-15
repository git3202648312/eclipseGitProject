package staticproxy;

//�����ɫ��ִ����ʵ��ɫ�ķ���+һЩ��������....
public class StaticProxy  extends Subject{

	//������ʵ���������
	Subject realSubject = new RealSubject();
	
	public void before() {
		System.out.println("before:��ɨ.....");
	}
	
	public void after() {
		System.out.println("����һ���µ����...");
	}
	
	@Override
	public void rent() {
		this.before();
		//ת����ʵ��ɫ�ķ�
		realSubject.rent();
		this.after();
	}
	
}
