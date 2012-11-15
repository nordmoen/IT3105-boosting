package no.ntnu.ai.classifiers.nbc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.ntnu.ai.data.DataElement;

public class ProbGiven<T, T2> {
	private final List<DataElement<T, T2>> instances;
	private final List<Double> weights;
	private final Map<Integer, Map<T, Double>> mem;
	private double wSum = 0.0;
	
	ProbGiven(){
		this.instances = new ArrayList<DataElement<T, T2>>();
		this.weights = new ArrayList<Double>();
		mem = new HashMap<Integer, Map<T,Double>>();
	}
	
	public void addClass(DataElement<T, T2> classi, double weight){
		this.instances.add(classi);
		this.weights.add(weight);
		wSum += weight;
	}
	
	public double calc(int attribute, T attVal){
		if(!mem.containsKey(attribute)){
			mem.put(attribute, new HashMap<T, Double>());
		}
		if(!mem.get(attribute).containsKey(attVal)){
			mem.get(attribute).put(attVal, calcValue(attribute, attVal));
		}
		return mem.get(attribute).get(attVal);
	}
	
	private double calcValue(int attr, T attVal){
		double result = 0.0;
		for(int i = 0; i < this.instances.size(); i++){
			if(instances.get(i).get(attr).equals(attVal)){
				result += this.weights.get(i);
			}
		}
		return result / this.wSum;
	}
	
	@Override
	public String toString(){
		return "ProbGiven: " + this.wSum +" , "+ this.instances;
	}
}
