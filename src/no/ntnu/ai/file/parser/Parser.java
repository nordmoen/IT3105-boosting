package no.ntnu.ai.file.parser;

import no.ntnu.ai.data.Data;
/**
 * Implemented by AbstractParser, all filereaders must implement this Interface. 
 * Most should extend the abstract parser.
 * @author Trond
 *
 */
public interface Parser{
	public Data getData();
}
