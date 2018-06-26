package com.nyc.calendarfrontend.models;

/**
 * Created by Wayne Kellman on 6/26/18.
 */

public class EventPostedResponse {

    private String status;
    private String message;
    private EventModel data;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public EventModel getData() {
        return data;
    }
}
