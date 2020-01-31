package com.example.webapp.services.authorization;

import com.example.webapp.db.model.authorization.Meeting;
import com.example.webapp.db.repo.authorization.MeetingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    MeetingRepo meetingRepo;


    public void saveMeeting(Meeting meeting, String initiator) {
        meeting.setInitiator(initiator);
        meeting.setEndTime();
        meetingRepo.save(meeting);
    }
}
