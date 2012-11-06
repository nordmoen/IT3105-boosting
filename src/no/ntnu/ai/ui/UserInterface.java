package no.ntnu.ai.ui;

import java.util.ArrayList;
import java.util.List;

import no.ntnu.ai.data.DataElement;
import no.ntnu.ai.file.parser.Parser;
import no.ntnu.ai.hypothesis.Generator;


public class UserInterface {

	private final static String FILE_STRING = "file";
	private final static String CLASSIFIER_STRING = "classifier";
	private final static String GLOBAL_OPTIONS = "global";

	public static List<Generator<?,?>> parseClassifier(List<String> options){
		try {
			int numberOf = Integer.parseInt(options.get(2));
			List<Generator<?, ?>> result = new ArrayList<Generator<?,?>>(numberOf);
			for(int i = 0; i < numberOf; i++){
				Generator<?,?> g = (Generator<?, ?>) Class.forName(options.get(1)).newInstance();
				g.initialize(options.subList(3, options.size()));
				result.add(g);
			}
			return result;
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
			if(s.equalsIgnoreCase("--" + CLASSIFIER_STRING) || s.equalsIgnoreCase("-c")){
				result.add(new ArrayList<String>());
				i++;
				result.get(i).add(CLASSIFIER_STRING);
			}else if(s.equalsIgnoreCase("--" + FILE_STRING) || s.equalsIgnoreCase("-f")){
				result.add(new ArrayList<String>());
				i++;
				result.get(i).add(FILE_STRING);
			}else if(s.equalsIgnoreCase("--" + GLOBAL_OPTIONS) || s.equalsIgnoreCase("-g")){
				result.add(new ArrayList<String>());
				i++;
				result.get(i).add(GLOBAL_OPTIONS);
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
			List<Generator<?,?>> classifierGenerators = new ArrayList<Generator<?,?>>();
			Parser<?, ?> dataParser = null;
			double percentage = 0.25;
			for(List<String> option : options){
				if(option.get(0).equalsIgnoreCase(CLASSIFIER_STRING)){
					classifierGenerators.addAll(parseClassifier(option));
				}else if(option.get(0).equalsIgnoreCase(FILE_STRING)){
					dataParser = parseFilename(option);
				}else if(option.get(0).equalsIgnoreCase(GLOBAL_OPTIONS)){
					percentage = Double.parseDouble(option.get(1));
				}else{
					System.err.println("Did not recoqnize the option: '" + 
							option.get(0) + "'");
				}
			}
		}else{
			String classy = "--" + CLASSIFIER_STRING + " classname " +
					"numberOfClassifiers [options]";
			System.out.println("Usage:");
			System.out.println("java " + UserInterface.class.getName() + 
					" --" + GLOBAL_OPTIONS + " percentageOfTestData" +
					" --" + FILE_STRING + " filereader name " +
					classy +
					" [" + classy + "]");
		}
	}

}
