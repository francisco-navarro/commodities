package es.sugarsoft.commodities.resources;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import es.sugarsoft.commodities.resources.json.serializer.ItemSerializer;

@JsonSerialize(using=ItemSerializer.class)
public class Item implements Serializable {
	
	private long id;
	private String description;
	private Value[] data;
	private String json;
	private String url;
	private long prevClose;
	private long open;
	private long oneYearRange;
	private long fiftyTwoWeeksRange;
	private long compra;
	private long venta;
	private String month;
	private String contractSize;
	private String settlementType;
	private String lastTradingDay;
	private String pointValue;
	private String tickSize;
	private String tickValue;
	private String baseSymbol;
	private String months;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
	
	public long getPrevClose() {
		return prevClose;
	}

	public void setPrevClose(long prevClose) {
		this.prevClose = prevClose;
	}

	public long getOpen() {
		return open;
	}

	public void setOpen(long open) {
		this.open = open;
	}

	public long getOneYearRange() {
		return oneYearRange;
	}

	public void setOneYearRange(long oneYearRange) {
		this.oneYearRange = oneYearRange;
	}

	public long getFiftyTwoWeeksRange() {
		return fiftyTwoWeeksRange;
	}

	public void setFiftyTwoWeeksRange(long fiftyTwoWeeksRange) {
		this.fiftyTwoWeeksRange = fiftyTwoWeeksRange;
	}

	public long getCompra() {
		return compra;
	}

	public void setCompra(long compra) {
		this.compra = compra;
	}

	public long getVenta() {
		return venta;
	}

	public void setVenta(long venta) {
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


}
