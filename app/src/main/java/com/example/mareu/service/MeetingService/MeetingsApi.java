package com.example.mareu.service.MeetingService;

import com.example.mareu.model.Meeting;
import com.example.mareu.model.Room;

import java.util.List;

public interface MeetingsApi {

    List<Meeting> getMeetings();

    String getMeetingAttendees(Meeting meeting);

    void addMeeting(Meeting meeting);

    void deleteMeeting(Meeting meeting);

}
