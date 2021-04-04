package com.example.mobile_coursework_02;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchMovies extends AppCompatActivity {

    private ListView listView;
    private EditText editText;
    DbHandler dbHandler = new DbHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movies);
        listView = findViewById(R.id.searchView);
        editText = findViewById(R.id.searchField);
    }

    private void search(String keyword) {

        ArrayList<Movies> moviesList = new ArrayList<>();
        Cursor cursor = dbHandler.search(keyword);

        try {
            if (cursor != null && cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        //from stackOverFlow
                        Movies movies = new Movies();
                        movies.setTitleOfTheMovie(cursor.getString(0));
                        movies.setTheDirector(cursor.getString(2));
                        movies.setListOfActors(cursor.getString(3));
                        moviesList.add(movies);
                    } while (cursor.moveToNext());
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            moviesList = null;
        }
        System.out.println(moviesList.toString());
        ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, moviesList);
        listView.setAdapter(listAdapter);
    }

    public void search(View view) {
        String searchTxt = editText.getText().toString();
        search(searchTxt);
    }
}