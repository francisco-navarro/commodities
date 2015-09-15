package es.sugarsoft.commodities.resources.persistence;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.sugarsoft.test.support.appconfig.DataSourceConfig;
import es.sugarsoft.test.support.appconfig.MyBatisConfig;
import es.sugarsoft.test.support.domain.Person;
import es.sugarsoft.test.support.services.PersonService;


@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class WorkerMapperIntegrationTest {

	@Configuration
	@ComponentScan(basePackageClasses = PersonService.class)
	@Import({DataSourceConfig.class, MyBatisConfig.class})
	static class LocalConfig {
		// nothing to declare - we get all the beans we need through component scanning
	}

	@Autowired
	PersonService personService;

	@Test
	public void testService() {
		final Person person = personService.getByFirstName("scooby");
		assertEquals("scooby", person.getFirstname());
		assertEquals("doo", person.getLastname());
		System.out.println(person);
	}
}
