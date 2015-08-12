package es.sugarsoft.commodities.investing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.sugarsoft.commodities.investing.Connection;

public class ConnectionTest {

	private Connection engine;
	
	@Before
	public void init() throws Exception{
		engine = new Connection(Connection.TABLE_URL);
	}
	
	@Test
	public void connect() throws IOException{
		List<String> response = engine.getHeader(null);
		assertTrue(!response.isEmpty());
		assertEquals(response.get(0),"HTTP/1.1 200 OK");
	}
}
