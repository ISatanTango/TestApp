package com.example.webapp.services.authorization;

import com.example.webapp.db.model.Person;
import com.example.webapp.db.model.Roles;
import com.example.webapp.db.repo.PersonRepo;
import com.example.webapp.services.authorization.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PersonRepo personRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(Person person) {
        person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
        person.setRoles(new HashSet<>(Arrays.asList(Roles.USER)));
        personRepo.save(person);
    }

    @Override
    public Person findByLogin(String username) {
        return personRepo.findByUsername(username);
    }
}
