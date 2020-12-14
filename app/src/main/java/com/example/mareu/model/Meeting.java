package com.example.mareu.model;

import java.util.List;

public class Meeting {
    private String title;
    private String date;
    private String time;
    private String room;
    private String imageUrl; //custom image or generate //round image
    private List<String> attendees;

    public Meeting(String title, String date, String time, String room, List<String> attendees) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.room = room;
        this.attendees = attendees;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public List<String> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<String> attendees) {
        this.attendees = attendees;
    }
}
