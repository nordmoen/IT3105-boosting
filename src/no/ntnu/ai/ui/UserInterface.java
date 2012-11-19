package no.ntnu.ai.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import no.ntnu.ai.boost.AdaBoost;
import no.ntnu.ai.data.DataElement;
import no.ntnu.ai.file.parser.FileParser;
import no.ntnu.ai.file.parser.Parser;
import no.ntnu.ai.filter.Filter;
import no.ntnu.ai.hypothesis.Generator;
import no.ntnu.ai.hypothesis.Hypothesis;


public class UserInterface {

	private final static String FILE_STRING = "file";
	private final static String CLASSIFIER_STRING = "classifier";
	private final static String GLOBAL_OPTIONS = "global";
	private final static String FILTER_STRING = "filter";

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

	public static Filter<Object, Object> parseFilter(List<String> options){
		try {
			@SuppressWarnings("unchecked")
			Filter<Object, Object> f = (Filter<Object, Object>) Class.forName(options.get(1)).newInstance();
			f.initialize(options.subList(2, options.size()));
			return f;
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
			}else if(s.equalsIgnoreCase("--" + FILTER_STRING) || s.equalsIgnoreCase("-fi")){
				result.add(new ArrayList<String>());
				i++;
				result.get(i).add(FILTER_STRING);
			}else{
				result.get(i).add(s);
			}
		}
		return result;
	}

	/**
	 * @param args
	 */
	@SuppressWarnings({ "unchecked" })
	public static void main(String[] args) {
		if(args.length > 1 && !args[0].equalsIgnoreCase("--help")){
			List<List<String>> options = parseCommandLine(args);
			List<Generator<?,?>> classifierGenerators = new ArrayList<Generator<?,?>>();
			Parser<String, String> dataParser = null;
			Filter<Object, Object> dataFilter = null;
			double percentage = 0.2;
			int randomValue = (int) System.currentTimeMillis();
			for(List<String> option : options){
				if(option.get(0).equalsIgnoreCase(CLASSIFIER_STRING)){
					classifierGenerators.addAll(parseClassifier(option));
				}else if(option.get(0).equalsIgnoreCase(FILE_STRING)){
					dataParser = new FileParser();
					dataParser.initialize(option.get(1));
				}else if(option.get(0).equalsIgnoreCase(GLOBAL_OPTIONS)){
					percentage = (option.size() > 1 ? Double.parseDouble(option.get(1)) : 0.2);
					randomValue = (option.size() > 2 ? Integer.parseInt(option.get(2)) :
						(int)System.currentTimeMillis());
				}else if(option.get(0).equalsIgnoreCase(FILTER_STRING)){
					dataFilter = parseFilter(option);
				}else{
					System.err.println("Did not recoqnize the option: '" + 
							option.get(0) + "'");
				}
			}
			//Shuffle data
			List<?> data = dataFilter.convert(dataParser.getData());
			Collections.shuffle(data, new Random(randomValue));
			List<DataElement<?, ?>> training = (List<DataElement<?, ?>>) data.subList(0, (int)(data.size() - data.size()*percentage));
			List<DataElement<?, ?>> test = (List<DataElement<?, ?>>) data.subList((int)(data.size() - data.size()*percentage), data.size());
			//Use adaboost to obtain result:

			Collections.shuffle(classifierGenerators, new Random(randomValue));
			List<Hypothesis> hypotheses = new ArrayList();
			AdaBoost<?, ?> boost = new AdaBoost(classifierGenerators, training);
			List<?> hypos = boost.runBooster();
			hypotheses.addAll((Collection<? extends Hypothesis>) hypos);
			
			Map<String, Integer> boosterCount = new HashMap<String, Integer>();
			for(Hypothesis<Object, Object> h : hypotheses){
				String name = h.getClass().getName();
				if(!boosterCount.containsKey(name)){
					boosterCount.put(name, 0);
				}
				boosterCount.put(name, boosterCount.get(name) + 1);
			}
			
			for(String s : boosterCount.keySet()){
				System.out.println(s + "(" + boosterCount.get(s) + "):");
				System.out.println("\tTraining avg error: " + boost.getAvg(s) + 
						", std dev of error: " + boost.getStdDev(s));
			}


			System.out.println("");
			int error = 0;
			for(DataElement dat : test){
				Map<Object, Double> classStrength = new HashMap<Object, Double>();
				for(Object o : hypotheses){
					Hypothesis h = (Hypothesis) o;
					Object classi = h.classify(dat);
					if(!classStrength.containsKey(classi)){
						classStrength.put(classi, 0.0);
					}
					classStrength.put(classi, classStrength.get(classi) + h.getWeight());
				}
				Object max = null;
				double maxVal = -1;
				for(Object o : classStrength.keySet()){
					if(classStrength.get(o) > maxVal){
						max = o;
						maxVal = classStrength.get(o);
					}
				}
				if(!dat.getClassification().equals(max)){
					error ++;
				}
			}
			int posError = (int) (((double)error / test.size()) * 100);
			System.out.println("Test error: " + error + " of " + test.size() + 
					" (" + posError + "%), Correct classified: " + 
					(test.size() - error) + " of " + test.size() + " (" + (100-posError) + "%)");

		}else{
			String classy = "--" + CLASSIFIER_STRING + " classname " +
					"numberOfClassifiers [options]";
			System.out.println("Usage:");
			System.out.println("java " + UserInterface.class.getName() + 
					" [--" + GLOBAL_OPTIONS + " percentageOfTestData randomKey]" +
					" --" + FILE_STRING + " name" +
					" --" + FILTER_STRING + " filter " +
					classy +
					" [" + classy + "]");
		}
	}

}
