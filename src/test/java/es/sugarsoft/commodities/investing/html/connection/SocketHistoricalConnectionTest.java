package es.sugarsoft.commodities.investing.html.connection;

import java.io.IOException;
import java.util.Date;

import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import es.sugarsoft.commodities.investing.http.BaseHttpParserConfig;
import es.sugarsoft.commodities.investing.http.connection.ICookiesService;
import es.sugarsoft.commodities.investing.http.connection.impl.SocketHistoricalConnectionService;

public class SocketHistoricalConnectionTest extends BaseHttpParserConfig{
	
	
	@Autowired
	private SocketHistoricalConnectionService socketHistoricalConnection;
	
	@Autowired
	private ICookiesService cookiesService;
	
	
	@Before
	public void init(){
		cookiesService.renewCookies(8830);	
	}
	
	@Test
	public void connectJsonChart() throws IOException, ParseException{		
			
		final long id = 8830;		
		String data = socketHistoricalConnection.getData(id, new Date(),  new Date());
		System.out.println(data);
		
	}
	

}
