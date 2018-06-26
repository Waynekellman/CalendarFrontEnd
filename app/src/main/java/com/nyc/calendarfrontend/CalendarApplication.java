package com.nyc.calendarfrontend;

import android.app.Application;

/**
 * Created by Wayne Kellman on 6/26/18.
 */

public class CalendarApplication extends Application{
   private CalendarComponent component;

    public CalendarComponent getComponent() {
        if (component == null) {
            component = DaggerCalendarComponent.create();
        }
        return component;
    }
}
