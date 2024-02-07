package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

// The DAO needs to be instantiated as a bean so we can inject it in other classes.
// Component could also be used, but repository is more precise.

/*
IMPORTANT: this stores everything in an ArrayList. It does not persist after runtime,
 */
@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao{
    private static List<Person> DB = new ArrayList<>();

    // There is a need to override the method with no implementation! The other is defaulted.
    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public int deletePersonById(UUID id) {
        //get person, if there delete, else return 0
        Optional<Person> person = selectPersonById(id);
        if(person.isEmpty()) {
            return 0;
        }
        DB.remove(person.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person update) {
        return selectPersonById(id).map(p-> {
            int indexOfPersonToUpdate = DB.indexOf(p);
            if (indexOfPersonToUpdate >= 0){
                // the updated person (new name) is set at the original index specified
                DB.set(indexOfPersonToUpdate, new Person(id, update.getName()));
                return 1;
            }
            return 0;
        }).orElse(0);
    }

    // Optional is used to handle the fact that the person may or may not exist in the Collection.
    @Override
    public Optional<Person> selectPersonById(UUID id) {
        // the predicate is inferred to be of type person because it is called on the DB ArrayList
        return DB.stream().filter(person -> person.getId().equals(id)).findFirst();
    }
}
