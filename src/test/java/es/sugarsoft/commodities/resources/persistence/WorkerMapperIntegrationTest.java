package es.sugarsoft.commodities.resources.persistence;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.sugarsoft.commodities.services.WorkerService;
import es.sugarsoft.commodities.services.impl.WorkerServiceImpl;
import es.sugarsoft.commodities.workers.config.MyBatisConfig;
import es.sugarsoft.commodities.workers.resources.WorkerResource;
import es.sugarsoft.test.support.appconfig.DataSourceConfig;


@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class WorkerMapperIntegrationTest {

	@Configuration
	@ComponentScan(basePackageClasses = WorkerServiceImpl.class)
	@Import({DataSourceConfig.class, MyBatisConfig.class})
	static class LocalConfig {
		// nothing to declare - we get all the beans we need through component scanning
	}

	@Autowired
	private WorkerService workerService;
	
	@Test
	public void testService() {
		System.out.println(workerService.count());		
		List<WorkerResource> list = workerService.getAllSectionWorkers();
		System.out.println(list);
	}
}
