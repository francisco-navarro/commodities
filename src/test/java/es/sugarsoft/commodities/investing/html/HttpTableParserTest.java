package es.sugarsoft.commodities.investing.html;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.sugarsoft.commodities.investing.http.HttpTableParser;
import es.sugarsoft.commodities.investing.http.connection.IHtmlConnectionService;
import es.sugarsoft.commodities.investing.http.connection.impl.HtmlConnectionService;
import es.sugarsoft.commodities.resources.Item;
import es.sugarsoft.commodities.servlet.config.MyBatisConfig;
import es.sugarsoft.test.support.appconfig.DataSourceConfig;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class HttpTableParserTest {


	@Configuration
	@ComponentScan(basePackageClasses = HtmlConnectionService.class)
	@Import({DataSourceConfig.class, MyBatisConfig.class})
	static class LocalConfig {
		// nothing to declare - we get all the beans we need through component scanning
	}
	
	@Autowired
	private IHtmlConnectionService htmlConnectionService; 
	
	private HttpTableParser parser = new HttpTableParser();
	
	@Test
	public void givenUrlWhenLoadItemsThenGetTheUrl() throws Exception{
		
		String connectionResult = htmlConnectionService.connect(HtmlConnectionService.SECTION_URL + "commodities/" );
		List<Item> list = parser.getItems(connectionResult);
		
		assertTrue(!list.isEmpty());
		for(Item item : list){
			assertNotNull(item.getUrl());
			assertNotNull(item.getPointValue());
		}
	}

}
