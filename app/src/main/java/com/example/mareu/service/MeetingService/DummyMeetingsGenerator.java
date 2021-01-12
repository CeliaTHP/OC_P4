package com.example.mareu.service.MeetingService;

import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.Room;
import com.example.mareu.model.User;
import com.example.mareu.service.RoomService.DummyRoomsGenerator;
import com.example.mareu.service.RoomService.RoomsApi;
import com.example.mareu.service.UserService.DummyUsersApi;
import com.example.mareu.service.UserService.DummyUsersGenerator;
import com.example.mareu.service.UserService.UsersApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//    public Meeting(String title, String date, String time, String room, List<String> attendees) {
//add attendees with stringbuilder

public class DummyMeetingsGenerator {

    
    private final static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting("Reunion A", "12/04/2020", "14h00", DummyRoomsGenerator.generateRoom().get(1),DummyUsersGenerator.generateUserEmails()),
            new Meeting("Reunion B", "14/02/2020", "17h00", DummyRoomsGenerator.generateRoom().get(4),DummyUsersGenerator.generateUserEmails()),
            new Meeting("Reunion C", "28/02/2020", "08h00", DummyRoomsGenerator.generateRoom().get(8),DummyUsersGenerator.generateUserEmails()),
            new Meeting("Reunion D", "12/04/2020", "14h00", DummyRoomsGenerator.generateRoom().get(3),DummyUsersGenerator.generateUserEmails()),
            new Meeting("Reunion E", "12/04/2020", "14h00", DummyRoomsGenerator.generateRoom().get(7),DummyUsersGenerator.generateUserEmails()),
            new Meeting("Reunion F", "12/04/2020", "14h00", DummyRoomsGenerator.generateRoom().get(0),DummyUsersGenerator.generateUserEmails()),
            new Meeting("Reunion G", "08/06/2020", "11h30", DummyRoomsGenerator.generateRoom().get(6),DummyUsersGenerator.generateUserEmails()),
            new Meeting("Reunion H", "12/04/2020", "14h00", DummyRoomsGenerator.generateRoom().get(2),DummyUsersGenerator.generateUserEmails()),
            new Meeting("Reunion I", "12/04/2020", "14h00", DummyRoomsGenerator.generateRoom().get(5),DummyUsersGenerator.generateUserEmails()),
            new Meeting("Reunion J", "12/04/2020", "14h00", DummyRoomsGenerator.generateRoom().get(9),DummyUsersGenerator.generateUserEmails())
    );

    public static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }

}

