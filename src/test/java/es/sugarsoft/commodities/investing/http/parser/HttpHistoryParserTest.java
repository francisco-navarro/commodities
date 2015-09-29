package es.sugarsoft.commodities.investing.http.parser;

import org.junit.Test;

import es.sugarsoft.commodities.investing.http.parser.impl.HttpDetailParser;
import es.sugarsoft.commodities.investing.http.parser.impl.HttpHistoryParser;
import es.sugarsoft.commodities.investing.http.parser.impl.HttpTableParser;
import es.sugarsoft.commodities.resources.Item;

public class HttpHistoryParserTest extends BaseHttparserConfig{
	

	private HttpHistoryParser parser;
	
	@Test
	public void shouldRetrieveItemInformation() throws Exception{
		Item item = new Item();
		item.setUrl("/commodities/gold");
		item= parser.getItemDetails(item);
	}
	

}
