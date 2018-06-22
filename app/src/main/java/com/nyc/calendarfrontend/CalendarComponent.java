package com.nyc.calendarfrontend;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by Wayne Kellman on 6/21/18.
 */
@Singleton
@Component (modules = CalendarNetworkModule.class)
public interface CalendarComponent {
    CalendarNetwork service();
}
