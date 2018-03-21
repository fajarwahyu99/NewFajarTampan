package com.example.infolabsolution.basisdatanew;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.example.infolabsolution.basisdatanew.DataContract.CONTENT_URI;


public class FavoriteContent extends ContentProvider{
    private static final int FAVORITE = 10;
    private static final int FAVORITE_ID = 20;
    public static final Uri CONTENT_URI = new Uri.Builder().scheme("moviefilms")
            .authority(BuildConfig.AUTHORITY)
            .appendPath(BuildConfig.MOVIE_TABLE)
            .build();
    private static final UriMatcher textUriLink = new UriMatcher(UriMatcher.NO_MATCH);

    static {

        textUriLink.addURI(BuildConfig.AUTHORITY, BuildConfig.MOVIE_TABLE, FAVORITE);
        textUriLink.addURI(BuildConfig.AUTHORITY,
                BuildConfig.MOVIE_TABLE+ BuildConfig.MOVIETABLE,
                FAVORITE_ID);
    }

    private HelperFavorite helperFavorite;
    @Override
    public boolean onCreate() {
        helperFavorite = new HelperFavorite(getContext());
        helperFavorite.open();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor;
        switch(textUriLink.match(uri)){
            case FAVORITE:
                cursor = helperFavorite.queryProvider();
                break;
            case FAVORITE_ID:
                cursor = helperFavorite.queryByIdProvider(uri.getLastPathSegment());
                break;
            default:
                cursor = null;
                break;
        }

        if (cursor != null){
            cursor.setNotificationUri(getContext().getContentResolver(),uri);
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long saved ;

        switch (textUriLink.match(uri)){
            case FAVORITE:
                saved = helperFavorite.insertProvider(values);
                break;
            default:
                saved = 0;
                break;
        }

        if (saved > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return Uri.parse(CONTENT_URI + "/" + saved);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int deleted;
        switch (textUriLink.match(uri)) {
            case FAVORITE_ID:
                deleted =  helperFavorite.deleteProvider(uri.getLastPathSegment());
                break;
            default:
                deleted = 0;
                break;
        }

        if (deleted > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return deleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
