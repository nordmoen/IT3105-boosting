package no.ntnu.ai.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.ntnu.ai.file.parser.Parser;


public class UserInterface {

	public static List<String> parseNBC(List<String> options, Parser parser){
		if(parser == null){
			throw new RuntimeException("Parser was null");
		}
		return null;
	}
	public static List<String> parseDTC(List<String> options, Parser parser){
		if(parser == null){
			throw new RuntimeException("Parser was null");
		}
		return null;
	}

	public static Parser parseFilename(List<String> options){
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
			}else if(s.equalsIgnoreCase("--filename") || s.equalsIgnoreCase("-f")){
				result.add(new ArrayList<String>());
				i++;
				result.get(i).add("file");
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
			List<String> parsers = new ArrayList<String>();
			Map<String, Parser> fileParsers = new HashMap<String, Parser>();
			try{
				for(List<String> option : options){
					if(option.get(0).equalsIgnoreCase("nbc")){
						parsers.addAll(parseNBC(option, fileParsers.get(option.get(1))));
					}else if(option.get(0).equalsIgnoreCase("dtc")){
						parsers.addAll(parseDTC(option, fileParsers.get(option.get(1))));
					}else if(option.get(0).equalsIgnoreCase("file")){
						fileParsers.put(option.get(1), parseFilename(option));
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
					" --filename filereader name [--filename filereader name]" +
					" --classifier classname filereader [options] " +
					"[--classifier classname filereader [options]]");
		}
	}

}
