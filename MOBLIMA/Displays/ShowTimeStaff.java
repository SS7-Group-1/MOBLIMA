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
    Scanner sc = new Scanner(System.in);

    public ShowTimeStaff(ArrayList<Cinema> cinema_list, ArrayList<Movie> movie_list, ArrayList<ShowTime> showtime_list){
        this.cinema_list=cinema_list;
        this.movie_list=movie_list;
        this.showtime_list=showtime_list;
    }

    // ITS SHOWTIME!!!
    // https://i.kym-cdn.com/entries/icons/original/000/025/605/showtime.jpg

    public void DisplayMenu() {
        System.out.println("*".repeat(40));
        System.out.println("Showtime management staff menu");
        int choice = 0;
        while (choice != 69) {
            System.out.println("*".repeat(40));
            //System.out.println("[1] View showtime by movie");
            //System.out.println("[1] View showtime by cineplex");
            System.out.println("[2] Manage movie showtime");
            //System.out.println("[3] Manage cinema showtime");
            System.out.println("[69] Exit");
            System.out.print("Enter option: ");

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
                            showTime.setSeats(cinema_list.get(add_choice - 1).getSeatLayout().clone());
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

                    //print
                    System.out.println(showTime);
                    System.out.println("Showtime added successfully.");
                    addShowTime(showTime);
                    break;
                case 3: // Update showtime
                case 4: // Remove showtime
                    System.out.println("*".repeat(40));
                    System.out.println((choice == 3? "Update" : "Remove") + " showtime for " + movie.getTitle());

                    // set cinema
                    System.out.println("*".repeat(40));
                    System.out.println("Showtime List");
                    int showtime_count = 0;
                    for (int i = 0; i < showtime_list.size(); i++) {
                        if(showtime_list.get(i).getMovie().getTitle().equals(movie.getTitle())){
                            System.out.println(" [" + (i + 1) + "] " + showtime_list.get(i)); // will fix da index
                            showtime_count++;
                        }
                    }
                    if(showtime_count == 0){
                        System.out.println("No showtimes found for this movie.");
                        break;
                    }
                    System.out.print("Select showtime to " + (choice == 3 ? "update" : "remove") + ": ");
                    while (true) {
                        add_choice = sc.nextInt();
                        if (add_choice < 0 || add_choice > showtime_list.size() + 1) {
                            System.out.println("Invalid option. Please try again.");
                        } else {
                            if(choice == 3){
                                updateShowTime(showtime_list.get(add_choice - 1));
                            }
                            else{
                                removeShowTime(showtime_list.get(add_choice - 1));
                            }
                            System.out.println("Showtime removed.");
                            break;
                        }
                    }
                    break;
            }
        }
    }

    private void updateShowTime(ShowTime showTime){
        boolean edit = true;
        while(edit){
            System.out.println("*".repeat(40));
            System.out.println("Updating " + showTime);
            System.out.println("Select field to edit");
            System.out.println("[1] Cinema ");
            System.out.println("[2] Date ");
            System.out.println("[3] Time ");
            System.out.println("[4] Duration ");
            System.out.print("Enter option: ");
            switch (sc.nextInt()){
                case 1:
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
                    break;
                case 2:
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
                    break;
                case 3:
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
                    break;
                case 4:
                    System.out.println("*".repeat(40));
                    System.out.println("REMOVED");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    continue;
            }
            FileHelper.write(showtime_list, "data/showtime.dat");
            System.out.println("Showtime updated successfully");
            System.out.println("Update another field? (y/N): ");
            sc.skip("\\R?");
            if (!sc.nextLine().equalsIgnoreCase("y")) {
                edit = false;
            }
        }
    }

    private void addShowTime(ShowTime showTime){
        showtime_list.add(showTime);
        FileHelper.write(showtime_list, "data/showtime.dat");
    }

    private void removeShowTime(ShowTime showTime){
        showtime_list.remove(showTime);
        FileHelper.write(showtime_list, "data/showtime.dat");
    }
}
