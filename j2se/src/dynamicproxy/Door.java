package dynamicproxy;

public class Door implements Lock{

	@Override
	public void locked() {
		System.out.println("πÿ√≈...");
	}
}
