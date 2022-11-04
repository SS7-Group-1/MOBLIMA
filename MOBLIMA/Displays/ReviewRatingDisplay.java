package MOBLIMA.Displays;

import MOBLIMA.Movie;

import java.util.Scanner;

public class ReviewRatingDisplay {
    private Movie movie;

    public ReviewRatingDisplay(Movie movie) {
        this.movie = movie;
    }

    public void DisplayMenu() {
        int choice = 0;
        while (choice != 5) {
            System.out.println("*".repeat(40));
            System.out.println("[1] View reviews");
            System.out.println("[2] Add a review");
            System.out.println("[3] View ratings");
            System.out.println("[4] Add a rating");
            System.out.println("[5] Exit");
            System.out.print("Enter selection: ");

            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("*".repeat(40));
                    System.out.println("Reviews for " + movie.getTitle());
                    movie.getReview().printReview();
                    break;
                case 2:
                    System.out.println("*".repeat(40));
                    System.out.println("Enter review to add:");
                    sc.skip("\\R?");
                    String review = sc.nextLine();
                    movie.getReview().addReview(review);
                    break;
                case 3:
                    System.out.println("*".repeat(40));
                    System.out.println("Ratings for " + movie.getTitle());
                    movie.getRating().printRatings();
                    break;
                case 4:
                    System.out.println("*".repeat(40));
                    System.out.println("Enter review to add (0-5):");
                    Float rating = sc.nextFloat();
                    movie.getRating().addRating(rating);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}