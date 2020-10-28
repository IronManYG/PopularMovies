package com.example.popularmovies;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * These utilities will be used to communicate with the themoviedb servers.
 */
public final class NetworkUtils {
    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String MOVIES_BASE_URL = "https://api.themoviedb.org/3/movie";

    private static final String KEY_PARAM = "api_key";

    /**
     *  You will need to create your own personal API key to make app get data form.
     *  Make account in "https://www.themoviedb.org/settings/api"  visit "https://www.themoviedb.org/settings/api" to get API kay.
     */
    private static final String API_KEY = "";

    private static final String LANGUAGE_PARAM = "language";

    private static final String LANGUAGE_KEY = "en-US";

    private static final String MOVIE_POSTER_BASE_URL = "https://image.tmdb.org/t/p";

    private static final   String MOVIE_POSTER_SIZE = "w500";

    /**
     * Builds the URL used to talk to the themoviedb servers.
     *
     * @return The URL to use to query the themoviedb servers.
     */

    public static URL buildUrl(String sort_by) {
        Uri builtUri = Uri.parse(MOVIES_BASE_URL).buildUpon()
                .appendPath(sort_by)
                .appendQueryParameter(KEY_PARAM, API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }

    /**
     * Builds the URL used to get movie_sort Poster jpg.
     *
     * @return The URL that displays movie_sort Poster jpg.
     */

    public static URL buildMoviePosterUrl(String posterPath) {
        Uri builtUri = Uri.parse(MOVIE_POSTER_BASE_URL).buildUpon()
                .appendPath(MOVIE_POSTER_SIZE)
                .appendEncodedPath(posterPath)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built Poster URI " + url);

        return url;
    }

    /**
     * Builds the URL used to get movie trailers.
     *
     * @return The URL that displays movie trailers.
     */

    public static URL buildMovieTrailersUrl(String movieId) {
        Uri builtUri = Uri.parse(MOVIES_BASE_URL).buildUpon()
                .appendEncodedPath(movieId)
                .appendPath("videos")
                .appendQueryParameter(KEY_PARAM, API_KEY)
                .appendQueryParameter(LANGUAGE_PARAM, LANGUAGE_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built Trailers URI " + url);

        return url;
    }

    /**
     * Builds the URL used to get movie review.
     *
     * @return The URL that displays movie review.
     */

    public static URL buildMovieReviewUrl(String movieId) {
        Uri builtUri = Uri.parse(MOVIES_BASE_URL).buildUpon()
                .appendEncodedPath(movieId)
                .appendPath("reviews")
                .appendQueryParameter(KEY_PARAM, API_KEY)
                .appendQueryParameter(LANGUAGE_PARAM, LANGUAGE_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built Review URI " + url);

        return url;
    }


    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
