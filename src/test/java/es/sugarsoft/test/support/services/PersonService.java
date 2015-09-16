package es.sugarsoft.test.support.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.sugarsoft.commodities.resources.Person;
import es.sugarsoft.commodities.resources.persistence.PersonMapper;

@Service
public class PersonService {

    @Autowired
    private PersonMapper personMapper;

    public Person getByFirstName(final String firstname) {
        return personMapper.getByFirstName(firstname);
    }
}