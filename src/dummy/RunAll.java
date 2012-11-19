package dummy;

import no.ntnu.ai.ui.UserInterface;

public class RunAll {
	public static void main(String[] args) {
		String file = "training_sets/yeast.txt";
		String filter = "no.ntnu.ai.filter.YeastFilter";

		String[] uiArgs = getConfig(file, filter);
		for(String uiArg : uiArgs){
			System.out.println(uiArg);
			UserInterface.main(uiArg.split(" "));
			System.out.println("-----------------------------------------------------------------");
		}
		System.out.println("***************************************");
	}

	private static String[] getConfig(String file, String filter) {
		String[] configs = {"--global 0.2 42 --file " + file + " --filter " + filter + " --classifier no.ntnu.ai.classifiers.nbc.NBCGenerator 1",
				"--global 0.2 42 --file " + file + " --filter " + filter + " --classifier no.ntnu.ai.classifiers.dtc.DTCGenerator 1",
				"--global 0.2 42 --file " + file + " --filter " + filter + " --classifier no.ntnu.ai.classifiers.nbc.NBCGenerator 5",
				"--global 0.2 42 --file " + file + " --filter " + filter + " --classifier no.ntnu.ai.classifiers.nbc.NBCGenerator 10",
				"--global 0.2 42 --file " + file + " --filter " + filter + " --classifier no.ntnu.ai.classifiers.nbc.NBCGenerator 20",
				"--global 0.2 42 --file " + file + " --filter " + filter + " --classifier no.ntnu.ai.classifiers.dtc.DTCGenerator 5",
				"--global 0.2 42 --file " + file + " --filter " + filter + " --classifier no.ntnu.ai.classifiers.dtc.DTCGenerator 10 1",
				"--global 0.2 42 --file " + file + " --filter " + filter + " --classifier no.ntnu.ai.classifiers.dtc.DTCGenerator 10 2",
				"--global 0.2 42 --file " + file + " --filter " + filter + " --classifier no.ntnu.ai.classifiers.dtc.DTCGenerator 10",
				"--global 0.2 42 --file " + file + " --filter " + filter + " --classifier no.ntnu.ai.classifiers.dtc.DTCGenerator 20",
				"--global 0.2 42 --file " + file + " --filter " + filter + " --classifier no.ntnu.ai.classifiers.nbc.NBCGenerator 5 --classifier no.ntnu.ai.classifiers.dtc.DTCGenerator 5 2",
				"--global 0.2 42 --file " + file + " --filter " + filter + " --classifier no.ntnu.ai.classifiers.nbc.NBCGenerator 10 --classifier no.ntnu.ai.classifiers.dtc.DTCGenerator 10 2",
				"--global 0.2 42 --file " + file + " --filter " + filter + " --classifier no.ntnu.ai.classifiers.nbc.NBCGenerator 20 --classifier no.ntnu.ai.classifiers.dtc.DTCGenerator 20 2"};
		return configs;
	};
}

