package com.example.webapp.services.authorization;

import com.example.webapp.db.model.Person;
import com.example.webapp.db.model.Roles;
import com.example.webapp.db.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PersonRepo personRepo;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        Person person = personRepo.findByUsername(username);
        if (person == null) throw new UsernameNotFoundException(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Roles role : person.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.USER.toString()));
        }

        return new org.springframework.security.core.userdetails.User(person.getUsername(), person.getPassword(), grantedAuthorities);
    }
}
