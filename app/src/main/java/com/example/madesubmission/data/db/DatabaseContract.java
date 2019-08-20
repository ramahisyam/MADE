package com.example.madesubmission.data.db;

import android.provider.BaseColumns;

public class DatabaseContract {
    static String TABLE_MOVIE = "movie";

    static final class NoteColumns implements BaseColumns {

        static String TITLE = "title";
        static String OVERVIEW = "overview";
        static String RELEASE_DATE = "release_date";
        static String VOTE_AVERAGE = "vote_average";
        static String POSTER_PATH = "poster_path";
        static String BACKDROP_PATH = "backdrop_path";
    }
}
