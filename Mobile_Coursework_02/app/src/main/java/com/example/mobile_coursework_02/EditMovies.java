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

public class EditMovies extends AppCompatActivity {

    DbHandler dbHandler = new DbHandler(this);
    ArrayList<String> arrayList =new ArrayList<>();
    private ListView listView;
    Cursor cursor;
    String editMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movies);
        listView = findViewById(R.id.listView3);
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
                editMovie = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(EditMovies.this,EditingPage.class);
                intent.putExtra("movie", editMovie);
                startActivity(intent);
            }
        });
    }
}