package com.nyc.calendarfrontend.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.nyc.calendarfrontend.CalendarApplication;
import com.nyc.calendarfrontend.CalendarComponent;
import com.nyc.calendarfrontend.CalendarNetwork;
import com.nyc.calendarfrontend.models.EventModel;
import com.nyc.calendarfrontend.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Wayne Kellman on 6/21/18.
 */

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {

    private Map<Integer, List<EventModel>> eventModelList = Collections.EMPTY_MAP;
    private CalendarComponent component;
    public final static String EVENTS_BUNDLE = "events";
    public final static String TAG = CalendarAdapter.class.getSimpleName();
    private AlertDialog alertDialog;

    public void setData(Map<Integer, List<EventModel>> eventModelList) {
        this.eventModelList = eventModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.days_view, parent, false);
        component = ((CalendarApplication) ((MainActivity) parent.getContext()).getApplication()).getComponent();
        return new CalendarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        holder.onBind(position + 1, eventModelList);
        View.OnClickListener listener = v -> {
            switch (v.getId()) {
                case R.id.add_event:
                    alertEvent(holder, position);
                    break;
                    default:
                        Intent intent = new Intent(holder.itemView.getContext(), EventsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList(EVENTS_BUNDLE, (ArrayList<? extends Parcelable>) eventModelList.get(position + 1));
                        intent.putExtras(bundle);
                        holder.itemView.getContext().startActivity(intent);
            }
        };
        holder.addEventButton.setOnClickListener(listener);
        holder.itemView.setOnClickListener(listener);

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
            String newEventMonth = "Not Applicable";
            int eventDay = position + 1;
            String startTime = startPicker.getHour() + ":" + startPicker.getMinute();
            String endTime = endPicker.getHour() + ":" + endPicker.getMinute();
            int startAsInt = Integer.parseInt(startPicker.getHour() +""+ startPicker.getMinute());
            int endAsInt = Integer.parseInt(endPicker.getHour() + "" + endPicker.getMinute());
            if (!(newEventTitle.isEmpty() || newEventDescription.isEmpty() || (startAsInt > endAsInt))) {

                EventModel model = new EventModel(newEventTitle, newEventDescription, newEventMonth, eventDay, startTime + endTime);
                service.postEvent(model)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(response -> Log.d(TAG, "alertEvent: " + response.getMessage()),
                                Throwable::printStackTrace);

                ((MainActivity )holder.itemView.getContext()).recreate();
//                alertDialog.dismiss();
            } else {
                if (newEventTitle.isEmpty() || newEventDescription.isEmpty()) {
                    Toast.makeText(holder.itemView.getContext(), "Please fill in everything", Toast.LENGTH_LONG).show();
                }
                if (startAsInt > endAsInt) {
                    Toast.makeText(holder.itemView.getContext(), "Please correct start and end times", Toast.LENGTH_LONG).show();
                }

            }

        });
        eventAlert.setView(eventView);
        alertDialog = eventAlert.create();
        alertDialog.show();
    }

    @Override
    public int getItemCount() {
        return 28;
    }
}
