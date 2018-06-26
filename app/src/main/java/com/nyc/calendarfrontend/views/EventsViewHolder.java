package com.nyc.calendarfrontend.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nyc.calendarfrontend.models.EventModel;
import com.nyc.calendarfrontend.R;

/**
 * Created by Wayne Kellman on 6/26/18.
 */

public class EventsViewHolder extends RecyclerView.ViewHolder {
    TextView time, title, desriptions;

    public EventsViewHolder(View itemView) {
        super(itemView);
        time = itemView.findViewById(R.id.event_time);
        title = itemView.findViewById(R.id.event_title_item);
        desriptions = itemView.findViewById(R.id.event_description_item);
    }

    public void onBind(EventModel model) {
        title.setText(model.getTitle());
        desriptions.setText(model.getBody());
        time.setText(model.getTime());
    }
}
