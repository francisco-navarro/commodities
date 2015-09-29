package es.sugarsoft.commodities.investing.html.connection;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import es.sugarsoft.commodities.investing.http.connection.impl.HtmlConnectionService;
import es.sugarsoft.commodities.investing.http.util.UriConstants;

public class HtmlConnectionTest {

	private HtmlConnectionService engine;
	
	@Before
	public void init() throws Exception{
		engine = new HtmlConnectionService(UriConstants.SECTION_URL);
	}
	
	@Test
	public void connect() throws IOException{
		//TODO: Cambiar test
//		List<String> response = engine.getHeader(null);
//		assertTrue(!response.isEmpty());
//		assertEquals(response.get(0),"HTTP/1.1 200 OK");
	}
}
