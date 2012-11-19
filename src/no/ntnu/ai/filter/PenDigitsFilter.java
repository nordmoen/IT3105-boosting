package no.ntnu.ai.filter;

import java.util.List;

public class PenDigitsFilter extends AbstractFilter<Integer, Integer, Integer> {

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
		if(val<10) return 1;
		if(val<20) return 2;
		if(val<30) return 3;
		if(val<40) return 4;
		if(val<50) return 5;
		if(val<60) return 6;
		if(val<70) return 7;
		if(val<80) return 8;
		if(val<90) return 9;
		return 10;
	}

	@Override
	protected Integer val01(Integer val) {
		return val00(val);
	}

	@Override
	protected Integer val02(Integer val) {
		return val00(val);
	}

	@Override
	protected Integer val03(Integer val) {
		return val00(val);
	}

	@Override
	protected Integer val04(Integer val) {
		return val00(val);
	}

	@Override
	protected Integer val05(Integer val) {
		return val00(val);
	}

	@Override
	protected Integer val06(Integer val) {
		return val00(val);
	}

	@Override
	protected Integer val07(Integer val) {
		return val00(val);
	}

	@Override
	protected Integer val08(Integer val) {
		return val00(val);
	}

	@Override
	protected Integer val09(Integer val) {
		return val00(val);
	}

	@Override
	protected Integer val10(Integer val) {
		return val00(val);
	}

	@Override
	protected Integer val11(Integer val) {
		return val00(val);
	}

	@Override
	protected Integer val12(Integer val) {
		return val00(val);
	}

	@Override
	protected Integer val13(Integer val) {
		return val00(val);
	}

	@Override
	protected Integer val14(Integer val) {
		return val00(val);
	}

	@Override
	protected Integer val15(Integer val) {
		return val00(val);
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

	@Override
	public void initialize(List<String> options) {
		// TODO Auto-generated method stub

	}

}
