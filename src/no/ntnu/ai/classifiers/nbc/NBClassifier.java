package no.ntnu.ai.classifiers.nbc;

import java.util.List;
import java.util.Map;

import no.ntnu.ai.classifiers.Classifier;

public class NBClassifier<T extends Number, T2> extends Classifier<T, T2>{
	private final Map<T2, Double> aProbs;
	private final List<ProbGiven<T2>> attProbs;

	@Override
	public T2 classify(List<T> input) {
		T2 max = null;
		double maxVal = -1.0;
		for(T2 classi : aProbs.keySet()){
			double probProd = aProbs.get(classi);
			for(int i=0; i<input.size(); i++){
				probProd *= attProbs.get(i).calc(classi, input.get(i).doubleValue());
			}
			if(probProd > maxVal){
				max = classi;
				maxVal = probProd;
			}
		}
		return max;
	}
	
	NBClassifier(Map<T2, Double> aProbs, List<ProbGiven<T2>> attProbs){
		this.aProbs = aProbs;
		this.attProbs = attProbs;
	}
	
	
}
