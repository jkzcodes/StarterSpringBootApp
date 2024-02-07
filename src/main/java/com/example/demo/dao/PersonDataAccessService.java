package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao{

    private final PersonRepository personRepository;

    // Dependency injection
    @Autowired
    public PersonDataAccessService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        Person newp = new Person(id, person.getName());
        personRepository.save(newp);
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return personRepository.findAll();
       // return
               // List.of(new Person(UUID.randomUUID(), "FROM POSTGRES DB"));
    }

    @Override
    public int deletePersonById(UUID id) {
        personRepository.deleteById(id);
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person person){
        Optional<Person> existingPerson = personRepository.findById(id);
        if (existingPerson.isPresent()) {
            Person updatedPerson = existingPerson.get();
            updatedPerson.setName(person.getName());
            personRepository.save(updatedPerson);
            return 1; // Indicate success
        }
        return 0; // Indicate failure (person not found)
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return personRepository.findById(id);
    }
}
