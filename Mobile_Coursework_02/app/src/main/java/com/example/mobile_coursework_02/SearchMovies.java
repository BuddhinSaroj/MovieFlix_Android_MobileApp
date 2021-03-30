package com.example.mobile_coursework_02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

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

        List<Movies> movies = dbHandler.search(keyword);
        //ArrayList<String> searched = new ArrayList<>();
        //searched.add(movies);
        if (movies != null) {
            ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, movies);
            listView.setAdapter(listAdapter);
        }
    }

    public void search(View view) {
        String searchTxt = editText.getText().toString();
        search(searchTxt);
    }
}