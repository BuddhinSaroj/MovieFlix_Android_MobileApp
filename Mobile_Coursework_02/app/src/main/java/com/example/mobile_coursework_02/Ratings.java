package com.example.mobile_coursework_02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Ratings extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    String selectedMovie;
    private String baseUrl = "https://imdb-api.com/en/API/SearchTitle/k_pnsw08gu/Inception 2010";
    String id;
    String title;
    String description;
    String imageUrls;
    ArrayList<String> movieList = new ArrayList<>();
    ArrayList<String> imDbArrayList = new ArrayList<>();
    ArrayList<String> moviesId = new ArrayList<>();
    ArrayList<String> imageUrlArrayList = new ArrayList<>();
    ArrayList<String> arrayListFinalResults = new ArrayList<>();
    ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings);
        //textView = findViewById(R.id.ratingTxt);
        listView = findViewById(R.id.listViewForImdB);
        Intent intent = getIntent();
        selectedMovie = intent.getStringExtra("movieForIMDB");
        System.out.println("Before onCreate()");
        getMoviesData();
        System.out.println("After onCreate()");
    }
    private boolean isInternetConnected(){
        ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Log.d(LOG_TAG, "Internet connected ");
            return true;
        } else {
            Log.d(LOG_TAG, "Internet not connected ");
            return false;
        }
    }
    public void getMoviesData(){
        if (!isInternetConnected()){
            Toast toast = Toast.makeText(this, "Internet not connected", Toast.LENGTH_LONG);
            toast.show();
        }else
        {
            System.out.println("In getMoviesData()");
            baseUrl = "https://imdb-api.com/en/API/SearchTitle/k_pnsw08gu/"+selectedMovie;
            new getData().execute();
        }
    }

    public class getData extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {
            try {
                Log.d(LOG_TAG,"Inside doInBackground()");

                URL url1 = new URL(baseUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url1.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

                String value = bufferedReader.readLine();

                httpURLConnection.disconnect();
                bufferedReader.close();

                return value;

            } catch (Exception e) {
                Log.d(LOG_TAG,"Error in do in background");
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject movieResults = (JSONObject) new JSONObject(s);
                JSONArray resultsArr = (JSONArray) movieResults.get("results");

                for (int i = 0 ; i<resultsArr.length(); i++) {
                    JSONObject resultsObj = resultsArr.getJSONObject(i);
                    id = (String) resultsObj.get("id");
                    title = (String) resultsObj.get("title");
                    description = (String) resultsObj.get("description");
                    imageUrls = (String) resultsObj.get("image");
                    String movieData = "Movie title: "+title + " "+ description;
                    movieList.add(i,movieData);
                    moviesId.add(i,id);
                    imageUrlArrayList.add(i,imageUrls);

                }
                System.out.println(moviesId);
                new getRatingsFromImDb().execute();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }
    }

    public class getRatingsFromImDb extends AsyncTask<String,String,String>{

        private String secondUrl = "https://imdb-api.com/en/API/UserRatings/k_pnsw08gu/"+id;

        @Override
        protected String doInBackground(String... strings) {
            try {
                Log.d(LOG_TAG,"Inside second doInBackground()");
                HttpURLConnection httpURLConnection = null;
                BufferedReader bufferedReader = null;
                String value2 = null;
                for (int i = 0; i< moviesId.size() ; i++) {
                    id = moviesId.get(i);
                    System.out.println(id);
                    System.out.println(moviesId.size());
                    secondUrl = "https://imdb-api.com/en/API/UserRatings/k_pnsw08gu/" + id;
                    URL url1 = new URL(secondUrl);
                    httpURLConnection = (HttpURLConnection) url1.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.connect();
                    bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    value2 = bufferedReader.readLine();
                    httpURLConnection.disconnect();
                    bufferedReader.close();
                    JSONObject movieResults = (JSONObject) new JSONObject(value2);
                    String imDbResult = movieResults.getString("totalRating");
                    imDbArrayList.add(i,imDbResult);
                }return value2;
            }catch (Exception e) {
                Log.d(LOG_TAG,"Error in do in background");
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                System.out.println(imDbArrayList);
                for (int x = 0; x< moviesId.size(); x++){
                    arrayListFinalResults.add(x, movieList.get(x)+"\nImDb Rating : "+ imDbArrayList.get(x));
                }

                arrayAd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }
    }
    public void arrayAd(){
        ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayListFinalResults);

        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(position);
                String url = imageUrlArrayList.get(position);
                System.out.println(url);
                Intent intent = new Intent(Ratings.this,RatingImageView.class);
                intent.putExtra("urlForImg", url);
                startActivity(intent);
            }
        });
    }
}