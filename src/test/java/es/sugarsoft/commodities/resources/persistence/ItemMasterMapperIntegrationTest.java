package es.sugarsoft.commodities.resources.persistence;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.sugarsoft.commodities.resources.Item;
import es.sugarsoft.commodities.services.impl.WorkerServiceImpl;
import es.sugarsoft.commodities.servlet.config.MyBatisConfig;
import es.sugarsoft.test.support.appconfig.DataSourceConfig;

@Ignore
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ItemMasterMapperIntegrationTest {

	@Configuration
	@ComponentScan(basePackageClasses = WorkerServiceImpl.class)
	@Import({DataSourceConfig.class, MyBatisConfig.class})
	static class LocalConfig {
		// nothing to declare - we get all the beans we need through component scanning
	}

	@Autowired
	private ItemMasterMapper itemMasterMapper;
	
	@Test
	public void testAdd() {
		Item commodity = new Item();
		commodity.setDescription("aa");
		commodity.setId(2l);
		commodity.setJson("json values");
		itemMasterMapper.add(commodity, 1l);
	}
	
	@Test
	public void testGetValues() {
		itemMasterMapper.getById(1l);
	}
	

}
