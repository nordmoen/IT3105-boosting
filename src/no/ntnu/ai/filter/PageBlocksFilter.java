package no.ntnu.ai.filter;

import java.util.List;

public class PageBlocksFilter extends AbstractFilter<Integer, Integer, Double>{

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
		if(val<2) return 1;
		if(val<5) return 2;
		if(val<8) return 3;
		if(val<10) return 4;
		if(val<15) return 5;
		if(val<20) return 6;
		if(val<50) return 7;
		if(val<200) return 8;
		return 9;
	}

	@Override
	protected Integer val1(Double val) {
		if(val<50) return 1;
		if(val<90) return 2;
		if(val<150) return 3;
		if(val<300) return 4;
		return 5;
	}


	@Override
	protected Integer val2(Double val) {
		if(val<300) return 1;
		if(val<500) return 2;
		if(val<800) return 3;
		if(val<1200) return 4;
		if(val<1500) return 5;
		if(val<1800) return 6;
		if(val<2400) return 7;
		if(val<10000) return 8;
		return 9;
	}

	@Override
	protected Integer val3(Double val) {
		if(val<4) return 1;
		if(val<8) return 2;
		if(val<13) return 3;
		if(val<16) return 4;
		if(val<20) return 5;
		if(val<30) return 6;
		if(val<100) return 7;
		if(val<250) return 8;
		return 9;
	}

	@Override
	protected Integer val4(Double val) {
		if(val<0.1) return 1;
		if(val<0.2) return 2;
		if(val<0.3) return 3;
		if(val<0.4) return 4;
		if(val<0.5) return 5;
		if(val<0.6) return 6;
		if(val<0.7) return 7;
		if(val<0.85) return 8;
		return 9;
	}

	@Override
	protected Integer val5(Double val) {
		if(val<0.15) return 1;
		if(val<0.3) return 2;
		if(val<0.4) return 3;
		if(val<0.5) return 4;
		if(val<0.6) return 5;
		if(val<0.7) return 6;
		if(val<0.8) return 7;
		if(val<0.9) return 8;
		return 9;
	}

	@Override
	protected Integer val6(Double val) {
		if(val<2) return 1;
		if(val<4) return 2;
		if(val<7) return 3;
		if(val<9) return 4;
		if(val<13) return 5;
		if(val<20) return 6;
		if(val<100) return 7;
		if(val<1000) return 8;
		return 9;
	}

	@Override
	protected Integer val7(Double val) {
		if(val<60) return 1;
		if(val<140) return 2;
		if(val<220) return 3;
		if(val<260) return 4;
		if(val<310) return 5;
		if(val<370) return 6;
		if(val<420) return 7;
		if(val<480) return 8;
		if(val<540) return 9;
		if(val<700) return 10;
		if(val<1000) return 11;
		return 12;
	}

	@Override
	protected Integer val8(Double val) {
		if(val<250) return 1;
		if(val<400) return 2;
		if(val<550) return 3;
		if(val<700) return 4;
		if(val<850) return 5;
		if(val<1000) return 6;
		if(val<1250) return 7;
		if(val<2000) return 8;
		return 9;
		
	}

	@Override
	protected Integer val9(Double val) {
		if(val<30) return 1;
		if(val<60) return 2;
		if(val<80) return 3;
		if(val<100) return 4;
		if(val<120) return 5;
		if(val<150) return 6;
		if(val<200) return 7;
		if(val<450) return 8;
		if(val<1000) return 9;
		return 10;
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
