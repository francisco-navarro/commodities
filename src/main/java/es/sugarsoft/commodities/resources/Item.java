package es.sugarsoft.commodities.resources;

import java.io.Serializable;
import java.lang.reflect.Method;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import es.sugarsoft.commodities.resources.json.serializer.ItemSerializer;

@JsonSerialize(using=ItemSerializer.class)
public class Item implements Serializable {
	
	private long id;
	private String description;
	private Value[] data;
	private String json;
	private String url;
	private Long prevClose;
	private Long open;
	private String oneYearRange;
	private Long fiftyTwoWeeksRange;
	private Long compra;
	private Long venta;
	private String month;
	private String contractSize;
	private String settlementType;
	private String lastTradingDay;
	private String pointValue;
	private String tickSize;
	private String tickValue;
	private String baseSymbol;
	private String months;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public Long getPrevClose() {
		return prevClose;
	}

	public void setPrevClose(Long prevClose) {
		this.prevClose = prevClose;
	}

	public Long getOpen() {
		return open;
	}

	public void setOpen(Long open) {
		this.open = open;
	}

	public String getOneYearRange() {
		return oneYearRange;
	}

	public void setOneYearRange(String oneYearRange) {
		this.oneYearRange = oneYearRange;
	}

	public Long getFiftyTwoWeeksRange() {
		return fiftyTwoWeeksRange;
	}

	public void setFiftyTwoWeeksRange(Long fiftyTwoWeeksRange) {
		this.fiftyTwoWeeksRange = fiftyTwoWeeksRange;
	}

	public Long getCompra() {
		return compra;
	}

	public void setCompra(Long compra) {
		this.compra = compra;
	}

	public Long getVenta() {
		return venta;
	}

	public void setVenta(Long venta) {
		this.venta = venta;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getContractSize() {
		return contractSize;
	}

	public void setContractSize(String contractSize) {
		this.contractSize = contractSize;
	}

	public String getSettlementType() {
		return settlementType;
	}

	public void setSettlementType(String settlementType) {
		this.settlementType = settlementType;
	}

	public String getLastTradingDay() {
		return lastTradingDay;
	}

	public void setLastTradingDay(String lastTradingDay) {
		this.lastTradingDay = lastTradingDay;
	}

	public String getPointValue() {
		return pointValue;
	}

	public void setPointValue(String pointValue) {
		this.pointValue = pointValue;
	}

	public String getTickSize() {
		return tickSize;
	}

	public void setTickSize(String tickSize) {
		this.tickSize = tickSize;
	}

	public String getTickValue() {
		return tickValue;
	}

	public void setTickValue(String tickValue) {
		this.tickValue = tickValue;
	}

	public String getBaseSymbol() {
		return baseSymbol;
	}

	public void setBaseSymbol(String baseSymbol) {
		this.baseSymbol = baseSymbol;
	}

	public String getMonths() {
		return months;
	}

	public void setMonths(String months) {
		this.months = months;
	}

	public Item(){
		
	}

	@Override
	public String toString() {
		return "Commodity [id=" + id + ", description=" + description + "]";
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public Value[] getData() {
		return data;
	}

	public void setData(Value[] data) {
		this.data = data;
	}
	
	@SuppressWarnings("rawtypes")
	public static Method getMethod(String name,  Class paramType){
		Class[] args = new Class[]{paramType};
		try {
			return Item.class.getMethod(name,args);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}

}
