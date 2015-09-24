package es.sugarsoft.commodities.investing.html;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import es.sugarsoft.commodities.investing.http.HttpTableParser;
import es.sugarsoft.commodities.investing.http.connection.HtmlConnection;
import es.sugarsoft.commodities.resources.Item;

public class HttpTableParserTest {
	
	
	private HttpTableParser parser = new HttpTableParser();
	
	@Test
	public void givenUrlWhenLoadItemsThenGetTheUrl() throws Exception{
		HtmlConnection connection = new HtmlConnection(HtmlConnection.SECTION_URL + "commodities/" );
		List<Item> list = parser.getItems(connection.getOutput());
		
		assertTrue(!list.isEmpty());
		for(Item item : list){
			assertNotNull(item.getUrl());
			assertNotNull(item.getPointValue());
		}
	}

}
