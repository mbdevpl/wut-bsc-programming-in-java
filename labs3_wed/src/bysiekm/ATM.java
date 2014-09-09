package bysiekm;

import java.util.AbstractSet;
import java.util.HashMap;
import java.util.Iterator;

public class ATM extends AbstractSet<ATM> {
	private HashMap<Coin, Integer> coins = null;
	private Coin currCoin = null;
	private CoinSet cs = new CoinSet();
	
	public ATM() {
		coins = new HashMap<Coin, Integer>();
		currCoin = null;
	}
	
	public ATM(HashMap<Coin, Integer> hm, Coin c) {
		coins = hm;
		currCoin = c;
	}
	
	public ATM(ATM src) {
		coins = new HashMap<Coin, Integer>(src.coins);
		currCoin = src.currCoin;
	}
	
	public int totalValue() {
		int val = 0;
		for(CoinIter i = new CoinIter(coins); i.hasNext(); ) {
			Coin c = i.next();
			val += c.val() * coins.get(c);
		}
		return val;
	}
	
	public boolean add(Coin coin) {
		//System.out.println("ATM.add()");
		if(coin.val() <= 0) return false;
		if(!(cs.contains(coin.val()))) return false; 
		if(contains(coin)) {
			for(CoinIter i = new CoinIter(coins); i.hasNext(); ) {
				Coin c = i.next();
				if(coin.equals(c)) {
					int in = coins.get(c);
					coins.remove(c);
					coins.put(coin, new Integer(in+1));
					break;
				}
			}
		} else {
			coins.put(coin, new Integer(1));
		}
		return true;
	}
	
	public boolean addAvailableVal(Float f) {
		return cs.add(f);
	}
	
	@Override
	public boolean contains(Object o) {
		//System.out.println("ATM.contains()");
		if(!(o instanceof Coin)) return false;
		Coin coin = (Coin)o;
		for(CoinIter i = new CoinIter(coins); i.hasNext(); ) {
			Coin c = i.next();
			if(c.equals(coin)) return true;
		}
		return false;
	}
	
	@Override
	public int size() {
		return coins.size();
	}
	
	@Override
	public Iterator<ATM> iterator() {
		return new AtmIter(coins);
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof ATM)) return false;
		ATM a = (ATM)o;
		if(a.coins == coins) return true;
		return false;
	}
	
	@Override
	public String toString () {
		Coin c = currCoin;
		Integer i = coins.get(c);
		return c + " x " + i;
	}
}
