package com.example.popularmovies;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_STRING = "extra_string";

    private TextView mMovieTitle;

    private TextView mReleaseDate;

    private TextView mVoteAverage;

    private TextView mOverview;

    private ImageView mMoviePoster;

    private Movie mMovie;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        this.setTitle("MovieDetail");

        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mMovieTitle = findViewById(R.id.movie_title);

        mReleaseDate = findViewById(R.id.release_date);

        mVoteAverage = findViewById(R.id.vote_average);

        mOverview = findViewById(R.id.overview);

        mMoviePoster = findViewById(R.id.movie_poster);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        assert intent != null;
        String mMovieDetail = intent.getStringExtra(EXTRA_STRING);
        if (mMovieDetail == null) {
            // EXTRA_STRING not found in intent
            closeOnError();
            return;
        }
        mMovie = new Movie(mMovieDetail);
        populateUI();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method will make the error message visible.
     */
    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    /**
     * This method will Publish movie detail in ui.
     */
    private void populateUI() {
        String UriPosterPath = String.valueOf(NetworkUtils.buildMoviePosterUrl(mMovie.getPosterPath()));

        Picasso.get().load(UriPosterPath).into(mMoviePoster);

        String mTag = "Detail Activity";

        mMovieTitle.setText(mMovie.getTitle());
        Log.i(mTag, "Movie Title : " + mMovie.getTitle());

        mReleaseDate.setText(mMovie.getReleaseDate());
        Log.i(mTag, "Release Date : " + mMovie.getReleaseDate());

        mVoteAverage.setText(mMovie.getVoteAverage());
        Log.i(mTag, "Vote Average : " + mMovie.getVoteAverage());

        mOverview.setText(mMovie.getOverView());
        Log.i(mTag, "Overview : " + mMovie.getOverView());

    }
}

