package com.example.webapp.db.repo;

import com.example.webapp.db.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo extends CrudRepository<Person, Long> {
    Person findByUsername(String username);
}

