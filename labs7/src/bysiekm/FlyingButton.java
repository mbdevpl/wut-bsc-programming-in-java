package bysiekm;

import java.awt.Button;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class FlyingButton extends Button implements Runnable, ActionListener {
	private Container parent;
	private volatile boolean moving = true;
	private String baseTitle = "I believe I can fly";
	
	public FlyingButton(Frame parent, int x, int y) {
		Dimension dim = new Dimension(200,30);
		this.parent = parent;
		this.setMaximumSize(dim);
		this.setMinimumSize(dim);
		this.setPreferredSize(dim);
		setSize(dim);
		this.addActionListener(this);
		this.setLocation(x, y);
		
		moving = true;
		this.setLabel(baseTitle);
	}

	private int getRightBound() {
		return this.getX() + this.getWidth();
	}
	
	private int getLeftBound() {
		return this.getX();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(moving) {
			this.setLabel(baseTitle + " but I don't like it");
			moving = false;
		}
		else {
			moving = true;
			(new Thread(this)).start();
			this.setLabel(baseTitle + " and I'm doing it");
		}
	}
	
	@Override
	public void run() {
		int diff = 1;
		while(moving) {
			if(getRightBound() >= parent.getWidth()) diff = -1;
			else if(getLeftBound() <= 0) diff = 1;
			this.setLocation(this.getX() + diff, this.getY());
			this.repaint();
			parent.validate();
			parent.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {}
		}
	}
}
