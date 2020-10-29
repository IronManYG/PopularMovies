package com.example.popularmovies;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.Objects;

public class DetailActivity extends AppCompatActivity implements TrailersMovieAdapter.MovieAdapterOnClickHandler, ReviewMovieAdapter.MovieAdapterOnClickHandler{

    public static final String EXTRA_STRING = "extra_string";

    private TextView mMovieTitle;

    private TextView mReleaseDate;

    private TextView mVoteAverage;

    private TextView mOverview;

    private ImageView mMoviePoster;

    private Movie mMovie;

    private RecyclerView mTrailerRecyclerView;

    private RecyclerView mReviewRecyclerView;

    private TrailersMovieAdapter mTrailersMovieAdapter;

    private ReviewMovieAdapter mReviewMovieAdapter;

    private String mSortBy = "top_rated";


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

        LinearLayoutManager trailerLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        // Trailer
        mTrailerRecyclerView = findViewById(R.id.recyclerview_movie_trailers);

        mTrailerRecyclerView.setLayoutManager(trailerLayoutManager);

        mTrailersMovieAdapter = new TrailersMovieAdapter(this);

        mTrailerRecyclerView.setAdapter(mTrailersMovieAdapter);

        LinearLayoutManager reviewLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        // Review
        mReviewRecyclerView = findViewById(R.id.recyclerview_movie_review);

        mReviewRecyclerView.setLayoutManager(reviewLayoutManager);

        mReviewMovieAdapter = new ReviewMovieAdapter(this);

        mReviewRecyclerView.setAdapter(mReviewMovieAdapter);




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

        loadMovieData();
    }

    /**
     * This method will tell some background method to get the movie data in the background.
     */
    private void loadMovieData() {
        new FetchMovieReviewTask().execute();
        new FetchMovieTrailerTask().execute();
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

    @Override
    public void onClick(String movieData) {

        MovieTrailer mMovieTrailer = new MovieTrailer(movieData);
        String youtube = "https://youtu.be/";
        String trailerKey = youtube + mMovieTrailer.getKey();


        Uri trailerWebPage = Uri.parse(trailerKey);
        Intent intent = new Intent(Intent.ACTION_VIEW, trailerWebPage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    @SuppressLint("StaticFieldLeak")
    public class FetchMovieTrailerTask extends AsyncTask<Void , Void, String[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String[] doInBackground(Void... voids) {

            URL movieTrailerRequestUrl = NetworkUtils.buildMovieTrailersUrl(mMovie.getId());

            try {
                String jsonMovieTrailerResponse = NetworkUtils.getResponseFromHttpUrl(movieTrailerRequestUrl);

                return TheMovieDbJsonUtils.parseMoviesTrailersInfoStringsFromJson(jsonMovieTrailerResponse);

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String[] MovieData) {
            if (MovieData != null) {
                mTrailersMovieAdapter.setMovieData(MovieData);
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class FetchMovieReviewTask extends AsyncTask<Void , Void, String[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String[] doInBackground(Void... voids) {

            URL movieReviewRequestUrl = NetworkUtils.buildMovieReviewUrl(mMovie.getId());

            try {
                String jsonMovieReviewResponse = NetworkUtils.getResponseFromHttpUrl(movieReviewRequestUrl);

                return TheMovieDbJsonUtils.parseMoviesReviewInfoStringsFromJson(jsonMovieReviewResponse);

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String[] MovieData) {
            if (MovieData != null) {
                mReviewMovieAdapter.setMovieData(MovieData);
            }
        }
    }

}

