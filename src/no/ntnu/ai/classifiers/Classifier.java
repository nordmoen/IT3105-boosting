package no.ntnu.ai.classifiers;

import no.ntnu.ai.hypothesis.Hypothesis;

public abstract class Classifier<T, T2> implements Hypothesis<T, T2>{
	private double weight = 0.0;
	
	@Override
	public double getWeight(){
		return this.weight;
	}
	
	@Override
	public void setWeight(double w){
		this.weight = w;
	}

}
