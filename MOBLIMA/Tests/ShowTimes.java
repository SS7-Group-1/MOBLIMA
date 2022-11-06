package MOBLIMA.Tests;

import MOBLIMA.Cinema;
import MOBLIMA.FileHelper;
import MOBLIMA.Movie;
import MOBLIMA.ShowTime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class ShowTimes {
    ArrayList<ShowTime> showtime_list;
    Scanner sc = new Scanner(System.in);

    public ShowTimes(){
        this.showtime_list = (ArrayList<ShowTime>) FileHelper.read("data/showtimes.dat");
      }

    public void displayShowtimes(){
        for (ShowTime showtime: showtime_list){
            System.out.println(showtime);
        }
    }

    public void displayShowtimesByMovie(Movie movie){
        for (ShowTime showtime: showtime_list){
            if (showtime.getMovie().equals(movie)){
                System.out.println(showtime);
            }
        }
    }

    public void displayShowtimesByCinema(Cinema cinema){
        for (ShowTime showtime: showtime_list){
            if (showtime.getCinema().equals(cinema)){
                System.out.println(showtime);
            }
        }
    }

    public ShowTime selectShowTime(){
        return null;
    }

    public void addShowtime(Movie movie){
        System.out.println("*".repeat(40));
        System.out.println("Adding new showtime for " + movie.getTitle());
        ShowTime showTime = new ShowTime(movie);

        Cinemas cinemas = new Cinemas();
        Cinema cinema = cinemas.selectCinema();
        showTime.setCinema(cinema);
        showTime.setSeats(cinema.getSeatLayout().clone());

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
    }

    public void updateShowtime(ShowTime showTime){
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
                    Cinemas cinemas = new Cinemas();
                    Cinema cinema = cinemas.selectCinema();
                    showTime.setCinema(cinema);
                    showTime.setSeats(cinema.getSeatLayout().clone());
                    System.out.println("Cinema updated successfully.");
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

    public void removeShowtime(ShowTime showTime){
        showtime_list.remove(showTime);
        System.out.println("Showtime removed successfully.");
    }
}
