package bysiekm;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

class Train extends AbstractList<Car> {
	ArrayList<Car> cars = new ArrayList<Car>();
	
	public boolean add(Animal a) {
		for(int i=0; i<size(); i++) {
			if(get(i).add(a)) {
				return true;
			}
		}
		cars.add(new Car());
		return cars.get(size()-1).add(a);
	}
	
	@Override
	public Car get(int i) {
		return cars.get(i);
	}

	@Override
	public int size() {
		return cars.size();
	}
	
	public void printReport() {
		HashMap<String, Integer> total = new HashMap<String, Integer>();
		for(int i=0; i<size(); i++) {
			Car c = get(i);
			for(int j=0; j<c.size(); j++) {
				Animal a = c.get(j);
				String s = a.toString();
				if(total.containsKey(s)) {
					int num = total.get(s)+1;
					total.remove(s);
					total.put(s, num);
				} else total.put(s, 1);
			}
		}
		System.out.println("Full report:");
		for(Iterator it = total.keySet().iterator(); it.hasNext(); ) {
			String s = it.next().toString();
			System.out.println(" " + s + "s: " + total.get(s));
		}
	}
	
	public Iterator iterator() {
		return cars.iterator();
	}
}

class Car extends AbstractList<Animal> {
	ArrayList<Animal> animals = new ArrayList<Animal>();
	@Override
	public boolean add(Animal a) {
		if(!canLiveWithAll(a)) return false;
		if(animals.size() >= 5) return false;
		return animals.add(a);
	}
	
	public boolean canLiveWithAll(Animal a) {
		for(int i=0; i<animals.size(); i++)
			if(!(animals.get(i).canLiveWith(a))) return false;
		return true;
	}
	
	@Override
	public Animal get(int i) {
		return animals.get(i);
	}

	@Override
	public int size() {
		return animals.size();
	}
	
	public String toString() {
		return animals.toString();
	}
}

abstract class Animal {
	//static String[] invalid;
	boolean canLiveWith(Animal a) {
		String[] invalid1 = getInvalid(); 
		String[] invalid2 = a.getInvalid();
		if(a.equals(this)) return true;
		for(int i=0; i<invalid2.length; i++) { //one way
			if(toString().equals(invalid2[i])) return false;
		}
		for(int i=0; i<invalid1.length; i++) { //and the other
			if(a.toString().equals(invalid1[i])) return false;
		}
		return true;
	}
	
	public boolean equals(Object o) {
		if(o instanceof Animal) {
			return toString().equals(o.toString());
		}
		return false;
	}
	
	public String toString() {
		return getClass().getSimpleName();
	}

	protected abstract String[] getInvalid();
}

public class Labs3_bysiekm {
	public static void main(String args[]) {
        Train train = new Train();
        
        train.add( new Zebra() );
        train.add( new Zebra() );
        train.add( new Dog() );
        train.add( new Cat() );
        train.add( new Tiger() );
        train.add( new Zebra() );
        train.add( new Zebra() );
        train.add( new Crocodile() );
        train.add( new Wolf() );

        System.out.println( "Train has " + train.size() + 
            " cars: " );
        for (Iterator i = train.iterator(); i.hasNext();)
                System.out.println( i.next().toString() );
        
        System.out.println();
        train.printReport();
    }
}

class Dog extends Animal {
	@Override
	protected String[] getInvalid() {
		String[] s = {"Cat"};
		return s;
	}
}
class Cat extends Animal {
	@Override
	protected String[] getInvalid() {
		String[] s = {"Dog"};
		return s;
	}
}
class Tiger extends Animal {
	@Override
	protected String[] getInvalid() {
		String[] s = {"Dog"};
		return s;
	}
}
class Wolf extends Animal {
	@Override
	protected String[] getInvalid() {
		String[] s = {"Cat"};
		return s;
	}
}
class Zebra extends Animal {
	@Override
	protected String[] getInvalid() {
		String[] s = {"Tiger", "Wolf"};
		return s;
	}
}
class Crocodile extends Animal {
	@Override
	protected String[] getInvalid() {
		String[] s = {"Dog", "Cat", "Tiger", "Wolf", "Zebra"};
		return s;
	}
}
