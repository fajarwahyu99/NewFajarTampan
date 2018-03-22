
/*
 * Copyright (c) By Fajar Nugraha Wahyu on 3/22/18 10:26 AM 2018.
 * All rights reserved
 */

package com.example.infolabsolution.basisdatanew;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.io.Serializable;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentUpComing extends Fragment {

    RecyclerView UpComingRecyclerView;
    List<MovieConnection> movieModels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_up_coming, container, false);
        UpComingRecyclerView = (RecyclerView) view.findViewById(R.id.rv_upcoming);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        UpComingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        UpComingRecyclerView.setHasFixedSize(true);
        if (savedInstanceState != null) {
            movieModels = (List<MovieConnection>) savedInstanceState.getSerializable(BuildConfig.SAVE_UPCOMING);
            UpComingRecyclerView.setAdapter(new AdapterUpComing(movieModels, getActivity()));
        }else {
            getRequest();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(BuildConfig.SAVE_UPCOMING, (Serializable) movieModels);
    }

    private void getRequest(){
        ApiInterface apiInterface = ApiService.getClient().create(ApiInterface.class);
        Call<ResponseNewMovieModel> callingmovie = apiInterface.getUpcomingMovie(BuildConfig.API_KEY_MOVIE);

        callingmovie.enqueue(new Callback<ResponseNewMovieModel>() {
            @Override
            public void onResponse(Call<ResponseNewMovieModel> call, Response<ResponseNewMovieModel> response) {
                movieModels = response.body().getResults();
                UpComingRecyclerView.setAdapter(new AdapterUpComing(movieModels, getActivity()));
            }

            @Override
            public void onFailure(Call<ResponseNewMovieModel> call, Throwable test) {
            }
        });
    }

}
