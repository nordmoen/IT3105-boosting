package no.ntnu.ai.classifiers.dtc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.ntnu.ai.classifiers.Classifier;

public class Node<T, T2> extends Classifier<T, T2>{
	private int index;
	private final T2 classification;
	private final T2 defaultClassification;
	private final Map<T, Node<T, T2>> children;

	Node(int index, T2 classification, T2 defClassi){
		this.index = index;
		this.classification = classification;
		this.defaultClassification = defClassi;
		this.children = new HashMap<T, Node<T,T2>>();
	}
	
	public boolean hasClassification(){
		return this.classification != null;
	}
	
	public void addChild(T value, Node<T, T2> child){
		this.children.put(value, child);
	}
	
	public void setIndex(int index){
		this.index = index;
	}
	
	@Override
	public T2 classify(List<T> input) {
		if(this.hasClassification()){
			return this.classification;
		}
		T value = input.get(this.index);
		Node<T, T2> child = this.children.get(value);
		if(child == null){
			return this.defaultClassification;
		}
		
		return child.classify(input);
	}
	
	@Override
	public String toString(){
		return "Node:\n\tClassification: " + this.classification + "\n\tIndex: " + this.index +
		"\n\t\tChildren: " + this.children;
	}
	

}
