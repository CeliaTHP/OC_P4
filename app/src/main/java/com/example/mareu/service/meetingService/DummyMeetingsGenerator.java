package com.example.mareu.service.meetingService;

import com.example.mareu.model.Meeting;
import com.example.mareu.utils.DisplayFormatter;

import java.util.ArrayList;

public class DummyMeetingsGenerator {

    private final static MeetingsApi meetingsApi = new DummyMeetingsApi();

    private final static ArrayList<Meeting> DUMMY_MEETINGS = new ArrayList<Meeting>(11) {
        {
            add(new Meeting("Reunion A", DisplayFormatter.formatStringToDate("19/03/2021"), DisplayFormatter.formatStringToTime("14:00"), DisplayFormatter.formatStringToTime("15:00"), meetingsApi.getRooms().get(1), meetingsApi.getUserEmails()));
            add(new Meeting("Reunion B", DisplayFormatter.formatStringToDate("14/02/2021"), DisplayFormatter.formatStringToTime("17:00"), DisplayFormatter.formatStringToTime("17:30"), meetingsApi.getRooms().get(4), meetingsApi.getUserEmails()));
            add(new Meeting("Reunion C", DisplayFormatter.formatStringToDate("02/11/2021"), DisplayFormatter.formatStringToTime("08:00"), DisplayFormatter.formatStringToTime("12:00"), meetingsApi.getRooms().get(8), meetingsApi.getUserEmails()));
            add(new Meeting("Reunion D", DisplayFormatter.formatStringToDate("20/05/2021"), DisplayFormatter.formatStringToTime("07:45"), DisplayFormatter.formatStringToTime("8:35"), meetingsApi.getRooms().get(3), meetingsApi.getUserEmails()));
            add(new Meeting("Reunion E", DisplayFormatter.formatStringToDate("29/12/2021"), DisplayFormatter.formatStringToTime("14:00"), DisplayFormatter.formatStringToTime("16:00"), meetingsApi.getRooms().get(7), meetingsApi.getUserEmails()));
            add(new Meeting("Reunion F", DisplayFormatter.formatStringToDate("12/04/2021"), DisplayFormatter.formatStringToTime("09:00"), DisplayFormatter.formatStringToTime("12:00"), meetingsApi.getRooms().get(0), meetingsApi.getUserEmails()));
            add(new Meeting("Reunion G", DisplayFormatter.formatStringToDate("08/06/2021"), DisplayFormatter.formatStringToTime("11:30"), DisplayFormatter.formatStringToTime("12:30"), meetingsApi.getRooms().get(6), meetingsApi.getUserEmails()));
            add(new Meeting("Reunion H", DisplayFormatter.formatStringToDate("30/04/2021"), DisplayFormatter.formatStringToTime("10:30"), DisplayFormatter.formatStringToTime("11:00"), meetingsApi.getRooms().get(2), meetingsApi.getUserEmails()));
            add(new Meeting("Reunion I", DisplayFormatter.formatStringToDate("09/04/2021"), DisplayFormatter.formatStringToTime("14:00"), DisplayFormatter.formatStringToTime("14:20"), meetingsApi.getRooms().get(5), meetingsApi.getUserEmails()));
            add(new Meeting("Reunion J", DisplayFormatter.formatStringToDate("12/07/2021"), DisplayFormatter.formatStringToTime("14:00"), DisplayFormatter.formatStringToTime("15:00"), meetingsApi.getRooms().get(9), meetingsApi.getUserEmails()));
            add(new Meeting("CursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursedCursed", DisplayFormatter.formatStringToDate("12/09/2021"), DisplayFormatter.formatStringToTime("14:00"), DisplayFormatter.formatStringToTime("20:00"), meetingsApi.getRooms().get(0), meetingsApi.getUserEmails()));
        }
    };

    public static ArrayList<Meeting> generateMeetings() {
        return DUMMY_MEETINGS;
    }

}

