package com.example.mareu.di;

import com.example.mareu.service.DummyMeetingsApi;
import com.example.mareu.service.MeetingsApi;


/**
 * Dependency injector to get instance of services
 */
public class DI {

    private static MeetingsApi service = new DummyMeetingsApi();

    /**
     * Get an instance on @{@link MeetingsApi}
     * @return
     */
    public static MeetingsApi getMeetingApi() {
        return service;
    }

}
