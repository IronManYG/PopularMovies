package com.example.popularmovies;

public class Movie {

    private String title;
    private String posterPath;
    private String voteAverage;
    private String overView;
    private String releaseDate;

    /**
     * No args constructor for use in serialization
     */
    public Movie() {}

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

    public void setTitle(String title) {this.title = title;}

    public String getPosterPath() {return posterPath;}

    public void setPosterPath(String posterPath) {this.posterPath = posterPath;}

    public String getVoteAverage() {return voteAverage;}

    public void setVoteAverage(String voteAverage) {this.voteAverage = voteAverage;}

    public String getOverView() {return overView;}

    public void setOverView(String overView) {this.overView = overView;}

    public String getReleaseDate() {return releaseDate;}

    public void setReleaseDate(String releaseDate) {this.releaseDate = releaseDate;}

}
