package es.sugarsoft.commodities.test.support.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.sugarsoft.commodities.test.support.domain.Person;
import es.sugarsoft.commodities.test.support.mapper.PersonMapper;

@Service
public class PersonService {

    @Autowired
    private PersonMapper personMapper;

    public Person getByFirstName(final String firstname) {
        return personMapper.getByFirstName(firstname);
    }
}
