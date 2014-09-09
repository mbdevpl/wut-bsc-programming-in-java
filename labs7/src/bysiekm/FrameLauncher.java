package bysiekm;

import java.awt.Frame;

public class FrameLauncher {
	private static Frame frame;
	/**
	 * @param args arguments are not read
	 */
	public static void main(String[] args) {
		frame = new Frame("Frame for flying button(s)");
		frame.setBounds(10, 10, 800, 400);
		frame.setLayout(null);
		
//		addFlyingButton(frame, 10, 100);
		for(int i = 0; i < frame.getHeight(); i+=40)
			addFlyingButton(frame, (int)(i*(1.5)), i);
		
		frame.setVisible(true);
	}
	
	public static void addFlyingButton(Frame frame, int x, int y) {
		FlyingButton fb = new FlyingButton(frame, x, y);
		frame.add(fb);
		(new Thread(fb)).start();
	}

}
