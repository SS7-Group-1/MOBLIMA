package MOBLIMA.Displays;

import MOBLIMA.Cinema;
import MOBLIMA.FileHelper;
import MOBLIMA.Movie;
import MOBLIMA.ShowTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StaffMenuDisplay {
    public StaffMenuDisplay(){

    }

    public static void displayMenu(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Cinema> cinema_list = (ArrayList<Cinema>) FileHelper.read("data/cinema.dat");
        ArrayList<Movie> movie_list = (ArrayList<Movie>) FileHelper.read("data/movie.dat");
        ArrayList<ShowTime> showtime_list = (ArrayList<ShowTime>) FileHelper.read("data/showtime.dat");


        System.out.println("*".repeat(40));
        System.out.println("Staff Display Menu");

        int choice = 0;

        while (choice != 5){
            System.out.println("[1] Manage Cinemas");
            System.out.println("[2] Manage Movies");
            System.out.println("[3] Manage Showtimes");
            System.out.println("[4] Configure System Settings");
            System.out.println("[5] Exit");
            System.out.print("Enter selection: ");

            choice = sc.nextInt();
            switch(choice){
                case 1:
                    CinemaStaffDisplay manageCinemaDisplay = new CinemaStaffDisplay(cinema_list);
                    manageCinemaDisplay.DisplayMenu();
                    break;
                case 2:
                    MovieListingStaff manageMovieDisplay = new MovieListingStaff(movie_list);
                    try {
                        manageMovieDisplay.DisplayMenu();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 3:
                    ShowTimeStaff manageShowtimeDisplay = new ShowTimeStaff(cinema_list, movie_list, showtime_list);
                    manageShowtimeDisplay.DisplayMenu();
                    break;
                case 4:
                    SystemSettingStaff sam = new SystemSettingStaff();
                    sam.DisplayMenu();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

}
