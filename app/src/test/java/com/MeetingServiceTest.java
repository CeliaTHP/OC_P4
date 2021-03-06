package com;

import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.Room;
import com.example.mareu.model.User;
import com.example.mareu.service.meetingService.DummyMeetingsGenerator;
import com.example.mareu.service.meetingService.MeetingsApi;
import com.example.mareu.service.roomService.DummyRoomsGenerator;
import com.example.mareu.service.userService.DummyUsersGenerator;
import com.example.mareu.utils.DisplayFormatter;

import org.hamcrest.*;
import org.hamcrest.beans.HasPropertyWithValue;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.hamcrest.core.Every;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit test on Meeting service
 */
@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class MeetingServiceTest {

    private MeetingsApi meetingService;

    @Before
    public void setup() {
        meetingService = DI.getMeetingApi();
    }

    /**
     * Get the Meeting list
     */
    @Test
    public void A_getMeetingsWithSuccess() {
        List<Meeting> meetings = meetingService.getMeetings();
        List<Meeting> expectedMeetings = DummyMeetingsGenerator.generateMeetings();
        //assert that our list contains all meetings
        MatcherAssert.assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeetings.toArray()));
    }

    /**
     * Create a Meeting
     */
    @Test
    public void B_addMeetingWithSuccess() {
        Meeting meeting = new Meeting("TestMeeting", DisplayFormatter.formatStringToDate("21/12/21"), DisplayFormatter.formatStringToTime("08:00"), DisplayFormatter.formatStringToTime("10:00"), meetingService.getRooms().get(3), meetingService.getUserEmails());
        assertFalse(meetingService.getMeetings().contains(meeting));
        meetingService.addMeeting(meeting);
        //assert that our Meeting has been added to the Meeting list
        assertTrue(meetingService.getMeetings().contains(meeting));
    }

    /**
     * Delete a meeting
     */
    @Test
    public void C_deleteMeetingWithSuccess() {
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
    public void D_getMeetingsByRoomWithSuccess() {
        Room roomToCheck = meetingService.getRooms().get(0);
        List<Meeting> filteredList = meetingService.getMeetingsByRoom(roomToCheck);
        //assert that every Meeting in our filtered list has the selected room
        MatcherAssert.assertThat(filteredList, Every.everyItem(HasPropertyWithValue.hasProperty("room", Is.is(roomToCheck))));
    }

    /**
     * Get the Meeting list filtered by date
     */
    @Test
    public void E_getMeetingsByDateWithSuccess() {
        Date dateToCheck = DisplayFormatter.formatStringToDate("14/02/2021");
        List<Meeting> filteredList = meetingService.getMeetingsByDate(dateToCheck);
        //assert that every Meeting in our filtered list has the selected date
        MatcherAssert.assertThat(filteredList, Every.everyItem(HasPropertyWithValue.hasProperty("date", Is.is(dateToCheck))));
    }

    /**
     * Get the Room List
     */
    @Test
    public void F_getRoomsWithSuccess() {
        List<Room> rooms = meetingService.getRooms();
        List<Room> expectedRooms = DummyRoomsGenerator.generateRoom();
        //assert that our list contains all meetings
        MatcherAssert.assertThat(rooms, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedRooms.toArray()));
    }

    /**
     * Get the User List
     */
    @Test
    public void G_getUsersWithSuccess() {
        List<User> users = meetingService.getUsers();
        List<User> expectedUsers = DummyUsersGenerator.generateUsers();
        //assert that our list contains all meetings
        MatcherAssert.assertThat(users, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedUsers.toArray()));
    }

}