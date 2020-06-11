package com.example.popularmovies;

/**
 * These utilities will be used to communicate with the themoviedb servers.
 */
public final class NetworkUtils {
    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String MOVIES_BASE_URL = "http://api.themoviedb.org/3/movie";

    private static final String apiKey = "";

    private static final String TOP_RATED_MOVIES = "top_rated";

    private static final String POPULAR_MOVIES = "popular";

    private static final String MOVIE_POSTER_BASE_URL = "http://image.tmdb.org/t/p";

    private static  String MOVIE_POSTER_SIZE = "w185";

    private static  String MOVIE_POSTER_PATH_FROM_QUERY = "";
    
}
