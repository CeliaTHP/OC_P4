package com.example.mareu.service.MeetingService;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.mareu.model.Meeting;
import com.example.mareu.service.RoomService.DummyRoomsApi;
import com.example.mareu.service.RoomService.RoomsApi;
import com.example.mareu.service.UserService.DummyUsersGenerator;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;


public class DummyMeetingsGenerator {

    public static RoomsApi roomsApi = new DummyRoomsApi();

    private final static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting("Reunion A", "19/03/2021", "14h00", roomsApi.getRooms().get(1), DummyUsersGenerator.generateUserEmails()),
            new Meeting("Reunion B", "14/02/2021", "17h00", roomsApi.getRooms().get(4), DummyUsersGenerator.generateUserEmails()),
            new Meeting("Reunion C", "02/11/2021", "08h00", roomsApi.getRooms().get(8), DummyUsersGenerator.generateUserEmails()),
            new Meeting("Reunion D", "20/05/2021", "14h00", roomsApi.getRooms().get(3), DummyUsersGenerator.generateUserEmails()),
            new Meeting("Reunion E", "29/12/2021", "14h00", roomsApi.getRooms().get(7), DummyUsersGenerator.generateUserEmails()),
            new Meeting("Reunion F", "12/04/2021", "14h00", roomsApi.getRooms().get(0), DummyUsersGenerator.generateUserEmails()),
            new Meeting("Reunion G", "08/06/2021", "11h30", roomsApi.getRooms().get(6), DummyUsersGenerator.generateUserEmails()),
            new Meeting("Reunion H", "30/04/2021", "14h00", roomsApi.getRooms().get(2), DummyUsersGenerator.generateUserEmails()),
            new Meeting("Reunion I", "09/04/2021", "14h00", roomsApi.getRooms().get(5), DummyUsersGenerator.generateUserEmails()),
            new Meeting("Reunion J", "12/07/2021", "14h00", roomsApi.getRooms().get(9), DummyUsersGenerator.generateUserEmails()),
            new Meeting("CursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursed", "12/09/2021", "14h00", roomsApi.getRooms().get(9), DummyUsersGenerator.generateUserEmails())

    );

    public static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }


}

