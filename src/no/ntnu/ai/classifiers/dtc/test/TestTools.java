package no.ntnu.ai.classifiers.dtc.test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.ntnu.ai.classifiers.dtc.DTCTools;
import no.ntnu.ai.data.DataElement;
import no.ntnu.ai.file.parser.FileParser;
import no.ntnu.ai.filter.Filter;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestTools {

	private static List<DataElement<Integer, Integer>> data2;
	private static List<DataElement<Integer, Integer>> data;
	private static Map<Integer, Double> weights;
	private static Map<Integer, Double> w2;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		/*
		 *Test data is of the following form and copies the values in
		 *the project description
		 *Small/Large, Red/Green/Blue, Circle/Star/Diamond, P/N
		 *Small = 0, Large=1
		 *Red = 1, Green = 2, Blue = 3
		 *Circle = 1, Star = 2, Diamond = 3
		 *P = 1, N = 0 
		 */
		FileParser parser = new FileParser();
		parser.initialize("training sets/test.txt");
		Filter<Integer, Integer> filter = new TestFilter();
		data2 = filter.convert(parser.getData());
		weights = new HashMap<Integer, Double>();
		for(int i = 0; i < data2.size(); i++){
			weights.put(i, 1.0);
		}
		
		parser = new FileParser();
		parser.initialize("training sets/test2.txt");
		data = filter.convert(parser.getData());
		w2 = new HashMap<Integer,Double>();
		w2.put(0,0.05);
		w2.put(1,0.03);
		w2.put(2,0.02);
		w2.put(3,0.1);
		w2.put(4,0.1);
		w2.put(5,0.01);
		w2.put(6,0.02);
		w2.put(7,0.02);
	}

	@Test
	public void testEntropy() {
		DTCTools<Integer, Integer> tool = new DTCTools<Integer, Integer>();
		assertEquals(0.954, tool.entropy(data2), 0.0009);
	}
	
	@Test
	public void testSplitEntropy(){
		DTCTools<Integer, Integer> tool = new DTCTools<Integer, Integer>();
		assertEquals(0.795, tool.splitEntropy(0, data2, weights), 0.0009);
		assertEquals(0.873, tool.splitEntropy(1, data2, weights), 0.0009);
		assertEquals(0.950, tool.splitEntropy(2, data2, weights), 0.009);
	}
	
	@Test
	public void testSplitEntropyWeights(){
		DTCTools<Integer, Integer> tool = new DTCTools<Integer, Integer>();
		assertEquals(0.964, tool.splitEntropy(2, data, w2), 0.001);
	}

}
