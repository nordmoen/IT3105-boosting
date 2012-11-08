package no.ntnu.ai.hypothesis;

import java.util.List;

/**
 * An Hypothesis which must be able to classify a list of elements into one single
 * classification
 * @author jorgno
 *
 * @param <T> - The return type of the classification
 * @param <T2> - The type of the list elements to classify
 */
public interface Hypothesis<T, T2> {
	/**
	 * Classify the input into a classification
	 * @param input - The list to classify
	 * @return - The classification of the input
	 */
	public T2 classify(List<T> input);
	
	/**
	 * Get the weight of this classifier
	 * @return - The weight of this classifier
	 */
	public double getWeight();
	
	/**
	 * Set the weight of this classifier to the given weight
	 * @param weight - The new weight of this classifier
	 */
	public void setWeight(double weight);
}
