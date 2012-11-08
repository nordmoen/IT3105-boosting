package no.ntnu.ai.classifiers.nbc;

import java.util.HashMap;
import java.util.Map;

public class ProbGiven<T> {
	private final Map<T, Double> avg;
	private final Map<T, Double> vari;
	
	ProbGiven(){
		this.avg = new HashMap<T, Double>();
		this.vari = new HashMap<T, Double>();
	}
	
	public void addClassi(T classi, double avg, double stdDev){
		this.avg.put(classi, avg);
		this.vari.put(classi, stdDev);
	}
	
	public double calc(T classi, double value){
		double base = 1.0 / Math.sqrt(2 * Math.PI * this.vari.get(classi));
		double exp = -(Math.pow(value - this.avg.get(classi), 2) / 2 * this.vari.get(classi));
		return base * Math.exp(exp);
	}
}
