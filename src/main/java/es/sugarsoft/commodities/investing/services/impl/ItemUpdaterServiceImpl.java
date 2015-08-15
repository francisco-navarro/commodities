package es.sugarsoft.commodities.investing.services.impl;

import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.sugarsoft.commodities.investing.ChartEngine;
import es.sugarsoft.commodities.investing.services.ItemUpdaterService;
import es.sugarsoft.commodities.resources.dao.ItemDao;

@Service("itemUpdaterService")
public class ItemUpdaterServiceImpl implements ItemUpdaterService {
	
	private ItemDao itemDao;
	private JSONParser parser;
	
	@Autowired
	public ItemUpdaterServiceImpl(ItemDao itemDao){
		parser=new JSONParser();
		this.itemDao = itemDao;
	}

	@Override
	public void updateItem(Long id) {
		try {
			ChartEngine chart = new ChartEngine(id);			
			JSONObject json = (JSONObject) parser.parse(chart.getJson());			
			
			Map attributes =(Map) json.get("attr");
			
			String name = attributes.get("symbol").toString();
			long  pairId = Long.valueOf(attributes.get("pair_id").toString());
			JSONArray candles = (JSONArray) json.get("candles");			
			for(Object elem : candles.toArray()){
				JSONArray value = (JSONArray) elem;
				long time = Long.valueOf((long)value.get(0));
				Double qty = new Double(value.get(1).toString());
				
				itemDao.add(pairId,time,qty);
				System.out.println(time + " " + qty);
			}
				
		} catch (Exception e) {			
			e.printStackTrace();
			
		}
	} 
}
