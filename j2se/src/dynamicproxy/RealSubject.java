package dynamicproxy;

public class RealSubject implements Subject{

	@Override
	public int rent() {
		System.out.println("��ʵ��ɫ���ⷿ.....");
		return 3000;
	}

}
