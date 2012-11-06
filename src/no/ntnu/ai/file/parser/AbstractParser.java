package no.ntnu.ai.file.parser;
/**
 * An abstract class which should be extended by all filereaders that wish to
 * interact with this project. 
 * @author Trond
 *
 */
public abstract class AbstractParser<T, T2> implements Parser<T, T2>{
	/**
	 * The file to be read by the parser.
	 */
	protected String filename;
	
	public void initialize(String filename){
		this.filename = filename;
	}

}
