package MOBLIMA.App;

import MOBLIMA.Cinema;
import MOBLIMA.Movie;
import MOBLIMA.ShowTime;
import MOBLIMA.User;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("▭".repeat(40));
        System.out.println("Welcome to Platinum Village");
        System.out.println("the leading movie theatre in Singapore");

        while(true){
            System.out.println("▭".repeat(40));
            System.out.println("[1] Login");
            System.out.println("[2] Register");
            System.out.println("[3] Continue as guest");
            System.out.println("[4] Exit MOBLIMA");
            System.out.print("Enter option: ");

            switch (sc.nextInt()) {
                case 1 -> { // LOGIN
                    Account account = new Account();
                    User user = account.login();

                    if(user != null){
                        Account.UserDetail.user = user;
                        displayMenu();
                    }
                }
                case 2 -> { // REGISTER
                    Account account = new Account();
                    User user = account.register();
                    if(user != null){
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

    public static void displayMenu() {
        Scanner sc = new Scanner(System.in);

        int choice = -1;
        while(choice != 0){
            System.out.println("▭".repeat(40));
            System.out.println("│ Main Menu │");
            System.out.println("[1] Movies");
            System.out.println("[2] Showtimes");
            System.out.println("[3] Reviews and Ratings");

            if(Account.UserDetail.user != null){
                System.out.println("[4] Booking History");

                if(Account.UserDetail.user.isAdmin()){
                    System.out.println("[5] Manage Cinemas");
                    System.out.println("[6] Manage Movies");
                    System.out.println("[7] Manage Showtimes");
                    System.out.println("[N/A] Configure System Settings");
                }
            }
            System.out.println("[0] " + (Account.UserDetail.user == null ? "Return to main menu" : "Logout"));

            choice = sc.nextInt();
            switch (choice) {
                case 1 -> { // MOVIES LIST
                    int subChoice = -1;
                    while(subChoice != 0){
                        System.out.println("▭".repeat(40));
                        System.out.println("│ Movies │");
                        System.out.println("[1] View all movies");
                        System.out.println("[2] Search for movie");
                        System.out.println("[3] Show top 5 movies by rating");
                        System.out.println("[4] Show top 5 movies by sales");
                        System.out.println("[0] Go back");
                        System.out.print("Enter option: ");
                        subChoice = sc.nextInt();
                        switch (subChoice){
                            case 0 -> {}
                            case 1 -> { // VIEW ALL MOVIES
                                Movies movies = new Movies();
                                movies.displayMovies();
                            }
                            case 2 -> { // SEARCH FOR MOVIE
                                Movies movies = new Movies();
                                movies.searchForMovie();
                            }
                            case 3 -> { // SHOW TOP 5 MOVIES BY RATING
                                Movies movies = new Movies();
                                movies.displayTop5rating();
                            }
                            case 4 -> { // SHOW TOP 5 MOVIES BY SALES
                                Movies movies = new Movies();
                                movies.displayTop5sales();
                            }
                            default -> System.out.println("Invalid option. Please try again.");
                        }
                    }
                }
                case 2 -> { // VIEW SHOWTIMES
                    int subChoice = -1;
                    while(subChoice != 0){
                        System.out.println("▭".repeat(40));
                        System.out.println("│ Showtimes │");
                        System.out.println("[1] View all showtimes");
                        System.out.println("[2] Search for showtimes by movie");
                        //System.out.println("[3] Search for showtimes by cinema");
                        System.out.println("[0] Go back");
                        System.out.print("Enter option: ");
                        subChoice = sc.nextInt();
                        switch (subChoice){
                            case 0 -> {}
                            case 1 -> { // VIEW ALL SHOWTIMES
                                ShowTimes showtimes = new ShowTimes();
                                showtimes.displayShowtimes();
                            }
                            case 2 -> { // SEARCH FOR SHOWTIMES BY MOVIE
                                Movies movies = new Movies();
                                Movie movie = movies.selectMovie();
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
                    Movies movies = new Movies();
                    Movie movie = movies.selectMovie();
                    if(movie != null){
                        int subChoice = -1;
                        while(subChoice != 0){
                            System.out.println("▭".repeat(40));
                            System.out.println("│ Reviews & Ratings │");
                            System.out.println("[1] View reviews");
                            System.out.println("[2] Add new review");
                            System.out.println("[3] View ratings");
                            System.out.println("[4] Add new rating");
                            System.out.println("[0] Go back");
                            System.out.print("Enter option: ");
                            subChoice = sc.nextInt();
                            switch (subChoice){
                                case 0 -> {}
                                case 1 -> { // VIEW REVIEWS
                                    ReviewRatings reviewRatings = new ReviewRatings();
                                    reviewRatings.viewReviews(movie);
                                }
                                case 2 -> { // ADD NEW REVIEW
                                    ReviewRatings reviewRatings = new ReviewRatings();
                                    reviewRatings.addReview(movie);
                                }
                                case 3 -> { // VIEW RATINGS
                                    ReviewRatings reviewRatings = new ReviewRatings();
                                    reviewRatings.viewRatings(movie);
                                }
                                case 4 -> { // ADD NEW RATING
                                    ReviewRatings reviewRatings = new ReviewRatings();
                                    reviewRatings.addRating(movie);
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
                case 5 -> { // MANAGE CINEMAS
                    int subChoice = -1;
                    while(subChoice != 0){
                        System.out.println("▭".repeat(40));
                        System.out.println("│ Manage Cinemas │");
                        System.out.println("[1] View all cinemas");
                        System.out.println("[2] Add new cinema");
                        System.out.println("[3] Update cinema details");
                        System.out.println("[4] Remove cinema");
                        System.out.println("[0] Go back");
                        System.out.print("Enter option: ");
                        subChoice = sc.nextInt();
                        switch (subChoice){
                            case 0 -> {}
                            case 1 -> { // VIEW ALL CINEMAS
                                Cinemas cinemas = new Cinemas();
                                cinemas.displayCinemas();
                            }
                            case 2 -> { // ADD NEW CINEMA
                                Cinemas cinemas = new Cinemas();
                                cinemas.addCinema();
                            }
                            case 3 -> { // UPDATE CINEMA DETAILS
                                Cinemas cinemas = new Cinemas();
                                Cinema cinema = cinemas.selectCinema();
                                cinemas.updateCinema(cinema);
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
                case 6 -> { // MANAGE MOVIES
                    int subChoice = -1;
                    while(subChoice != 0){
                        System.out.println("▭".repeat(40));
                        System.out.println("│ Manage Movies │");
                        System.out.println("[1] Add new movie");
                        System.out.println("[2] Update existing movie");
                        System.out.println("[3] Remove movie");
                        System.out.println("[0] Go back");
                        System.out.print("Enter option: ");
                        subChoice = sc.nextInt();
                        switch (subChoice){
                            case 0 -> {}
                            case 1 -> { // ADD NEW MOVIE
                                Movies movies = new Movies();
                                movies.addMovie();
                            }
                            case 2 -> { // UPDATE EXISTING MOVIE
                                Movies movies = new Movies();
                                Movie movie = movies.selectMovie();
                                movies.updateMovie(movie);
                            }
                            case 3 -> { // REMOVE MOVIE
                                Movies movies = new Movies();
                                Movie movie = movies.selectMovie();
                                movies.removeMovie(movie);
                            }
                            default -> System.out.println("Invalid option. Please try again.");
                        }
                    }
                }
                case 7 -> { // MANAGE SHOWTIMES
                    int subChoice = -1;
                    while(subChoice != 0){
                        System.out.println("▭".repeat(40));
                        System.out.println("│ Manage Showtimes │");
                        System.out.println("[1] Add new showtime");
                        System.out.println("[2] Update existing showtime");
                        System.out.println("[3] Remove showtime");
                        System.out.println("[0] Go back");
                        System.out.print("Enter option: ");
                        subChoice = sc.nextInt();
                        switch (subChoice){
                            case 0 -> {}
                            case 1 -> { // ADD NEW SHOWTIME
                                ShowTimes showTimes = new ShowTimes();
                                Movies movies = new Movies();
                                Movie movie = movies.selectMovie();
                                showTimes.addShowtime(movie);
                            }
                            case 2 -> { // UPDATE EXISTING SHOWTIME
                                ShowTimes showTimes = new ShowTimes();
                                ShowTime showTime = showTimes.selectShowTime();
                                showTimes.updateShowtime(showTime);
                            }
                            case 3 -> { // REMOVE SHOWTIME
                                ShowTimes showTimes = new ShowTimes();
                                ShowTime showTime = showTimes.selectShowTime();
                                showTimes.removeFromShowtimeFile(showTime);
                            }
                            default -> System.out.println("Invalid option. Please try again.");
                        }
                    }
                }
                case 8 -> { // CONFIGURE SYSTEM SETTINGS
                    SystemSetting test = new SystemSetting();
                    test.DisplayMenu();
                }
                default -> System.out.println("Invalid option");
            }
        }
    }
}