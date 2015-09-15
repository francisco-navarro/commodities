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
import es.sugarsoft.commodities.investing.services.ItemUpdaterService;
import es.sugarsoft.commodities.investing.services.SectionService;
import es.sugarsoft.commodities.resources.Item;
import es.sugarsoft.commodities.resources.Section;
import es.sugarsoft.commodities.resources.json.deserializer.CommodityDeserializer;
import es.sugarsoft.commodities.resources.persistence.ItemMasterDao;

@SuppressWarnings("rawtypes")
@Service("itemMasterLoaderService")
public class ItemMasterLoaderServiceImpl implements ItemMasterLoaderService {

	private static final String MAIN_TABLE = "#dailyTab > tbody > tr";
	private static final String SECOND_TABLE = "#cross_rate_1 > tbody > tr";
	
	private SectionService sectionService;
	private ItemMasterDao itemMasterDao;
	private ItemUpdaterService itemUpdaterService;
	private JSONParser parser;

	@Autowired
	public ItemMasterLoaderServiceImpl(ItemMasterDao itemMasterDao,
			SectionService sectionService,
			ItemUpdaterService itemUpdaterService) {
		parser=new JSONParser();
		this.itemMasterDao = itemMasterDao;
		this.sectionService = sectionService;
		this.itemUpdaterService = itemUpdaterService;
	}

	@Override
	@Deprecated
	public void loadTableItems(String market, String table) {

		HttpConnection connection = null;
		Document doc = null;

		try {
			String url = HttpConnection.getTableUri(market + "/" + URLEncoder.encode(table, "UTF-8"));

			connection = new HttpConnection(url);
			doc = Jsoup.parse(connection.getOutput());
			List<Item> list = getListFromDoc(doc);

			for (Item commodity : list) {
				itemMasterDao.add(commodity, 2l);
				itemUpdaterService.updateItem(commodity.getId());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void  loadTableItemsFromSectionId(long id){

		HttpConnection connection = null;
		Document doc = null;

		try {
			String url = getUriFromSectionId(id);

			connection = new HttpConnection(url);
			doc = Jsoup.parse(connection.getOutput());
			List<Item> list = getListFromDoc(doc);

			for (Item commodity : list) {
				itemMasterDao.add(commodity,id);
				itemUpdaterService.updateItem(commodity.getId());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String getUriFromSectionId(long id) {
		Section section = sectionService.get(id);
		return section.getUrl();
	}

	private List<Item> getListFromDocOld(Element doc) throws Exception {

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
	
	private List<Item> getListFromDoc(Element doc) throws Exception {

		List<Item> list = new ArrayList<Item>();
		Elements tables = doc.select("table");
		
		
		for (int i = 0; i < tables.size(); i++) {
			if(tables.get(i).hasAttr("tablesorter")){
				Elements elems = tables.get(i).select("tbody > tr");
				for(int j=0;j<elems.size();j++){
					Item c = CommodityDeserializer.deserialize(elems.get(j));
					c.setJson(getAdditionalData(c));
					list.add(c);
				}
			}
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
