package com.example.popularmovies;

public class Movie {

    private String title;
    private String posterPath;
    private String voteAverage;
    private String overView;
    private String releaseDate;

    public Movie(String movieData)
    {
        String[] movieDetail = movieData.split("-_-");
        title = movieDetail[0];
        posterPath = movieDetail[1];
        voteAverage = movieDetail[2];
        overView = movieDetail[3];
        releaseDate = movieDetail[4];
    }

    public String getTitle() {return title;}

    public String getPosterPath() {return posterPath;}

    public String getVoteAverage() {return voteAverage;}

    public String getOverView() {return overView;}

    public String getReleaseDate() {return releaseDate;}

}
