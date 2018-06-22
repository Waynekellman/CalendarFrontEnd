package com.nyc.calendarfrontend;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Wayne Kellman on 6/21/18.
 */

public class CalendarViewHolder extends RecyclerView.ViewHolder {

    private TextView day;
    public CalendarViewHolder(View itemView) {
        super(itemView);
        day = itemView.findViewById(R.id.day);
    }
    public void onBind(int dayNum){
        day.setText(String.valueOf(dayNum));
    }
}
