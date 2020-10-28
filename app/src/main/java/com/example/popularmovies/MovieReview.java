package com.example.popularmovies;

public class MovieReview {

    private final String author;
    private final String content;
    private final String id;

    public MovieReview(String movieData)
    {
        String[] movieDetail = movieData.split("-_-");
            author = movieDetail[0];
            content = movieDetail[1];
            id = movieDetail[2];
    }

    public String getAuthor() { return author; }

    public String getId() { return id; }

    public String getContent() { return content; }

}
