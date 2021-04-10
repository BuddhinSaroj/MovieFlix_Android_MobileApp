package com.example.mobile_coursework_02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterMovie extends AppCompatActivity {

    private EditText movieTitle,theYear,theDirector,listOfActors,ratings,reviews;
    private String titleOfTheMovie,movieYear,movieDirector,movieActors,movieRatings,movieReviews;
    private Context context = this;//"this" keyword used to assign Context of the current class.
    private DbHandler dbHandler;
    Cursor cursor,cursor2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_movie);
        movieTitle = findViewById(R.id.editTxtMovieTitle1);
        theYear = findViewById(R.id.editTxtMovieYear1);
        theDirector = findViewById(R.id.editTxtMovieDirector1);
        listOfActors = findViewById(R.id.editTxtMovieActors1);
        ratings = findViewById(R.id.editTxtMovieRatings1);
        reviews = findViewById(R.id.editTxtMovieReviews1);
        dbHandler = new DbHandler(context);//need to pass Context object to DbHandler class
        cursor = dbHandler.displayAllMovies();

        if (savedInstanceState != null) {
            // Restore the saved state.
            // See onSaveInstanceState() for what gets saved.
            titleOfTheMovie = savedInstanceState.getString("titleOfTheMovie");
            movieYear = savedInstanceState.getString("movieYear");
            movieDirector = savedInstanceState.getString("movieDirector");
            movieActors = savedInstanceState.getString("movieActors");
            movieRatings = savedInstanceState.getString("movieRatings");
            movieReviews = savedInstanceState.getString("movieReviews");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("titleOfTheMovie",titleOfTheMovie);
        outState.putString("movieYear",movieYear);
        outState.putString("movieDirector",movieDirector);
        outState.putString("movieActors",movieActors);
        outState.putString("movieRatings",movieRatings);
        outState.putString("movieReviews",movieReviews);

    }

    public void saveToDb(View view) {
        titleOfTheMovie = movieTitle.getText().toString();
        movieYear = theYear.getText().toString();
        movieDirector = theDirector.getText().toString();
        movieActors = listOfActors.getText().toString();
        movieRatings = ratings.getText().toString();
        movieReviews = reviews.getText().toString();

        cursor2 = dbHandler.userChecking(titleOfTheMovie);
        if (cursor2.getCount() > 0) {
            Toast.makeText(getApplicationContext(), "MOVIE ALREADY EXITS", Toast.LENGTH_LONG).show();
        } else {
            if (titleOfTheMovie.equalsIgnoreCase("")) {
                Toast toast = Toast.makeText(this, "Please fill title of the movie", Toast.LENGTH_SHORT);
                toast.show();
            } else if (movieYear.equalsIgnoreCase("") || Integer.parseInt(movieYear) < 1985) {
                Toast toast;
                if (movieYear.equalsIgnoreCase("")) {
                    toast = Toast.makeText(this, "Please fill year of the movie", Toast.LENGTH_SHORT);
                } else {
                    toast = Toast.makeText(this, "Please fill year over 1985", Toast.LENGTH_SHORT);
                }
                toast.show();
            } else if (movieDirector.equalsIgnoreCase("")) {
                Toast toast = Toast.makeText(this, "Please fill director of the movie", Toast.LENGTH_SHORT);
                toast.show();
            } else if (movieActors.equalsIgnoreCase("")) {
                Toast toast = Toast.makeText(this, "Please fill actors/actress of the movie", Toast.LENGTH_SHORT);
                toast.show();
            } else if (movieRatings.equalsIgnoreCase("") || Integer.parseInt(movieRatings) <= 0 || Integer.parseInt(movieRatings) > 10) {
                Toast toast;
                if (movieRatings.equalsIgnoreCase("")) {
                    toast = Toast.makeText(this, "Please fill ratings of the movie", Toast.LENGTH_SHORT);
                } else {
                    toast = Toast.makeText(this, "Enter ratings between 1-10", Toast.LENGTH_SHORT);
                }
                toast.show();

            } else if (movieReviews.equalsIgnoreCase("")) {
                Toast toast = Toast.makeText(this, "Please fill your reviews of the movie", Toast.LENGTH_SHORT);
                toast.show();
            } else {
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
}