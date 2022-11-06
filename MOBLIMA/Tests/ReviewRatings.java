package MOBLIMA.Tests;

import MOBLIMA.Movie;

import java.util.Scanner;

public class ReviewRatings {
    Scanner sc = new Scanner(System.in);
    public void viewReviews(Movie movie){
        System.out.println("*".repeat(40));
        System.out.println("Reviews for " + movie.getTitle());
        movie.getReview().printReviews();
    }
    public void addReview(Movie movie){
        System.out.println("*".repeat(40));
        System.out.println("Enter review to add:");
        sc.skip("\\R?");
        String review = sc.nextLine();
        movie.getReview().addReview(review);
    }
    public void viewRatings(Movie movie){
        System.out.println("*".repeat(40));
        System.out.println("Ratings for " + movie.getTitle());
        movie.getRating().printRatings();
    }
    public void addRating(Movie movie){
        System.out.println("*".repeat(40));
        System.out.println("Enter review to add (0-5):");
        Float rating = sc.nextFloat();
        movie.getRating().addRating(rating);
    }
}
