package com.example.webapp.db.repo.authorization;

import com.example.webapp.db.model.authorization.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MeetingRepo extends JpaRepository <Meeting, Long> {
    /*@Query("SELECT DATE, INITIATOR, PARTICIPANT FROM MEETING WHERE YEAR(DATE) = YEAR(NOW()) AND WEEK(DATE) = WEEK(NOW())")
    List<Meeting>  ();*/
    List<Meeting> findMeetingByWeekOrderByDate(int week);
}
