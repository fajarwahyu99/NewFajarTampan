
/*
 * Copyright (c) By Fajar Nugraha Wahyu on 3/22/18 10:27 AM 2018.
 * All rights reserved
 */

package com.example.infolabsolution.basisdatanew;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.infolabsolution.basisdatanew.MovieItems;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class MyAsyncTaskLoader extends AsyncTaskLoader<ArrayList<MovieItems>> {

    private ArrayList<MovieItems> moviesData;
    private boolean moviesResult = false;

    private String url = "";
    public MyAsyncTaskLoader(Context context, String urllink) {
        super(context);
        onContentChanged();
        this.url = urllink;
    }


    @Override
    public void deliverResult(ArrayList<MovieItems> data) {
        moviesData = data;
        moviesResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (moviesResult){
            onReleaseResources(moviesData);
            moviesData = null;
            moviesResult = false;
        }
    }


    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (takeContentChanged()){
            forceLoad();
        }else if(moviesResult) {
            deliverResult(moviesData);
        }
    }
    @Override
    public ArrayList<MovieItems> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();
        final ArrayList<MovieItems> moviesItems = new ArrayList<>();
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject repObject = new JSONObject(result);
                    JSONArray listingmovie = repObject.getJSONArray("results");

                    for (int i = 0; i < listingmovie.length(); i++){
                        JSONObject movie = listingmovie.getJSONObject(i);
                        MovieItems itemsMovie = new MovieItems(movie);
                        moviesItems.add(itemsMovie);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        return moviesItems;
    }


    protected void onReleaseResources(ArrayList<MovieItems> data) {
    }
}
