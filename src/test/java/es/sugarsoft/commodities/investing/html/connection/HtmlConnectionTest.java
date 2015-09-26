package es.sugarsoft.commodities.investing.html.connection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.sugarsoft.commodities.investing.http.connection.impl.HtmlConnectionService;

public class HtmlConnectionTest {

	private HtmlConnectionService engine;
	
	@Before
	public void init() throws Exception{
		engine = new HtmlConnectionService(HtmlConnectionService.SECTION_URL);
	}
	
	@Test
	public void connect() throws IOException{
		List<String> response = engine.getHeader(null);
		assertTrue(!response.isEmpty());
		assertEquals(response.get(0),"HTTP/1.1 200 OK");
	}
}
