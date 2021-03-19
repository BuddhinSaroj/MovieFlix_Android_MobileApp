package com.example.mobile_coursework_02;

import androidx.appcompat.app.AppCompatActivity;

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

public class DisplayMovies extends AppCompatActivity {

    DbHandler dbHandler = new DbHandler(this);
    private ListView listView;
    ArrayList<String> arrayList =new ArrayList<>();
    Cursor cursor;
    String favourites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_movies);
        listView = findViewById(R.id.listView);
        cursor = dbHandler.displayAllMovies();
        viewAllData();
    }

    public void viewAllData(){
        Cursor cursor = dbHandler.displayAllMovies();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "NO DATA AVAILABLE", Toast.LENGTH_LONG).show();
            return;
        }
        while (cursor.moveToNext()) {
            arrayList.add(cursor.getString(0));
        }
        //------------------ sort arrayList -------------------------------
            Collections.sort(arrayList);
        //------------------ set adapter ----------------------------------
            ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, arrayList);
            listView.setAdapter(listAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                favourites = (String) parent.getItemAtPosition(position);
                System.out.println(favourites);
                //text.setText(goingToEdit);
            }
        });
    }



    public void addFavourites(View view) {
    }
}