package com.example.mareu.service.MeetingService;

import com.example.mareu.model.Meeting;
import com.example.mareu.model.Room;
import com.example.mareu.service.RoomService.DummyRoomsGenerator;
import com.example.mareu.service.RoomService.RoomsApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//    public Meeting(String title, String date, String time, String room, List<String> attendees) {
//add attendees with stringbuilder

public class DummyMeetingsGenerator {


    private final static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting("Reunion A", "12/04/2020", "14h00", DummyRoomsGenerator.generateRoom().get(1),"maxime@lamzone.fr, alex@lamzone.fr, judy@lamzone.fr, panam@lamzone.fr, judy@lamzone.fr"),
            new Meeting("Reunion B", "14/02/2020", "17h00", DummyRoomsGenerator.generateRoom().get(4), "maxime@lamzone.fr, alex@lamzone.fr, judy@lamzone.fr, panam@lamzone.fr, judy@lamzone.fr"),
            new Meeting("Reunion C", "28/02/2020", "08h00", DummyRoomsGenerator.generateRoom().get(8), "maxime@lamzone.fr, alex@lamzone.fr, judy@lamzone.fr, panam@lamzone.fr, judy@lamzone.fr"),
            new Meeting("Reunion D", "12/04/2020", "14h00", DummyRoomsGenerator.generateRoom().get(3), "maxime@lamzone.fr, alex@lamzone.fr, judy@lamzone.fr, panam@lamzone.fr, judy@lamzone.fr"),
            new Meeting("Reunion E", "12/04/2020", "14h00", DummyRoomsGenerator.generateRoom().get(7),"maxime@lamzone.fr, alex@lamzone.fr, judy@lamzone.fr, panam@lamzone.fr, judy@lamzone.fr"),
            new Meeting("Reunion F", "12/04/2020", "14h00", DummyRoomsGenerator.generateRoom().get(0), "maxime@lamzone.fr, alex@lamzone.fr, judy@lamzone.fr, panam@lamzone.fr, judy@lamzone.fr"),
            new Meeting("Reunion G", "08/06/2020", "11h30", DummyRoomsGenerator.generateRoom().get(6), "maxime@lamzone.fr, alex@lamzone.fr, judy@lamzone.fr, panam@lamzone.fr, judy@lamzone.fr"),
            new Meeting("Reunion H", "12/04/2020", "14h00", DummyRoomsGenerator.generateRoom().get(2), "maxime@lamzone.fr, alex@lamzone.fr, judy@lamzone.fr, panam@lamzone.fr, judy@lamzone.fr"),
            new Meeting("Reunion I", "12/04/2020", "14h00", DummyRoomsGenerator.generateRoom().get(5), "maxime@lamzone.fr, alex@lamzone.fr, judy@lamzone.fr, panam@lamzone.fr, judy@lamzone.fr"),
            new Meeting("Reunion J", "12/04/2020", "14h00", DummyRoomsGenerator.generateRoom().get(9), "maxime@lamzone.fr, alex@lamzone.fr, judy@lamzone.fr, panam@lamzone.fr, judy@lamzone.fr")
    );

    public static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }

}

