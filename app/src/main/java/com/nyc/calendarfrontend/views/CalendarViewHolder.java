package com.nyc.calendarfrontend.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nyc.calendarfrontend.models.EventModel;
import com.nyc.calendarfrontend.R;
import com.nyc.calendarfrontend.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by Wayne Kellman on 6/21/18.
 */

public class CalendarViewHolder extends RecyclerView.ViewHolder {

    private TextView day;
    private TextView event1, event2, event3, more_events;
    Button addEventButton;

    public CalendarViewHolder(View itemView) {
        super(itemView);
        day = itemView.findViewById(R.id.day);
        event1 = itemView.findViewById(R.id.event1);
        event2 = itemView.findViewById(R.id.event2);
        event3 = itemView.findViewById(R.id.event3);
        more_events = itemView.findViewById(R.id.more_events);
        addEventButton = itemView.findViewById(R.id.add_event);
    }

    public void onBind(int dayNum, Map<Integer, List<EventModel>> eventModelList) {
        day.setText(String.valueOf(dayNum));
        setEventsPerDayUi(dayNum, eventModelList);

    }

    private void setEventsPerDayUi(int dayNum, Map<Integer, List<EventModel>> eventModelList) {
        if (eventModelList.containsKey(dayNum)) {
            List<EventModel> events = eventModelList.get(dayNum);
            int NUMBER_OF_EVENTS = events.size();
            switch (NUMBER_OF_EVENTS) {
                case 0:
                    event1.setVisibility(View.GONE);
                    event2.setVisibility(View.GONE);
                    event3.setVisibility(View.GONE);
                    more_events.setVisibility(View.GONE);
                    break;
                case 1:
                    event1.setVisibility(View.VISIBLE);
                    event1.setText(StringUtils.shortenTitle(events.get(0).getTitle()));

                    event2.setVisibility(View.GONE);
                    event3.setVisibility(View.GONE);
                    more_events.setVisibility(View.GONE);
                    break;

                case 2:

                    event1.setVisibility(View.VISIBLE);
                    event2.setVisibility(View.VISIBLE);

                    event1.setText(StringUtils.shortenTitle(events.get(0).getTitle()));
                    event2.setText(StringUtils.shortenTitle(events.get(1).getTitle()));

                    event3.setVisibility(View.GONE);
                    more_events.setVisibility(View.GONE);
                    break;
                case 3:

                    event1.setVisibility(View.VISIBLE);
                    event2.setVisibility(View.VISIBLE);
                    event3.setVisibility(View.VISIBLE);
                    event1.setText(StringUtils.shortenTitle(events.get(0).getTitle()));
                    event2.setText(StringUtils.shortenTitle(events.get(1).getTitle()));
                    event3.setText(StringUtils.shortenTitle(events.get(2).getTitle()));

                    more_events.setVisibility(View.GONE);
                    break;
                default:

                    event1.setVisibility(View.VISIBLE);
                    event2.setVisibility(View.VISIBLE);
                    event3.setVisibility(View.VISIBLE);
                    event1.setText(StringUtils.shortenTitle(events.get(0).getTitle()));
                    event2.setText(StringUtils.shortenTitle(events.get(1).getTitle()));
                    event3.setText(StringUtils.shortenTitle(events.get(2).getTitle()));
                    more_events.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

}

