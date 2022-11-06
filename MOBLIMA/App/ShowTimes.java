package MOBLIMA.App;

import MOBLIMA.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

public class ShowTimes {
    ArrayList<ShowTime> showtime_list;
    Scanner sc = new Scanner(System.in);

    public ShowTimes(){
        this.showtime_list = (ArrayList<ShowTime>) FileHelper.read("data/showtimes.dat");
      }

    public void displayShowtimes(){
        System.out.println("*".repeat(40));
        System.out.println("All showtimes");
        System.out.println("*".repeat(40));
        Map<String, List<ShowTime>> movie_group = showtime_list.stream().collect(Collectors.groupingBy(nigel -> nigel.getMovie().getTitle()));

        int showtime_count = 0;
        Map<Integer, ShowTime> showtimeMap = new HashMap<>();
        for (Map.Entry<String, List<ShowTime>> entry : movie_group.entrySet()) {
            System.out.println("■ " + entry.getKey());
            Map<String, List<ShowTime>> cinema_group =
                    entry.getValue().stream().collect(Collectors.groupingBy(nigel -> nigel.getCinema().getCineplex()));

            for (Map.Entry<String, List<ShowTime>> entryz : cinema_group.entrySet()) {
                System.out.println("  ▬ " + entryz.getKey() + " ▬");
                for (ShowTime showtimez : entryz.getValue()) {
                    System.out.println("    [" + ++showtime_count + "] " + showtimez.getDay() + ", " + showtimez.getDate() + ", " + showtimez.getTime() + (showtimez.getCinema().isPlatinum() ? " (Platinum Cinema)" : ""));
                    showtimeMap.put(showtime_count, showtimez);
                }
            }
        }int choice = -1;
        while (choice != 0) {
            System.out.println("▭".repeat(40));
            System.out.println("[1-" + showtime_count + "] View movie information or buy tickets");
            System.out.println("[0] Go back");
            System.out.print("Enter option: ");
            choice = sc.nextInt();
            if (choice > 0 && choice <= showtime_count) {
                Bookings bookings = new Bookings();
                ShowTime showTime = showtimeMap.get(choice);
                bookings.newBooking(showTime);
                return;
            } else if (choice != 0) {
                System.out.println("Invalid option");
            }
        }
    }

    public void displayShowtimesByMovie(Movie movie){
        System.out.println(movie);
        for (ShowTime showtime: showtime_list){
            if (showtime.getMovie().getTitle().equals(movie.getTitle())){
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

    public void removeSeat(ShowTime showTime, int row, int col){
        Seat[][] seats = showTime.getSeats();
        seats[row][col].setOccupied();
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
