package no.ntnu.ai.classifiers.nbc;

import java.util.ArrayList;
import java.util.List;

import no.ntnu.ai.data.DataElement;

public class ProbGiven<T, T2> {
	private final List<DataElement<T, T2>> instances;
	private final List<Double> weights;
	private double wSum = 0.0;
	
	ProbGiven(){
		this.instances = new ArrayList<DataElement<T, T2>>();
		this.weights = new ArrayList<Double>();
	}
	
	public void addClass(DataElement<T, T2> classi, double weight){
		this.instances.add(classi);
		this.weights.add(weight);
		wSum += weight;
	}
	
	public double calc(int attribute, T attVal){
		double result = 0.0;
		for(int i = 0; i < this.instances.size(); i++){
			if(instances.get(i).get(attribute).equals(attVal)){
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
