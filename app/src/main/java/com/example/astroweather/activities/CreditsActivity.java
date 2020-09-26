package com.example.astroweather.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.astroweather.R;

public class CreditsActivity extends AppCompatActivity {

    private TextView credits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        credits = findViewById(R.id.credits);
        credits.setText("Author: Leonard Wojtczak\n");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}