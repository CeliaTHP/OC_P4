package com.example.mareu.di;

import com.example.mareu.model.Meeting;
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

    /**
     * Get always a new instance on @{@link Meeting}. Useful for tests, so we ensure the context is clean.
     * @return
     */
    public static MeetingsApi getNewInstanceApiService() {
        return new DummyMeetingsApi();
    }
}
