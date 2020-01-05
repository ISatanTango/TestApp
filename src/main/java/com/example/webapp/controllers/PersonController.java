package com.example.webapp.controllers;

import com.example.webapp.db.model.Person;
import com.example.webapp.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "", produces = "application/json")
public class PersonController {

    @Autowired
    PersonService personService;

    @RequestMapping("create-person/{name}")
    public Person createPerson(@PathVariable("name") String name) {
        return personService.createPerson(new Person(name));
    }

    @RequestMapping("delete-person/{id}")
    public void deletePerson(@PathVariable("id") Long id) {
        personService.deletePerson(id);
    }

    @RequestMapping("get-person/{id}")
    public Optional<Person> getPerson(@PathVariable("id") Long id) {
        return personService.getPerson(id);
    }

}
