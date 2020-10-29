package com.example.popularmovies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieReview {

    private final String author;
    private final String content;
    private final String id;
    private final String typeOf = "review";

    public MovieReview(String movieData)
    {
        List<String> movieDetail = new ArrayList<String>(Arrays.asList(movieData.split("-_-")));

        author = movieDetail.get(0);
        content = movieDetail.get(1);
        id = movieDetail.get(2);
        movieDetail.add(typeOf);
    }

    public String getAuthor() { return author; }

    public String getId() { return id; }

    public String getContent() { return content; }

    public String getTypeOf() { return typeOf; }

}
