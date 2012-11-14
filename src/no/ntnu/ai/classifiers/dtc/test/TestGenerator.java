package no.ntnu.ai.classifiers.dtc.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.ntnu.ai.classifiers.dtc.DTCGenerator;
import no.ntnu.ai.classifiers.dtc.Node;
import no.ntnu.ai.data.DataElement;
import no.ntnu.ai.file.parser.FileParser;
import no.ntnu.ai.filter.Filter;
import no.ntnu.ai.hypothesis.Hypothesis;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestGenerator {
	private static List<DataElement<Integer, Integer>> data2;
	private static Map<Integer, Double> weights;

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
		Hypothesis<Integer, Integer> h = gen.generateHypothesis(data2, weights);

		List<Integer> test1 = new ArrayList<Integer>();
		test1.add(1);
		test1.add(1);
		test1.add(1);
		int res1 = h.classify(test1);
		assertEquals(1, res1);

		List<Integer> test2 = new ArrayList<Integer>();
		test2.add(1);
		test2.add(1);
		test2.add(3);
		int res2 = h.classify(test2);
		assertEquals(1, res2);
	}

	@Test
	public void testMaxDepth(){
		for(int i = 1; i < data2.get(0).size(); i++){
			DTCGenerator<Integer, Integer> gen = new DTCGenerator<Integer, Integer>();
			List<String> options = new ArrayList<String>();
			options.add("" + i);
			gen.initialize(options);
			Hypothesis<Integer, Integer> h = gen.generateHypothesis(data2, weights);
			Node<Integer, Integer> n = (Node<Integer, Integer>) h;
			assertEquals(i, n.size());
		}
	}

}
