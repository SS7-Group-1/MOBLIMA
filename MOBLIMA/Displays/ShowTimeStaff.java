package MOBLIMA.Displays;

import MOBLIMA.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class ShowTimeStaff {
    private ArrayList<ShowTime> showtime_list;
    private ArrayList<Movie> movie_list;
    private ArrayList<Cinema> cinema_list;

    public ShowTimeStaff() {
    }

    public void DisplayMenu() {
        movie_list = (ArrayList<Movie>) FileHelper.read("data/movie.dat");
        cinema_list = (ArrayList<Cinema>) FileHelper.read("data/cinema.dat");
        showtime_list = (ArrayList<ShowTime>) FileHelper.read("data/showtime.dat");

        System.out.println("*".repeat(40));
        System.out.println("Showtime management staff menu");
        int choice = 0;
        while (choice != 69) {
            System.out.println("*".repeat(40));
            //System.out.println("[1] View all showtime");
            System.out.println("[2] Manage movie showtime");
            //System.out.println("[3] Manage cinema showtime");
            System.out.println("[69] Exit");
            System.out.print("Enter option: ");

            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            switch (choice) {
                case 2:
                    // list all movies
                    System.out.println("*".repeat(40));
                    System.out.println("Select movie to manage showtime");
                    // loop movie_list
                    for (int i = 0; i < movie_list.size(); i++) {
                        System.out.println(" [" + (i + 1) + "] " + movie_list.get(i).getTitle());
                    }
                    System.out.print("Select movie: ");
                    int add_choice;
                    while (true) {
                        add_choice = sc.nextInt();
                        if (add_choice < 0 || add_choice > movie_list.size() + 1) {
                            System.out.println("Invalid option. Please try again.");
                        } else {
                            ManageMovieShowtime(movie_list.get(add_choice - 1));
                            break;
                        }
                    }
                    break;
            }
        }
    }

    public void ManageMovieShowtime(Movie movie) {
        System.out.println("*".repeat(40));
        System.out.println("Manage showtimes for " + movie.getTitle());
        int choice = 0;
        while (choice != 5) {
            System.out.println("*".repeat(40));
            System.out.println("[1] View all showtime");
            System.out.println("[2] Add showtime");
            System.out.println("[3] Update showtime");
            System.out.println("[4] Remove showtime");
            System.out.println("[5] Exit");
            System.out.print("Enter option: ");

            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            switch (choice) {
                case 1: // View all showtime
                    System.out.println("*".repeat(40));
                    System.out.println("List of showtimes for " + movie.getTitle());
                    int total_showtimes = 0;
                    for (ShowTime showtime : showtime_list) {
                        if (showtime.getMovie().getTitle().equals(movie.getTitle())) {
                            System.out.println(showtime);
                            total_showtimes++;
                        }
                    }
                    if (total_showtimes == 0) {
                        System.out.println("No showtimes found for this movie.");
                    }
                    break;
                case 2: // Add new showtime
                    System.out.println("*".repeat(40));
                    System.out.println("Adding new showtime for " + movie.getTitle());
                    ShowTime showTime = new ShowTime(movie);

                    // set cinema
                    System.out.println("*".repeat(40));
                    System.out.println("Cinema List");
                    for (int i = 0; i < cinema_list.size(); i++) {
                        System.out.println(" [" + (i + 1) + "] " + cinema_list.get(i));
                    }
                    System.out.print("Select cinema: ");
                    int add_choice;
                    while (true) {
                        add_choice = sc.nextInt();
                        if (add_choice < 0 || add_choice > cinema_list.size() + 1) {
                            System.out.println("Invalid option. Please try again.");
                        } else {
                            showTime.setCinema(cinema_list.get(add_choice - 1));
                            break;
                        }
                    }

                    System.out.println("*".repeat(40));
                    System.out.println("Enter movie date (DD/MM/YYYY): ");
                    sc.skip("\\R?");
                    while(true){
                        try {
                            String date = sc.nextLine();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                            LocalDate localDate = LocalDate.parse(date, formatter);
                            showTime.setDate(localDate);
                            break;
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date. Please try again.");
                        }
                    }

                    System.out.println("*".repeat(40));
                    System.out.println("Enter movie start time (HH:mm): ");
                    sc.skip("\\R?");
                    while(true){
                        try {
                            String time = sc.nextLine();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                            LocalTime localTime = LocalTime.parse(time, formatter);
                            showTime.setTime(localTime);
                            break;
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid time. Please try again.");
                        }
                    }
                    System.out.println("*".repeat(40));
                    System.out.println("Enter movie duration (in minutes): ");
                    int duration = sc.nextInt();
                    showTime.setDuration(duration);

                    //print
                    System.out.println(showTime);
                    addShowTime(showTime);
                    break;
                case 3: // Update showtime
                    break;
                case 4: // Remove showtime

                    break;
            }
        }
    }

    private void addShowTime(ShowTime showTime){
        showtime_list.add(showTime);
        FileHelper.write(showtime_list, "data/showtime.dat");
    }
}
