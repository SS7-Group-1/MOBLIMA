package MOBLIMA.App;

import MOBLIMA.Cinema;
import MOBLIMA.Movie;
import MOBLIMA.ShowTime;
import MOBLIMA.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class that runs the main programme
 *
 * @author Lim Jia Wei
 * @version 1.7
 * @since 2022-11-07
 */
public class Main {
    /**
     * main method
     * @param args arguments that are being passed in
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=".repeat(40));
        System.out.println("Welcome to Platinum Village");
        System.out.println("the leading movie theatre in Singapore");

        while (true) {
            System.out.println("=".repeat(40));
            System.out.println("[1] Login");
            System.out.println("[2] Register");
            System.out.println("[3] Continue as guest");
            System.out.println("[4] Exit MOBLIMA");
            System.out.print("Enter option: ");

            switch (sc.nextInt()) {
                case 1 -> { // LOGIN
                    Account account = new Account();
                    User user = account.login();

                    if (user != null) {
                        Account.UserDetail.user = user;
                        displayMenu();
                    }
                }
                case 2 -> { // REGISTER
                    Account account = new Account();
                    User user = account.register();
                    if (user != null) {
                        Account.UserDetail.user = user;
                        displayMenu();
                    }
                }
                case 3 -> { // GUEST
                    Account.UserDetail.user = null;
                    displayMenu();
                }
                case 4 -> {
                    System.out.println("Exiting application");
                    System.out.println("Thanks for using MOBLIMA!");
                    System.exit(69);
                }
                default -> System.out.println("Invalid option");
            }
        }
    }

    /**
     * Function that show the Main Display Menu for users to interact with
     */
    public static void displayMenu() {
        Scanner sc = new Scanner(System.in);

        int choice = -1;
        while (choice != 0) {
            System.out.println("=".repeat(40));
            System.out.println("│ Main Menu │");
            System.out.println("[1] Movies");
            System.out.println("[2] Showtimes");
            System.out.println("[3] Reviews and Ratings");

            if (Account.UserDetail.user != null) {
                System.out.println("[4] Booking History");
                System.out.println("[5] Update account details");

                if (Account.UserDetail.user.isAdmin()) {
                    System.out.println("[6] Manage Cinemas");
                    System.out.println("[7] Manage Movies");
                    System.out.println("[8] Manage Showtimes");
                    System.out.println("[9] Configure System Settings");
                }
            }
            System.out.println("[0] " + (Account.UserDetail.user == null ? "Return to main menu" : "Logout"));

            choice = sc.nextInt();
            switch (choice) {
                case 0 -> {}
                case 1 -> { // MOVIES LIST
                    int subChoice = -1;
                    while (subChoice != 0) {
                        System.out.println("=".repeat(40));
                        System.out.println("│ Movies │");
                        System.out.println("[1] View all movies");
                        System.out.println("[2] Search for movie");
                        BufferedReader br = null;
                        try {
                            br = new BufferedReader(new FileReader("data/UserPermission.txt"));
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        boolean topRatingsAllowed = false;
                        boolean topSalesAllowed = false;
                        String permission;
                        try {
                            permission = br.readLine();
                            if (permission.equals("1") || permission.equals("0")) {
                                System.out.println("[3] Show top 5 movies by rating");
                                topRatingsAllowed = true;
                            }
                            if (permission.equals("2") || permission.equals("0")) {
                                System.out.println("[4] Show top 5 movies by sales");
                                topSalesAllowed = true;
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("[0] Go back");
                        System.out.print("Enter option: ");
                        subChoice = sc.nextInt();
                        switch (subChoice) {
                            case 0 -> {
                            }
                            case 1 -> { // VIEW ALL MOVIES
                                Movies movies = new Movies();
                                if (Account.UserDetail.user != null && Account.UserDetail.user.isAdmin()){
                                    movies.displayMovies(1);
                                }
                                else{
                                    movies.displayMovies(0);
                                }
                            }
                            case 2 -> { // SEARCH FOR MOVIE
                                Movies movies = new Movies();
                                movies.searchForMovie();
                            }
                            case 3 -> { // SHOW TOP 5 MOVIES BY RATING
                                if (topRatingsAllowed) {
                                    Movies movies = new Movies();
                                    movies.displayTop5rating();
                                } else {
                                    System.out.println("You do not have permission to view this");
                                }
                            }
                            case 4 -> { // SHOW TOP 5 MOVIES BY SALES
                                if (topSalesAllowed) {
                                    Movies movies = new Movies();
                                    movies.displayTop5sales();
                                } else {
                                    System.out.println("You do not have permission to view this");
                                }
                            }
                            default -> System.out.println("Invalid option. Please try again.");
                        }
                    }
                }
                case 2 -> { // VIEW SHOWTIMES
                    int subChoice = -1;
                    while (subChoice != 0) {
                        System.out.println("=".repeat(40));
                        System.out.println("│ Showtimes │");
                        System.out.println("[1] View all showtimes");
                        System.out.println("[2] Search for showtimes by movie");
                        //System.out.println("[3] Search for showtimes by cinema");
                        System.out.println("[0] Go back");
                        System.out.print("Enter option: ");
                        subChoice = sc.nextInt();
                        switch (subChoice) {
                            case 0 -> {
                            }
                            case 1 -> { // VIEW ALL SHOWTIMES
                                ShowTimes showtimes = new ShowTimes();
                                showtimes.displayMenu();
                            }
                            case 2 -> { // SEARCH FOR SHOWTIMES BY MOVIE
                                Movies movies = new Movies();
                                Movie movie = movies.selectMovie(false);
                                ShowTimes showtimes = new ShowTimes();
                                showtimes.displayShowtimesByMovie(movie);
                            }
//                            case 3 -> { // SEARCH FOR SHOWTIMES BY CINEMA
//                                Cinemas cinemas = new Cinemas();
//                                Cinema cinema = cinemas.selectCinema();
//                                ShowTimes showtimes = new ShowTimes();
//                                showtimes.displayShowtimesByCinema(cinema);
//                            }
                            default -> System.out.println("Invalid option. Please try again.");
                        }
                    }
                }
                case 3 -> { // REVIEW AND RATING
                    int subChoice = -1;
                    while (subChoice != 0) {
                        Movies movies = new Movies();
                        Movie movie = movies.selectMovie(false);
                        if (movie != null) {
                            System.out.println("=".repeat(40));
                            System.out.println("│ Reviews & Ratings │");
                            System.out.println("[1] View reviews");
                            System.out.println("[2] Add new review");
                            System.out.println("[3] View ratings");
                            System.out.println("[4] Add new rating");
                            System.out.println("[0] Go back");
                            System.out.print("Enter option: ");
                            subChoice = sc.nextInt();
                            switch (subChoice) {
                                case 0 -> {
                                }
                                case 1 -> { // VIEW REVIEWS
                                    movies.viewReviews(movie);
                                }
                                case 2 -> { // ADD NEW REVIEW
                                    movies.addReview(movie);
                                }
                                case 3 -> { // VIEW RATINGS
                                    movies.viewRatings(movie);
                                }
                                case 4 -> { // ADD NEW RATING
                                    movies.addRating(movie);
                                }
                                default -> System.out.println("Invalid option. Please try again.");
                            }
                        }
                    }
                }
                case 4 -> { // BOOKING
                    Bookings bookings = new Bookings();
                    bookings.viewRecords();
                }
                case 5->{
                    Account account = new Account();
                    account.updateAccount();
                }
                case 6 -> { // MANAGE CINEMAS
                    int subChoice = -1;
                    while (subChoice != 0) {
                        System.out.println("=".repeat(40));
                        System.out.println("│ Manage Cinemas │");
                        System.out.println("[1] View all cinemas");
                        System.out.println("[2] Add new cinema");
                        System.out.println("[3] Update cinema details");
                        System.out.println("[4] Remove cinema");
                        System.out.println("[0] Go back");
                        System.out.print("Enter option: ");
                        subChoice = sc.nextInt();
                        switch (subChoice) {
                            case 0 -> {
                            }
                            case 1 -> { // VIEW ALL CINEMAS
                                Cinemas cinemas = new Cinemas();
                                cinemas.displayMenu();
                            }
                            case 2 -> { // ADD NEW CINEMA
                                Cinemas cinemas = new Cinemas();
                                cinemas.add();
                            }
                            case 3 -> { // UPDATE CINEMA DETAILS
                                Cinemas cinemas = new Cinemas();
                                System.out.println("*".repeat(40));
                                System.out.println("[1] Update cinema detail");
                                System.out.println("[2] Update seating layout");
                                switch (sc.nextInt()) {
                                    case 1 -> {
                                        Cinema cinema = cinemas.selectCinema();
                                        cinemas.update(cinema);
                                    }
                                    case 2 -> {
                                        Cinema cinema = cinemas.selectCinema();
                                        cinemas.updateCinemaSeating(cinema);
                                    }
                                    default -> {
                                        System.out.println("Invalid option. Please try again.");
                                    }
                                }
                            }
                            case 4 -> { // REMOVE CINEMA
                                Cinemas cinemas = new Cinemas();
                                Cinema cinema = cinemas.selectCinema();
                                cinemas.removeCinema(cinema);
                            }
                            default -> System.out.println("Invalid option. Please try again.");
                        }
                    }
                }
                case 7 -> { // MANAGE MOVIES
                    int subChoice = -1;
                    while (subChoice != 0) {
                        System.out.println("=".repeat(40));
                        System.out.println("│ Manage Movies │");
                        System.out.println("[1] Add new movie");
                        System.out.println("[2] Update existing movie");
                        System.out.println("[3] Remove movie");
                        System.out.println("[0] Go back");
                        System.out.print("Enter option: ");
                        subChoice = sc.nextInt();
                        switch (subChoice) {
                            case 0 -> {
                            }
                            case 1 -> { // ADD NEW MOVIE
                                Movies movies = new Movies();
                                movies.addMovie();
                            }
                            case 2 -> { // UPDATE EXISTING MOVIE
                                Movies movies = new Movies();
                                Movie movie = movies.selectMovie(true);
                                movies.updateMovie(movie);
                            }
                            case 3 -> { // REMOVE MOVIE
                                Movies movies = new Movies();
                                Movie movie = movies.selectMovie(true);
                                movies.removeMovie(movie);
                            }
                            default -> System.out.println("Invalid option. Please try again.");
                        }
                    }
                }
                case 8 -> { // MANAGE SHOWTIMES
                    int subChoice = -1;
                    while (subChoice != 0) {
                        System.out.println("=".repeat(40));
                        System.out.println("│ Manage Showtimes │");
                        System.out.println("[1] Add new showtime");
                        System.out.println("[2] Update existing showtime");
                        System.out.println("[3] Remove showtime");
                        System.out.println("[0] Go back");
                        System.out.print("Enter option: ");
                        subChoice = sc.nextInt();
                        switch (subChoice) {
                            case 0 -> {
                            }
                            case 1 -> { // ADD NEW SHOWTIME
                                ShowTimes showTimes = new ShowTimes();
                                Movies movies = new Movies();
                                Movie movie = movies.selectMovie(true);
                                showTimes.addShowtime(movie);
                            }
                            case 2 -> { // UPDATE EXISTING SHOWTIME
                                ShowTimes showTimes = new ShowTimes();
                                ShowTime showTime = showTimes.selectShowTime(false);
                                if (showTime != null){
                                    showTimes.updateShowtime(showTime);
                                }

                            }
                            case 3 -> { // REMOVE SHOWTIME
                                ShowTimes showTimes = new ShowTimes();
                                ShowTime showTime = showTimes.selectShowTime(false);
                                if (showTime != null)
                                {
                                    showTimes.removeFromShowtimeFile(showTime);
                                }
                            }
                            default -> System.out.println("Invalid option. Please try again.");
                        }
                    }
                }
                case 9 -> { // CONFIGURE SYSTEM SETTINGS
                    SystemSetting test = new SystemSetting();
                    test.displayMenu();
                }
                default -> System.out.println("Invalid option");
            }
        }
    }
}