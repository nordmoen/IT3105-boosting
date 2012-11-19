package no.ntnu.ai.filter;

import java.util.List;

public class GlassFilter extends AbstractFilter<Integer, Integer, Double> {

	@Override
	protected Integer val00(Double val) {
		if(val<1.52) return 1;
		if(val<1.53) return 2;
		return 3;
	}

	@Override
	protected Integer val01(Double val) {
		if(val<13) return 1;
		if(val<14.5) return 2;
		if(val<16) return 3;
		return 4;
	}


	@Override
	protected Integer val02(Double val) {
		if(val<1.5) return 1;
		if(val<3) return 2;
		return 3;
	}

	@Override
	protected Integer val03(Double val) {
		if(val<1) return 1;
		if(val<2) return 2;
		return 3;
	}

	@Override
	protected Integer val04(Double val) {
		if(val<71) return 1;
		if(val<73) return 2;
		return 3;
	}

	@Override
	protected Integer val05(Double val) {
		if(val<0.5) return 1;
		if(val<1.5) return 2;
		if(val<3) return 3;
		return 4;
	}

	@Override
	protected Integer val06(Double val) {
		if(val<8) return 1;
		if(val<12) return 2;
		return 3;
	}

	@Override
	protected Integer val07(Double val) {
		if(val<0.1) return 1;
		if(val<0.3) return 2;
		if(val<1.5) return 3;
		return 4;
	}

	@Override
	protected Integer val08(Double val) {
		if(val<0.04) return 1;
		if(val<0.2) return 2;
		return 3;
	}

	@Override
	protected Integer val09(Double val) {
		// TODO Auto-generated method stub
		return null;
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
	
	@Override
	protected Integer val15(Double val) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected Integer val16(Double val) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected Integer val17(Double val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initialize(List<String> options) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Integer convertClassification(String classif) {
		return Integer.parseInt(classif);
	}

	@Override
	protected Double convertValue(String value) {
		return Double.parseDouble(value);
	}

}
