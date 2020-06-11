package com.example.popularmovies;

import android.content.Context;

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
     * @param movieJsonStr JSON response from server
     *
     * @return Array of Strings describing movies data
     *
     * @throws JSONException If JSON data cannot be properly parsed
     */

    public static String[] getMoviesInfoStringsFromJson(Context context, String movieJsonStr) throws JSONException
    {
        /*Movies information. Each movie's details info is an element of the "results" array*/
        final String TMD_RESULTS = "results";

        final String TMD_TITLE = "title";

        /*the poster path returned by the query*/
        final String TMD_POSTER_PATH = "poster_path";

        /*user rating */
        final String TMD_VOTE_AVERAGE = "vote_average";

        final String TMD_OVERVIEW = "overview";

        final String TMD_RELEASE_DATE = "release_date";

        /* String array to hold each movie's String */
        String[] parsedMovieData = null;

        JSONObject movieJson = new JSONObject(movieJsonStr);

        JSONArray movieArray = movieJson.getJSONArray(TMD_RESULTS);

        parsedMovieData = new String[movieArray.length()];

        return parsedMovieData;
    }

}
