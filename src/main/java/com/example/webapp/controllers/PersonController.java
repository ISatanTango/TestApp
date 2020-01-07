package com.example.webapp.controllers;

import com.example.webapp.db.model.Person;
import com.example.webapp.db.model.Roles;
import com.example.webapp.db.repo.PersonRepo;
import com.example.webapp.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;
import java.util.Optional;

/*@RestController*/
@Controller
/*@RequestMapping(value = "", produces = "application/json")*/
public class PersonController {

    @Autowired
    PersonService personService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    PersonRepo personRepo;
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String createPerson(String username, String password) {
        Person person = new Person();
        person.setUsername(username);
        person.setPassword(passwordEncoder.encode(password));
        person.setRoles(Collections.singleton(Roles.USER));
        person.setActive(true);
        personRepo.save(person);
        return "redirect:/login";
    }

    @GetMapping("/")
    public String index(Principal principal)
    {
        if(principal != null)
        {
            return "redirect:/notes";
        }
        return "index";
    }

    @RequestMapping("delete-person/{id}")
    public void deletePerson(@PathVariable("id") Long id) {
        personService.deletePerson(id);
    }

    @RequestMapping("get-person/{id}")
    public Optional<Person> getPerson(@PathVariable("id") Long id) {
        return personService.getPerson(id);
    }

    @RequestMapping("get-persons")
    public Iterable<Person> getPerson() {
        return personService.getPerson();
    }
}
