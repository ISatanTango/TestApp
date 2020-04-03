package com.example.webapp.services.authorization;

import com.example.webapp.db.model.authorization.Meeting;
import com.example.webapp.db.repo.authorization.MeetingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    MeetingRepo meetingRepo;


    public void saveMeeting(Meeting meeting, String initiator) {
        meeting.setInitiator(initiator);
        meeting.setEndTime();
        meeting.setWeek();
        meeting.setWeekday();
        meetingRepo.save(meeting);
    }

    @Override
    public void getMeeting(Long id) {
        //TODO
    }

    @Override
    public List<Meeting> getMeetingForWeek(int week) {
        List<Meeting> meetingList;
        meetingList = meetingRepo.findMeetingByWeekOrderByDate(week);
        return meetingList;

    }
}
