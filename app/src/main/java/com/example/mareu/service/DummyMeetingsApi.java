package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

public class DummyMeetingsApi implements MeetingsApi {

    private List<Meeting> meetingList = new ArrayList<>();

    @Override
    public List<Meeting> getMeetings() {
        return meetingList;
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
    public List<String> getMeetingAttendees(Meeting meeting) {
        return meeting.getAttendees();
    }


}
