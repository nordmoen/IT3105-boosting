package no.ntnu.ai.data;

import java.util.List;

import no.ntnu.ai.hypothesis.Hypothesis;
/**
 * Represents a single instance of some data, which contains any number of features
 * and a classification of the instance. The features can be of any type as long
 * as they are all of the same type.
 * @author Trond, Jørgen
 * @param <T> The type of the features in this DataElement
 * @param <T2> The type of the classification to this DataElement
 *
 */
public interface DataElement<T, T2> extends List<T>{
	/**
	 * Get the classification of the features contained in this DataElement
	 * @return - The classification of the features
	 */
	public T2 getClassification();
	
	/**
	 * Clone the underlying list into for use with {@link Hypothesis}
	 * @return - A list of data elements of type T
	 */
	public List<T> cloneList();
}
