package es.sugarsoft.commodities.resources;

import java.io.Serializable;
import java.util.Date;

public class ItemHistory implements Serializable {
	
	private long itemId;
	private float max;
	private float min;
	private float var;
	private Date day;
	private float openValue;
	private float closeValue;
	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	public float getMax() {
		return max;
	}
	public void setMax(float max) {
		this.max = max;
	}
	public float getMin() {
		return min;
	}
	public void setMin(float min) {
		this.min = min;
	}
	public float getVar() {
		return var;
	}
	public void setVar(float var) {
		this.var = var;
	}
	public Date getDay() {
		return day;
	}
	public void setDay(Date day) {
		this.day = day;
	}
	public float getOpenValue() {
		return openValue;
	}
	public void setOpenValue(float openValue) {
		this.openValue = openValue;
	}
	public float getCloseValue() {
		return closeValue;
	}
	public void setCloseValue(float closeValue) {
		this.closeValue = closeValue;
	}
	
	

}
