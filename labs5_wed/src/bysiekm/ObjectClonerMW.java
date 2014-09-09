package bysiekm;

import java.io.*;
import java.util.LinkedList;

@SuppressWarnings("serial")
class UnclonableException extends Exception {
    public UnclonableException(Exception e){
        super(e);
    }
    @Override
    public String toString(){
    	return "object cannot be cloned";
    }
}

@SuppressWarnings("serial")
class Parent implements Serializable {
    public String name;
    Parent(String n){name=n;}
    @Override
    public String toString() {
    	return name;
    }
}

@SuppressWarnings("serial")
class Person extends Object implements Serializable {
    public String name;
    public int year;
    public Person friend;
    public Parent mom;
   
    public Person( String name, int year, Person friend, Parent p)
    {
        this.name = name;
        this.year = year;
        this.friend = friend;
        this.mom= p;
    }
    @Override
    public String toString() {
        if(friend==null)return name+" "+year+" "+" ";
        return name+" "+year+" "+" "+"friend: "+friend.toString();
    }
    @Override
    public boolean equals(Object oo){
    	Person o = (Person)oo;
        if(o.friend==null && friend==null )if(o.name.equals( name)&&o.year== year)return true;
        if(o.name.equals( name)&&o.year== year&&o.friend.equals(friend)) return true;
        return false;
    }
}

public class ObjectClonerMW {
    public static Object cloneObject(Serializable o)throws UnclonableException{
        Object o1=null;
        try{
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out= new PipedOutputStream (in);
        ObjectOutputStream p = new ObjectOutputStream(out);
        p.writeObject(o);
        p.close();
        ObjectInputStream t = new ObjectInputStream(in);
        o1= t.readObject();
        t.close();
        }catch(Exception e) {

            throw new UnclonableException(e);
        }
        return o1;
    }
    static public Object[] cloneArrayofObject(Serializable [] arr)throws UnclonableException{
        return (Object [])cloneObject(arr);
    }
	public static void main(String [] args){
        Person s = new Person( "John", 1990, null, new Parent("mother") );
        Person v = new Person("Agnes", 1995, s, new Parent("father") );
        //Person cloned_s=null;
        //Person cloned_v=null;
        Person[] ppl = {s,v};
        try{
        	//print original
        	LinkedList<Person> ppll = new LinkedList<Person>();
        	ppll.add(s);
        	ppll.add(v);
            System.out.println(ppll);
            //clone
            Person[] pplClone = (Person[])cloneArrayofObject(ppl);
            //print clone
        	LinkedList<Person> ppllClone = new LinkedList<Person>();
        	ppllClone.add(pplClone[0]);
        	ppllClone.add(pplClone[1]);
            System.out.println(ppllClone.toString());
//	        cloned_s=(Person) cloneObject(s);
//	        cloned_v=(Person) cloneObject(v);
//	        System.out.println(s.toString());
//	        System.out.println(cloned_s.toString());
//	        if(s==cloned_s)
//	            System.out.println("the same instance");
//	        if(s.equals(cloned_s))
//	            System.out.println("the same content");
//	        System.out.println();
//	        System.out.println(s.toString());
//	        System.out.println(cloned_v.toString());
//	        if(v==cloned_v)
//	            System.out.println("the same instance");
//	        if(v.equals(cloned_v))
//	            System.out.println("the same content");
       }catch(UnclonableException ue)
        {
           ue.printStackTrace();
        }
	}
}