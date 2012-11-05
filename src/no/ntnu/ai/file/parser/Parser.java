package no.ntnu.ai.file.parser;

import java.util.List;

import no.ntnu.ai.data.DataElement;
/**
 * Implemented by AbstractParser, all filereaders must implement this Interface. 
 * Most should extend the abstract parser.
 * @author Trond
 *
 */
public interface Parser{
	public List<DataElement<?,?>> getData();
}
