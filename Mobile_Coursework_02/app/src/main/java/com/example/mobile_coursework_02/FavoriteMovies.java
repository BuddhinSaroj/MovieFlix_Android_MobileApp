package com.example.mobile_coursework_02;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class FavoriteMovies extends AppCompatActivity {

    DbHandler dbHandler = new DbHandler(this);
    private ListView listView;
    ArrayList<String> arrayList =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_movies);
        listView = findViewById(R.id.listView);
        viewFavouritesData();
    }

    public void viewFavouritesData(){
        Cursor cursor = dbHandler.viewFavourites();

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
        ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, arrayList);
        listView.setAdapter(listAdapter);
    }
}