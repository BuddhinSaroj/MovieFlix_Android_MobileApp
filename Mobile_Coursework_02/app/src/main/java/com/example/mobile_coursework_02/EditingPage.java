package com.example.mobile_coursework_02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

public class EditingPage extends AppCompatActivity {

    private EditText movieTitle,theYear,theDirector,listOfActors,reviews;
    private String titleOfMovie,movieYear,movieDirector,movieActors,movieRatings,movieReview;
    private int favOrNot;
    private RadioButton fav,notFav;
    private RatingBar ratingBar;
    DbHandler dbHandler = new DbHandler(this);
    private String movieName;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing_page);
        movieTitle = findViewById(R.id.editTxtMovieTitle);
        theYear = findViewById(R.id.editTxtMovieYear);
        theDirector = findViewById(R.id.editTxtMovieDirector);
        listOfActors = findViewById(R.id.editTxtMovieActors);
        ratingBar = findViewById(R.id.ratingBar);
        reviews = findViewById(R.id.editTxtMovieReviews);
        fav = findViewById(R.id.fav);
        notFav = findViewById(R.id.notFav);

        Intent intent = getIntent();
        movieName = intent.getStringExtra("movie");
        System.out.println(movieName);
        cursor = dbHandler.getSingleMovie(movieName);
        edit();
    }

    public void edit(){
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "NO DATA AVAILABLE", Toast.LENGTH_LONG).show();
            return;
        }
        while (cursor.moveToNext()) {
            movieTitle.setText(cursor.getString(0));
            theYear.setText(cursor.getString(1));
            theDirector.setText(cursor.getString(2));
            listOfActors.setText(cursor.getString(3));
            float rating = Float.parseFloat(cursor.getString(4));
            ratingBar.setRating(rating);
            reviews.setText(cursor.getString(5));
            if (cursor.getString(6).equalsIgnoreCase("1")){
                fav.setChecked(true);
            }else if (cursor.getString(6).equalsIgnoreCase("0")){
                notFav.setChecked(true);
            }
        }
    }

    public void updateDb(View view) {

        titleOfMovie = movieTitle.getText().toString();
        movieYear = theYear.getText().toString();
        movieDirector = theDirector.getText().toString();
        movieActors = listOfActors.getText().toString();
        movieRatings = String.valueOf(ratingBar.getRating());
        movieReview = reviews.getText().toString();
        if (fav.isChecked()){
            favOrNot = 1;
            System.out.println("Favourite Btn SELECTED......");
        }else {
            favOrNot = 0;
        }

        Movies movies = new Movies(titleOfMovie,movieYear,movieDirector,movieActors,movieRatings,movieReview,favOrNot);
        dbHandler.updateSingleMovie(movies,movieName);

        Toast toast;
        toast = Toast.makeText(this, "Updated", Toast.LENGTH_SHORT);
        toast.show();
    }
}