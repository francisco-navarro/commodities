package es.sugarsoft.commodities.investing.http.parser;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import es.sugarsoft.commodities.investing.http.parser.impl.HttpTableParser;
import es.sugarsoft.commodities.investing.http.util.UriConstants;
import es.sugarsoft.commodities.resources.Item;

public class HttpTableParserTest extends BaseHttparserConfig{

	
	@Autowired
	private HttpTableParser parser;
	
	@Test
	public void givenUrlWhenLoadItemsThenGetTheUrl() throws Exception{
		
		String url = UriConstants.SECTION_URL + "commodities/";
		List<Item> list = parser.getItemsFromTableUrl(url);
		
		assertTrue(!list.isEmpty());
		for(Item item : list){
			assertNotNull(item.getUrl());
			assertNotNull(item.getPointValue());
		}
	}

}
