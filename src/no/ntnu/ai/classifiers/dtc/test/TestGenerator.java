package no.ntnu.ai.classifiers.dtc.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.ntnu.ai.classifiers.dtc.DTCGenerator;
import no.ntnu.ai.data.DataElement;
import no.ntnu.ai.file.parser.FileParser;
import no.ntnu.ai.filter.Filter;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestGenerator {
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
	}

	@Test
	public void testGenerateHypothesis() {
		DTCGenerator<Integer, Integer> gen = new DTCGenerator<Integer, Integer>();
		System.out.println(gen.generateHypothesis(data2, weights));
	}

}
