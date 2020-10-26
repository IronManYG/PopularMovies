package com.example.popularmovies;

public class Movie {

    private final String title;
    private final String posterPath;
    private final String voteAverage;
    private final String overView;
    private final String releaseDate;
    private final String id;

    public Movie(String movieData)
    {
        String[] movieDetail = movieData.split("-_-");
        title = movieDetail[0];
        posterPath = movieDetail[1];
        voteAverage = movieDetail[2];
        overView = movieDetail[3];
        releaseDate = movieDetail[4];
        id  = movieDetail[5];
    }

    public String getTitle() {return title;}

    public String getPosterPath() {return posterPath;}

    public String getVoteAverage() {return voteAverage;}

    public String getOverView() {return overView;}

    public String getReleaseDate() {return releaseDate;}

    public String getId() {return id;}

}
