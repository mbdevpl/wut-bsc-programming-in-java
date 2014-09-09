package bysiekm;

public class AsciiTrain extends Thread {
	String[] locomotive = {"-OOoo" , "-oooo"};
	String[] car = {"-o##o" , "-O##O"};
	int pos = 0;
	
	@Override
	public void run() {
		while(true) {
			if(pos > (79 - 5 - 5)) break;
			for(int i = 0; i<pos; i++)
				System.out.print(" ");
			System.out.print(car[1] + locomotive[1] + "\r");
			
			pos++;
			try {
				sleep(50);
			} catch (InterruptedException e) { }
		}
	}
	
	
	public static void main(String[] args) {
		AsciiTrain train = new AsciiTrain();
		train.start();
	}

}
