package MOBLIMA.App;

import MOBLIMA.Movie;

import java.util.Scanner;

/**
 * Class that handles the ratings for Movies
 * @since 2022-11-07
 * @author Lim Jia Wei
 * @version 1.2
 */
public class ReviewRatings {
    Scanner sc = new Scanner(System.in);

    /**
     * Function that allows the viewing of Reviews of a movie
     * @param movie - movie object for its reviews to be viewed
     */
    public void viewReviews(Movie movie){
        System.out.println("▭".repeat(40));
        System.out.println("Reviews for " + movie.getTitle());
        movie.getReview().printReviews();
    }

    /**
     * Function that adds a review for a movie
     * @param movie - movie object to be added a review
     */
    public void addReview(Movie movie){
        System.out.println("▭".repeat(40));
        System.out.println("Enter review to add:");
        sc.skip("\\R?");
        String review = sc.nextLine();
        movie.getReview().addReview(review);
    }

    /**
     * Function that views the rating of a movie
     * @param movie - movie object for its rating to be viewed
     */
    public void viewRatings(Movie movie){
        System.out.println("▭".repeat(40));
        System.out.println("Ratings for " + movie.getTitle());
        movie.getRating().printRatings();
    }

    /**
     * Function that adds a rating for a movie
     * @param movie - movie object for its rating to be added
     */
    public void addRating(Movie movie){
        System.out.println("▭".repeat(40));
        System.out.println("Enter review to add (0-5):");
        Float rating = sc.nextFloat();
        movie.getRating().addRating(rating);
    }
}
