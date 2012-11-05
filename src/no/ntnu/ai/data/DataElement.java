package no.ntnu.ai.data;

import java.util.List;
/**
 * Represents a single instance of Data, which contains any number of features
 * and a classification of the instance.
 * @author Trond
 *
 */
public interface DataElement extends List<DataItem>{
	public int getClassification();
	

}
