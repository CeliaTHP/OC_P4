package com.example.mareu;

import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.Room;
import com.example.mareu.service.MeetingService.DummyMeetingsGenerator;
import com.example.mareu.service.MeetingService.MeetingsApi;
import com.example.mareu.service.RoomService.RoomsApi;
import com.example.mareu.service.UserService.UsersApi;
import com.example.mareu.utils.DisplayFormatter;

import org.hamcrest.*;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit test on Meeting service
 */
@RunWith(JUnit4.class)
public class MeetingServiceTest {

    private MeetingsApi meetingService;
    private RoomsApi roomsService;
    private UsersApi userService;


    @Before
    public void setup() {
        meetingService = DI.getMeetingApi();
        roomsService = DI.getRoomApi();
        userService = DI.getUsersApi();
    }

    /**
     * Get the Meeting list
     */
    @Test
    public void getMeetingsWithSuccess() {
        List<Meeting> meetings = meetingService.getMeetings();
        List<Meeting> expectedMeetings = DummyMeetingsGenerator.DUMMY_MEETINGS;
        //assert that our list contains all meetings
        MatcherAssert.assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeetings.toArray()));
    }

    /**
     * Create a Meeting
     */
    @Test
    public void addMeetingWithSuccess() {
        Meeting meeting = new Meeting("TestMeeting", DisplayFormatter.formatStringToDate("21/12/21"), DisplayFormatter.formatStringToTime("08h00"), roomsService.getRooms().get(3), userService.getUserEmails());
        assertFalse(meetingService.getMeetings().contains(meeting));
        meetingService.addMeeting(meeting);
        //assert that our Meeting has been added to the Meeting list
        assertTrue(meetingService.getMeetings().contains(meeting));

    }

    /**
     * Delete a meeting
     */
    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meetingToDelete = meetingService.getMeetings().get(0);
        assertTrue(meetingService.getMeetings().contains(meetingToDelete));
        meetingService.deleteMeeting(meetingToDelete);
        //assert that our Meeting has been deleted from our list
        assertFalse(meetingService.getMeetings().contains(meetingToDelete));
    }

    /**
     * Get the Meeting list filtered by room
     */
    @Test
    public void getMeetingsByRoomWithSuccess() {
        Room roomToCheck = roomsService.getRooms().get(6);
        List<Meeting> filteredList = meetingService.getMeetingsByRoom(roomToCheck);
        //with each
        //use getRoom on map
        assertSame(filteredList.get(0).getRoom(), roomToCheck);
    }


    /**
     * Get the Meeting list filtered by date
     */
    @Test
    public void getMeetingsByDateWithSuccess() {
         String dateToCheck= "14/02/2021";
        List<Meeting> filteredList = meetingService.getMeetings();
        assertNotSame(filteredList.get(0).getDate(), dateToCheck);
        filteredList = meetingService.getMeetingsByDate(dateToCheck);
        //with each
        //TODO use getStartDate on map
        assertSame(filteredList.get(0).getDate(), dateToCheck);
    }


}