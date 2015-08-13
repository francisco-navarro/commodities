package es.sugarsoft.commodities.servlet.config;  
  
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
  
@Configuration 
@ComponentScan({"es.sugarsoft.commodities.controller",
	"es.sugarsoft.commodities.resources.dao.h2",
	"es.sugarsoft.commodities.investing.services.impl"}) 
@EnableWebMvc   
public class AppConfig {  

}  
