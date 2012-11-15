package no.ntnu.ai.filter;

import java.util.List;

public class YeastFilter extends AbstractFilter<Integer, Integer, Double>{

	@Override
	protected Integer convertClassification(String classif) {
		return Integer.parseInt(classif);
	}

	@Override
	protected Double convertValue(String value) {
		return Double.parseDouble(value);
	}

	@Override
	protected Integer val0(Double val) {
		if(val<0.2) return 1;
		if(val<0.4) return 2;
		if(val<0.6) return 3;
		if(val<0.8) return 4;
		return 5;
	}

	@Override
	protected Integer val1(Double val) {
		return val0(val);
	}


	@Override
	protected Integer val2(Double val) {
		return val0(val) -1;
	}

	@Override
	protected Integer val3(Double val) {
		return val0(val);
	}

	@Override
	protected Integer val4(Double val) {
		return val0(val);
	}

	@Override
	protected Integer val5(Double val) {
		if(val<0.005) return 1;
		if(val<0.01) return 2;
		if(val<0.2) return 3;
		if(val<0.4) return 4;
		if(val<0.7) return 5;
		return 6;
	}

	@Override
	protected Integer val6(Double val) {
		return val0(val);
	}

	@Override
	protected Integer val7(Double val) {
		if(val<0.1) return 1;
		if(val<0.2) return 2;
		if(val<0.3) return 3;
		if(val<0.5) return 4;
		if(val<0.7) return 5;
		return 6;
	}

	@Override
	protected Integer val8(Double val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Integer val9(Double val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initialize(List<String> options) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Integer val10(Double val) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected Integer val11(Double val) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected Integer val12(Double val) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected Integer val13(Double val) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected Integer val14(Double val) {
		// TODO Auto-generated method stub
		return null;
	}
}
