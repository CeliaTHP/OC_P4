package com.example.mareu.service.MeetingService;

import android.util.Log;

import com.example.mareu.model.Meeting;
import com.example.mareu.model.Room;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DummyMeetingsApi implements MeetingsApi {

    private List<Meeting> meetingList = DummyMeetingsGenerator.generateMeetings();


    @Override
    public List<Meeting> getMeetings() {
        return meetingList;
    }

    @Override
    public List<Meeting> getMeetingsByRoom(Room room) {
        List<Meeting> filteredList = new ArrayList<>();
        for (int i = 0; i < meetingList.size(); i++) {
            if (meetingList.get(i).getRoom() == room && !filteredList.contains(meetingList.get(i)))
                filteredList.add(meetingList.get(i));
        }
        return filteredList;
    }

    @Override
    public List<Meeting> getMeetingsByDate(String date) {
        List<Meeting> filteredList = new ArrayList<>();
        for (int i = 0; i < meetingList.size(); i++) {
            if (meetingList.get(i).getDate().equals(date) && !filteredList.contains(meetingList.get(i)))
                filteredList.add(meetingList.get(i));
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

    @Override
    public int getMeetingPosition(Meeting meeting) {
        return meetingList.indexOf(meeting);

    }

    @Override
    public List<String> getMeetingAttendees(Meeting meeting) {
        return meeting.getAttendees();
    }


}
