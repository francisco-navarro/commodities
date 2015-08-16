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
