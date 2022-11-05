package MOBLIMA.Tests;

import MOBLIMA.Cinema;
import MOBLIMA.Displays.*;
import MOBLIMA.FileHelper;
import MOBLIMA.Movie;
import MOBLIMA.ShowTime;

import java.util.ArrayList;
import java.util.Scanner;

public class _oldMenu {

    public static void main(String args[]){

        ArrayList<Cinema> cinema_list = (ArrayList<Cinema>) FileHelper.read("data/cinema.dat");
        ArrayList<Movie> movie_list = (ArrayList<Movie>) FileHelper.read("data/movie.dat");
        ArrayList<ShowTime> showtime_list = (ArrayList<ShowTime>) FileHelper.read("data/showtime.dat");

        System.out.println("Sup welcome to cinema TEMPERORY pagez");


        System.out.println("*".repeat(40));
        System.out.println("Select user type");
        System.out.println("[1] User");
        System.out.println("[2] Staff");
        
        // TODO: IMPLEMENT STAFF / USER DIFFERENT MENUS

        System.out.println("*".repeat(40));
        System.out.println("Select option");
        System.out.println("[1] MovieListing");
        System.out.println("[2] ReviewRating");
        System.out.println("[3] Booking");
        System.out.println("[4] Manage Cinema (Staff)");
        System.out.println("[5] Manage Movie (Staff)");
        System.out.println("[6] Manage Showtime (Staff)");
        System.out.print("Enter selection: ");
        Scanner sc = new Scanner(System.in);
        while(true){
            switch (sc.nextInt()){
                case 1:
                    MovieListingDisplay movieListing = new MovieListingDisplay(movie_list);
                    movieListing.DisplayMenu();
                    break;
                case 2:
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
                case 3:
                    BookingDisplay bookingDisplay = new BookingDisplay(showtime_list);
                    bookingDisplay.DisplayMenu();
                    break;
                case 4:
                    CinemaStaffDisplay manageCinemaDisplay = new CinemaStaffDisplay(cinema_list);
                    manageCinemaDisplay.DisplayMenu();
                    break;
                case 5:
                    MovieListingStaff manageMovieDisplay = new MovieListingStaff(movie_list);
                    //manageMovieDisplay.DisplayMenu();
                    break;
                case 6:
                    ShowTimeStaff manageShowtimeDisplay = new ShowTimeStaff(cinema_list, movie_list, showtime_list);
                    manageShowtimeDisplay.DisplayMenu();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
