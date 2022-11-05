package MOBLIMA;

import java.io.Serializable;
import java.util.ArrayList;

public class Movie implements Serializable {
    private String title;
    private MovieStatus status;
    private String synopsis;
    private String director;
    private ArrayList<String> cast;
    private Ratings rating;
    private AgeRating ageRating;
    private int sales;
    private Reviews review;
    private MovieType movieType;
    private int duration;

    public Movie() {
        this.sales = 0;
    }

    public Movie(String title, MovieStatus status, String synopsis, String director, ArrayList<String> cast, Ratings rating, AgeRating ageRating, Reviews review, MovieType movieType, int duration) {
        this.title = title;
        this.status = status;
        this.synopsis = synopsis;
        this.director = director;
        this.cast = cast;
        this.rating = rating;
        this.ageRating = ageRating;
        this.sales = 0;
        this.review = review;
        this.movieType = movieType;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSales() {
        return this.sales;
    }

    public void addSales(int sales) {
        this.sales += sales;
    }

    public Ratings getRating() {
        return rating;
    }

    public void setRating(Ratings rating) {
        this.rating = rating;
    }

    public Reviews getReview() {
        return review;
    }

    public void setReview(Reviews review) {
        this.review = review;
    }

    public MovieStatus getStatus() {
        return status;
    }

    public void setStatus(MovieStatus status) {
        this.status = status;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public ArrayList<String> getCast() {
        return cast;
    }

    public void setCast(ArrayList<String> cast) {
        this.cast = cast;
    }

    public String getAgeRating() {
        return String.valueOf(ageRating);
    }

    public void setAgeRating(AgeRating ageRating) {
        this.ageRating = ageRating;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", status=" + status +
                ", synopsis='" + synopsis + '\'' +
                ", director='" + director + '\'' +
                ", cast=" + cast +
                ", rating=" + rating +
                ", ageRating=" + ageRating +
                ", sales=" + sales +
                ", review=" + review +
                ", movieType=" + movieType +
                '}';
    }
}