package com.example.mareu.service.meetingService;

import com.example.mareu.model.Meeting;
import com.example.mareu.model.Room;
import com.example.mareu.model.User;

import java.util.Date;
import java.util.List;

public interface MeetingsApi {

    /**
     * Meetings
     */

    List<Meeting> getMeetings();

    List<Meeting> getMeetingsByRoom(Room room);

    List<Meeting> getMeetingsByDate(Date date);

    void addMeeting(Meeting meeting);

    void deleteMeeting(Meeting meeting);

    /**
     * Users
     */

    List<User> getUsers();

    List<String> getUserEmails();

    /**
     * Rooms
     */

    List<Room> getRooms();


}
