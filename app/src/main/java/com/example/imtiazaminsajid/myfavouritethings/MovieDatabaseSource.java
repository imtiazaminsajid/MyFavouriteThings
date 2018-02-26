package com.example.imtiazaminsajid.myfavouritethings;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Imtiaz Amin Sajid on 2/26/2018.
 */

public class MovieDatabaseSource {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    public MovieDatabaseSource(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void open(){
        sqLiteDatabase = databaseHelper.getWritableDatabase();
    }
    public void close(){
        sqLiteDatabase.close();
    }
    public boolean addMovie(Movie movie){
        this.open();
        ContentValues values = new ContentValues();
        values.put(databaseHelper.COL_NAME, movie.getMovieName());
        values.put(databaseHelper.COL_YEAR, movie.getMovieYear());
        long row_id = sqLiteDatabase.insert(databaseHelper.TABLE_MOVIE, null, values);
        this.close();
        if (row_id> 0){
            return true;
        }else{
            return false;
        }
    }

    public ArrayList<Movie> getAllMovies(){
        ArrayList<Movie> movies = new ArrayList<>();
        this.open();
//        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+DatabaseHelper.TABLE_MOVIE, null);
        Cursor cursor = sqLiteDatabase.query(databaseHelper.TABLE_MOVIE, null,
                null, null, null, null, null);
        if (cursor.moveToFirst()){
            do {

                int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ID));
                String movieName= cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NAME));
                String movieYear = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_YEAR));
                Movie movie = new Movie(id, movieName, movieYear, R.mipmap.ic_launcher_round);
                movies.add(movie);
            }while (cursor.moveToNext());
        }
        cursor.close();
        this.close();
        return movies;
    }

    public boolean updateMovie(Movie movie, int rowId){
        this.open();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_ID, movie.getMovieID());
        values.put(DatabaseHelper.COL_NAME, movie.getMovieName());
        values.put(DatabaseHelper.COL_YEAR, movie.getMovieYear());
        int updated = sqLiteDatabase.update(DatabaseHelper.TABLE_MOVIE, values,
                DatabaseHelper.COL_ID+" = ?",new String[]{Integer.toString(rowId)});

        if (updated>0){
            return true;
        }else {
            return false;
        }
    }


    public boolean deleteMovie(int rowId){
        this.open();
        int deleted = sqLiteDatabase.delete(DatabaseHelper.TABLE_MOVIE, DatabaseHelper.COL_ID+" = ?", new String[] {Integer.toString(rowId)});
        this.close();
        if (deleted>0){
            return true;
        }else{
            return false;
        }
    }
}