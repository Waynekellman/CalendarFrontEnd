package com.nyc.calendarfrontend.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nyc.calendarfrontend.models.EventModel;
import com.nyc.calendarfrontend.R;

import java.util.ArrayList;

public class EventsActivity extends AppCompatActivity {

    private static final String TAG = EventsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        ArrayList<EventModel> eventModels = bundle != null ? bundle.getParcelableArrayList(CalendarAdapter.EVENTS_BUNDLE) : new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.events_recyclerview);
        EventsAdapter adapter = new EventsAdapter();
        adapter.setData(eventModels);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
    }
}
