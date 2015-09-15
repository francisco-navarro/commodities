package es.sugarsoft.commodities.resources.persistence;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import es.sugarsoft.test.support.domain.Person;

public interface PersonMapper extends MyMapper {

    public Person getByFirstName(@Param("firstname") String firstname);
}
