package com.example.mareu.service.meetingService;

import android.util.Log;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.Room;
import com.example.mareu.model.User;
import com.example.mareu.service.roomService.DummyRoomsGenerator;
import com.example.mareu.service.userService.DummyUsersGenerator;
import com.example.mareu.utils.DisplayFormatter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DummyMeetingsApi implements MeetingsApi {

    private final List<Meeting> meetingList = DummyMeetingsGenerator.generateMeetings();
    private final List<Room> roomList = DummyRoomsGenerator.generateRoom();
    private final List<User> users = DummyUsersGenerator.generateUsers();

    @Override
    public List<Meeting> getMeetings() {
        return meetingList;
    }

    @Override
    public List<Meeting> getMeetingsByRoom(Room room) {
        List<Meeting> filteredList = new ArrayList<>();
        for (Meeting meeting : meetingList) {
            if (meeting.getRoom() == room && !filteredList.contains(meeting))
                filteredList.add(meeting);
        }
        return filteredList;
    }

    @Override
    public List<Meeting> getMeetingsByDate(Date date) {
        List<Meeting> filteredList = new ArrayList<>();
        for (Meeting meeting : meetingList) {
            if (DisplayFormatter.formatDateToString(meeting.getDate()).equals(DisplayFormatter.formatDateToString(date)) && !filteredList.contains(meeting))
                filteredList.add(meeting);
        }
        return filteredList;
    }

    @Override
    public void addMeeting(Meeting meeting) {
        meetingList.add(meeting);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        meetingList.remove(meeting);
    }

    /**
     * Rooms
     */

    @Override
    public List<Room> getRooms() {
        return roomList;
    }

    /**
     * Users
     */

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public List<String> getUserEmails() {
        return DummyUsersGenerator.generateUserEmails();
    }

    @Override
    public boolean availableTiming(Meeting meeting) {
        boolean bool = true;
        for (Meeting meetingChecked : meetingList) {
            if (meeting.getDate() == meetingChecked.getDate() && meeting.getRoom() == meeting.getRoom()) {
                if (meeting.getStartTime().before(meetingChecked.getStartTime()) && meeting.getStartTime().after(meetingChecked.getEndTime())) {
                    bool = false;
                }
            }
        }
        Log.d("DummyAPI", bool + "");
        return bool;
    }
}
