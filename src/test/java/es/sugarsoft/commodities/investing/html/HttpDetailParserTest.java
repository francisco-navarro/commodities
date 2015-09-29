package es.sugarsoft.commodities.investing.html;

import org.junit.Test;

import es.sugarsoft.commodities.investing.http.parser.impl.HttpDetailParser;
import es.sugarsoft.commodities.investing.http.parser.impl.HttpTableParser;
import es.sugarsoft.commodities.resources.Item;

public class HttpDetailParserTest {
	

	private HttpDetailParser parser;
	
	@Test
	public void shouldRetrieveItemInformation() throws Exception{
		Item item = new Item();
		item.setUrl("/commodities/gold");
		parser = new HttpDetailParser(item.getUrl());
		item= parser.getItemDetails(item);
	}
	

}
