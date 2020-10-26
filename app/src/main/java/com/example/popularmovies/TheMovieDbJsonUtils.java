package com.example.popularmovies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Utility functions to handle TheMovieDb JSON data.
 */
public class TheMovieDbJsonUtils {
    /**
     * This method parses JSON from a web response and returns an array of Strings
     * describing movies .
     * <p/>
     *
     * @param movieJsonStr JSON response from server.
     *
     * @return Array of Strings describing movies data.
     */

    public static String[] parseMoviesInfoStringsFromJson(String movieJsonStr)
    {
        /*TMD mean themoviedb*/
        /*Movies information. Each movie_sort's details info is an element of the "results" array*/
        final String TMD_RESULTS = "results";

        final String TMD_TITLE = "title";

        /*the poster path returned by the query*/
        final String TMD_POSTER_PATH = "poster_path";

        /*user rating */
        final String TMD_VOTE_AVERAGE = "vote_average";

        final String TMD_OVERVIEW = "overview";

        final String TMD_RELEASE_DATE = "release_date";

        /*movie id */
        final String TMD_ID = "id";

        /* String array to hold each Movie String */
        String[] parsedMovieData;

        try {
             JSONObject moviesListJson = new JSONObject(movieJsonStr);
             JSONArray resultsArray = moviesListJson.getJSONArray(TMD_RESULTS);

             parsedMovieData = new  String[resultsArray.length()];

             for(int i = 0 ; i < resultsArray.length(); i++)
             {
                 JSONObject movieDetails = resultsArray.getJSONObject(i);
                 String title = movieDetails.optString(TMD_TITLE);
                 String posterPath = movieDetails.optString(TMD_POSTER_PATH);
                 String voteAverage = movieDetails.optString(TMD_VOTE_AVERAGE);
                 String overView = movieDetails.optString(TMD_OVERVIEW);
                 String releaseDate = movieDetails.optString(TMD_RELEASE_DATE);
                 String id = movieDetails.optString(TMD_ID);
                 parsedMovieData[i] = title + "-_-" + posterPath + "-_-" + voteAverage + "-_-" + overView + "-_-" + releaseDate + "-_-" + id;
             }

            return parsedMovieData;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
