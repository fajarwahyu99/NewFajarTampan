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
