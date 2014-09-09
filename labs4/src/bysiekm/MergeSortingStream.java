package bysiekm;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

public class MergeSortingStream extends MergingStream {
	public MergeSortingStream(InputStream[] inputs) {
		super(inputs);
	}
	
	@Override
	public int read() throws IOException {
		Vector<Integer> chars = new Vector<Integer>();
		int max = -1;
		for(int i = 0; i < is.size(); i++) {
			chars.add(is.get(isNum).read());
			if(chars.get(i) > max) max = chars.get(i);
		}
		if(max == -1) return -1;
		
		int min = chars.get(max);
		for(int i = 0; i < chars.size(); i++) {
			if(chars.get(i) < chars.get(min) && chars.get(i) != -1) min = i;
		}
		for(int i = 0; i < is.size() &&  i < chars.size(); i++) {
			if(i != min) {
				int avail = is.elementAt(i).available();
				is.elementAt(i).reset();
				is.elementAt(i).skip(avail - 1);
			}
		}
		return chars.get(min);
	}
}
