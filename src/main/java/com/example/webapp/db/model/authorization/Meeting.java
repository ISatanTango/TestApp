package com.example.webapp.db.model.authorization;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "MEETING")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "initiator")
    private String initiator;

    @Column(name = "participant")
    private String participant;

    @Column(name = "date")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date date;

    /**
     * duration meet (in minutes)
     */
    @Column(name = "duration")
    private int duration;

    /**
     * Time is end meeting
     */
    @Column(name = "endTime")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date endTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime() {
        // Calendar.getInstance инициализирует пустой объект класса Calendar
        Calendar calendar = Calendar.getInstance();

        // Установка времени начала встречи из вэб-формы, поле date класса Meeting
        calendar.setTime(this.getDate());

        // Установка времени конца встречи, прибавление к начальному времени продолжительность
        calendar.add(Calendar.MINUTE, duration);
        this.endTime = (calendar.getTime());
    }
}
