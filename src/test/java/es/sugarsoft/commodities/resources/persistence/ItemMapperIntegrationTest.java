package es.sugarsoft.commodities.resources.persistence;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.sugarsoft.commodities.services.impl.WorkerServiceImpl;
import es.sugarsoft.commodities.servlet.config.MyBatisConfig;
import es.sugarsoft.test.support.appconfig.DataSourceConfig;


@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ItemMapperIntegrationTest {

	@Configuration
	@ComponentScan(basePackageClasses = WorkerServiceImpl.class)
	@Import({DataSourceConfig.class, MyBatisConfig.class})
	static class LocalConfig {
		// nothing to declare - we get all the beans we need through component scanning
	}

	@Autowired
	private ItemMapper itemMapper;
	
	@Test
	public void testAdd() {
		itemMapper.add(1l, new Date(), 1.2);
	}
	
	@Test
	public void testGetValues() {
		itemMapper.getValues(1, 122) ;
	}
	

	@Test
	public void testGetValuesBySection() {
		itemMapper.getValuesBySection(1, 122) ;
	}
}
