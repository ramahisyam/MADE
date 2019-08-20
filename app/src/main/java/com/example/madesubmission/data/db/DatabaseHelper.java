package com.example.madesubmission.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.madesubmission.data.db.DatabaseContract.NoteColumns.BACKDROP_PATH;
import static com.example.madesubmission.data.db.DatabaseContract.NoteColumns.OVERVIEW;
import static com.example.madesubmission.data.db.DatabaseContract.NoteColumns.POSTER_PATH;
import static com.example.madesubmission.data.db.DatabaseContract.NoteColumns.RELEASE_DATE;
import static com.example.madesubmission.data.db.DatabaseContract.NoteColumns.TITLE;
import static com.example.madesubmission.data.db.DatabaseContract.NoteColumns.VOTE_AVERAGE;
import static com.example.madesubmission.data.db.DatabaseContract.NoteColumns._ID;
import static com.example.madesubmission.data.db.DatabaseContract.TABLE_MOVIE;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "moviecatalogue";

    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_MOVIE = String.format("CREATE TABLE %s" +
            " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
            " %s TEXT NOT NULL," +
            " %s TEXT NOT NULL," +
            " %s TEXT NOT NULL," +
            " %s TEXT NOT NULL," +
            " %s TEXT NOT NULL," +
            " %s TEXT NOT NULL)",
            TABLE_MOVIE,
            _ID,
            TITLE,
            OVERVIEW,
            RELEASE_DATE,
            VOTE_AVERAGE,
            POSTER_PATH,
            BACKDROP_PATH);

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE);
        onCreate(db);
    }
}
