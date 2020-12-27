package com.example.mareu.service;

import android.app.ListActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.example.mareu.databinding.ActivityMeetingListBinding;
import com.example.mareu.model.Meeting;

import java.util.ArrayList;
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
    public List<String> getMeetingAttendees(Meeting meeting) {
        return meeting.getAttendees();
    }


}
