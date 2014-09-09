package bysiekm;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class GridFrame extends Frame implements ActionListener {
	public GridFrame() {
		super("Animated list");

		this.setSize(500,300);
		this.setLayout(new GridLayout(1,0));
		
		MenuBar mb = new MenuBar();
		Menu m = new Menu("Application");
		MenuItem mi = new MenuItem("Add list");
		mi.addActionListener(this);
		this.setMenuBar(mb);
		mb.add(m);
		m.add(mi);
		
		addNewList();
	}
	
	public static void main(String[] args) {
		GridFrame f = new GridFrame();
		f.setVisible(true);
	}
	
	private void addNewList() {
		GridLayout gl = (GridLayout)this.getLayout();
		System.out.println(gl.getColumns() + 1);
		this.setLayout(new GridLayout(1,gl.getColumns() + 1));
		AnimatedList al = new AnimatedList(); 
		this.add(al);
		this.validate();
		(new Thread(al)).start();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getActionCommand().equals("Add list"))
			addNewList();
	}
}
