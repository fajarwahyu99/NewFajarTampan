
/*
 * Copyright (c) By Fajar Nugraha Wahyu on 3/22/18 10:24 AM 2018.
 * All rights reserved
 */

package com.example.infolabsolution.basisdatanew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.infolabsolution.basisdatanew.MovieItems;
import com.example.infolabsolution.basisdatanew.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterMovie extends BaseAdapter {
    private ArrayList<MovieItems> moviesData = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;

    public AdapterMovie(Context context){
        this.context = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<MovieItems> items){
        moviesData = items;
        notifyDataSetChanged();
    }

    public void addItems(final MovieItems item){
        moviesData.add(item);
        notifyDataSetChanged();
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    public void clearData(){
        moviesData.clear();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getCount() {
        if (moviesData == null){
            return 0;
        }
        return moviesData.size();
    }

    @Override
    public MovieItems getItem(int i) {
        return moviesData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder hold = null;
        if (view == null){
            hold = new ViewHolder();
            view = mInflater.inflate(R.layout.movie_item, null);
            hold.textViewJudul = (TextView) view.findViewById(R.id.textJudul);
            hold.textViewDeskripsi = (TextView) view.findViewById(R.id.textDesc);
            hold.textViewTerbit = (TextView) view.findViewById(R.id.textTayang);
            hold.imgMovie = (ImageView) view.findViewById(R.id.img_movie);
            view.setTag(hold);
        }else{
            hold = (ViewHolder) view.getTag();
        }

        hold.textViewJudul.setText(moviesData.get(i).getJudul());
        hold.textViewDeskripsi.setText(moviesData.get(i).getDeskripsi());
        hold.textViewTerbit.setText(moviesData.get(i).getTayang());
        return view;
    }


    private static class ViewHolder{
        TextView textViewJudul;
        TextView textViewDeskripsi;
        TextView textViewTerbit;
        ImageView imgMovie;
    }
}
