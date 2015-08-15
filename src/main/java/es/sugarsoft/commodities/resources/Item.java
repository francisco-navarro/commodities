package es.sugarsoft.commodities.resources;

import java.io.Serializable;

public class Item implements Serializable {
	
	private long id;
	private String description;
	private Value value;
	private String json;

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

	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
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


}
