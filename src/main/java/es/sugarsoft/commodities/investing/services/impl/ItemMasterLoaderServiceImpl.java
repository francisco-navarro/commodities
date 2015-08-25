package es.sugarsoft.commodities.investing.services.impl;

import java.net.URLEncoder;
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

import es.sugarsoft.commodities.investing.ChartEngine;
import es.sugarsoft.commodities.investing.http.HttpConnection;
import es.sugarsoft.commodities.investing.services.ItemMasterLoaderService;
import es.sugarsoft.commodities.resources.Item;
import es.sugarsoft.commodities.resources.dao.ItemMasterDao;
import es.sugarsoft.commodities.resources.json.deserializer.CommodityDeserializer;

@SuppressWarnings("rawtypes")
@Service("itemMasterLoaderService")
public class ItemMasterLoaderServiceImpl implements ItemMasterLoaderService {

	private static final String MAIN_TABLE = "#dailyTab > tbody > tr";
	private static final String SECOND_TABLE = "#cross_rate_1 > tbody > tr";
	
	private ItemMasterDao itemMasterDao;
	private JSONParser parser;

	@Autowired
	public ItemMasterLoaderServiceImpl(ItemMasterDao itemMasterDao) {
		parser=new JSONParser();
		this.itemMasterDao = itemMasterDao;
	}

	@Override
	public void loadTableItems(String market, String table) {

		HttpConnection connection = null;
		Document doc = null;

		try {
			String url = HttpConnection.getTableUri(market + "/" + URLEncoder.encode(table, "UTF-8"));

			connection = new HttpConnection(url);
			doc = Jsoup.parse(connection.getOutput());
			List<Item> list = getListFromDoc(doc);

			for (Item commodity : list) {
				itemMasterDao.add(commodity);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private List<Item> getListFromDoc(Element doc) throws Exception {

		List<Item> list = new ArrayList<Item>();
		Elements elems = doc.select(MAIN_TABLE);
		
		if(elems.size() == 0){
			elems = doc.select(SECOND_TABLE);
		}

		for (int i = 0; i < elems.size(); i++) {
			Item c = CommodityDeserializer.deserialize(elems.get(i));
			c.setJson(getAdditionalData(c));
			list.add(c);
		}

		return list;
	}

	private String getAdditionalData(Item c) {
		try {
			
			ChartEngine chart = new ChartEngine(c.getId());			
			JSONObject json = (JSONObject) parser.parse(chart.getJson());			
			
			Map attributes =(Map) json.get("attr");

			return attributes.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
