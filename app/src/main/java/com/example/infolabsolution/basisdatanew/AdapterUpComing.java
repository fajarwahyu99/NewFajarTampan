package com.example.infolabsolution.basisdatanew;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import java.util.List;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import com.squareup.picasso.Picasso;
import android.view.View;
import android.widget.TextView;



public class AdapterUpComing extends RecyclerView.Adapter<AdapterUpComing.UpComingViewHolder> {
    private Context context;

    private List<MovieConnection> movieModels;

    public AdapterUpComing(List<MovieConnection> movieModels, Context context){
        this.movieModels = movieModels;
        this.context = context;
    }
    @Override
    public UpComingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies, parent, false);
        return new UpComingViewHolder(item);
    }

    @Override
    public void onBindViewHolder(UpComingViewHolder hold, final int position) {
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

    public class UpComingViewHolder extends RecyclerView.ViewHolder {
        TextView teksTitle, textDesk;
        Button detailBtn;
        ImageView imgMovie;
        public UpComingViewHolder(View itemView) {
            super(itemView);
            teksTitle = (TextView)itemView.findViewById(R.id.np_title);
            textDesk = (TextView)itemView.findViewById(R.id.np_text_desc);
            imgMovie = (ImageView)itemView.findViewById(R.id.np_imgmovie);
            detailBtn = (Button) itemView.findViewById(R.id.btn_detail);
        }
    }
}
