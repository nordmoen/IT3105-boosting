package no.ntnu.ai.file.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import no.ntnu.ai.data.ArrayElement;
import no.ntnu.ai.data.DataElement;

public class DoubleParser extends AbstractParser<Double, Integer> {

	private boolean readData = false;
	private final List<DataElement<Double, Integer>> data;

	public DoubleParser() {
		data = new ArrayList<DataElement<Double,Integer>>();
	}

	@Override
	public List<DataElement<Double, Integer>> getData() {
		if(!readData){
			if(this.filename.isEmpty()){
				throw new IllegalStateException("Filename is not initialized properly");
			}
			FileInputStream fis;
			Scanner scan = null;
			try {
				fis = new FileInputStream(new File(this.filename));
				scan = new Scanner(fis);
				while(scan.hasNextLine()){
					String[] line = scan.nextLine().split(",");
					Double[] features = new Double[line.length - 1];
					for(int i = 0; i < line.length - 1; i++){
						features[i] = Double.parseDouble(line[i]);
					}
					DataElement<Double, Integer> dataLine = 
							new ArrayElement<Double, Integer>(features, 
									Integer.parseInt(line[line.length - 1]));
					this.data.add(dataLine);
				}
				fis.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if(scan != null){
					scan.close();
				}
			}
		}
		return this.data;
	}

}
