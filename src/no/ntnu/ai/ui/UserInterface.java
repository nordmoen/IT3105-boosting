package no.ntnu.ai.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.ntnu.ai.file.parser.Parser;
import no.ntnu.ai.hypothesis.Generator;


public class UserInterface {
	
	private final static String FILE_STRING = "file";
	private final static String CLASSIFIER_STRING = "classifier";

	public static Generator<?,?> parseClassifier(List<String> options){
		try {
			Generator<?,?> g = (Generator<?, ?>) Class.forName(options.get(1)).newInstance();
			g.initialize(options);
			return g;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("Could not find the specified class: " + options.get(1));
			System.exit(-1);
		}
		return null;
	}

	public static Parser<?, ?> parseFilename(List<String> options){
		try {
			Parser<?, ?> p = (Parser<?, ?>) Class.forName(options.get(1)).newInstance();
			p.initialize(options.get(2));
			return p;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("Could not find the specified class: " + options.get(1));
			System.exit(-1);
		}
		return null;
	}

	public static List<List<String>> parseCommandLine(String[] args){
		List<List<String>> result = new ArrayList<List<String>>();
		int i = -1;
		for(int j = 0; j < args.length; j++){
			String s = args[j];
			if(s.equalsIgnoreCase("--classifier") || s.equalsIgnoreCase("-c")){
				result.add(new ArrayList<String>());
				i++;
				result.get(i).add(CLASSIFIER_STRING);
			}else if(s.equalsIgnoreCase("--filename") || s.equalsIgnoreCase("-f")){
				result.add(new ArrayList<String>());
				i++;
				result.get(i).add(FILE_STRING);
			}else{
				result.get(i).add(s);
			}
		}
		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(args.length > 1 && !args[0].equalsIgnoreCase("--help")){
			List<List<String>> options = parseCommandLine(args);
			List<Generator<?,?>> parserGenerators = new ArrayList<Generator<?,?>>();
			Map<String, Parser<?,?>> fileParsers = new HashMap<String, Parser<?,?>>();
			try{
				for(List<String> option : options){
					if(option.get(0).equalsIgnoreCase(CLASSIFIER_STRING)){
						parserGenerators.add(parseClassifier(option));
					}else if(option.get(0).equalsIgnoreCase(FILE_STRING)){
						fileParsers.put(option.get(1), parseFilename(option));
						System.out.println(fileParsers.get(option.get(1)).getData());
					}else{
						System.err.println("Did not recoqnize the option: '" + 
								option.get(0) + "'");
					}
				}
			}catch(RuntimeException as){
				System.err.println("Could not find a file parser for the " +
						"specified command line option. Each --classifier need " +
						"to have a specified filereader object which must be" +
						"specified before the --classifier option");
				as.printStackTrace();
				System.exit(-1);
			}
		}else{
			System.out.println("Usage:");
			System.out.println("java " + UserInterface.class.getName() + 
					" --filename filereader name" +
					" --classifier classname [options] " +
					"[--classifier classname [options]]");
		}
	}

}
