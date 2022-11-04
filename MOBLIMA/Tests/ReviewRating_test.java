package MOBLIMA.Tests;

import MOBLIMA.*;
import MOBLIMA.Displays.ReviewRatingDisplay;

import java.util.ArrayList;
import java.util.Scanner;

public class ReviewRating_test {
    public static void main(String[] args) {
        ArrayList<Movie> movie_list = (ArrayList<Movie>) FileHelper.read("data/movie.dat");
        Scanner sc = new Scanner(System.in);

        System.out.println("*".repeat(40));
        System.out.println("Select movie");
        for (int i = 0; i < movie_list.size(); i++) {
            System.out.println(" [" + (i + 1) + "] " + movie_list.get(i).getTitle());
        }
        System.out.println(" [" + (movie_list.size() + 1) + "] Exit");
        int add_choice;
        while (true) {
            System.out.print("Enter selection: ");
            add_choice = sc.nextInt();
            if (add_choice == (movie_list.size() + 1)) {
                break;
            } else if(add_choice < 0 || add_choice > movie_list.size()) {
                System.out.println("Invalid option. Please try again.");
            }else {
                ReviewRatingDisplay rwrd = new ReviewRatingDisplay(movie_list.get(add_choice-1));
                rwrd.DisplayMenu();
                break;
            }
        }
    }
}
