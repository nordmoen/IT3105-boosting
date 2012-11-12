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

public class FileParser extends AbstractParser<String, String> {

	private boolean readData = false;
	private final List<DataElement<String, String>> data;

	public FileParser() {
		data = new ArrayList<DataElement<String, String>>();
	}

	@Override
	public List<DataElement<String, String>> getData() {
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
					String[] features = new String[line.length - 1];
					for(int i = 0; i < line.length - 1; i++){
						features[i] = line[i].trim();
					}
					DataElement<String, String> dataLine = 
							new ArrayElement<String, String>(features, 
									line[line.length - 1]);
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
