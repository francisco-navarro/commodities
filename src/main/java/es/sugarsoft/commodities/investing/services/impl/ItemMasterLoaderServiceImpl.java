package es.sugarsoft.commodities.investing.services.impl;

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
import es.sugarsoft.commodities.investing.http.SocketConnection;
import es.sugarsoft.commodities.investing.services.ItemMasterLoaderService;
import es.sugarsoft.commodities.resources.Item;
import es.sugarsoft.commodities.resources.dao.ItemMasterDao;
import es.sugarsoft.commodities.resources.deserializer.CommodityDeserializer;

@Service("itemMasterLoaderService")
public class ItemMasterLoaderServiceImpl implements ItemMasterLoaderService {

	private static final String MAIN_TABLE = "#dailyTab > tbody > tr";

	private ItemMasterDao itemMasterDao;
	private JSONParser parser;

	@Autowired
	public ItemMasterLoaderServiceImpl(ItemMasterDao itemMasterDao) {
		parser=new JSONParser();
		this.itemMasterDao = itemMasterDao;
	}

	@Override
	public void loadTableItems(String table) {

		HttpConnection connection = null;
		Document doc = null;
		String url = HttpConnection.getTableUri(table);

		try {

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
