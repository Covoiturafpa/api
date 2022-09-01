package fr.afpa.covoiturafpa.model;

import java.time.LocalTime;

public class DayTimetable {
    private int id;
    private Day day;
    private LocalTime startMorning;
    private LocalTime endMorning;
    private LocalTime startAfternoon;
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
