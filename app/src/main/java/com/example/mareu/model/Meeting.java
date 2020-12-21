package com.example.mareu.model;

import android.widget.ImageView;

import java.util.List;

public class Meeting {
    private String title;
    private String time;
    private String room;
    private ImageView pic;
    private List<String> attendees;

    public Meeting(String title, String date, String time, String room, List<String> attendees) {
        this.title = title;
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

    public ImageView getPic() {
        return pic;
    }

    public void setPic(ImageView pic) {
        this.pic = pic;
    }

    public List<String> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<String> attendees) {
        this.attendees = attendees;
    }

}
