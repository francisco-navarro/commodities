package es.sugarsoft.commodities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ConnectionTest {

	private Connection engine;
	
	@Before
	public void init() throws Exception{
		engine = new Connection("http://sbcharts.investing.com/charts_xml/jschart_sideblock_169_area.json");
	}
	
	@Test
	public void connect() throws IOException{
		List<String> response = engine.getHeader(null);
		assertTrue(!response.isEmpty());
		assertEquals(response.get(0),"HTTP/1.1 200 OK");
	}
}
