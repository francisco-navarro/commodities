package es.sugarsoft.commodities.resources.deserializer;

import org.jsoup.nodes.Element;

import es.sugarsoft.commodities.resources.Item;

public class CommodityDeserializer {

	public static Item deserialize(Element element) {
		Item c = new Item();			
		c.setId(Long.valueOf(element.attr("id").replace("pair_", "")));
		c.setDescription(element.select("a").get(0).attr("title"));
		
		return c;
	}
	
}
