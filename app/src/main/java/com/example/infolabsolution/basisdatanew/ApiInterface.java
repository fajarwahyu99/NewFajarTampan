
/*
 * Copyright (c) By Fajar Nugraha Wahyu on 3/22/18 10:25 AM 2018.
 * All rights reserved
 */

package com.example.infolabsolution.basisdatanew;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {
    @GET(BuildConfig.NOW_PLAYING)
    Call<ResponseNewMovieModel> getNowPlayingMovie(@Query("api_key") String apiKey);

    @GET(BuildConfig.UPCOMING)
    Call<ResponseNewMovieModel> getUpcomingMovie(@Query("api_key") String apiKey);
}
