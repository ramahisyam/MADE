package com.example.madesubmission.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.madesubmission.data.model.Movies;

import java.util.ArrayList;

import static com.example.madesubmission.data.db.DatabaseContract.NoteColumns.BACKDROP_PATH;
import static com.example.madesubmission.data.db.DatabaseContract.NoteColumns.OVERVIEW;
import static com.example.madesubmission.data.db.DatabaseContract.NoteColumns.POSTER_PATH;
import static com.example.madesubmission.data.db.DatabaseContract.NoteColumns.RELEASE_DATE;
import static com.example.madesubmission.data.db.DatabaseContract.NoteColumns.TITLE;
import static com.example.madesubmission.data.db.DatabaseContract.NoteColumns.VOTE_AVERAGE;
import static com.example.madesubmission.data.db.DatabaseContract.TABLE_MOVIE;
import static com.example.madesubmission.data.db.DatabaseContract.NoteColumns._ID;

public class MovieHelper {
    private static  DatabaseHelper databaseHelper;
    private static MovieHelper INSTANCE;

    private static SQLiteDatabase database;

    public MovieHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public static MovieHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MovieHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();

        if (database.isOpen()) {
            database.close();
        }
    }

    public ArrayList<Movies> getAllMovies() {
        ArrayList<Movies> arrayList = new ArrayList<>();
        Cursor cursor = database.query(TABLE_MOVIE, null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        Movies movies;
        if (cursor.getCount() > 0) {
            do {
                movies = new Movies();
                movies.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                movies.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                movies.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));
                movies.setReleaseDate(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE_DATE)));
                movies.setVoteAverage(Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow(VOTE_AVERAGE))));
                movies.setPosterPath(cursor.getString(cursor.getColumnIndexOrThrow(POSTER_PATH)));
                movies.setBackdropPath(cursor.getString(cursor.getColumnIndexOrThrow(BACKDROP_PATH)));

                arrayList.add(movies);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insertMovie(Movies movies) {
        ContentValues args = new ContentValues();
        args.put(TITLE, movies.getTitle());
        args.put(OVERVIEW, movies.getOverview());
        args.put(RELEASE_DATE, movies.getReleaseDate());
        args.put(VOTE_AVERAGE, movies.getVoteAverage());
        args.put(POSTER_PATH, movies.getPosterPath());
        args.put(BACKDROP_PATH, movies.getBackdropPath());
        return database.insert(TABLE_MOVIE, null, args);
    }

    public int deleteMovie(int id) {
        return database.delete(TABLE_MOVIE, _ID + " = '" + id + "'", null);
    }

//    public void favoriteState() {
//        Cursor cursor = database.rawQuery("select * from " +);
//    }
}
