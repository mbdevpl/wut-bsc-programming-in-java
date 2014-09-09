package bysiekm;

//import java.util.AbstractSet;
import java.util.HashSet;
import java.util.Iterator;

public class CoinSet {
	private HashSet<Float> values = null;
	
	public CoinSet() {
		values = new HashSet<Float>();
		values.add(new Float(1.0));
		values.add(new Float(2.0));
		values.add(new Float(5.0));
		values.add(new Float(10.0));
		values.add(new Float(20.0));
		values.add(new Float(100.0));
	}
	
	public boolean add(Float f) {
		if(f == null) return false;
		if(contains(f)) return false;
		return values.add(f);
	}
	
	public boolean contains(Float f) {
		for(Iterator<Float> i = values.iterator(); i.hasNext(); ) {
			Float fl = i.next();
			if(fl.equals(f))
				return true;
		}
		return false;
	}
	
	public boolean remove(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
}
