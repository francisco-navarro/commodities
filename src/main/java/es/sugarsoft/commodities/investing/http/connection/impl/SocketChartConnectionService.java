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

	private enum Interval {
		
		MINUTE(300),
		MINUTES_5(60),
		MINUTES_15(900),
		MINUTES_30(1800),
		HOUR(3600),
		HOURS_5(18000),
		DAY(86400);
		
		private Integer value;
		
		private Interval(int value){
			this.value = value;
		}
		
		public int getValue(){
			return value;
		}

		public static Interval getNextInterval(int secondsInterval) {
			for(Interval interval : Interval.values()){
				if(interval.getValue()< secondsInterval){
					return interval;
				}
			}
			return MINUTES_30;
		}
	}

	@Override
	public JSONObject getJsonData(long id,  int interval) throws ParseException {

		HttpConenction connection = null;
		connection = new HttpConenction(buildURI(id,interval),ACCEPT_TYPES);
		String plainJson = connection.getJson();
		return (JSONObject) parser.parse(plainJson);

	}

	private String buildURI(long id, int secondsInterval) {
		Interval nextInterval = Interval.getNextInterval(secondsInterval);
		String uri = URI + "?";
		uri += "pair_id=" +id+ "&" +
				"chart_type=area" + "&" +
				"pair_interval="+nextInterval.getValue() + "&" + 
				"candle_count=25" + "&" +
				"events=no" + "&" +
				"volume_series=yes";
		return uri;
	}


}
