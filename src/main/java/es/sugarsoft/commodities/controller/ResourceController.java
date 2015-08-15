package es.sugarsoft.commodities.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.sugarsoft.commodities.investing.ChartEngine;
import es.sugarsoft.commodities.resources.dao.ItemDao;

@Controller
public class ResourceController {
	
	private JSONParser parser;
	
	private ItemDao itemDao;
	
	@Autowired
	public ResourceController(ItemDao itemDao) {
		parser=new JSONParser();
		this.itemDao = itemDao;
	}

	@RequestMapping(value = "/resource/{id}", method = RequestMethod.GET,produces = "application/json" )
    public @ResponseBody String list( HttpServletRequest request,
    		 @PathVariable("id") Long id) {
		try {
			ChartEngine chart = new ChartEngine(id.toString());			
			return chart.getJson();
		} catch (Exception e) {			
			e.printStackTrace();
			return null;
		}
    }  
	
	@RequestMapping(value = "/resource/{id}", method = RequestMethod.PUT,produces = "application/json" )
    public @ResponseBody String load( HttpServletRequest request,
    		 @PathVariable("id") Long id) {
		try {
			ChartEngine chart = new ChartEngine(id.toString());			
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
		
		return null;
    } 
}
