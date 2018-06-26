package com.nyc.calendarfrontend;

/**
 * Created by Wayne Kellman on 6/21/18.
 */

class EventModel {

    private String title;
    private String body;
    private String month;
    private int day;
    private String time;

    public EventModel(String title, String body, String month, int day, String time) {
        this.title = title;
        this.body = body;
        this.month = month;
        this.day = day;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public String getStartTime(){
        String[] times = getTime().split("-");
        if (times.length > 0) {
            return times[0];
        }
        return "no start time";
    }

    public String getEndTime(){
        String[] times = getTime().split("-");
        if (times.length == 2) {
            return times[1];
        }
        return "no end time";
    }
}
