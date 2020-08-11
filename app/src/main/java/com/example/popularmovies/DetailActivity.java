package com.example.popularmovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_STRING = "extra_string";

    private TextView mMovieTitle;

    private TextView mReleaseDate;

    private TextView mVoteAverage;

    private TextView mOverview;

    private Button mFavorite;

    private ImageView mMoviePoster;

    private Movie mMovie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        this.setTitle("MovieDetail");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mMovieTitle = findViewById(R.id.movie_title);

        mReleaseDate = findViewById(R.id.release_date);

        mVoteAverage = findViewById(R.id.vote_average);

        mOverview = findViewById(R.id.overview);

        mFavorite = findViewById(R.id.favorite_button);

        mMoviePoster = findViewById(R.id.movie_poster);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        String mMovieDetail = intent.getStringExtra(EXTRA_STRING);
        if (mMovieDetail == null) {
            // EXTRA_STRING not found in intent
            closeOnError();
            return;
        }

        mMovie = new Movie(mMovieDetail);
        if (mMovie == null) {
            // Movie data unavailable
            closeOnError();
            return;
        }

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

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

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

