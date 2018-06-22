package com.nyc.calendarfrontend;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 7);
        CalendarComponent component = DaggerCalendarComponent.create();
        CalendarNetwork service = component.service();
        service.getEvents()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(response -> {
                    CalendarAdapter adapter = new CalendarAdapter(mapEvents(response.getData()));
                    recyclerView.setAdapter(adapter);

                }, Throwable::printStackTrace);
        recyclerView.setLayoutManager(gridLayoutManager);

    }
    private Map<Integer, List<EventModel>> mapEvents(List<EventModel> events){
        Map<Integer, List<EventModel>> mapOfEventAndDays = new HashMap<>();
        for (EventModel e : events) {
            if (mapOfEventAndDays.containsKey(e.getDay())) {
                List<EventModel> eventList = mapOfEventAndDays.get(e.getDay());
                eventList.add(e);
                mapOfEventAndDays.put(e.getDay(), eventList);
            } else {
                List<EventModel> eventList = new ArrayList<>();
                eventList.add(e);
                mapOfEventAndDays.put(e.getDay(),eventList);
            }
        }
        return mapOfEventAndDays;

    }
}
