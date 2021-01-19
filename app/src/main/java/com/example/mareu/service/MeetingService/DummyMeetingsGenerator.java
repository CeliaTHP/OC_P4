package com.example.mareu.service.MeetingService;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.mareu.model.Meeting;
import com.example.mareu.service.RoomService.DummyRoomsApi;
import com.example.mareu.service.RoomService.RoomsApi;
import com.example.mareu.service.UserService.DummyUsersApi;
import com.example.mareu.service.UserService.DummyUsersGenerator;
import com.example.mareu.service.UserService.UsersApi;
import com.example.mareu.utils.DisplayFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;


public class DummyMeetingsGenerator {

    public static RoomsApi roomsApi = new DummyRoomsApi();
    public static UsersApi usersApi = new DummyUsersApi();


    public final static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting("Reunion A", DisplayFormatter.formatStringToDate("19/03/2021"), DisplayFormatter.formatStringToTime("14:00"), roomsApi.getRooms().get(1), usersApi.getUserEmails()),
            new Meeting("Reunion B", DisplayFormatter.formatStringToDate("14/02/2021"), DisplayFormatter.formatStringToTime("17:00"), roomsApi.getRooms().get(4), usersApi.getUserEmails()),
            new Meeting("Reunion C", DisplayFormatter.formatStringToDate("02/11/2021"), DisplayFormatter.formatStringToTime("08:00"), roomsApi.getRooms().get(8), usersApi.getUserEmails()),
            new Meeting("Reunion D", DisplayFormatter.formatStringToDate("20/05/2021"), DisplayFormatter.formatStringToTime("07:45"), roomsApi.getRooms().get(3), usersApi.getUserEmails()),
            new Meeting("Reunion E", DisplayFormatter.formatStringToDate("29/12/2021"), DisplayFormatter.formatStringToTime("14:00"), roomsApi.getRooms().get(7), usersApi.getUserEmails()),
            new Meeting("Reunion F", DisplayFormatter.formatStringToDate("12/04/2021"), DisplayFormatter.formatStringToTime("09:00"), roomsApi.getRooms().get(0), usersApi.getUserEmails()),
            new Meeting("Reunion G", DisplayFormatter.formatStringToDate("08/06/2021"), DisplayFormatter.formatStringToTime("11:30"), roomsApi.getRooms().get(6), usersApi.getUserEmails()),
            new Meeting("Reunion H", DisplayFormatter.formatStringToDate("30/04/2021"), DisplayFormatter.formatStringToTime("10:30"), roomsApi.getRooms().get(2), usersApi.getUserEmails()),
            new Meeting("Reunion I", DisplayFormatter.formatStringToDate("09/04/2021"), DisplayFormatter.formatStringToTime("14:00"), roomsApi.getRooms().get(5), usersApi.getUserEmails()),
            new Meeting("Reunion J", DisplayFormatter.formatStringToDate("12/07/2021"), DisplayFormatter.formatStringToTime("14:00"), roomsApi.getRooms().get(9), usersApi.getUserEmails()),
            new Meeting("CursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursed", DisplayFormatter.formatStringToDate("12/09/2021"), DisplayFormatter.formatStringToTime("14:00"), roomsApi.getRooms().get(0), usersApi.getUserEmails())

    );

    public static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }




}

