package es.sugarsoft.commodities.investing.http.parser;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import es.sugarsoft.commodities.investing.http.BaseHttpParserConfig;
import es.sugarsoft.commodities.investing.http.connection.IHtmlConnectionService;
import es.sugarsoft.commodities.investing.http.parser.impl.HttpTableParser;
import es.sugarsoft.commodities.investing.http.util.UriConstants;
import es.sugarsoft.commodities.resources.Item;

public class HttpTableParserTest extends BaseHttpParserConfig{

	//SUT
	private HttpTableParser parser;
	
	private IHttpDetailParser httpDetailParser = mock(IHttpDetailParser.class);
	
	@Autowired
	private IHtmlConnectionService htmlConnectionService;
	
	@Autowired
	private IChartEngine chartEngine;

	@Before
	public void init(){
		parser = new HttpTableParser(httpDetailParser, htmlConnectionService, chartEngine);
	}
	
	@Test
	public void givenUrlWhenLoadItemsThenGetTheUrl() throws Exception{
		
		String url = UriConstants.SECTION_URL + "commodities/";
		List<Item> list = parser.getItemsFromTableUrl(url);
		
		assertTrue(!list.isEmpty());
		for(Item item : list){
			assertNotNull(item.getUrl());		
		}
	}

}
