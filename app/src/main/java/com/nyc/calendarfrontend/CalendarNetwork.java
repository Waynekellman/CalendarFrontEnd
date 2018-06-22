package com.nyc.calendarfrontend;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Wayne Kellman on 6/21/18.
 */

public interface CalendarNetwork {
    @GET("events/")
    Observable<EventResponse> getEvents();
}
