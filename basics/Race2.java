package basics;

public class Race2 {

	public static class Counter {
		private int c=0;

		public int getC() {
			return c;
		}

		public void bump() {
			this.c = c+1;
		}
	
	}
	
	public static class Turnstile implements Runnable {
		private Counter c;
		
		Turnstile(Counter c) {
			this.c=c;
		}
		
		public void run() {
			for (int i=0; i<1000; i++) {
				c.bump();
			}
		}
		
	}
	
	public static void main(String[] args) {
		Counter c = new Counter();
		Boolean done = false;
		
		while (!done) {
		   c.c=0;			
		Thread t1 = new Thread(new Turnstile(c));
		Thread t2 = new Thread(new Turnstile(c));
		
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(c.getC());
		done = c.getC()!=2000;
		}	
	}
}
