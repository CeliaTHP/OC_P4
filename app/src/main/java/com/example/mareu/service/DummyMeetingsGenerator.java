package com.example.mareu.service;

import com.example.mareu.model.Meeting;
import com.example.mareu.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//    public Meeting(String title, String date, String time, String room, List<String> attendees) {
//add attendees with stringbuilder

public class DummyMeetingsGenerator {

    private final static List<Room> DUMMY_ROOMS = RoomsGenerator.generateRoom();


    private final static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting("Reunion A", "12/04/2020", "14h00", DUMMY_ROOMS.get(1)),
            new Meeting("Reunion B", "14/02/2020", "17h00", DUMMY_ROOMS.get(4)),
            new Meeting("Reunion C", "28/02/2020", "08h00", DUMMY_ROOMS.get(8)),
            new Meeting("Reunion A", "12/04/2020", "14h00", DUMMY_ROOMS.get(3)),
            new Meeting("Reunion A", "12/04/2020", "14h00", DUMMY_ROOMS.get(7)),
            new Meeting("Reunion A", "12/04/2020", "14h00", DUMMY_ROOMS.get(0)),
            new Meeting("Reunion D", "08/06/2020", "11h30", DUMMY_ROOMS.get(6)),
            new Meeting("Reunion A", "12/04/2020", "14h00", DUMMY_ROOMS.get(2)),
            new Meeting("Reunion A", "12/04/2020", "14h00", DUMMY_ROOMS.get(5)),
            new Meeting("Reunion A", "12/04/2020", "14h00", DUMMY_ROOMS.get(9))
            );

    public static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }

}

