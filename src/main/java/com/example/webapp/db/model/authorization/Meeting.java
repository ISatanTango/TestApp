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

    @Column(name = "week")
    private int week;

    @Column(name = "weekday")
    private int weekday;

    public int getWeek() {
        return week;
    }

    public int getWeekday() {
        return weekday;
    }
    // Установка дня недели
    public void setWeekday() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.getDate());
        // День недели в России в понедельника
        weekday = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        // Если воскресенье присвоить значени 7
        if (weekday == 0) {
            this.weekday = 7;
        }
    }
    // Set number is week
    public void setWeek() {
        // Создание пустого объекта календарь
        Calendar calendar = Calendar.getInstance();
        // Получение даты
        calendar.setTime(this.getDate());
        // Получение номера недели в году
        this.week = calendar.get(Calendar.WEEK_OF_YEAR);
    }

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
