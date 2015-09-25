package es.sugarsoft.commodities.investing.html;

import org.junit.Test;

import es.sugarsoft.commodities.investing.http.HttpDetailParser;
import es.sugarsoft.commodities.investing.http.HttpTableParser;
import es.sugarsoft.commodities.resources.Item;

public class HttpHistoryParserTest {
	

	private HttpDetailParser parser;
	
	@Test
	public void shouldRetrieveItemInformation() throws Exception{
		Item item = new Item();
		item.setUrl("/commodities/gold");
		parser = new HttpDetailParser(item.getUrl());
		item= parser.getItemDetails(item);
	}
	

}
