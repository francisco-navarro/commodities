package es.sugarsoft.commodities.investing.http.connection.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import es.sugarsoft.commodities.investing.http.connection.ISocketChartConnectionService;

@Service("socketChartConnectionService")
public class SocketChartConnectionService  implements ISocketChartConnectionService{

	private static final Logger logger = Logger.getLogger(SocketChartConnectionService.class);

	private static final String URI = "/common/modules/js_instrument_chart/api/data.php";
	private static final String ACCEPT_TYPES = "application/json, text/javascript";	
	
	private JSONParser parser;
	
	public SocketChartConnectionService() {
		parser = new JSONParser();
	}


	@Override
	public JSONObject getJsonData(long id,  Date fromDate, Date endDate) throws ParseException {

		HttpConenction connection = null;
		connection = new HttpConenction(buildURI(id),ACCEPT_TYPES);
		String plainJson = connection.getJson();
		return (JSONObject) parser.parse(plainJson);

	}

	private String buildURI(long id) {
		String uri = URI + "?";
		uri += "pair_id=" +id+ "&" +
				"chart_type=area" + "&" +
				"pair_interval=1800" + "&" + 
				"candle_count=25" + "&" +
				"events=no" + "&" +
				"volume_series=yes";
		return uri;
	}


}
