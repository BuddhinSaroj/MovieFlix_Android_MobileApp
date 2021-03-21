package com.example.mobile_coursework_02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class EditingPage extends AppCompatActivity {

    private EditText movieTitle,theYear,theDirector,listOfActors,ratings,reviews;
    private String titleOfTheMovie,movieYear,movieDirector,movieActors,movieRatings,movieReviews;
    DbHandler dbHandler = new DbHandler(this);
    private String  moviePos;
    Cursor cursor;
    ArrayList<String> arrayList =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing_page);
        movieTitle = findViewById(R.id.editTxtMovieTitle);
        theYear = findViewById(R.id.editTxtMovieYear);
        theDirector = findViewById(R.id.editTxtMovieDirector);
        listOfActors = findViewById(R.id.editTxtMovieActors);
        ratings = findViewById(R.id.editTxtMovieRatings);
        reviews = findViewById(R.id.editTxtMovieReviews);

        Intent intent = getIntent();
        moviePos = intent.getStringExtra("editMovie");
        edit();
    }

    public void edit(){
        dbHandler.getSingleMovie(moviePos);

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "NO DATA AVAILABLE", Toast.LENGTH_LONG).show();
            return;
        }
        while (cursor.moveToNext()) {
            arrayList.add(cursor.getString(1));
            arrayList.add(cursor.getString(2));
        }
        System.out.println(arrayList.toString());
    }

    public void updateDb(View view) {
    }
}