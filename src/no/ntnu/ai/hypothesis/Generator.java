package no.ntnu.ai.hypothesis;

import java.util.List;
import java.util.Map;

import no.ntnu.ai.data.DataElement;

/**
 * The generator creates hypothesis based on some weights passed to it, when
 * creating the hypothesis
 * @author jorgno
 *
 * @param <T> - The Type of list elements which the Hypothesis must classify
 * @param <T2> - The type of the classification returned by the Hypothesis
 */
public interface Generator<T, T2> {
	/**
	 * Initialize method of the given generator, this will be called once after
	 * the class is created to initialize the generator with its needed options
	 * @param options - The options passed to this class
	 */
	public void initialize(List<String> options);
	
	/**
	 * Generate an Hypothesis from the weights, the Hypothesis will be classify
	 * a list of T to a classification of type T2
	 * @param weights - A list of double weights signifying the current weights
	 * @return - A new Hypothesis generated from the weights
	 */
	public Hypothesis<T, T2> generateHypothesis(List<DataElement<T, T2>> data, final Map<Integer, Double> weights);
}
