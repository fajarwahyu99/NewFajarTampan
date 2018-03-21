package com.example.infolabsolution.basisdatanew;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.LinkedList;

public class FragmentFavorite extends Fragment {

    RecyclerView viewFavorite;
    private LinkedList<FavoriteMovie> listingmovie;
    private HelperFavorite helperFavorite;
    private AdapterFavorite favoriteAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        viewFavorite = (RecyclerView) view.findViewById(R.id.rv_favorite);
        viewFavorite.setLayoutManager(new LinearLayoutManager(getActivity()));
        viewFavorite.setHasFixedSize(true);
        helperFavorite = new HelperFavorite(getActivity());
        helperFavorite.open();

        listingmovie = new LinkedList<>();
        favoriteAdapter = new AdapterFavorite(getActivity());
        favoriteAdapter.setListFavorite(listingmovie);
        viewFavorite.setAdapter(favoriteAdapter);
        new LoadDatabase().execute();
        return view;
    }

    private class LoadDatabase extends AsyncTask<Void, Void, ArrayList<FavoriteMovie>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (listingmovie.size() > 0 ){
                listingmovie.clear();
            }

        }

        @Override
        protected void onPostExecute(ArrayList<FavoriteMovie> favoritemovies) {
            super.onPostExecute(favoritemovies);
            listingmovie.addAll(favoritemovies);
            favoriteAdapter.setListFavorite(listingmovie);
            favoriteAdapter.notifyDataSetChanged();

            if (listingmovie.size() == 0){
            }
        }

        @Override
        protected ArrayList<FavoriteMovie> doInBackground(Void... voids) {
            return helperFavorite.query();
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (helperFavorite != null){
            helperFavorite.close();
        }
    }
}
