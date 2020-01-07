package com.example.webapp.services;

import com.example.webapp.db.model.Person;
import com.example.webapp.db.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PersonRepo personRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person userFindByUsername = personRepo.findByUsername(username);
        if (userFindByUsername != null) {
            return (UserDetails) userFindByUsername;
        }
        return null;
    }
}
