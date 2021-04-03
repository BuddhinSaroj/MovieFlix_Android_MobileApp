package com.example.mobile_coursework_02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
    private String baseUrl = "https://imdb-api.com/en/API/SearchTitle/k_edbkosuq/Inception 2010";
    String id;
    String title;
    String description;
    private TextView textView;
    ArrayList<String> arrayListMovie = new ArrayList<>();
    ArrayList<String> arrayList = new ArrayList<>();
    int count;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings);
        textView = findViewById(R.id.ratingTxt);
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
            Log.d(LOG_TAG, "internet connected ");
            return true;
        } else {
            Log.d(LOG_TAG, "internet not connected ");
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
            baseUrl = "https://imdb-api.com/en/API/SearchTitle/k_edbkosuq/"+selectedMovie;
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
                //Log.d(LOG_TAG,"the result is: "+value);

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
                    count++;
                    String movieData = "The movie title : "+title + " "+ description;
                    arrayListMovie.add(i,movieData);
                    System.out.println("The movie id : "+id);
                    System.out.println("The movie title : "+title);
                    System.out.println("The movie description : "+description);
                    new getRatingsFromImDb().execute(id);
//                    Log.d(LOG_TAG,"The movie id : "+id);
//                    Log.d(LOG_TAG,"The movie title : "+title);
//                    Log.d(LOG_TAG,"The movie description : "+description);
                }

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

        private String secondUrl = "https://imdb-api.com/en/API/Ratings/k_edbkosuq/"+id;

        @Override
        protected String doInBackground(String... strings) {
            try {
                Log.d(LOG_TAG,"Inside second doInBackground()");

                URL url1 = new URL(secondUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url1.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

                String value2 = bufferedReader.readLine();
                //Log.d(LOG_TAG,"the result is: "+value2);

                httpURLConnection.disconnect();
                bufferedReader.close();

                return value2;

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
                JSONObject movieResults = (JSONObject) new JSONObject(s);

                String imDbResult = movieResults.getString("imDb");
//                Log.d(LOG_TAG,"imDb : "+imDbResult);
                arrayList.add(imDbResult);

//                String imDbResult = movieResults.getString("imDb");
//                Log.d(LOG_TAG,"imDb : "+imDbResult);
//                Log.d(LOG_TAG,"The movie id : "+id);
//                Log.d(LOG_TAG,"The movie title : "+title);
//                Log.d(LOG_TAG,"imDb : "+imDbResult);
//                Log.d(LOG_TAG,"The movie description : "+description);
                //String finalData = "The movie title : "+title + " "+ description + " imDb : "+imDbResult+"\n";

//                arrayList.add(imDbResult);

                //textView.setText("The movie title : "+title + " "+ description + " imDb : "+imDbResult);


            } catch (JSONException e) {
                e.printStackTrace();
            }
            //textView.setText(arrayList.toString());
            for (int x = 0 ; x<arrayList.size() ; x++){
                textView.setText("Movie " + x + ": "+ arrayList.get(x).toString());
                //Log.d(LOG_TAG,"Movie : " + x + ": "+ arrayList.get(x));
                System.out.println("Movie : " + x + ": "+ arrayList.get(x));
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }
    }
}