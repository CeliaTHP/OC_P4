package com.example.mareu.di;

import com.example.mareu.service.MeetingService.DummyMeetingsApi;
import com.example.mareu.service.RoomService.DummyRoomsApi;
import com.example.mareu.service.MeetingService.MeetingsApi;
import com.example.mareu.service.RoomService.RoomsApi;
import com.example.mareu.service.UserService.DummyUsersApi;
import com.example.mareu.service.UserService.UsersApi;


/**
 * Dependency injector to get instance of services
 */
public class DI {

    private static final MeetingsApi meetingsApi = new DummyMeetingsApi();

    private static final RoomsApi roomsApi = new DummyRoomsApi();

    private static final UsersApi usersApi = new DummyUsersApi();

    /**
     * Get an instance on @{@link MeetingsApi}
     *
     * @return
     */
    public static MeetingsApi getMeetingApi() {
        return meetingsApi;
    }

    /**
     * Get an instance on @{@link RoomsApi}
     *
     * @return
     */
    public static RoomsApi getRoomApi() {
        return roomsApi;
    }

    /**
     * Get an instance on @{@link UsersApi}
     *
     * @return
     */
    public static UsersApi getUsersApi() {
        return usersApi;
    }



}
