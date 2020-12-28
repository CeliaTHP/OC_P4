package com.example.mareu.di;

import com.example.mareu.service.MeetingService.DummyMeetingsApi;
import com.example.mareu.service.RoomService.DummyRoomsApi;
import com.example.mareu.service.MeetingService.MeetingsApi;
import com.example.mareu.service.RoomService.RoomsApi;


/**
 * Dependency injector to get instance of services
 */
public class DI {

    private static MeetingsApi meetingsApi = new DummyMeetingsApi();

    private static RoomsApi roomsApi = new DummyRoomsApi();

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

}
