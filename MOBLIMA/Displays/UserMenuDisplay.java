package MOBLIMA.Displays;

import MOBLIMA.Cinema;
import MOBLIMA.FileHelper;
import MOBLIMA.Movie;
import MOBLIMA.ShowTime;

import java.util.ArrayList;
import java.util.Scanner;

public class UserMenuDisplay {
    public UserMenuDisplay(){

    }

    public void displayMenu(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Cinema> cinema_list = (ArrayList<Cinema>) FileHelper.read("data/cinema.dat");
        ArrayList<Movie> movie_list = (ArrayList<Movie>) FileHelper.read("data/movie.dat");
        ArrayList<ShowTime> showtime_list = (ArrayList<ShowTime>) FileHelper.read("data/showtime.dat");


        int choice = 0;

        while (choice != 5){
            System.out.println("*".repeat(40));
            System.out.println("Guest Display Menu");
            System.out.println("*".repeat(40));
            System.out.println("[1] Movies");
            System.out.println("[2] Show Times");
            System.out.println("[3] Reviews and Ratings");
            System.out.println("[4] Booking");
            System.out.println("[5] Exit");

            choice = sc.nextInt();
            switch(choice){
                case 1:
                    MovieListingDisplay movieListing = new MovieListingDisplay(movie_list);
                    movieListing.DisplayMenu();
                    break;
                case 2:
                    System.out.println("WIP");
                    break;
                case 3:
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
                    break;
                case 4:
                    BookingDisplay bookingDisplay = new BookingDisplay(showtime_list);
                    bookingDisplay.DisplayMenu();
                    break;
            }
        }
    }
}
