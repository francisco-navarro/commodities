package es.sugarsoft.commodities.investing.http.parser.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.sugarsoft.commodities.investing.http.connection.IHtmlConnectionService;
import es.sugarsoft.commodities.investing.http.parser.IChartEngine;
import es.sugarsoft.commodities.investing.http.parser.IHttpDetailParser;
import es.sugarsoft.commodities.investing.http.parser.IHttpTableParser;
import es.sugarsoft.commodities.resources.Item;
import es.sugarsoft.commodities.resources.json.deserializer.CommodityDeserializer;

@Service("httpTableParser")
public class HttpTableParser implements IHttpTableParser {
	
	private JSONParser jsonParser;
	private IHttpDetailParser httpDetailParser;
	private IHtmlConnectionService htmlConnectionService;
	
	@Autowired
	public HttpTableParser(IHttpDetailParser httpDetailParser,
			IHtmlConnectionService htmlConnectionService){
		jsonParser=new JSONParser();
		this.httpDetailParser = httpDetailParser;
		this.htmlConnectionService = htmlConnectionService;
	}
	
	@Override
	public List<Item> getItemsFromTableUrl(String url){
		
		Document doc = null;		
		try {
			String html = htmlConnectionService.connect(url);
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
					getDetailData(c);
					list.add(c);
				}
			}
		}

		return list;
	}

	private Item getDetailData(Item c) {		
		return httpDetailParser.getItemDetails(c);
	}
	
//	@Override
//	public String getAdditionalData(Item c) {
//		//TODO: Esto no puede ir aqui ya que es otro tipo de parser
//		try {			
//			JSONObject json = (JSONObject) jsonParser.parse(
//					chartEngine.getJson(c.getId()));			
//			
//			Map attributes =(Map) json.get("attr");
//
//			return attributes.toString();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

}
