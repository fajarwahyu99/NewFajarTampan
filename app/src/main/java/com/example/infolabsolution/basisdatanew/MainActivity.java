package com.example.infolabsolution.basisdatanew;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Parcelable;
import com.example.infolabsolution.basisdatanew.AdapterMovie;
import com.example.infolabsolution.basisdatanew.MovieItems;
import com.example.infolabsolution.basisdatanew.ModelMovie;
import com.example.infolabsolution.basisdatanew.MyAsyncTaskLoader;
import com.example.infolabsolution.basisdatanew.R;
import android.support.v7.app.AppCompatActivity;

import android.widget.ListView;



import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<MovieItems>>, AdapterView.OnItemClickListener{

    ListView listView;
    private AdapterMovie adaptermovies;

    private String url;
    @BindView(R.id.edit_movie)
    EditText editMovie;
    @BindView(R.id.btn_cari)
    Button btnCari;
    ModelMovie teksFilm = new ModelMovie();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        adaptermovies = new AdapterMovie(this);
        adaptermovies.notifyDataSetChanged();
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adaptermovies);
        listView.setOnItemClickListener(this);
        btnCari.setOnClickListener(myListener);
        String query = editMovie.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(BuildConfig.EXTRAS_MOVIE, query);
        getLoaderManager().initLoader(0, bundle, this);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
    }

    @Override
    public Loader<ArrayList<MovieItems>> onCreateLoader(int i, Bundle bundle) {
        String testQuery ="";
        if (bundle != null){
            testQuery = bundle.getString(BuildConfig.EXTRAS_MOVIE);
            url = BuildConfig.SEARCH_MOVIE+testQuery;

        }
        return new MyAsyncTaskLoader(this, url);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MovieItems>> loader, ArrayList<MovieItems> movieItems) {
        adaptermovies.setData(movieItems);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MovieItems>> loader) {
        adaptermovies.setData(null);
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String query = editMovie.getText().toString();

            if (TextUtils.isEmpty(query))return;

            Bundle bundle = new Bundle();
            bundle.putString(BuildConfig.EXTRAS_MOVIE, query);
            getLoaderManager().restartLoader(0, bundle, MainActivity.this);
        }
    };

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        MovieItems movieItems = adaptermovies.getItem(i);

        teksFilm.setTitle(movieItems.getJudul());
        teksFilm.setRelease_date(movieItems.getTayang());
        teksFilm.setOverview(movieItems.getDeskripsi());
        teksFilm.setRating(movieItems.getRating());
        teksFilm.setBanner(movieItems.getBanner());
        Intent intent = new Intent(this, ActivityMovieItem.class);
        intent.putExtra(BuildConfig.EXTRA_MOVIE, (Parcelable) teksFilm);
        startActivity(intent);

    }
}
