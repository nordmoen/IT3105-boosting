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
	protected Integer val00(Integer val) {
		return val;
	}

	@Override
	protected Integer val01(Integer val) {
		return val;
	}

	@Override
	protected Integer val02(Integer val) {
		return val;
	}

	@Override
	protected Integer val03(Integer val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Integer val04(Integer val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Integer val05(Integer val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Integer val06(Integer val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Integer val07(Integer val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Integer val08(Integer val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Integer val09(Integer val) {
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

	@Override
	protected Integer val15(Integer val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Integer val16(Integer val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Integer val17(Integer val) {
		// TODO Auto-generated method stub
		return null;
	}

}
