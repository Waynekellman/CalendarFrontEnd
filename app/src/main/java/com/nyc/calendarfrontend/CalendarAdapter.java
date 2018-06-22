package com.nyc.calendarfrontend;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Map;

/**
 * Created by Wayne Kellman on 6/21/18.
 */

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {

    private Map<Integer, List<EventModel>>  eventModelList;

    public CalendarAdapter(Map<Integer, List<EventModel>> eventModelList) {
        this.eventModelList = eventModelList;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview,parent,false);
        return new CalendarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        holder.onBind(position + 1);

    }

    @Override
    public int getItemCount() {
        return 28;
    }
}
