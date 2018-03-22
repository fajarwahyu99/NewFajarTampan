
/*
 * Copyright (c) By Fajar Nugraha Wahyu on 3/22/18 10:26 AM 2018.
 * All rights reserved
 */

package com.example.infolabsolution.basisdatanew;

import android.os.Build;
import android.provider.BaseColumns;
import android.database.Cursor;
import android.net.Uri;



public class DataContract {
    public static final class FavoColumn implements BaseColumns {
      public static String MOVIE_TITLE = "titlemovies";
        public static String RATING = "ratingmovies";
      public static String OVERVIEW = "overviewmovies";
      public static String RELEASE_DATE = "releasedates";
      public static String IMAGE_MOVIE = "imagemovies";
      public static String POSTER = "postermovies";
    }
    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(BuildConfig.AUTHORITY)
            .appendPath(BuildConfig.MOVIE_TABLE)
            .build();
    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt( cursor.getColumnIndex(columnName) );
    }


    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong( cursor.getColumnIndex(columnName) );
    }
    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString( cursor.getColumnIndex(columnName) );
    }
}
