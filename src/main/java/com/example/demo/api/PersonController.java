package com.example.demo.api;

import com.example.demo.service.PersonService;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

// Using rest controllers gives us access to endpoints that clients can consume.
// We need to map the endpoint to the controller, which needs to map to a method, which in this case
// needs to the take the requestbody as the parameter.

// Post, Get, Update, Delete

@RequestMapping("/api/v1/person")
@RestController
public class PersonController {

    // We create an instance of the service class
    private final PersonService personService;

    // We also need DI, but personService is a class, so we do not need a qualifier.
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    // We call the addPerson method on the instance of service.
    // Post: adding resource to the server.
    // @RequestBody we want to take the body of the request and attribute it to Person
    // in other words, turn JSON object into a person

    // post mapping adds to the database
    @PostMapping
    public void addPerson(@RequestBody Person person){
        personService.addPerson(person);

    }

    @GetMapping
    public List<Person> getAllPeople(){
        return personService.getAllPeople();
    }
    // get mapping retrieves from database
    @GetMapping("{id}")
    public Person getPersonById(@PathVariable("id") UUID id){
        return personService.getPersonById(id).orElse(null);
    }

    @DeleteMapping(path="{id}")
    public void deletePersonById(@PathVariable("id") UUID id){
        personService.deletePerson(id);
    }
    // put mapping updates database
    // in this case we need to provide a message body
    @PutMapping(path="{id}")
    public void updatePersonById(@PathVariable("id") UUID id, @RequestBody Person personToUpdate){
        personService.updatePerson(id, personToUpdate);

    }


}
