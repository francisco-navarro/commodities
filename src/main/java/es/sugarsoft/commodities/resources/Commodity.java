package es.sugarsoft.commodities.resources;

import java.io.Serializable;

public class Commodity implements Serializable {
	
	private String pair;
	private String name;
	private String json;
	
	public Commodity(){
		
	}
	
	public String getPair() {
		return pair;
	}
	public void setPair(String pair) {
		this.pair = pair;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Commodity [" + pair + ", name=" + name + "]";
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	
	
	

}
