package com.example.mobile_coursework_02;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.SparseBooleanArray;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_movies);
        listView = findViewById(R.id.listView);
        cursor = dbHandler.displayAllMovies();
        viewAllData();
    }

    public void viewAllData(){
        //Cursor cursor = dbHandler.displayAllMovies();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "NO DATA AVAILABLE", Toast.LENGTH_LONG).show();
            return;
        }
        while (cursor.moveToNext()) {
            arrayList.add(cursor.getString(1));
        }
        //------------------ sort arrayList -------------------------------
            Collections.sort(arrayList);
        //------------------ set adapter ----------------------------------
            ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, arrayList);
            listView.setAdapter(listAdapter);
    }



    public void addFavourites(View view) {
        int x = 0;

        SparseBooleanArray checked = listView.getCheckedItemPositions();//from StackOverFlow

        for (int i = 0 ; i < listView.getCount() ; i++){
            if (checked.get(i)){
                x++;
                String movies = (String) listView.getItemAtPosition(i);//get checked movies positions from listView
                System.out.println(movies);
                dbHandler.addFavourites(movies);//movies variable passed to dbHandler class as a parameter.
            }
        }
        if (x == 0){
            Toast.makeText(this, "Please select a movie to add favourites", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "Added to favourites", Toast.LENGTH_LONG).show();
        }
    }
}