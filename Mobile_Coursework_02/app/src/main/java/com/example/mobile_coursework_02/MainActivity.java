package com.example.mobile_coursework_02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void registerMovie(View view) {
        intent = new Intent(MainActivity.this,RegisterMovie.class);
        startActivity(intent);
    }

    public void displayMovies(View view) {
        intent = new Intent(MainActivity.this,DisplayMovies.class);
        startActivity(intent);
    }
}