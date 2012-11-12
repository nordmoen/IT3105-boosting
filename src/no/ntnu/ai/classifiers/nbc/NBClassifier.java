package no.ntnu.ai.classifiers.nbc;

import java.util.List;
import java.util.Map;

import no.ntnu.ai.classifiers.Classifier;

public class NBClassifier<T extends Number, T2> extends Classifier<T, T2>{
	private final Map<T2, Double> occurs;
	private final Map<T2, ProbGiven<T, T2>> aProbs;

	@Override
	public T2 classify(List<T> input) {
		T2 max = null;
		double maxVal = -1.0;
		for(T2 classi : occurs.keySet()){
			double probProd = occurs.get(classi);
			for(int i=0; i < input.size(); i++){
				probProd *= aProbs.get(classi).calc(i, input.get(i));
			}
			if(probProd > maxVal){
				max = classi;
				maxVal = probProd;
			}
		}
		return max;
	}
	
	NBClassifier(Map<T2, Double> occurs, Map<T2, ProbGiven<T, T2>> aProbs){
		this.occurs = occurs;
		this.aProbs = aProbs;
	}
	
	
}
