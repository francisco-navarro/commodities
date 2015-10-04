package es.sugarsoft.commodities.investing.http.parser;

import java.util.Date;

import org.junit.Test;

import es.sugarsoft.commodities.investing.http.parser.impl.HttpHistoryParser;
import es.sugarsoft.commodities.resources.Item;

public class HttpHistoryParserTest {
	

	private HttpHistoryParser parser;
	
	@Test
	public void shouldRetrieveItemInformation() throws Exception{
		Item item = new Item();
		item.setUrl("/commodities/gold");
		parser.getItemDetails(item, new Date(), new Date() );
	}
	

}
