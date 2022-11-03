package MOBLIMA;

import java.io.Serializable;
import java.lang.reflect.Array;
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


    public Movie(){
        this.sales =0;
    }

    public Movie(String titl, MovieStatus stat, String synopsis, String director, ArrayList<String>cast, Ratings rating, AgeRating ageRating,Reviews review,MovieType movieType, int sales) {
        this.title=titl;
        this.status=stat;
        this.synopsis=synopsis;
        this.director=director;
        this.cast = cast;
        this.rating=rating;
        this.ageRating=ageRating;
        this.sales=sales;
        this.review=review;
        this.movieType=movieType;

    }

    public String getTitle() {
        return title;
    }
    public void addSales(){
        this.sales= this.sales+1;
    }
    public int getSales(){
        return this.sales;
    }

    public Ratings getRating() {
        return rating;
    }
    public Reviews getReview(){
        return review;
    }

    public MovieStatus getStatus() {
        return status;
    }
    public String getDirector(){
        return director;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public String getSynopsis() {
        return synopsis;
    }
    public ArrayList<String> getcast(){
        return cast;
    }
    public String getAgeRating(){
        return String.valueOf(ageRating);
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