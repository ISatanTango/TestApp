package com.example.webapp.services;

import com.example.webapp.db.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class AuthProvider implements AuthenticationProvider {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        Person person = (Person) userService.loadUserByUsername(username);

        if (person != null && (person.getUsername().equals(username))) {
            if (!passwordEncoder.matches(password, person.getPassword())) {
                throw new BadCredentialsException("Wrong password");
            }
            Collection<? extends GrantedAuthority> authorities = person.getAuthorities();

            return new UsernamePasswordAuthenticationToken(person, password, authorities);
        }
        else
            throw new BadCredentialsException("Username not found");
    }

    public boolean supports(Class<?> arg)
    {
        return true;
    }
}
