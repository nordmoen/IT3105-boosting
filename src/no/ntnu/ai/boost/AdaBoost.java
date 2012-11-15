package no.ntnu.ai.boost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import no.ntnu.ai.data.DataElement;
import no.ntnu.ai.hypothesis.Generator;
import no.ntnu.ai.hypothesis.Hypothesis;

public class AdaBoost<T, T2> {
	private final List<Generator<T, T2>> generators;
	private final List<DataElement<T, T2>> data;
	private final Map<Integer, Double> weights;
	private final Map<String, List<Double>> errorMap;
	private boolean hasRun = false;
	private final double diffClasses;
	
	public AdaBoost(List<Generator<T, T2>> generators, List<DataElement<T, T2>> data){
		this.generators = generators;
		this.data = data;
		weights = new HashMap<Integer, Double>();
		for(int i = 0; i < data.size(); i++){
			weights.put(i, 1.0/data.size());
		}
		Set<T2> classes = new HashSet<T2>();
		for(DataElement<T, T2> dat : data){
			classes.add(dat.getClassification());
		}
		this.diffClasses = classes.size();
		errorMap = new HashMap<String, List<Double>>();
	}
	
	public List<Hypothesis<T, T2>> runBooster(){
		List<Hypothesis<T, T2>> hypotheses = new ArrayList<Hypothesis<T,T2>>();
		for(int i = 0; i < this.generators.size(); i++){
			Generator<T, T2> g = this.generators.get(i);
			Hypothesis<T, T2> current = g.generateHypothesis(this.data, this.weights);
			if(updateWeights(current)){
				hypotheses.add(current);
			}else{
				System.out.println("Discarded classifier when updating weights");
				i--;
			}
		}
		hasRun = true;
		return hypotheses;
	}
	
	private boolean updateWeights(Hypothesis<T, T2> h){
		double error = 0;
		for(int i = 0; i < this.weights.size(); i++){
			DataElement<T, T2> d = data.get(i);
			if(!h.classify(d).equals(d.getClassification())){
				error += weights.get(i);
			}
		}
		if(error > (this.diffClasses -1) / this.diffClasses){
			return false;
		}
		for(int i = 0; i < this.weights.size(); i++){
			DataElement<T, T2> d = data.get(i);
			if(h.classify(d).equals(d.getClassification())){
				this.weights.put(i, this.weights.get(i)*(error/(1-error)*
						(this.diffClasses-1)));
			}
		}
		double sum = 0;
		for(Double w : this.weights.values()){
			sum += w;
		}
		for(int classi : weights.keySet()){
			this.weights.put(classi, weights.get(classi)/sum);
		}
		
		String boosterName = h.getClass().getName();
		if(!errorMap.containsKey(boosterName)){
			errorMap.put(boosterName, new ArrayList<Double>());
		}
		errorMap.get(boosterName).add(error);
		
		h.setWeight(Math.log((1-error)/error)/Math.log(2));
		
		if(error == 0){
			System.err.println("Error equals 0");
		}
		
		return true;
	}
	
	public double getAvg(String booster){
		if(!hasRun){
			throw new RuntimeException("Adaboost algorithm must run before " +
					"calculating average");
		}
		double sum = 0.0;
		List<Double> errorList = this.errorMap.get(booster);
		for(Double d : errorList){
			sum += d;
		}
		return sum / errorList.size();
	}
	
	public double getStdDev(String booster){
		if(!hasRun){
			throw new RuntimeException("Adaboost algorithm must run before " +
					"calculating average");
		}
		double sum = 0.0;
		double avg = this.getAvg(booster);
		List<Double> errorList = this.errorMap.get(booster);
		for(Double d : errorList){
			sum += Math.pow(d - avg, 2);
		}
		return Math.sqrt(sum / errorList.size());
	}

}
