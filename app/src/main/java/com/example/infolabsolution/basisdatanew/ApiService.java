
/*
 * Copyright (c) By Fajar Nugraha Wahyu on 3/22/18 10:25 AM 2018.
 * All rights reserved
 */

package com.example.infolabsolution.basisdatanew;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class ApiService {

    private static Retrofit connection = null;
    public static Retrofit getClient(){
        if (connection == null){
            connection = new Retrofit.Builder().baseUrl(BuildConfig.BASE_RETORFIT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return connection;
    }

}
