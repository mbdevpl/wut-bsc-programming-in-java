package bysiekm;

public class Coin {
	private Float value = null;
	
	public static Coin getInstance(Float f) {
		return new Coin(f);
	}
	
	private Coin(Float val) {
		value = new Float(val);
	}
	
	public float val() {
		return value;
	}
	
	@Override
	public String toString () {
		//System.out.println(" Coin.toString()");
		return value.toString();
	}
	
	@Override
	public boolean equals (Object o) {
		//System.out.println(" Coin.compare()");
		if(!(o instanceof Coin)) return false;
		Coin c = (Coin)o;
		if(val() == c.val()) return true;
		return false;
	}
}
