package es.sugarsoft.commodities;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import javax.xml.xpath.XPathExpressionException;

import org.junit.Before;
import org.junit.Test;

import es.sugarsoft.commodities.resources.Commodity;

public class UrlTableTest {
	
	private UrlTableEngine urlTableEngine;
	
	@Before
	public void init() throws Exception{
		urlTableEngine = new UrlTableEngine("http://es.investing.com/commodities/");
	}

	@Test
	public void initValues() throws Exception{
		List<Commodity> list = urlTableEngine.getList();
		assertTrue(!list.isEmpty());
	}
}
