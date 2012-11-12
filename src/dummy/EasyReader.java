package dummy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class EasyReader {
	public static void main(String[] args) {
		String filename = "training sets/glass.txt";
		int numAtts = 10;

		Double[] minVals = new Double[numAtts];
		Double[] maxVals = new Double[numAtts];
		Double[] avgVals = new Double[numAtts];
		for(int i=0; i<numAtts; i++){
			minVals[i] = Double.POSITIVE_INFINITY;
			maxVals[i] = Double.NEGATIVE_INFINITY;
			avgVals[i] = 0.0;
		}
		int numLines = 0;
		
		FileInputStream fis;
		Scanner scan = null;
		
		try{
			fis = new FileInputStream(new File(filename));
			scan = new Scanner(fis);
			while (scan.hasNextLine()){
				numLines ++;
				String[] line = scan.nextLine().split(",");
				Double[] features = new Double[line.length];
				for(int i=0; i<line.length; i++){
					features[i] = Double.parseDouble(line[i]);
					if(features[i] < minVals[i]){
						minVals[i] = features[i];
					}
					if(features[i] > maxVals[i]){
						maxVals[i] = features[i];
					}
					avgVals[i] += features[i];
				}
			}
			fis.close();
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
		
		for(int i=0; i<numAtts; i++){
			avgVals[i] /= numLines;
		}
		
		System.out.println("Maximum Values for each attribute:");
		System.out.println(Arrays.toString(maxVals));
		System.out.println("Minimum Values for each attribute:");
		System.out.println(Arrays.toString(minVals));
		System.out.println("Average Values for each attribute:");
		System.out.println(Arrays.toString(avgVals));
		
	}

}
