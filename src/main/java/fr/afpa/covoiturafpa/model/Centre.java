package fr.afpa.covoiturafpa.model;

import java.util.List;

public class Centre {
    private int id;
    private String name;
    private String address;
    private float latitude;
    private float longitude;
    private String phoneNumber;
    private List<DayTimetable> daysTimetable;
    private NotifConfig notifConfig;
    private List<Partner> partners;
    private List<Formation> formations;
}
