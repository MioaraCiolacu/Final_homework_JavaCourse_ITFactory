package com.itfactory.sbdemo.service;

import com.itfactory.sbdemo.dao.PersonDao;
import com.itfactory.sbdemo.models.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class PersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);

    private final PersonDao personDao;

    @Autowired
    public PersonService(PersonDao personDao) {
        this.personDao = personDao;
    }


    public boolean insertPerson(Person person) {
        return personDao.insertPerson(person);
    }

    public boolean deletePerson(int id) {
        return personDao.deletePerson(id);
    }

    public boolean updatePersonEmail(int id, String email) {
        return personDao.updatePersonEmail(id,email);
    }

    public boolean updatePersonTelephone(int id, String newTelephone) {
        return personDao.updatePersonTelephone(id, newTelephone);
    }

    public List<Person> retrievePersons() {
        LOGGER.info("Accessing service for person retrieval");
        return personDao.retrievePersons();
    }
}