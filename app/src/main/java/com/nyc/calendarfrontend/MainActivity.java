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
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    RecyclerView recyclerView;
    private CompositeDisposable compositeDisposable;
    private CalendarNetwork service;
    private CalendarComponent component;
    private CalendarAdapter calendarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        component = ((CalendarApplication) this.getApplication()).getComponent();
        recyclerView = findViewById(R.id.recyclerview);
        service = component.service();
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onResume() {
        super.onResume();

        calendarAdapter = new CalendarAdapter();
        compositeDisposable.add(
                service.getEvents()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                response -> calendarAdapter.setData(mapEvents(response.getData())),
                                Throwable::printStackTrace)
        );


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 7);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(calendarAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        compositeDisposable.dispose();
    }

    private Map<Integer, List<EventModel>> mapEvents(List<EventModel> events) {
        Map<Integer, List<EventModel>> mapOfEventAndDays = new HashMap<>();
        for (EventModel e : events) {
            if (mapOfEventAndDays.containsKey(e.getDay())) {
                List<EventModel> eventList = mapOfEventAndDays.get(e.getDay());
                eventList.add(e);
                mapOfEventAndDays.put(e.getDay(), eventList);
            } else {
                List<EventModel> eventList = new ArrayList<>();
                eventList.add(e);
                mapOfEventAndDays.put(e.getDay(), eventList);
            }
        }
        return mapOfEventAndDays;

    }
}
