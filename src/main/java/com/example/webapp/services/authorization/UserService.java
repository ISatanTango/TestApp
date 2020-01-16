package com.example.webapp.services.authorization;

import com.example.webapp.db.model.Person;

public interface UserService {
    void save(Person person);
    Person findByLogin(String login);
}
