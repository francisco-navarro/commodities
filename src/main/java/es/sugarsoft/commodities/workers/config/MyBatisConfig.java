package es.sugarsoft.commodities.workers.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.sugarsoft.commodities.resources.persistence.MyMapper;

@Configuration
@MapperScan(basePackageClasses = { MyMapper.class } )
public class MyBatisConfig {

    @Autowired
    DataSource dataSource;
    
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setTypeAliasesPackage("es.sugarsoft.commodities.test.support.domain");
        return sessionFactory.getObject();
    }

}
