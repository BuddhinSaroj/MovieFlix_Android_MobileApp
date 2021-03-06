package com.example.mobile_coursework_02;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {

    private static final int VERSION =5; //version is used to update and recreate database tables
    private static final String DB_NAME = "Movie";
    private static final String TABLE_NAME = "Movie_table";

    //Column names
    private static final String ID = "id";
    public static final String MOVIE_TITLE = "Title";
    public static final String MOVIE_YEAR = "Year";
    public static final String MOVIE_DIRECTOR = "Director";
    public static final String MOVIE_ACTORS = "Actors";
    public static final String MOVIE_RATINGS = "Ratings";
    public static final String MOVIE_REVIEWS = "Reviews";
    public static final String FAVOURITES = "Favourites";

    private static String [] FROM = { /*ID ,*/ MOVIE_TITLE , MOVIE_YEAR , MOVIE_DIRECTOR , MOVIE_ACTORS,MOVIE_RATINGS,MOVIE_REVIEWS,FAVOURITES};

    public DbHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {//onCreate method used to create tables
        String SQL_CREATE_TABLES = "CREATE TABLE " + DbHandler.TABLE_NAME + " ("
                + DbHandler.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + DbHandler.MOVIE_TITLE + " TEXT ,"//PRIMARY KEY,"
                + DbHandler.MOVIE_YEAR + " TEXT,"
                + DbHandler.MOVIE_DIRECTOR + " TEXT,"
                + DbHandler.MOVIE_ACTORS + " TEXT,"
                + DbHandler.MOVIE_RATINGS + " TEXT,"
                + DbHandler.MOVIE_REVIEWS + " TEXT,"
                + DbHandler.FAVOURITES + " TEXT)";

        db.execSQL(SQL_CREATE_TABLES);//run the query and create the database

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {//if you done any changes of the current table and change the version ,this method used to drop older database you created.
        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS "+TABLE_NAME;
        //drop older table if existed
        db.execSQL(DROP_TABLE_QUERY);
        //Create tables again
        onCreate(db);
    }

    public void addMovieToDatabase(Movies movies){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();//structuring data and saved data in db
        contentValues.put(MOVIE_TITLE,movies.getTitleOfTheMovie());//key value pairs
        contentValues.put(MOVIE_YEAR,movies.getTheYear());
        contentValues.put(MOVIE_DIRECTOR,movies.getTheDirector());
        contentValues.put(MOVIE_ACTORS,movies.getListOfActors());
        contentValues.put(MOVIE_RATINGS,movies.getRatings());
        contentValues.put(MOVIE_REVIEWS,movies.getReview());
        contentValues.put(FAVOURITES,0);

        //save to table
        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        //sqLiteDatabase.close();
    }

    public Cursor userChecking(String username){
        SQLiteDatabase db = this.getReadableDatabase();

//        String selectQuery = "SELECT * FROM TABLE_NAME where MOVIE_TITLE= "+username  ;
//
//        Cursor c = db.rawQuery(selectQuery, null);

        String query = "select " + MOVIE_TITLE + " from " + TABLE_NAME + " where " + MOVIE_TITLE +" LIKE ?;";
        Cursor cursor = db.query(TABLE_NAME, FROM, MOVIE_TITLE + " LIKE ?", new String[]{"%"+username+"%"}, null, null, null);


        return  cursor;
    }

    public Cursor displayAllMovies(){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_NAME   ;

        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }

    public void addFavourites(String movie){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FAVOURITES,1);
        db.update(TABLE_NAME,values,MOVIE_TITLE+" =?",new String[]{movie});
    }

    public Cursor viewFavourites(){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_NAME  +" WHERE FAVOURITES = 1";

        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor;
    }

    public void removeFromFavourites(String movie){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FAVOURITES,0);
        db.update(TABLE_NAME,values,MOVIE_TITLE+" =?",new String[]{movie});
    }

    public Cursor getSingleMovie(String movie){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, FROM, MOVIE_TITLE+" =?", new String[]{movie}, null, null, null);

        return cursor;
    }

    public int updateSingleMovie(Movies movies,String movie){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        System.out.println(movie);
        ContentValues contentValues = new ContentValues();//structuring data and saved data in db
        contentValues.put(MOVIE_TITLE,movies.getTitleOfTheMovie());//key value pairs
        contentValues.put(MOVIE_YEAR,movies.getTheYear());
        contentValues.put(MOVIE_DIRECTOR,movies.getTheDirector());
        contentValues.put(MOVIE_ACTORS,movies.getListOfActors());
        contentValues.put(MOVIE_RATINGS,movies.getRatings());
        contentValues.put(MOVIE_REVIEWS,movies.getReview());
        contentValues.put(FAVOURITES,movies.getFavourites());

        int status = sqLiteDatabase.update(TABLE_NAME,contentValues,MOVIE_TITLE+" =?",new String[]{String.valueOf(movie)});

        return status;

    }

    public Cursor search(String keyword) {

            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            String query = "select " + MOVIE_TITLE + " from " + TABLE_NAME + " where " + MOVIE_TITLE + " LIKE ? OR " + MOVIE_DIRECTOR + " LIKE ? OR " + MOVIE_ACTORS + " LIKE ?;";
            Cursor cursor = sqLiteDatabase.query(TABLE_NAME, FROM, MOVIE_TITLE + " LIKE ? OR "+MOVIE_DIRECTOR+" LIKE ? OR "+MOVIE_ACTORS+" LIKE ?", new String[]{"%"+keyword+"%","%"+keyword+"%","%"+keyword+"%"}, null, null, null);

            return cursor;
    }
}
