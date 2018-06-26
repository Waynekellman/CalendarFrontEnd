package com.nyc.calendarfrontend.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Wayne Kellman on 6/21/18.
 */

public class EventModel implements Parcelable {

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

    protected EventModel(Parcel in) {
        title = in.readString();
        body = in.readString();
        month = in.readString();
        day = in.readInt();
        time = in.readString();
    }

    public static final Creator<EventModel> CREATOR = new Creator<EventModel>() {
        @Override
        public EventModel createFromParcel(Parcel in) {
            return new EventModel(in);
        }

        @Override
        public EventModel[] newArray(int size) {
            return new EventModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(body);
        dest.writeString(month);
        dest.writeInt(day);
        dest.writeString(time);
    }
}
