package bysiekm;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class ObjectCloner {
	public static Object[] cloneObjectArray(Object[] o) throws ObjectNotSerializableException,
			IOException, ClassNotFoundException {
		Object[] oClone = new Object[o.length];
		for(int i = 0; i < o.length; i++)
			oClone[i] = cloneObject(o[i]);
		return oClone;
	}
	
	
	public static Object cloneObject(Object o) throws ObjectNotSerializableException,
			IOException, ClassNotFoundException {
		//checking if Object o is serializable
		if(!Serializable.class.isAssignableFrom(o.getClass()))
			throw new ObjectNotSerializableException();
		
		//cloning the object
		PipedOutputStream pipeOut = new PipedOutputStream();
		PipedInputStream pipeIn = new PipedInputStream(pipeOut);
		ObjectOutputStream os = new ObjectOutputStream(pipeOut);
		ObjectInputStream is = new ObjectInputStream(pipeIn);
		os.writeObject(o);
		os.close();
		Object oClone = is.readObject();
		is.close();
		
		return oClone;
	}
	
	public static void showEquality(Object o1, Object o2) {
		System.out.println("'" + o1 + "'?='" + o2 + "' ["
				+ (o1.equals(o2)?"equal":"not equal") +", "
				+ (o1==o2?"same object":"different object") + "]");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException,
			ClassNotFoundException {
		Object[] o = new Object[5];
		o[0] = new String("testu testu testu, nie mam liter stu dwudziestu");
		o[1] = new Thread();
		o[2] = new Integer(120);
		o[3] = new NullPointerException();
		o[4] = new ArrayList<String>(Collections.nCopies(4, new String("hello")));
		
		System.out.println("One by one:");
		for(int i = 0; i < o.length; i++) {
			try {
				Object oClone = cloneObject(o[i]);
				showEquality(o[i],oClone);
			} catch (ObjectNotSerializableException e) {
				System.out.println(e);
			}
		}
		
		System.out.println("\nArray:");
		try {
			@SuppressWarnings("unused")
			Object[] oClone = cloneObjectArray(o);
		} catch (ObjectNotSerializableException e) {
			System.out.println(e);
		}
	}

	@SuppressWarnings("serial")
	public static class ObjectNotSerializableException extends RuntimeException {
		public ObjectNotSerializableException() {
			//
		}
		
		public String toString() {
			return getClass().getSimpleName();
		}
	}
}