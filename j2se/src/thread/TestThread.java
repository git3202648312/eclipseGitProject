package thread;

public class TestThread {
	public static void main(String[] args) {
		new Thread() {
			public void run() {
				while(true) {
					System.out.println("123");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
		}.start();
		new Thread() {
			public void run() {
				while(true) {
					System.out.println("456");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
		}.start();
	}
}
