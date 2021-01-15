package com.example.mareu.service.MeetingService;

import com.example.mareu.model.Meeting;
import com.example.mareu.model.Room;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public interface MeetingsApi {

    List<Meeting> getMeetings();

    List<Meeting> getMeetingsByRoom(Room room);

    List<Meeting> getMeetingsByDate(String date);

    void addMeeting(Meeting meeting);

    void deleteMeeting(Meeting meeting);

}
