package com.example.webapp.controllers.authorization;

import com.example.webapp.db.model.authorization.Person;
import com.example.webapp.services.authorization.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
public class RegistrationController {
    @Autowired
    private PersonServiceImpl personService;

    @GetMapping("/registration")
    public ModelAndView registration(ModelAndView modelAndView) {
        modelAndView.addObject("personForm", new Person());
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView createPerson(@ModelAttribute("personForm") @Valid Person personForm,
                                     BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("createPerson");
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
            return modelAndView;
        }

        if (!personForm.getPassword().equals(personForm.getPasswordConfirm())) {
            modelAndView.addObject("passwordError", "Пароли не совпадают");
            modelAndView.setViewName("registration");
            return modelAndView;
        }

        if (!personService.savePerson(personForm)) {
            modelAndView.addObject("usernameError", "Пользователь с таким имененем уже существует");
            modelAndView.setViewName("registrarion");
            return modelAndView;
        }

        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}


