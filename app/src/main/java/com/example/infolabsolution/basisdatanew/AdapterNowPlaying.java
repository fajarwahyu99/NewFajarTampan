package com.example.infolabsolution.basisdatanew;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AdapterNowPlaying extends RecyclerView.Adapter<AdapterNowPlaying.NowPlayingViewHolder> {
    private Context context;
    @BindView(R.id.btn_favorite)
    Button btnFovorite;
    private List<MovieConnection> movieModels;
    private HelperFavorite helperFavorite;
    private boolean boolFavorite = false;
    private int favorite;

    public AdapterNowPlaying(List<MovieConnection> movieModels, Context context){
        this.movieModels = movieModels;
        this.context = context;
    }

    public void clearData(){
        movieModels.clear();
    }

    @Override
    public NowPlayingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies, parent, false);
        return new NowPlayingViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(NowPlayingViewHolder hold, final int position) {

        hold.teksTitle.setText(movieModels.get(position).getTitle());
        hold.textDesk.setText(movieModels.get(position).getOverview());
        Picasso.with(context).load(movieModels.get(position).getPoster()).into(hold.imgMovie);
        hold.detailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goActivity(position, view);
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

    private void goActivity(int position, View view) {
        ModelMovie filmMovie = new ModelMovie();

        filmMovie.setTitle(movieModels.get(position).getTitle());
        filmMovie.setOverview(movieModels.get(position).getOverview());
        filmMovie.setRating(movieModels.get(position).getRating());
        filmMovie.setRelease_date(movieModels.get(position).getRelease_date());
        filmMovie.setBanner(movieModels.get(position).getBanner());
        filmMovie.setPoster(movieModels.get(position).getPoster());
        Intent intent = new Intent(view.getContext(), ActivityMovieItem.class);
        intent.putExtra(BuildConfig.EXTRA_MOVIE, (Parcelable) filmMovie);
        view.getContext().startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return movieModels.size();
    }

    public class NowPlayingViewHolder extends RecyclerView.ViewHolder{
        TextView teksTitle, textDesk;
        ImageView imgMovie;
        Button detailBtn;
        public NowPlayingViewHolder(View itemView) {
            super(itemView);
            teksTitle = (TextView)itemView.findViewById(R.id.np_title);
            textDesk = (TextView)itemView.findViewById(R.id.np_text_desc);
            imgMovie = (ImageView)itemView.findViewById(R.id.np_imgmovie);
            detailBtn = (Button) itemView.findViewById(R.id.btn_detail);
        }
    }
}
