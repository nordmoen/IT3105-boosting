package no.ntnu.ai.classifiers.dtc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import no.ntnu.ai.data.DataElement;
import no.ntnu.ai.hypothesis.Generator;
import no.ntnu.ai.hypothesis.Hypothesis;

public class DTCGenerator<T extends Number, T2> implements Generator<T, T2>{
	private int maxDepth = -1;
	private T2 defaultClassi;
	private final DTCTools<T, T2> tools = new DTCTools<T, T2>();

	@Override
	public Hypothesis<T, T2> generateHypothesis(List<DataElement<T, T2>> data,
			Map<Integer, Double> weights) {
		if(this.maxDepth == -1){
			this.maxDepth = data.get(0).size();
		}
		
		Map<T2, Double> classWeights = new HashMap<T2, Double>();
		for(int i=0; i<data.size(); i++){
			T2 classi = data.get(i).getClassification();
			if(!classWeights.containsKey(classi)){
				classWeights.put(classi, 0.0);
			}
			classWeights.put(classi, classWeights.get(classi) + weights.get(i));
		}
		
		double maxWeight = 0.0;
		for(T2 key: classWeights.keySet()){
			if(classWeights.get(key)>maxWeight){
				defaultClassi = key;
				maxWeight = classWeights.get(key);
			}
		}
		
		
		boolean[] used = new boolean[data.get(0).size()];
		Map<Integer, Set<T>> possVals = new HashMap<Integer, Set<T>>();

		for(int i=0; i<data.get(0).size(); i++){
			possVals.put(i, new HashSet<T>());
		}

		for(DataElement<T, T2> dat: data){
			for(int i=0; i<dat.size(); i++){
				possVals.get(i).add(dat.get(i));
			}
		}

		Node<T, T2> start = new Node<T, T2>(-1, null, this.defaultClassi);


		return createTree(start, data, weights, used, 0, possVals);
	}

	@Override
	public void initialize(List<String> options) {
		if(options.size()>0){
			this.maxDepth = Integer.parseInt(options.get(0));
		}
	}

	private Node<T, T2> createTree(Node<T, T2> current, List<DataElement<T, T2>> data,
			Map<Integer, Double> weights, boolean[] used, int depth,
			Map<Integer, Set<T>> possVals){
		if(data == null){
			return new Node<T, T2>(-1, this.defaultClassi, this.defaultClassi);
		}
		double minVal = Double.POSITIVE_INFINITY;
		int index = -1;
		for(int j=0; j<used.length; j++){
			if(!used[j]){
				double tmp = this.tools.splitEntropy(j, data, weights);
				if(tmp<minVal){
					index = j;
					minVal = tmp;
				}
			}
		}

		if(depth == this.maxDepth || data.size() == 1){
			T2 classi = null;
			double maxWeight = 0.0;
			Map<T2, Double> weightSums= new HashMap<T2, Double>();
			for(int k = 0; k<data.size(); k++){
				T2 classific = data.get(k).getClassification();
				if(!weightSums.containsKey(classific)){
					weightSums.put(classific, 0.0);
				}
				weightSums.put(classific, weightSums.get(classific) + weights.get(k));
			}
			for(T2 key: weightSums.keySet()){
				if(weightSums.get(key)> maxWeight){
					maxWeight = weightSums.get(key);
					classi = key;
				}
			}
			return new Node<T, T2>(-1, classi, this.defaultClassi);
		}else{
			current.setIndex(index);
			used[index] = true;
			
			T2 tmp = data.get(0).getClassification();
			boolean equals = true;
			for(DataElement<T, T2> dat : data){
				if(!dat.getClassification().equals(tmp)){
					equals = false;
					break;
				}
			}
			if(equals){
				return new Node<T, T2>(-1, tmp, this.defaultClassi);
			}

			Map<T, List<DataElement<T, T2>>> valMap = new HashMap<T, List<DataElement<T,T2>>>();
			Map<T, Map<Integer, Double>> weightMap = new HashMap<T, Map<Integer,Double>>();

			for(int i=0; i<data.size(); i++){
				T val = data.get(i).get(index);
				if(!valMap.containsKey(val)){
					valMap.put(val, new ArrayList<DataElement<T,T2>>());
					weightMap.put(val, new HashMap<Integer, Double>());
				}
				valMap.get(val).add(data.get(i));
				weightMap.get(val).put(valMap.get(val).size()-1, weights.get(i));
				//			System.out.println(weightMap);
			}
			for(T val : possVals.get(index)){
				boolean[] childUsed = used.clone();
				current.addChild(val, createTree(new Node<T, T2>(-1, null, this.defaultClassi), valMap.get(val),
						weightMap.get(val), childUsed, depth+1, possVals));
			}
		}
		return current;
	}

}
