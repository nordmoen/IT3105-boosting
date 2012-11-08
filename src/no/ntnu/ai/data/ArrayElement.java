package no.ntnu.ai.data;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * An implementation of DataElement backed by an ArrayList
 * @author Jørgen
 *
 * @param <T> - The type of the features
 * @param <T2> - The type of the classification	
 */
public class ArrayElement<T, T2> extends AbstractList<T> implements DataElement<T, T2> {
	
	private final ArrayList<T> list = new ArrayList<T>();
	private final T2 classification;
	
	/**
	 * Constructor for ArrayElement
	 * @param elements - The elements to add in this instance
	 * @param classif - The classification of this instance
	 */
	public ArrayElement(T[] elements, T2 classif){
		this.classification = classif;
		for(T elem : elements){
			this.list.add(elem);
		}
	}

	@Override
	public boolean contains(Object arg0) {
		return this.list.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return this.list.containsAll(arg0);
	}

	@Override
	public T get(int arg0) {
		return this.list.get(arg0);
	}
	
	public List<T> cloneList(){
		return this.list;
	}

	@Override
	public int indexOf(Object arg0) {
		return this.list.indexOf(arg0);
	}

	@Override
	public boolean isEmpty() {
		return this.list.isEmpty();
	}

	@Override
	public Iterator<T> iterator() {
		return this.list.iterator();
	}

	@Override
	public int lastIndexOf(Object arg0) {
		return this.list.lastIndexOf(arg0);
	}

	@Override
	public ListIterator<T> listIterator() {
		return this.list.listIterator();
	}

	@Override
	public ListIterator<T> listIterator(int arg0) {
		return this.list.listIterator(arg0);
	}

	@Override
	public int size() {
		return this.list.size();
	}

	@Override
	public List<T> subList(int arg0, int arg1) {
		return this.list.subList(arg0, arg1);
	}

	@Override
	public Object[] toArray() {
		return this.list.toArray();
	}

	@SuppressWarnings("hiding")
	@Override
	public <T> T[] toArray(T[] arg0) {
		return this.list.toArray(arg0);
	}

	@Override
	public T2 getClassification() {
		return this.classification;
	}
	
	@Override
	public String toString(){
		return "Classification: " + this.classification + ", Data: " + this.list;
	}
}
