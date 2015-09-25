package es.sugarsoft.commodities.investing.http.util;

import java.lang.reflect.Method;
import java.util.HashMap;

import es.sugarsoft.commodities.resources.Item;

public enum ItemMethod {
	
	oneYearRange("Retorno 1 año", "setOneYearRange", String.class),
	month( "Mes", "setMonth", String.class),
	contractSize("Tamaño contrato", "setContractSize", String.class),
	settlementType("Clase de liquidación","setSettlementType", String.class),
	lastTradingDay("Último día negociación","setLastTradingDay",String.class),
	pointValue("Valor del punto","setPointValue",String.class),
	tickSize("Tamaño del tick","setTickSize",String.class),
	tickValue("Valor del tick","setTickValue",String.class),
	baseSymbol("Símbolo base","setTickValue",String.class),
	months("Meses","setMonths",String.class);
	
	
	private Method method;
	private String keyValue;
	private static HashMap<String, ItemMethod>	map = new HashMap<>();
	
	ItemMethod(String keyvalue, String methodName, Class paramType){
		this.method=Item.getMethod(methodName, paramType);
		this.keyValue= keyvalue;
	}

	static{
		for(ItemMethod m : ItemMethod.values()){
			map.put(m.keyValue, m);
		}
	}
	public Method getMethod() {
		return method;
	}

	public String getKeyValue() {
		return keyValue;
	}
	
	public static ItemMethod get(String keyValue){
		return map.get(keyValue);
	}

}
