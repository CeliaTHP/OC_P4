package com.example.mareu.model;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.util.List;

public class Meeting {
    private String title;
    private String date;
    private String time;
    private Room room;
    private Drawable pic;
    private String attendees;

    public Meeting(String title, String date, String time, Room room, String attendees) {
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

    public String getAttendees() {
        return attendees;
    }

    public void setAttendees(String attendees) {
        this.attendees = attendees;
    }
}
