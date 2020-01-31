package com.example.webapp.db.repo.authorization;

import com.example.webapp.db.model.authorization.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepo extends JpaRepository <Meeting, Long> {
}
