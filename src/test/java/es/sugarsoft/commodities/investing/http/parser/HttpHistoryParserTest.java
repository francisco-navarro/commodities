package es.sugarsoft.commodities.investing.http.parser;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.sugarsoft.commodities.investing.http.connection.ISocketHistoricalConnectionService;
import es.sugarsoft.commodities.investing.http.parser.impl.HttpHistoryParser;
import es.sugarsoft.commodities.resources.Item;
import es.sugarsoft.commodities.resources.ItemHistory;

public class HttpHistoryParserTest {

	private HttpHistoryParser parser;
	
	@Before
	public void init(){
		ISocketHistoricalConnectionService connection = mock(ISocketHistoricalConnectionService.class);
		parser = new HttpHistoryParser(connection );
		String html = getHtml();
		when(connection.getData(anyLong(), any(Date.class), any(Date.class))).thenReturn(html );
	}
	
	private String getHtml() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("test/historicalData.html").getFile());
		StringBuilder result = new StringBuilder("");
		String line = null;
		
		try (BufferedReader bf = new BufferedReader(new FileReader(file))) {
			while ((line = bf.readLine())!=null) {				
				result.append(line).append("\n");
			}
			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	@Test
	public void shouldRetrieveItemInformation() throws Exception{
		Item item = new Item();
		item.setUrl("/commodities/gold");
		List<ItemHistory> list = parser.getItemDetails(item.getId(), new Date(), new Date() );
		assertTrue(!list.isEmpty());
	}
	

}
