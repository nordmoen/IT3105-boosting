package no.ntnu.ai.classifiers.nbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.ntnu.ai.data.DataElement;
import no.ntnu.ai.hypothesis.Generator;
import no.ntnu.ai.hypothesis.Hypothesis;

public class NBCGenerator<T extends Number, T2> implements Generator<T, T2>{
	

	@Override
	public Hypothesis<T, T2> generateHypothesis(List<DataElement<T, T2>> data, Map<Integer, Double> weights) {
		Map<T2, Double> occurences = new HashMap<T2, Double>();
		Map<T2, ProbGiven<T, T2>> aProbs = new HashMap<T2, ProbGiven<T, T2>>();
		for(int i = 0; i < data.size(); i++){
			T2 classi = data.get(i).getClassification();
			if(!occurences.containsKey(classi)){
				occurences.put(classi, 0.0);
			}
			if(!aProbs.containsKey(classi)){
				aProbs.put(classi, new ProbGiven<T, T2>());
			}
			occurences.put(classi, occurences.get(
					classi) + 1);
			aProbs.get(i).addClass(data.get(i), weights.get(i));
		}
		for(T2 t : occurences.keySet()){
			occurences.put(t, occurences.get(t) / occurences.size());
		}

		return new NBClassifier<T, T2>(occurences, aProbs);
	}
	
	@Override
	public void initialize(List<String> options) {
		// TODO Auto-generated method stub
		
	}

}
