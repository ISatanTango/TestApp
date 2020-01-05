package com.example.webapp.services;

import com.example.webapp.db.model.Person;
import com.example.webapp.db.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepo personRepo;

    @Override
    public Person createPerson(Person person) {
        personRepo.save(person);
        return person;
    }

    @Override
    public void deletePerson(Long id) {
        personRepo.deleteById(id);
    }

    @Override
    public Optional<Person> getPerson(Long id) {
        return personRepo.findById(id);
    }
}
