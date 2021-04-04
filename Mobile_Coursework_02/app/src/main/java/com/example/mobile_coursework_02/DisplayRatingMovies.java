package com.example.mobile_coursework_02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class DisplayRatingMovies extends AppCompatActivity {

    DbHandler dbHandler = new DbHandler(this);
    ArrayList<String> arrayList =new ArrayList<>();
    private ListView listView;
    Cursor cursor;
    String moviesForIMDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_rating_movies);
        listView = findViewById(R.id.dispalyMovieListView);
        cursor = dbHandler.displayAllMovies();
        viewData();
    }

    public void viewData(){

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "NO DATA AVAILABLE", Toast.LENGTH_LONG).show();
            return;
        }
        while (cursor.moveToNext()) {
            arrayList.add(cursor.getString(1));//add data to array list
        }
        //------------------ sort arrayList -------------------------------
        Collections.sort(arrayList);
        //------------------ set adapter ----------------------------------
        ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, arrayList);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                moviesForIMDB = (String) parent.getItemAtPosition(position);
            }
        });
    }

    public void IMDBbtn(View view) {
        Intent intent = new Intent(DisplayRatingMovies.this,Ratings.class);
        intent.putExtra("movieForIMDB", moviesForIMDB);
        startActivity(intent);
    }
}