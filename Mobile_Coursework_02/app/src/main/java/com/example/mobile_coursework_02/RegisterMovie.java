package com.example.mobile_coursework_02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterMovie extends AppCompatActivity {

    private EditText movieTitle,theYear,theDirector,listOfActors,ratings,reviews;
    private String titleOfTheMovie,movieYear,movieDirector,movieActors,movieRatings,movieReviews;
    private Context context = this;//"this" keyword used to assign Context of the current class.
    private DbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_movie);
        movieTitle = findViewById(R.id.editTxtMovieTitle);
        theYear = findViewById(R.id.editTxtMovieYear);
        theDirector = findViewById(R.id.editTxtMovieDirector);
        listOfActors = findViewById(R.id.editTxtMovieActors);
        ratings = findViewById(R.id.editTxtMovieRatings);
        reviews = findViewById(R.id.editTxtMovieReviews);
        dbHandler = new DbHandler(context);//need to pass Context object to DbHandler class
    }

    public void saveToDb(View view) {
        titleOfTheMovie = movieTitle.getText().toString();
        movieYear = theYear.getText().toString();
        movieDirector = theDirector.getText().toString();
        movieActors = listOfActors.getText().toString();
        movieRatings = ratings.getText().toString();
        movieReviews = reviews.getText().toString();
        if(titleOfTheMovie.equalsIgnoreCase("")){
            Toast toast = Toast.makeText(this, "Please fill title of the movie", Toast.LENGTH_SHORT);
            toast.show();
        }else if (movieYear.equalsIgnoreCase("")){
            Toast toast = Toast.makeText(this, "Please fill year of the movie", Toast.LENGTH_SHORT);
            toast.show();
        }else if (movieDirector.equalsIgnoreCase("")){
            Toast toast = Toast.makeText(this, "Please fill director of the movie", Toast.LENGTH_SHORT);
            toast.show();
        }else if (movieActors.equalsIgnoreCase("")){
            Toast toast = Toast.makeText(this, "Please fill actors/actress of the movie", Toast.LENGTH_SHORT);
            toast.show();
        }
        else if (movieRatings.equalsIgnoreCase("")){
            Toast toast = Toast.makeText(this, "Please fill ratings of the movie", Toast.LENGTH_SHORT);
            toast.show();
        }else if (movieReviews.equalsIgnoreCase("")){
            Toast toast = Toast.makeText(this, "Please fill your reviews of the movie", Toast.LENGTH_SHORT);
            toast.show();
        }else {
            Movies movies = new Movies(titleOfTheMovie, movieYear, movieDirector, movieActors, movieRatings, movieReviews);
            dbHandler.addMovieToDatabase(movies);

            Toast toast = Toast.makeText(this, "Successfully added your data to the database ! ", Toast.LENGTH_SHORT);
            toast.show();

            //set edit text value to empty strings
            movieTitle.setText("");
            theYear.setText("");
            theDirector.setText("");
            listOfActors.setText("");
            ratings.setText("");
            reviews.setText("");
        }
    }
}