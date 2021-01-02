package com.example.mareu.service.MeetingService;

import com.example.mareu.model.Meeting;

import java.util.List;

public class DummyMeetingsApi implements MeetingsApi {

    private List<Meeting> meetingList = DummyMeetingsGenerator.generateMeetings();


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
    public String getMeetingAttendees(Meeting meeting) {
        return meeting.getAttendees();
    }




}
