package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

// Each entity represents a table in the database
@Entity
public class Person {

    @Id
    private  UUID id;
    private String name;


    // We need to map the name and the id to a postman request.
    public Person(@JsonProperty("id") UUID id, @JsonProperty("name") String name){
        this.name = name;
        this.id = id;
    }

    public Person() {

    }

    public UUID getId(){
        return id;
    }
    public String getName(){
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }
}
