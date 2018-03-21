package com.example.infolabsolution.basisdatanew;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 100;

    private static final String SQL_CREATE_TABLE_FAVORITE = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            BuildConfig.MOVIE_TABLE,
            DataContract.FavoColumn._ID,
            DataContract.FavoColumn.MOVIE_TITLE,
            DataContract.FavoColumn.OVERVIEW,
            DataContract.FavoColumn.RELEASE_DATE,
            DataContract.FavoColumn.RATING,
            DataContract.FavoColumn.IMAGE_MOVIE,
            DataContract.FavoColumn.POSTER

    );
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
