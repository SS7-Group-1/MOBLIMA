package MOBLIMA;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Movie Class.
 * @author SS7 Group 1
 * @version 1.0
 * @since 2022-11-07
 */
public class Movie implements Serializable {
    /**
     * Title of movie.
     */
    private String title;
    /**
     * Movie status.
     */
    private MovieStatus status;
    /**
     * Movie synopsis.
     */
    private String synopsis;
    /**
     * Director of movie.
     */
    private String director;
    /**
     * Names of cast. At least two casts.
     */
    private ArrayList<String> cast;
    /**
     * Movie Ratings.
     */
    private Rating rating;
    /**
     * Age rating of movie.
     */
    private AgeRating ageRating;
    /**
     * Number of ticket sales for movie.
     */
    private int sales;
    /**
     * Movie Reviews.
     */
    private Review review;
    /**
     * Movie type.
     */
    private MovieType movieType;
    /**
     * Duration of movie.
     */
    private int duration;

    /**
     * Default Constructor
     */
    public Movie() {
        this.sales = 0;
        this.rating = new Rating();
        this.review = new Review();
    }

    /**
     * Creates a new Movie class with attributes initialised.
     * @param title This movie title.
     * @param status This movie status.
     * @param synopsis This movie's synopsis.
     * @param director This movie's director.
     * @param cast This movie's casts.
     * @param rating This movie's ratings.
     * @param ageRating This movie's age rating.
     * @param review This movie's reviews.
     * @param movieType This movie's movie type.
     * @param duration This movie's duration.
     */
    public Movie(String title, MovieStatus status, String synopsis, String director, ArrayList<String> cast, Rating rating, AgeRating ageRating, Review review, MovieType movieType, int duration) {
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

    /**
     * Accessor for movie title.
     * @return String: Movie title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Mutator for movie title.
     * @param title Movie title to be changed to.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Accessor for movie sales.
     * @return int: Number of sales for movie tickets.
     */
    public int getSales() {
        return this.sales;
    }

    /**
     * Increase sales for this movie.
     * @param sales Amount to increase movie sales.
     */
    public void addSales(int sales) {
        this.sales += sales;
    }

    /**
     * Accessor for movie rating.
     * @return Rating: Movie's ratings.
     */
    public Rating getRating() {
        return rating;
    }

    /**
     * Mutator for movie ratings.
     * @param rating Ratings for movie to be changed to.
     */
    public void setRating(Rating rating) {
        this.rating = rating;
    }

    /**
     * Accessor for movie reviews.
     * @return Review: Movie reviews.
     */
    public Review getReview() {
        return review;
    }

    /**
     * Mutator for movie reviews.
     * @param review Review for movie to be changed to.
     */
    public void setReview(Review review) {
        this.review = review;
    }

    /**
     * Accessor for movie status.
     * @return MovieStatus: Movie status.
     */
    public MovieStatus getStatus() {
        return status;
    }

    /**
     * Mutator for movie status.
     * @param status Movie status to be changed to.
     */
    public void setStatus(MovieStatus status) {
        this.status = status;
    }

    /**
     * Accessor for movie's director.
     * @return String: Movie's director.
     */
    public String getDirector() {
        return director;
    }

    /**
     * Mutator for movie's director.
     * @param director Movie's director to be changed to.
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Accessor for movie type.
     * @return MovieType: Movie type.
     */
    public MovieType getMovieType() {
        return movieType;
    }

    /**
     * Mutator for movie type.
     * @param movieType Movie type to be changed to.
     */
    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    /**
     * Accessor for movie's synopsis.
     * @return String: Movie's synopsis.
     */
    public String getSynopsis() {
        return synopsis;
    }

    /**
     * Mutator for movie's synopsis.
     * @param synopsis Movie's synopsis to be changed to.
     */
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    /**
     * Accessor for movie's casts.
     * @return ArrayList<String> :Movie's casts.
     */
    public ArrayList<String> getCast() {
        return cast;
    }

    /**
     * Print out all the casts for this movie.
     */
    public void printCasts(){
        for (String cast : cast) {
            System.out.println(" - " + cast);
        }
    }

    /**
     * Mutator for movie's casts.
     * @param cast Movie's casts to be changed to.
     */
    public void setCast(ArrayList<String> cast) {
        this.cast = cast;
    }

    /**
     * Accessor for movie's age rating.
     * @return String: Movie's age rating.
     */
    public String getAgeRating() {
        return String.valueOf(ageRating);
    }

    /**
     * Mutator for movie's age rating.
     * @param ageRating Movie's age rating to be changed to.
     */
    public void setAgeRating(AgeRating ageRating) {
        this.ageRating = ageRating;
    }

    /**
     * Mutator for movie's duration.
     * @param duration Movie's duration to be changed to.
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Accessor for movie's duration.
     * @return int: Movie's duration.
     */
    public int getDuration() {
        return duration;
    }
}