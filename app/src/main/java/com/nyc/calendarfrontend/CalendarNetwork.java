package com.nyc.calendarfrontend;

import com.nyc.calendarfrontend.models.EventModel;
import com.nyc.calendarfrontend.models.EventPostedResponse;
import com.nyc.calendarfrontend.models.EventResponse;

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
    Observable<EventPostedResponse> postEvent(@Body EventModel eventModel);
}
