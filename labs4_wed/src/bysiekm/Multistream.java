package bysiekm;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Multistream extends Writer implements List<PrintWriter> {
	ArrayList<PrintWriter> streams = null;
	
	public Multistream(PrintWriter[] pw) {
		streams = new ArrayList<PrintWriter>();
		//array should be added to List!
		//pw.length;
	}
	
	public void addStream(OutputStream out) {
	}
	
	@Override
	public void close() {
		for(int i = 0; i < streams.size(); i++)
			//if(streams.get(i).checkError())
				streams.get(i).close();
		
	}

	@Override
	public void flush() {
		for(int i = 0; i < streams.size(); i++)
			streams.get(i).flush();
	}


	public void write(int index, String str) {
		if(index < 0) throw new NullPointerException();
		if(index >= streams.size()) throw new NullPointerException();
		streams.get(index).write(str);
	}

	@Override
	public void write(char[] arg0, int arg1, int arg2) throws IOException {
		for(int i = 0; i < streams.size(); i++)
			streams.get(i).write(arg0,arg1,arg2);
		
	}
	
	@Override
	public int size() {
		return streams.size();
	}

	@Override
	public List<PrintWriter> subList(int arg0, int arg1) {
		return null;
	}

	@Override
	public Object[] toArray() {
		return null;
	}

	@Override
	public boolean add(PrintWriter out) {
		return streams.add(new PrintWriter(out));
	}

	@Override
	public void add(int arg0, PrintWriter arg1) {
		
	}

	@Override
	public boolean addAll(Collection<? extends PrintWriter> arg0) {
		return false;
	}

	@Override
	public boolean addAll(int arg0, Collection<? extends PrintWriter> arg1) {
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return false;
	}

	@Override
	public PrintWriter set(int arg0, PrintWriter arg1) {
		return null;
	}

	@Override
	public void clear() {
		
	}

	@Override
	public boolean contains(Object arg0) {
		return false;
	}

	@Override
	public PrintWriter get(int arg0) {
		return null;
	}

	@Override
	public int indexOf(Object arg0) {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return streams.isEmpty();
	}

	@Override
	public Iterator<PrintWriter> iterator() {
		return streams.iterator();
	}

	@Override
	public int lastIndexOf(Object o) {
		return streams.lastIndexOf(o);
	}

	@Override
	public ListIterator<PrintWriter> listIterator() {
		return streams.listIterator();
	}

	@Override
	public ListIterator<PrintWriter> listIterator(int arg0) {
		return null;
	}

	@Override
	public boolean remove(Object o) {
		return streams.remove(o);
	}

	@Override
	public PrintWriter remove(int index) {
		return streams.remove(index);
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return null;
	}
}
