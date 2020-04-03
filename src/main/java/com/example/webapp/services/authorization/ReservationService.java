package com.example.webapp.services.authorization;

import com.example.webapp.db.model.authorization.Meeting;

import java.util.Date;
import java.util.List;

public interface ReservationService {
    public void saveMeeting(Meeting meeting, String initiator);
    public void getMeeting(Long id);
    List<Meeting> getMeetingForWeek(int week);
}
