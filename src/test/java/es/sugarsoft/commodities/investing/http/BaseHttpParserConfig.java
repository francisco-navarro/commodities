package es.sugarsoft.commodities.investing.http;

import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.sugarsoft.commodities.investing.http.connection.impl.HtmlConnectionService;
import es.sugarsoft.commodities.investing.http.parser.impl.HttpTableParser;
import es.sugarsoft.commodities.servlet.config.MyBatisConfig;
import es.sugarsoft.test.support.appconfig.DataSourceConfig;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseHttpParserConfig {

	@Configuration
	@ComponentScan(basePackageClasses = {HtmlConnectionService.class, HttpTableParser.class})
	@Import({DataSourceConfig.class, MyBatisConfig.class})
	static class LocalConfig {
		// nothing to declare - we get all the beans we need through component scanning
	}
	
	
}
