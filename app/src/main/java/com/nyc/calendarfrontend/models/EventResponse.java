package com.nyc.calendarfrontend.models;

import com.nyc.calendarfrontend.models.EventModel;

import java.util.List;

/**
 * Created by Wayne Kellman on 6/21/18.
 */

public class EventResponse {

    private String status;
    private String message;
    private List<EventModel> data;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<EventModel> getData() {
        return data;
    }
}
