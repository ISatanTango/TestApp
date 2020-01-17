package com.example.webapp.services.authorization;

import com.example.webapp.db.model.authorization.Person;
import com.example.webapp.db.model.authorization.Role;
import com.example.webapp.db.repo.authorization.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.Optional;

@Service
public class PersonService implements UserDetailsService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private PersonRepo personRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepo.findByUsername(username);
        if (person == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return person;
    }

    /**
     *
     * @param personId
     * @return exist Person or create new Person
     */
    public Person findPersonById(Long personId) {
        Optional<Person> personFromDb = personRepo.findById(personId);
        return personFromDb.orElse(new Person());
    }

    /**
     *
     * @return all exist Persons
     */
    public Iterable<Person> findAllPersons() {
        return personRepo.findAll();
    }

    /**
     *
     * @param person
     * @return
     */
    public boolean savePerson(Person person) {
        Person personFromDb = personRepo.findByUsername(person.getUsername());
        if (personFromDb != null) {
            return false;
        }
        person.setRoles(Collections.singleton(Role.ROLE_USER));
        person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
        personRepo.save(person);
        return true;
    }
}
