package com.nyc.calendarfrontend;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Wayne Kellman on 6/21/18.
 */

public interface CalendarNetwork {
    @GET("events")
    Observable<EventResponse> getEvents();

    @POST("events")
    void postEvent(@Body EventModel eventModel);
}
