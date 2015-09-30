package es.sugarsoft.commodities.investing.html.connection;

import java.io.IOException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import es.sugarsoft.commodities.investing.http.BaseHttpParserConfig;
import es.sugarsoft.commodities.investing.http.connection.impl.SocketChartConnectionService;

public class SocketConnectionTest extends BaseHttpParserConfig{
	
	
	@Autowired
	private SocketChartConnectionService connection;
	
	private JSONParser parser;
	
	@Before
	public void init(){
		parser = new JSONParser();
	}
	
	@Test
	public void connectJsonChart() throws IOException, ParseException{
		final long id = 8830;
		String plainJson = connection.getJsonData(id, null, null);
		System.out.println(plainJson);
		JSONObject json = (JSONObject) parser.parse(plainJson);
	}
	
	@Test
	public void connectHistorical() throws IOException, ParseException{
		final long id = 8830;
		Date fromDate = new Date();
		Date endDate = new Date();
		String plainJson = connection.getJsonData(id, fromDate, endDate);
		System.out.println(plainJson);		
	}
}
