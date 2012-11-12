package no.ntnu.ai.filter;

import java.util.List;

public class GlassFilter extends AbstractFilter<Integer, Integer, Double> {

	@Override
	protected Integer val0(Double val) {
		if(val<1.52) return 1;
		if(val<1.53) return 2;
		return 3;
	}

	@Override
	protected Integer val1(Double val) {
		if(val<13) return 1;
		if(val<14.5) return 2;
		if(val<16) return 3;
		return 4;
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
	protected Integer val2(Double val) {
		if(val<1.5) return 1;
		if(val<3) return 2;
		return 3;
	}

	@Override
	protected Integer val3(Double val) {
		if(val<1) return 1;
		if(val<2) return 2;
		return 3;
	}

	@Override
	protected Integer val4(Double val) {
		if(val<71) return 1;
		if(val<73) return 2;
		return 3;
	}

	@Override
	protected Integer val5(Double val) {
		if(val<0.5) return 1;
		if(val<1.5) return 2;
		if(val<3) return 3;
		return 4;
	}

	@Override
	protected Integer val6(Double val) {
		if(val<8) return 1;
		if(val<12) return 2;
		return 3;
	}

	@Override
	protected Integer val7(Double val) {
		if(val<0.1) return 1;
		if(val<0.3) return 2;
		if(val<1.5) return 3;
		return 4;
	}

	@Override
	protected Integer val8(Double val) {
		if(val<0.04) return 1;
		if(val<0.2) return 2;
		return 3;
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

}
