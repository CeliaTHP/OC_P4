package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//    public Meeting(String title, String date, String time, String room, List<String> attendees) {
//add attendees with stringbuilder

public class DummyMeetingsGenerator {

    private final static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting("Reunion A", "12/04/2020", "14h00", "Peach", null),
            new Meeting("Reunion B", "14/02/2020", "17h00", "Mario", null),
            new Meeting("Reunion C", "28/02/2020", "08h00", "Luigi", null),
            new Meeting("Reunion D", "08/06/2020", "11h30", "Wario", null)
    );

    public static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }
}

