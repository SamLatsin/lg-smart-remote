package com.sensustech.lgremote.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.sensustech.lgremote.adapters.GesturesAdapter;
import com.sensustech.lgremote.R;

public class GesturesActivity extends AppCompatActivity {
    private GesturesAdapter adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private final String[] titles = {"1 finger", "Tap", "Double tap", "Long press", "Swipe up", "Swipe down", "Swipe left", "Swipe right", "2 fingers", "Tap (2 fingers)", "Double tap (2 fingers)", "Long press (2 fingers)", "Swipe up (2 fingers)", "Swipe down (2 fingers)", "Swipe left (2 fingers)", "Swipe right (2 fingers)"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestures);
        recyclerView = findViewById(R.id.rv_gestures);
        recyclerView.setHasFixedSize(true);
        adapter = new GesturesAdapter(this,titles);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    public void closeClick(View view) {
        finish();
    }

    public void backClick(View view) {
        finish();
    }
}