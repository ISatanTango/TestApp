package com.example.webapp.db.repo.authorization;

import com.example.webapp.db.model.authorization.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long> {
    Person findByUsername(String username);
}

