package com.example.popularmovies;

public class MovieTrailer {

    private final String key;
    private final String name;
    private final String type;

    public MovieTrailer(String movieData)
    {
        String[] movieDetail = movieData.split("-_-");
            key = movieDetail[0];
            name = movieDetail[1];
            type = movieDetail[2];
    }

    public String getKey() { return key; }

    public String getType() { return type; }

    public String getName() { return name; }

}
