package es.sugarsoft.commodities.investing.html.connection;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import es.sugarsoft.commodities.investing.http.BaseHttpParserConfig;
import es.sugarsoft.commodities.investing.http.connection.ICookiesService;
import es.sugarsoft.commodities.investing.http.connection.impl.SocketChartConnectionService;

public class SocketChartConnectionTest extends BaseHttpParserConfig{
	
	
	@Autowired
	private SocketChartConnectionService socketChartConnection;
	
	@Autowired
	private ICookiesService cookiesService;
	
	
	@Before
	public void init(){
	}
	
	@Test
	public void connectJsonChart() throws IOException, ParseException{
		
		cookiesService.renewCookies(8830);
		
		final long id = 8830;
		JSONObject json = socketChartConnection.getJsonData(id, null, null);	
		
		Map attributes =(Map) json.get("attr");
		assertNotNull(attributes.get("last_value"));
	}
	

}
