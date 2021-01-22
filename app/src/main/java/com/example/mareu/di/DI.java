package com.example.mareu.di;

import com.example.mareu.service.MeetingService.DummyMeetingsApi;
import com.example.mareu.service.MeetingService.MeetingsApi;


/**
 * Dependency injector to get instance of services
 */
public class DI {

    private static final MeetingsApi meetingsApi = new DummyMeetingsApi();

    /**
     * Get an instance on @{@link MeetingsApi}
     *
     * @return
     */
    public static MeetingsApi getMeetingApi() {
        return meetingsApi;
    }


}
