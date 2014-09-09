package bysiekm;

import java.util.HashMap;
import java.util.Iterator;

public class CoinIter implements Iterator<Coin> {
	private Iterator<Coin> it;
	//private HashMap<Coin, Integer> hm;
	
	public CoinIter(HashMap<Coin, Integer> hm) {
		it = hm.keySet().iterator();
	}
	
	@Override
	public boolean hasNext() {
		return it.hasNext();
	}
	
	@Override
	public Coin next() {
		return it.next();
	}
	
	@Override
	public void remove() {
		it.remove();
	}
}
