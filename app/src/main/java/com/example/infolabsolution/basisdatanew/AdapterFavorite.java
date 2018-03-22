
/*
 * Copyright (c) By Fajar Nugraha Wahyu on 3/22/18 10:24 AM 2018.
 * All rights reserved
 */

package com.example.infolabsolution.basisdatanew;
import com.squareup.picasso.Picasso;
import java.util.LinkedList;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;



public class AdapterFavorite extends RecyclerView.Adapter<AdapterFavorite.ViewHolder> {
    private LinkedList<FavoriteMovie> listFavorites;
    private Activity activity;
    private Context context;

    public AdapterFavorite(Context context) {
        this.context = context;
    }

    public LinkedList<FavoriteMovie> getListFavorite() {
        return listFavorites;
    }

    public void setListFavorite(LinkedList<FavoriteMovie> listFavorites) {
        this.listFavorites = listFavorites;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder hold, final int position) {
        hold.teksTitle.setText(listFavorites.get(position).getTitle());
        hold.textDesk.setText(listFavorites.get(position).getOverview());
        Picasso.with(context).load(listFavorites.get(position).getPoster()).into(hold.imgMovie);
        hold.detailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goActivity(position, v);
            }
        });
    }

    private void goActivity(int position, View view) {
        ModelMovie filmMovie = new ModelMovie();

        filmMovie.setId(listFavorites.get(position).getId());
        filmMovie.setTitle(listFavorites.get(position).getTitle());
        filmMovie.setOverview(listFavorites.get(position).getOverview());
        filmMovie.setRating(listFavorites.get(position).getRating());
        filmMovie.setRelease_date(listFavorites.get(position).getRelease_date());
        filmMovie.setBanner(listFavorites.get(position).getBanner());
        filmMovie.setPoster(listFavorites.get(position).getPoster());
        Intent intent = new Intent(view.getContext(), ActivityMovieItem.class);
        intent.putExtra(BuildConfig.EXTRA_MOVIE, (Parcelable) filmMovie);
        intent.putExtra(BuildConfig.FILM_FAVORITE, 1);
        view.getContext().startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return getListFavorite().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView teksTitle;
        TextView textDesk;
        ImageView imgMovie;
        Button detailBtn;
        public ViewHolder(View itemView) {
            super(itemView);
            teksTitle = (TextView)itemView.findViewById(R.id.np_title);
            textDesk = (TextView)itemView.findViewById(R.id.np_text_desc);
            imgMovie = (ImageView)itemView.findViewById(R.id.np_imgmovie);
            detailBtn = (Button) itemView.findViewById(R.id.btn_detail);
        }
    }
}
