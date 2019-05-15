package dynamicproxy;

public class RealSubject implements Subject{

	@Override
	public int rent() {
		System.out.println("真实角色的租房.....");
		return 3000;
	}

}
