package com.example.webapp.controllers.main;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class MainController {
    @RequestMapping("/")
    public ModelAndView index(ModelAndView modelAndView) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String string = auth.getName();
        if (string != null) {
            modelAndView.addObject("usernamePerson", string);
            modelAndView.setViewName("index");
            return modelAndView;
        }
        modelAndView.setViewName("index");
        return modelAndView;
    }


}
