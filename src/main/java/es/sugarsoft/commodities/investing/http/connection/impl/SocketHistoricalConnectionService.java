package es.sugarsoft.commodities.investing.http.connection.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import es.sugarsoft.commodities.investing.http.connection.ISocketHistoricalChartConnectionService;

@Service("socketHistoricalConnectionService")
public class SocketHistoricalConnectionService  implements ISocketHistoricalChartConnectionService{

	private static final Logger logger = Logger.getLogger(SocketHistoricalConnectionService.class);

	private static final String URI_HISTORICAL = "/instruments/HistoricalDataAjax";	
	private static final String ACCEPT_TYPES = "text/plain";
	private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public String getData(long id,  Date fromDate, Date endDate) {

		HttpConenction connection = null; 
		String params = buildParams(id, fromDate, endDate); //"action=historical_data&curr_id="+id+"&st_date=03%2F08%2F2015&end_date=22%2F08%2F2015&interval_sec=Daily";
		connection = new HttpConenction(URI_HISTORICAL, ACCEPT_TYPES, "POST");
		connection.setParams(params);
		return connection.getJson();

	}


	private String buildParams(long id, Date fromDate, Date endDate){
		String date1 ="";
		System.out.println();
		String date2 ="";
		try {
			date1 = URLEncoder.encode(dateFormatter.format(fromDate),"UTF-8");
			date2 = URLEncoder.encode(dateFormatter.format(endDate),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("Error parseando fecha ",e);
		}
		return "action=historical_data&curr_id="+id+
				"&st_date="+date1+
				"&end_date="+date2+
				"&interval_sec=Daily";		
	}

}
