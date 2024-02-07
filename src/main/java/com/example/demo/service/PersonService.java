package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    // We create an instance of the database INTERFACE.
    // Notice that the instance is NOT instantiated, so dependency injection with Spring MUST BE USED!
    private final PersonDao personDao;

    // We need to link this to a bean in the DAO
    // How do we know which interface to link to? We need to use the @qualifier
    // The qualifier maps to the fakeDao, so in the FakePersonDataAccessService, we need to name the repo "fakeDao"

    @Autowired
    public PersonService(@Qualifier("postgres") PersonDao personDao){
        this.personDao = personDao;
    }

    // We define addPerson, which is used in our controller.
    // This method calls insertPerson on the instance of the DAO.
    public int addPerson(Person person){
        // option of providing the ID or not
        return personDao.insertPerson(person);
    }
    public List<Person> getAllPeople(){
        return personDao.selectAllPeople();
    }

    public Optional<Person> getPersonById(UUID id){
        return personDao.selectPersonById(id);

    }
    public int deletePerson(UUID id){
        return personDao.deletePersonById(id);
    }

    public int updatePerson(UUID id, Person newPerson){
        return personDao.updatePersonById(id, newPerson);
    }
}
