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
			if(!children.isEmpty()){
				//This might be better for classification as the default for the
				//whole tree might be far away from what it should be for this
				//subtree
				return averageChildClassification(input);
			}else{
				return this.defaultClassification;
			}
		}
		
		return child.classify(input);
	}
	
	private T2 averageChildClassification(List<T> input){
		Map<T2, Integer> accounts = new HashMap<T2, Integer>();
		for(Node<T, T2> child : this.children.values()){
			T2 classi = child.classify(input);
			if(!accounts.containsKey(classi)){
				accounts.put(classi, 0);
			}
			accounts.put(classi, accounts.get(classi) + 1);
		}
		
		T2 result = null;
		int max = -1;
		for(T2 key : accounts.keySet()){
			if(accounts.get(key) > max){
				result = key;
				max = accounts.get(key);
			}
		}
		return result;
	}
	
	@Override
	public String toString(){
		return "Node:\n\tClassification: " + this.classification + "\n\tIndex: " + this.index +
		"\n\t\tChildren: " + this.children;
	}
	
	/**
	 * Get the size of the tree
	 * @return - The size of the tree
	 */
	public int size(){
		if(this.hasClassification()){
			//We are a stub
			return 0;
		}else{
			//Return the "deepest" of the children plus one
			int max = -1;
			for(Node<T, T2> n : this.children.values()){
				int childSize = n.size();
				if(childSize > max){
					max = childSize;
				}
			}
			return max + 1;
		}
	}
	
}
