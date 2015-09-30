package es.sugarsoft.commodities.investing.http.connection.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import es.sugarsoft.commodities.investing.http.connection.ISocketChartConnectionService;
import es.sugarsoft.commodities.investing.http.connection.ISocketHistoricalChartConnectionService;

@Service("socketHistoricalConnectionService")
public class SocketHistoricalConnectionService  implements ISocketHistoricalChartConnectionService{

	private static final Logger logger = Logger.getLogger(SocketHistoricalConnectionService.class);

	private static final String URI_HISTORICAL = "/instruments/HistoricalDataAjax";	
	private static final String ACCEPT_TYPES = "text/plain";	

	@Override
	public String getData(long id,  Date fromDate, Date endDate) {

		HttpConenction connection = null; 
		String uri = buildHistoricalURI(id, fromDate, endDate);
		connection = new HttpConenction(uri, ACCEPT_TYPES, "POST");
		return connection.getJson();

	}


	private String buildHistoricalURI(long id, Date fromDate, Date endDate){
//		String uri = URI_HISTORICAL + "?";
//		uri += "action=historical_data"+"&" +
//				"curr_id=" +id+ "&" +
//				"st_date=14/08/2015" + "&" +
//				"end_date=30/08/2015" + "&" +
//				"interval_sec=Daily";
//		return uri;
		return URI_HISTORICAL;
	}

}
