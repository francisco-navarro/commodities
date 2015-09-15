package es.sugarsoft.commodities.test.support.mapper;

import org.apache.ibatis.annotations.Param;

import es.sugarsoft.commodities.test.support.domain.Person;

public interface PersonMapper extends MyMapper {

    public Person getByFirstName(@Param("firstname") String firstname);
}
