package com.nyc.calendarfrontend;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Wayne Kellman on 6/21/18.
 */

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {

    private Map<Integer, List<EventModel>> eventModelList = Collections.EMPTY_MAP;
    private CalendarComponent component;

    public void setData(Map<Integer, List<EventModel>> eventModelList) {
        this.eventModelList = eventModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview, parent, false);
        component = ((CalendarApplication) ((MainActivity) parent.getContext()).getApplication()).getComponent();
        return new CalendarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        holder.onBind(position + 1, eventModelList);
        holder.addEventButton.setOnClickListener(v -> alertEvent(holder, position));
        View.OnClickListener listener = v -> {
            switch (v.getId()) {
                case R.id.add_event:
                    alertEvent(holder, position);
                    break;
                    default:

            }
        };
        holder.itemView.setOnClickListener(v -> {

        });

    }

    private void alertEvent(@NonNull CalendarViewHolder holder, int position) {
        AlertDialog.Builder eventAlert = new AlertDialog.Builder(holder.itemView.getContext());
        View eventView = LayoutInflater
                .from(holder.itemView.getContext())
                .inflate(R.layout.event_alert, null);

        EditText title = eventView.findViewById(R.id.event_title);
        EditText description = eventView.findViewById(R.id.event_description);
        TimePicker startPicker = eventView.findViewById(R.id.start_time_picker);
        TimePicker endPicker = eventView.findViewById(R.id.end_time_picker);
        CalendarNetwork service = component.service();
        Button submit = eventView.findViewById(R.id.submit_button);

        submit.setOnClickListener(view -> {

            String newEventTitle = title.getText().toString();
            String newEventDescription = description.getText().toString();
            String newEventMonth = "";
            int eventDay = position + 1;
            String startTime = startPicker.getHour() + ":" + startPicker.getMinute();
            String endTime = endPicker.getHour() + ":" + endPicker.getMinute();
            if (!(newEventTitle.isEmpty() || newEventDescription.isEmpty() || startTime.compareTo(endTime) > 0)) {

                EventModel model = new EventModel(newEventTitle, newEventDescription, newEventMonth, eventDay, startTime + endTime);
                service.postEvent(model);
            } else {
                if (newEventTitle.isEmpty() || newEventDescription.isEmpty()) {
                    Toast.makeText(holder.itemView.getContext(), "Please fill in everything", Toast.LENGTH_LONG).show();
                }
                if (startTime.compareTo(endTime) > 0) {
                    Toast.makeText(holder.itemView.getContext(), "Please correct start and end times", Toast.LENGTH_LONG).show();
                }
            }
        });
        eventAlert.setView(eventView);
        AlertDialog alertDialog = eventAlert.create();
        alertDialog.show();
    }

    @Override
    public int getItemCount() {
        return 28;
    }
}
