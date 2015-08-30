package es.sugarsoft.commodities.servlet.config;  
  
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
  
@Configuration 
@ComponentScan({"es.sugarsoft.commodities.controller",
	"es.sugarsoft.commodities.resources.dao.h2",
	"es.sugarsoft.commodities.resources.dao.jndi",
	"es.sugarsoft.commodities.investing.services.impl"}) 
@EnableWebMvc   
public class AppConfig extends WebMvcConfigurationSupport {

		
	@Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {

		RequestMappingHandlerMapping handlerMapping = super.requestMappingHandlerMapping();
		return handlerMapping;
	}
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(converter());
        addDefaultHttpMessageConverters(converters);
    }

	@Bean
	MappingJackson2HttpMessageConverter converter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		//Customizations		
//		ObjectMapper objectMapper = converter.getObjectMapper();
//		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//		objectMapper.registerModule(new ResourcesModule());
		
        return converter;
    }  

}  
