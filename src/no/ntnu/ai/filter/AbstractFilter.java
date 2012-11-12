package no.ntnu.ai.filter;

import java.util.ArrayList;
import java.util.List;

import no.ntnu.ai.data.ArrayElement;
import no.ntnu.ai.data.DataElement;

public abstract class AbstractFilter<T, T2, T3> implements Filter<T, T2, T3>{

	@Override
	public List<DataElement<T, T2>> convert(
			List<DataElement<T3, T2>> input) {
		List<DataElement<T, T2>> result = new ArrayList<DataElement<T,T2>>();
		for(DataElement<T3, T2> dat : input){
			List<T> values = new ArrayList<T>();
			for(int i=0; i<dat.size(); i++){
				values.add (val(i, dat.get(i)));
			}
			result.add(new ArrayElement<T, T2>(values, dat.getClassification()));
			
		}
		return result;
	}
	
	private T val(int index, T3 val){
		switch (index) {
		case 0:
			return val0(val);
		case 1:
			return val1(val);
		case 2:
			return val2(val);
		case 3:
			return val3(val);
		case 4:
			return val4(val);
		case 5:
			return val5(val);
		case 6:
			return val6(val);
		case 7:
			return val7(val);
		case 8:
			return val8(val);
		case 9:
			return val9(val);
		case 10:
			return val10(val);
		case 11:
			return val11(val);
		case 12:
			return val12(val);
		case 13:
			return val13(val);
		case 14:
			return val14(val);
		default:
			return null;
		}
	}
	
	protected abstract T val0(T3 val);
	protected abstract T val1(T3 val);
	protected abstract T val2(T3 val);
	protected abstract T val3(T3 val);
	protected abstract T val4(T3 val);
	protected abstract T val5(T3 val);
	protected abstract T val6(T3 val);
	protected abstract T val7(T3 val);
	protected abstract T val8(T3 val);
	protected abstract T val9(T3 val);
	protected abstract T val10(T3 val);
	protected abstract T val11(T3 val);
	protected abstract T val12(T3 val);
	protected abstract T val13(T3 val);
	protected abstract T val14(T3 val);



}
