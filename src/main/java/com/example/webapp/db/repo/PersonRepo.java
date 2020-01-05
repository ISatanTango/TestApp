package com.example.webapp.db.repo;

import com.example.webapp.db.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepo extends CrudRepository <Person, Long> {
    Optional<Person> findById(Long id);
}

