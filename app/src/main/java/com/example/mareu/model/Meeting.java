package com.example.mareu.model;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Meeting {

    private String title;

    private Date date;

    private Date startTime;

    private Room room;

    private Drawable pic;

    private List<String> attendees;

    public Meeting(String title, Date date, Date startTime, Room room, List<String> attendees) {
        this.title = title;
        this.date = date;
        this.startTime = startTime;
        this.room = room;
        this.attendees = attendees;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Drawable getPic() {
        return pic;
    }

    public void setPic(Drawable pic) {
        this.pic = pic;
    }

    public List<String> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<String> attendees) {
        this.attendees = attendees;
    }
}
