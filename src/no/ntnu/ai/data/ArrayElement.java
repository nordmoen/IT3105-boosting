package no.ntnu.ai.data;

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
public class ArrayElement<T, T2> implements DataElement<T, T2> {
	
	private final ArrayList<T> list = new ArrayList<T>();
	private final T2 classification;
	
	public ArrayElement(T2 classification){
		this.classification = classification;
	}
	
	public ArrayElement(T[] elements, T2 classif){
		this(classif);
		for(T elem : elements){
			this.list.add(elem);
		}
	}

	@Override
	public boolean add(T arg0) {
		return this.list.add(arg0);
	}

	@Override
	public void add(int arg0, T arg1) {
		this.list.add(arg0, arg1);
	}

	@Override
	public boolean addAll(Collection<? extends T> arg0) {
		return this.list.addAll(arg0);
	}

	@Override
	public boolean addAll(int arg0, Collection<? extends T> arg1) {
		return this.list.addAll(arg0, arg1);
	}

	@Override
	public void clear() {
		this.list.clear();
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
	public boolean remove(Object arg0) {
		return this.list.remove(arg0);
	}

	@Override
	public T remove(int arg0) {
		return this.list.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return this.list.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return this.list.retainAll(arg0);
	}

	@Override
	public T set(int arg0, T arg1) {
		return this.list.set(arg0, arg1);
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

}
