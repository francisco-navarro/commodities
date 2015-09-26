package es.sugarsoft.commodities.investing.html.connection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.sugarsoft.commodities.investing.http.connection.impl.SocketChartConnectionService;

public class SocketConnectionTest {

	private SocketChartConnectionService engine;
	
	@Before
	public void init() throws Exception{
		engine = new SocketChartConnectionService(8830l);
	}
	
	//TODO: Hacer un test distinto con mock de las cookies
//	@Test
//	public void connect() throws IOException{
//		List<String> response = engine.getHeader(null);
//		assertTrue(!response.isEmpty());
//		assertEquals(response.get(0),"HTTP/1.1 200 OK");
//	}
}
