package bysiekm;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

public class MergingStream extends InputStream {
	Vector<InputStream> is = null;
	Vector<Boolean> finished = null;
	
	int isNum = 0;
	
	public MergingStream(InputStream[] inputs) {
		is = new Vector<InputStream>();
		finished = new Vector<Boolean>();
		for(int i = 0; i < inputs.length; i++) {
			is.add(inputs[i]);
			finished.add(false);
		}
	}
	
	protected void setNextStreamNum() {
		int startingPoint = isNum; //preventing endless loop when all streams finished
		if(isNum >= is.size() - 1) isNum = 0;
		else isNum++;
		while(finished.elementAt(isNum) && isNum != startingPoint) {
			if(isNum >= is.size() - 1) isNum = 0;
			else isNum++;
		}
		if(isNum == startingPoint && finished.elementAt(isNum))
			isNum = -1;
	}
	
	@Override
	public int read() throws IOException {
		int ch = 0;
		ch = is.get(isNum).read();
		if(ch == -1) {
			finished.set(isNum, true);
			//System.out.print(" *" + finished.elementAt(isNum) + "* ");
			setNextStreamNum();
			if(isNum == -1) {
				//System.out.print(" *" + isNum + "* ");
				return -1;
			}
			ch = is.get(isNum).read();
			return ch;
		} else { 
			setNextStreamNum();
			return ch;
		}
	}
}

