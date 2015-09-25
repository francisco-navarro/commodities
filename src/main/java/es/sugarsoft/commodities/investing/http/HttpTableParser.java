package es.sugarsoft.commodities.investing.http;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import es.sugarsoft.commodities.resources.Item;
import es.sugarsoft.commodities.resources.json.deserializer.CommodityDeserializer;

public class HttpTableParser {
	
	private JSONParser jsonParser;
	
	public HttpTableParser(){
		jsonParser=new JSONParser();
	}
	
	public List<Item> getItems(String html){
		
		Document doc = null;		
		try {
			doc = Jsoup.parse(html);
			return getListFromDoc(doc);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return null;
	}
	
	private List<Item> getListFromDoc(Element doc) throws Exception {

		List<Item> list = new ArrayList<Item>();
		Elements tables = doc.select("table");
		
		
		for (int i = 0; i < tables.size(); i++) {
			if(tables.get(i).hasAttr("tablesorter")){
				Elements elems = tables.get(i).select("tbody > tr");
				for(int j=0;j<elems.size();j++){
					Item c = CommodityDeserializer.deserialize(elems.get(j));					
					//c.setJson(getAdditionalData(c));
					c = getDetailData(c);
					list.add(c);
				}
			}
		}

		return list;
	}

	private Item getDetailData(Item c) {
		try{
			HttpDetailParser detailParser = new HttpDetailParser(c.getUrl());
			return detailParser.getItemDetails(c);
		}catch(Exception e){
			e.printStackTrace();
		}
		return c;
	}

	private String getAdditionalData(Item c) {
		try {
			
			ChartEngine chart = new ChartEngine(c.getId());			
			JSONObject json = (JSONObject) jsonParser.parse(chart.getJson());			
			
			Map attributes =(Map) json.get("attr");

			return attributes.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
