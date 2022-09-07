package fr.afpa.covoiturafpa.model;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "day_timetable")
public class DayTimetable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_day_timetable")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "day")
    private Day day;

    @Column(name = "start_morning")
    private LocalTime startMorning;

    @Column(name = "end_morning")
    private LocalTime endMorning;

    @Column(name = "start_afternoon")
    private LocalTime startAfternoon;

    @Column(name = "end_afternoon")
    private LocalTime endAfternoon;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public LocalTime getStartMorning() {
        return startMorning;
    }

    public void setStartMorning(LocalTime startMorning) {
        this.startMorning = startMorning;
    }

    public LocalTime getEndMorning() {
        return endMorning;
    }

    public void setEndMorning(LocalTime endMorning) {
        this.endMorning = endMorning;
    }

    public LocalTime getStartAfternoon() {
        return startAfternoon;
    }

    public void setStartAfternoon(LocalTime startAfternoon) {
        this.startAfternoon = startAfternoon;
    }

    public LocalTime getEndAfternoon() {
        return endAfternoon;
    }

    public void setEndAfternoon(LocalTime endAfternoon) {
        this.endAfternoon = endAfternoon;
    }

    public DayTimetable() {
    }
}
