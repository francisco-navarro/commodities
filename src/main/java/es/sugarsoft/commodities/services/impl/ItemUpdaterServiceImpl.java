package es.sugarsoft.commodities.services.impl;

import java.util.Date;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.sugarsoft.commodities.investing.http.connection.impl.SocketChartConnectionService;
import es.sugarsoft.commodities.resources.Item;
import es.sugarsoft.commodities.resources.persistence.ItemMapper;
import es.sugarsoft.commodities.services.ItemUpdaterService;

@SuppressWarnings("rawtypes")
@Service("itemUpdaterService")
public class ItemUpdaterServiceImpl implements ItemUpdaterService {

	private ItemMapper itemDao;
	private SocketChartConnectionService socketChartConnectionService;
	private JSONParser parser;

	@Autowired
	public ItemUpdaterServiceImpl(ItemMapper itemDao,
			SocketChartConnectionService socketChartConnectionService) {
		parser = new JSONParser();
		this.itemDao = itemDao;
		this.socketChartConnectionService = socketChartConnectionService;
	}

	@Override
	public void updateItem(long id) {
		try {

			String plainJson = socketChartConnectionService.getJsonData(id);
			JSONObject json = (JSONObject) parser.parse(plainJson);
			
			Map attributes = (Map) json.get("attr");

			//TODO: Meter en deserializador
			long pairId = Long.valueOf(attributes.get("pair_id").toString());
			JSONArray candles = (JSONArray) json.get("candles");
			for (Object elem : candles.toArray()) {
				JSONArray value = (JSONArray) elem;
				Date time = new Date((long) value.get(0));
				Double qty = new Double(value.get(1).toString());

				itemDao.add(pairId, time, qty);

			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	@Override
	public Item getValues(long id, long interval) {

		return itemDao.getValues(id, interval);
	}
}
