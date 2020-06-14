package com.example.popularmovies;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
     */

    public static Movie parseMoviesInfoStringsFromJson(String movieJsonStr)
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

        try {
             JSONObject moviesListJson = new JSONObject(movieJsonStr);
             JSONArray resultsArray = moviesListJson.getJSONArray(TMD_RESULTS);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
