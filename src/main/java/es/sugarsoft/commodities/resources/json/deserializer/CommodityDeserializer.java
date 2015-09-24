package es.sugarsoft.commodities.resources.json.deserializer;

import org.jsoup.nodes.Element;

import es.sugarsoft.commodities.resources.Item;

public class CommodityDeserializer {

	public static Item deserialize(Element element) {
		Item c = new Item();			
		c.setId(Long.valueOf(element.attr("id").replace("pair_", "")));
		c.setDescription(element.select("a").get(0).attr("title"));
		c.setUrl(element.select("a").get(0).attr("href"));
		return c;
	}
	
}
