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

    private static final String MOVIES_BASE_URL = "http://api.themoviedb.org/3/movie";

    private static final String KEY_PARAM = "api_key";

    private static final String API_KEY = "";

    private static final String TOP_RATED_MOVIES = "top_rated";

    private static final String POPULAR_MOVIES = "popular";

    private static final String MOVIE_POSTER_BASE_URL = "http://image.tmdb.org/t/p";

    private static  String MOVIE_POSTER_SIZE = "w185";

    private static  String MOVIE_POSTER_PATH_FROM_QUERY = "";

    /**
     * Builds the URL used to talk to the themoviedb servers.
     *
     * @return The URL to use to query the themoviedb servers.
     */

    public static URL buildUrl() {
        Uri builtUri = Uri.parse(MOVIES_BASE_URL).buildUpon()
                .appendPath(TOP_RATED_MOVIES)
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
