package com.example.webapp.controllers.main;

import com.example.webapp.db.model.authorization.Meeting;
import com.example.webapp.db.repo.authorization.MeetingRepo;
import com.example.webapp.services.authorization.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@RestController
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

    @Autowired
    MeetingRepo meetingRepo;
    @Autowired
    ReservationServiceImpl reservationService;

    @PostMapping("/reservation")
    public String createMeeting(@ModelAttribute("meetingForm") Meeting meeting) {
        // Получение имени текущего пользователя
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        reservationService.saveMeeting(meeting, auth.getName());
        return "OK";
    }

    @GetMapping("/meeting")
    public List<Meeting> showMeeting(@RequestParam ("week") int week) {
        return reservationService.getMeetingForWeek(week);
    }
}
