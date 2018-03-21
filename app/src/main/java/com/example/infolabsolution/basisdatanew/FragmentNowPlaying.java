package com.example.infolabsolution.basisdatanew;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentNowPlaying extends Fragment {
    List<MovieConnection> movieModels;
    RecyclerView NowPlayingRv;

    RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_now_playing, container, false);
        NowPlayingRv = (RecyclerView) view.findViewById(R.id.rv_nowplaying);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        NowPlayingRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        NowPlayingRv.setHasFixedSize(true);
        if (savedInstanceState != null){
            movieModels = (List<MovieConnection>) savedInstanceState.getSerializable(BuildConfig.SAVE_NOWPLAYING);
            NowPlayingRv.setAdapter(new AdapterNowPlaying(movieModels, getActivity()));
        }else {
            getRequest();
        }

    }

    private void getRequest(){
        ApiInterface apiInterface = ApiService.getClient().create(ApiInterface.class);
        Call<ResponseNewMovieModel> call = apiInterface.getNowPlayingMovie(BuildConfig.API_KEY_MOVIE);
        call.enqueue(new Callback<ResponseNewMovieModel>() {
            @Override
            public void onResponse(Call<ResponseNewMovieModel> call, Response<ResponseNewMovieModel> response) {
                movieModels = response.body().getResults();
                NowPlayingRv.setAdapter(new AdapterNowPlaying(movieModels, getActivity()));

            }

            @Override
            public void onFailure(Call<ResponseNewMovieModel> call, Throwable test) {
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(BuildConfig.SAVE_NOWPLAYING, (Serializable) movieModels);
    }

}
