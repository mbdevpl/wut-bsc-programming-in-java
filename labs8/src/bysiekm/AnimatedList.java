package bysiekm;

import java.awt.List;

@SuppressWarnings("serial")
public class AnimatedList extends List implements Runnable {

	public AnimatedList() {
		super();
		for(int i = 0; i < 10; i++)
			this.add( i + ". element");
		this.select(0);
	}
	
	@Override
	public void run() {
		while(true) {
			//this
			this.select( (this.getSelectedIndex()+1) % 10 );
			//or this:
			/*int curr = this.getSelectedIndex();
			if(curr >= this.getItemCount() - 1)
				this.select(0);
			else
				this.select(curr+1);*/
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
	}

}
