
/*
 * Copyright (c) By Fajar Nugraha Wahyu on 3/22/18 10:26 AM 2018.
 * All rights reserved
 */

package com.example.infolabsolution.basisdatanew;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;import static com.example.infolabsolution.basisdatanew.DataContract.FavoColumn._ID;
import static com.example.infolabsolution.basisdatanew.DataContract.FavoColumn.IMAGE_MOVIE;
import static com.example.infolabsolution.basisdatanew.DataContract.FavoColumn.OVERVIEW;
import static com.example.infolabsolution.basisdatanew.DataContract.FavoColumn.RATING;
import static com.example.infolabsolution.basisdatanew.DataContract.FavoColumn.POSTER;
import static com.example.infolabsolution.basisdatanew.DataContract.FavoColumn.RELEASE_DATE;
import static com.example.infolabsolution.basisdatanew.DataContract.FavoColumn.MOVIE_TITLE;

public class DataHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 100;

    private static final String SQL_CREATE_TABLE_FAVORITE = "CREATE TABLE " + BuildConfig.MOVIE_TABLE
            + " (" + _ID +"  INTEGER PRIMARY KEY AUTOINCREMENT,"
            +  MOVIE_TITLE +"TEXT NOT NULL,"
            +  OVERVIEW +" TEXT NOT NULL," +
                RELEASE_DATE +" TEXT NOT NULL," +
            RATING +" TEXT NOT NULL," +
            IMAGE_MOVIE+ " TEXT NOT NULL," +
            POSTER+" TEXT NOT NULL);";


    public DataHelper(Context context) {
        super(context, BuildConfig.DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BuildConfig.MOVIE_TABLE);
        onCreate(db);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_FAVORITE);
    }


}
