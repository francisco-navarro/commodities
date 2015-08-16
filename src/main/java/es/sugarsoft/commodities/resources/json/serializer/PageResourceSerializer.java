package es.sugarsoft.commodities.resources.json.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import es.sugarsoft.commodities.resources.Item;
import es.sugarsoft.commodities.resources.json.PageResource;

public class PageResourceSerializer extends JsonSerializer<PageResource> {

	@Override
	public void serialize(PageResource value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		jgen.writeStartObject();
	    jgen.writeNumberField("totalElements", value.getTotalItems());
//	    for(Item item : value.getElements()){
//	    	writeElements(jgen, item);	    	
//	    }
	    jgen.writeObjectField("elements",value.getElements());
	    jgen.writeEndObject();
	}

	private void writeElements(JsonGenerator jgen, Item[] elements) throws IOException {
		
		jgen.writeObjectField("elements", new Object[]{"v",1231});
	}

}
