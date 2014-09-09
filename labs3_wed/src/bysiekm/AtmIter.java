package bysiekm;

import java.util.HashMap;
import java.util.Iterator;

public class AtmIter implements Iterator<ATM> {
	private CoinIter it;
	private HashMap<Coin, Integer> hm;
	
	public AtmIter(HashMap<Coin, Integer> hm) {
		it = new CoinIter(hm);
		this.hm = hm;
	}

	@Override
	public boolean hasNext() {
		return it.hasNext();
	}
	
	@Override
	public ATM next() {
		return new ATM(hm,it.next());
	}
	
	@Override
	public void remove() {
		it.remove();
	}
}
