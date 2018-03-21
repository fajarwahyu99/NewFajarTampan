package com.example.infolabsolution.basisdatanew;
import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import static com.example.infolabsolution.basisdatanew.DataContract.FavoColumn._ID;
import static com.example.infolabsolution.basisdatanew.DataContract.FavoColumn.IMAGE_MOVIE;
import static com.example.infolabsolution.basisdatanew.DataContract.FavoColumn.OVERVIEW;
import static com.example.infolabsolution.basisdatanew.DataContract.FavoColumn.RATING;
import static com.example.infolabsolution.basisdatanew.DataContract.FavoColumn.POSTER;
import static com.example.infolabsolution.basisdatanew.DataContract.FavoColumn.RELEASE_DATE;
import static com.example.infolabsolution.basisdatanew.DataContract.FavoColumn.MOVIE_TITLE;


public class HelperFavorite {
    private static String DATABASE_TABLE = BuildConfig.MOVIE_TABLE;
    private Context context;
    private DataHelper dataHelper;
    private SQLiteDatabase database;

    public HelperFavorite(Context context){
        this.context = context;
    }

    public HelperFavorite open() throws SQLException {
        dataHelper = new DataHelper(context);
        database = dataHelper.getWritableDatabase();
        return this;
    }


    public ArrayList<FavoriteMovie> query(){
        ArrayList<FavoriteMovie> arrayList = new ArrayList<FavoriteMovie>();
        Cursor cursor = database.query(DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                _ID +" DESC",
                null);
        cursor.moveToFirst();
        FavoriteMovie savelist;
        if (cursor.getCount()>0) {
            do {

                savelist = new FavoriteMovie();
                savelist.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                savelist.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(MOVIE_TITLE)));
                savelist.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));
                savelist.setRelease_date(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE_DATE)));
                savelist.setRating(cursor.getString(cursor.getColumnIndexOrThrow(RATING)));
                savelist.setBanner(cursor.getString(cursor.getColumnIndexOrThrow(IMAGE_MOVIE)));
                savelist.setPoster(cursor.getString(cursor.getColumnIndexOrThrow(POSTER)));
                arrayList.add(savelist);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }


    public void close(){
        dataHelper.close();
    }
    public long insert(FavoriteMovie savelist){
        ContentValues nomorMovie =  new ContentValues();
        nomorMovie.put(MOVIE_TITLE, savelist.getTitle());
        nomorMovie.put(OVERVIEW, savelist.getOverview());
        nomorMovie.put(RELEASE_DATE, savelist.getRelease_date());
        nomorMovie.put(RATING, savelist.getRating());
        nomorMovie.put(IMAGE_MOVIE, savelist.getBanner());
        nomorMovie.put(POSTER, savelist.getPoster());
        return database.insert(DATABASE_TABLE, null, nomorMovie);
    }

    public int delete(int id){
        return database.delete(BuildConfig.MOVIE_TABLE, _ID + " = '"+id+"'", null);
    }


    public Cursor queryByIdProvider(String id){
        return database.query(DATABASE_TABLE,null
                ,_ID + " = ?"
                ,new String[]{id}
                ,null
                ,null
                ,null
                ,null);
    }
    public Cursor queryProvider(){
        return database.query(DATABASE_TABLE
                ,null
                ,null
                ,null
                ,null
                ,null
                ,_ID
                        + " DESC");
    }


    public int deleteProvider(String id){
        return database.delete(DATABASE_TABLE,_ID + " = ?", new String[]{id});
    }
    public long insertProvider(ContentValues values){
        return database.insert(DATABASE_TABLE,null,values);
    }
}
