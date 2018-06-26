package com.nyc.calendarfrontend.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyc.calendarfrontend.models.EventModel;
import com.nyc.calendarfrontend.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by Wayne Kellman on 6/26/18.
 */

public class EventsAdapter extends RecyclerView.Adapter<EventsViewHolder>{
    private List<EventModel> events = Collections.EMPTY_LIST;

    public void setData(List<EventModel> events){
        this.events = events;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public EventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);
        return new EventsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsViewHolder holder, int position) {
        holder.onBind(events.get(position));
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
