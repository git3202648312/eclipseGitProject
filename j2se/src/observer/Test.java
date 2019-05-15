package observer;

public class Test {
	public static void main(String[] args) {
		Subject subject = new ConcreteSubject();
		
		Observer observer1 = new ConcreteObserver();
		Observer observer2 = new ConcreteObserver();
		Observer observer3 = new ConcreteObserver();
		
		subject.addObserver(observer1);
		subject.addObserver(observer2);
		subject.addObserver(observer3);
		
		subject.notifyObserver("你好");
		
		subject.deleteObserver(observer2);
		
		subject.notifyObserver("天气不错");
		
	}
}
