package no.ntnu.ai.classifiers.dtc.test;

import java.util.List;

import no.ntnu.ai.filter.AbstractFilter;

public class TestFilter extends AbstractFilter<Integer, Integer, Integer> {

	@Override
	public void initialize(List<String> options) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Integer convertClassification(String classif) {
		return Integer.parseInt(classif);
	}

	@Override
	protected Integer convertValue(String value) {
		return Integer.parseInt(value);
	}

	@Override
	protected Integer val0(Integer val) {
		return val;
	}

	@Override
	protected Integer val1(Integer val) {
		return val;
	}

	@Override
	protected Integer val2(Integer val) {
		return val;
	}

	@Override
	protected Integer val3(Integer val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Integer val4(Integer val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Integer val5(Integer val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Integer val6(Integer val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Integer val7(Integer val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Integer val8(Integer val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Integer val9(Integer val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Integer val10(Integer val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Integer val11(Integer val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Integer val12(Integer val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Integer val13(Integer val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Integer val14(Integer val) {
		// TODO Auto-generated method stub
		return null;
	}

}
