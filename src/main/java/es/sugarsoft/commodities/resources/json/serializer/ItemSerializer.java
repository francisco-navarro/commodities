package es.sugarsoft.commodities.resources.json.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import es.sugarsoft.commodities.resources.Item;
import es.sugarsoft.commodities.resources.Value;
import es.sugarsoft.commodities.resources.json.PageResource;

public class ItemSerializer extends JsonSerializer<Item> {

	@Override
	public void serialize(Item item, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		jgen.writeStartObject();
	    jgen.writeStringField("name", item.getDescription());
	    jgen.writeArrayFieldStart("data");
	    for(Value value : item.getData()){
	    	jgen.writeStartObject();
	    	writeElement(jgen, value); 
	    	jgen.writeEndObject();
	    }
	    jgen.writeEndArray();
	    jgen.writeEndObject();
		
	}
	
	private void writeElement(JsonGenerator jgen, Value value) throws IOException {
		jgen.writeArrayFieldStart("c");
		
		jgen.writeStartObject();
		jgen.writeStringField("v", "Date("+value.getTime()+")");
		jgen.writeEndObject();
		
		jgen.writeStartObject();
		jgen.writeNumberField("v", value.getValue());
		jgen.writeEndObject();
		
		jgen.writeStartObject();
		jgen.writeStringField("v", getTooltip(value));
		jgen.writeEndObject();		
		
		jgen.writeEndArray();
	}

	private String getTooltip(Value value) {
		return "tooltip";
	}

}
