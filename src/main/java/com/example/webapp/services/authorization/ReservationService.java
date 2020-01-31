package com.example.webapp.services.authorization;

import com.example.webapp.db.model.authorization.Meeting;

public interface ReservationService {
    public void saveMeeting(Meeting meeting, String initiator);
}
