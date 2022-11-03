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
            System.out.println("1.Get All Reviews");
            System.out.println("2.Get All Ratings");
            System.out.println("3.Add Reviews");
            System.out.println("4.Add Ratings");
            System.out.println("5.Exit");
            Scanner sc = new Scanner(System.in);

            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    movie.getReview().printReview();
                    break;
                case 2:
                    movie.getRating().printRatings();

                    break;
                case 3:
                    System.out.println("Type Review to add");
                    sc.skip("\\R?");
                    String review = sc.nextLine();
                    movie.getReview().addReview(review);
                    break;
                case 4:
                    System.out.println("Type Rating to add");
                    Float rating = sc.nextFloat();
                    movie.getRating().addRating(rating);
                    break;


            }


        }


    }

}