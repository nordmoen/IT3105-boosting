package no.ntnu.ai.ui;

import java.util.ArrayList;
import java.util.List;

import no.ntnu.ai.file.parser.Parser;


public class UserInterface {

	public static List<String> parseNBC(List<String> options){
		return null;
	}
	public static List<String> parseDTC(List<String> options){
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
			Parser fileParser;
			for(List<String> option : options){
				if(option.get(0).equalsIgnoreCase("nbc")){
					parsers.addAll(parseNBC(option));
				}else if(option.get(0).equalsIgnoreCase("dtc")){
					parsers.addAll(parseDTC(option));
				}else if(option.get(0).equalsIgnoreCase("file")){
					fileParser = parseFilename(option);
				}else{
					System.err.println("Did not recoqnize the option: '" + 
							option.get(0) + "'");
				}
			}
		}else{
			System.out.println("Usage:");
			System.out.println("java " + UserInterface.class.getName() + 
					" --filename name filereader --classifier classname [options] " +
					"[--classifier classname [options]]");
		}
	}

}
