package es.sugarsoft.commodities.resources;

import java.io.Serializable;

public class Value implements Serializable {
	
	private long time;
	private double value;
	
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	

}
