package com.nyc.calendarfrontend;

/**
 * Created by Wayne Kellman on 6/25/18.
 */

public class StringUtils {
    public static String shortenTitle(String title) {
        if (title.length() > 6){
            return title.substring(6) + "...";
        }
        return title;
    }
}
