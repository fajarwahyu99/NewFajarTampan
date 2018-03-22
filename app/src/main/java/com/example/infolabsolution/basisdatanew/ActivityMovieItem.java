/*
 * Copyright (c) By Fajar Nugraha Wahyu on 3/22/18 10:24 AM 2018.
 * All rights reserved
 */

package com.example.infolabsolution.basisdatanew;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ActivityMovieItem extends AppCompatActivity {
    CoordinatorLayout coordinatorLayout;
    private HelperFavorite helperFavorite;
    private boolean boolFavorite = false;
    @BindView(R.id.overview)
    TextView overview;
    @BindView(R.id.release_date)
    TextView release_note;
    @BindView(R.id.btn_favorite)
    Button btnFovorite;
    @BindView(R.id.sharebtn)
    Button shareBtn;
    private int favorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_item);
        ButterKnife.bind(this);


        final ModelMovie filmMovie = getIntent()
                .getParcelableExtra(BuildConfig.EXTRA_MOVIE);
        release_note.setText(filmMovie.getRelease_date());
        overview.setText(filmMovie.getOverview());
        ImageView imageBanner = (ImageView) findViewById(R.id.img_banner);
        Picasso.with(this).load(filmMovie.getBanner()).into(imageBanner);
        helperFavorite = new HelperFavorite(this);
        helperFavorite.open();

        favorite = getIntent().getIntExtra(BuildConfig.FILM_FAVORITE, 0);
        if (favorite == 1){
            boolFavorite = true;
            btnFovorite.setText("hapus favorite");
        }

        btnFovorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!boolFavorite){
                    favoritkan(filmMovie);
                    Toast.makeText(ActivityMovieItem.this, "Favorited", Toast.LENGTH_LONG).show();
                }else {
                    deleteFavorite(filmMovie);
                    Toast.makeText(ActivityMovieItem.this, "Deleted", Toast.LENGTH_LONG).show();
                }
            }
        });

        getSupportActionBar().setTitle(filmMovie.getTitle());

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(android.content.Intent.EXTRA_TEXT, filmMovie.getTitle()+filmMovie.getOverview());
                startActivity(intent);
            }
        });
    }


    private void favoritkan(ModelMovie filmMovie){
        FavoriteMovie favoritemovies = new FavoriteMovie();
        favoritemovies.setTitle(filmMovie.getTitle());
        favoritemovies.setOverview(filmMovie.getOverview());
        favoritemovies.setRating(filmMovie.getRating());
        favoritemovies.setRelease_date(filmMovie.getRelease_date());
        favoritemovies.setPoster(filmMovie.getPoster());
        favoritemovies.setBanner(filmMovie.getBanner());
        helperFavorite.insert(favoritemovies);
    }

    private void deleteFavorite(ModelMovie filmMovie){
        helperFavorite.delete(filmMovie.getId());
    }


}
