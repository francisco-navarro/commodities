package es.sugarsoft.commodities.investing.http.parser;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import es.sugarsoft.commodities.investing.http.BaseHttpParserConfig;
import es.sugarsoft.commodities.investing.http.parser.impl.HttpDetailParser;
import es.sugarsoft.commodities.resources.Item;

public class HttpDetailParserTest extends BaseHttpParserConfig{
	
	@Autowired
	private HttpDetailParser parser;
	
	@Test
	public void shouldRetrieveItemInformation() throws Exception{
		Item item = new Item();
		item.setUrl("/commodities/gold");
		item = parser.getItemDetails(item);
		assertNotNull(item.getContractSize());
	}
	

}
