package com.example.webapp.services;

import com.example.webapp.db.model.Person;

import java.util.Collection;
import java.util.Optional;

public interface PersonService {
    public abstract Person createPerson(Person person);
    public abstract void deletePerson(Long id);
    /*public abstract void updatePerson(Long id, Person person);*/
    public abstract Optional<Person> getPerson(Long id);
    /*public abstract Collection<Person> getPerson();*/
}
