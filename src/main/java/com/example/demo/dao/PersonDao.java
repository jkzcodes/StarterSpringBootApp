package com.example.demo.dao;
import com.example.demo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// contract for anyone wishing to implement a databse.
public interface PersonDao {

    // Restarting the application means that none of this will be saved.

    // We provide methods without the method body or default implementations of the method.
    // Need to create a class that implements the interface.
    int insertPerson(UUID id, Person person);

    default int insertPerson(Person person){
        UUID id = UUID.randomUUID();
        return insertPerson(id,person);
    }

    List<Person> selectAllPeople();

    int deletePersonById(UUID id);

    int updatePersonById(UUID id, Person person);

    Optional<Person> selectPersonById(UUID id);

}
