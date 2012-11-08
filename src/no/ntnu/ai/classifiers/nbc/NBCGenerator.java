package no.ntnu.ai.classifiers.nbc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.ntnu.ai.data.DataElement;
import no.ntnu.ai.hypothesis.Generator;
import no.ntnu.ai.hypothesis.Hypothesis;

public class NBCGenerator<T extends Number, T2> implements Generator<T, T2>{
	

	@Override
	public Hypothesis<T, T2> generateHypothesis(List<DataElement<T, T2>> data, List<Double> weights) {
		Map<T2, Integer> occurences = new HashMap<T2, Integer>();
		for(DataElement<T, T2> dat : data){
			if(!occurences.containsKey(dat.getClassification())){
				occurences.put(dat.getClassification(), 0);
			}
			occurences.put(dat.getClassification(), occurences.get(dat.getClassification()) + 1);
		}
		
		Map<T2, Double> aProbs = new HashMap<T2, Double>();
		for(T2 key : occurences.keySet()){
			aProbs.put(key, (double)(occurences.get(key) / data.size()));
		}
		
		List<ProbGiven<T2>> attProbs = new ArrayList<ProbGiven<T2>>();
		for(int i=0; i<data.get(0).size(); i++){
			attProbs.add(new ProbGiven<T2>());
			for(T2 classi : occurences.keySet()){
				double avg = findAvg(classi, i, data);
				double var = findVar(classi, i, data, avg);
				attProbs.get(i).addClassi(classi, avg, var);
			}
		}

		return new NBClassifier<T, T2>(aProbs, attProbs);
	}


	private double findVar(T2 classi, int i, List<DataElement<T, T2>> data,
			double avg) {
		double occ = 0.0;
		double sum = 0.0;
		for(DataElement<T, T2> dat: data){
			if(dat.getClassification().equals(classi)){
				occ++;
				sum += Math.pow(dat.get(i).doubleValue()-avg, 2);					
			}
		}
		return sum/occ;
	}


	private double findAvg(T2 classi, int i, List<DataElement<T, T2>> data) {
		int occ = 0;
		double sum = 0;
		for(DataElement<T, T2> dat: data){
			if(dat.getClassification().equals(classi)){
				occ++;
				sum += dat.get(i).doubleValue();					
			}
		}
		return sum/occ;
	}




	@Override
	public void initialize(List<String> options) {
		// TODO Auto-generated method stub
		
	}

}
