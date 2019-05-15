package observer;

public class ConcreteObserver implements Observer {

	@Override
	public void update(String content) {
		System.out.println(content);
	}

}
