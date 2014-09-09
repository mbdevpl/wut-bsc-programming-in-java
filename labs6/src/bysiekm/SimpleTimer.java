package bysiekm;

public class SimpleTimer extends Thread {
	public volatile boolean stopMe = false;
	private int hour;
	private int minute; 
	private int second;
	private int interval = 1000; //speed can be changed with second constructor
	
	public SimpleTimer(int hours, int minutes, int seconds) {
		hour = hours;
		minute = minutes;
		second = seconds;
	}
	public SimpleTimer(int hours, int minutes, int seconds, int interval) {
		hour = hours;
		minute = minutes;
		second = seconds;
		this.interval = interval;
	}
	
	public int getHour() {
		return hour;
	}
	public int getMinute() {
		return minute;
	}
	public int getSecond() {
		return second;
	}
	
	public String toString() {
		return SetDigits(getHour(),2)
			+ ":" + SetDigits(getMinute(),2)
			+ "." + SetDigits(getSecond(),2);
	}
	
	static private String SetDigits(int a, int Digits){
		String TheResult=""+a;
		int len=TheResult.length();
		if(len<Digits)
			for(int i=1;i<=Digits-len;i++)
				TheResult="0"+TheResult;
		return TheResult;
	}
	
	@Override
	public void run() {
		while(!stopMe) {
			//decreament
			if(second > 0) second--;
			else if(minute > 0) {
				minute--;
				second = 59;
			} else if(hour > 0) {
				hour--;
				minute = 59;
				second = 59;
			} else stopMe = true;
			//sleep
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) { }
		}
	}
	
	static class Printer implements Runnable {
		SimpleTimer timer = null;
		
		public Printer(SimpleTimer timer) {
			this.timer = timer;
		}

		@Override
		public void run() {
			while(!timer.stopMe) {
				//printout
				System.out.print(timer + "\r");
				//sleep
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) { }
			}
		}
	}
	
	static class MultiPrinter extends Thread {
		public volatile boolean stopMe = false;
		SimpleTimer[] timers = null;

		public MultiPrinter(SimpleTimer[] timers) {
			this.timers = timers;
			this.setDaemon(true);
			
		}
		
		@Override
		public void run() {
			while(!stopMe) {
				//printout
				for(int i = 0; i < timers.length; i++) {
					System.out.print(timers[i] + " ");
				}
				System.out.print("\r");
				//sleep
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) { }
			}
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println("One timer:");
		SimpleTimer timer = new SimpleTimer(0,0,5);
		timer.start();
		Thread print = new Thread(new Printer(timer));
		//print.setDaemon(true);
		print.start();
		try {
			print.join();
		} catch (InterruptedException e) { }
		System.out.println("\n finished!");
		
		System.out.println("Many timers:");
		SimpleTimer[] timers = {new SimpleTimer(1,0,5), new SimpleTimer(0,1,10),
				new SimpleTimer(0,0,15), new SimpleTimer(0,56,10,2000),
				new SimpleTimer(1,23,45,50), new SimpleTimer(0,2,30,500)};
		for(SimpleTimer st: timers) st.start();
		Thread mprint = new MultiPrinter(timers);
		mprint.start();
		try {
			mprint.join();
		} catch (InterruptedException e) { }
		System.out.println("\n finished!");
	}
}
