package no.ntnu.ai.filter;

import java.util.List;

import no.ntnu.ai.data.DataElement;

public interface Filter<T, T2, T3> {
	public List<DataElement<T, T2>> convert(List<DataElement<T3, T2>> input);
	
	public void initialize(List<String> options);
}
