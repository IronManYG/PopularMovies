package com.example.popularmovies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieTrailer {

    private final String key;
    private final String name;
    private final String type;
    private final String typeOf = "Trailer";

    public MovieTrailer(String movieData)
    {
        List<String> movieDetail = new ArrayList<String>(Arrays.asList(movieData.split("-_-")));

        key = movieDetail.get(0);
        name = movieDetail.get(1);
        type = movieDetail.get(2);
        movieDetail.add(typeOf);


    }

    public String getKey() { return key; }

    public String getType() { return type; }

    public String getName() { return name; }

    public String getTypeOf() { return typeOf; }

}
