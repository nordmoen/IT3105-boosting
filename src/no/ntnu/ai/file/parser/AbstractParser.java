package no.ntnu.ai.file.parser;
/**
 * An abstract class which should be extended by all filereaders that wish to
 * interact with this project. 
 * @author Trond
 *
 */
public abstract class AbstractParser implements Parser{
	/**
	 * The file to be read by the parser.
	 */
	protected final String filename;
	
	AbstractParser(String filename){
		this.filename = filename;
	}

}
