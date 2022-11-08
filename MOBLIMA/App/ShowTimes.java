package MOBLIMA.App;

import MOBLIMA.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Class that handles all the Show Times
 * @since 2022-11-08
 * @author Lim Jia Wei
 */
public class ShowTimes {
    ArrayList<ShowTime> showtime_list;
    ArrayList<BookingRecord> booking_list;
    Scanner sc = new Scanner(System.in);

    /**
     * Default Constructor
     * Initialises an array list of Show Time objects by reading from showtimes.dat
     * Initialises an array list of Booking Record objects by reading from bookings.dat
     */
    public ShowTimes(){
        this.showtime_list = (ArrayList<ShowTime>) FileHelper.read("data/showtimes.dat");
        this.booking_list = (ArrayList<BookingRecord>) FileHelper.read("data/bookings.dat");
      }

    /**
     * Function that Display all showtimes and allowing the purchasing of tickets
     */
    public void displayShowtimes(){
        System.out.println("=".repeat(40));
        System.out.println("All showtimes");
        System.out.println("=".repeat(40));
        Map<String, List<ShowTime>> movie_group = showtime_list.stream().collect(Collectors.groupingBy(nigel -> nigel.getMovie().getTitle()  + (nigel.getMovie().getMovieType() == MovieType.threeD ? " (3D)" : "")));

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
        }
        if(showtime_count == 0){
            System.out.println("No showtimes found");
            return;
        }
        int choice = -1;
        while (choice != 0) {
            System.out.println("=".repeat(40));
            System.out.println("[1-" + showtime_count + "] Buy tickets");
            System.out.println("[0] Go back");
            System.out.print("Enter option: ");
            choice = sc.nextInt();
            if (choice > 0 && choice <= showtime_count) {
                ShowTime showTime = showtimeMap.get(choice);
                bookShowtime(showTime);
                return;
            } else if (choice != 0) {
                System.out.println("Invalid option");
            }
        }
    }

    /**
     * Function that allows the booking of a showtime , selection of ticket type and seat type and payment of ticket price
     * @param showTime - Show time that will be previewed
     */
    public void bookShowtime(ShowTime showTime){
        System.out.println("=".repeat(40));
        System.out.println("New Booking for " + showTime.getMovie().getTitle()  + (showTime.getMovie().getMovieType() == MovieType.threeD ? " (3D)" : ""));
        System.out.println(" on " + showTime.getDay() + ", " + showTime.getDate() + " at " + showTime.getTime());
        Cinemas cinemas = new Cinemas();
        Pricing pricing = new Pricing();
        BookingRecord new_booking = new BookingRecord();
        new_booking.setShowTime(showTime);
        sc.skip("\\R?");
        boolean selectSeat = true;
        while(selectSeat){
            cinemas.printSeatingLayout(showTime.getSeats(), true, showTime.getCinema());
            System.out.print("Select seat to book: ");
            String seat = sc.nextLine();
            if(showTime.getSeats()[seat.charAt(0) - 65][Integer.parseInt(seat.substring(1)) - 1].isOccupied()){
                System.out.println("Seat is occupied. Please select another seat");
            }
            else if(showTime.getSeats()[seat.charAt(0) - 65][Integer.parseInt(seat.substring(1)) - 1].isSelected()){
                System.out.println("You have already selected this seat");
            }

            else{
                Ticket ticket = new Ticket();
                ticket.setSeatType(showTime.getSeats()[seat.charAt(0) - 65][Integer.parseInt(seat.substring(1)) - 1].getSeatType());
                ticket.setSeatNumber(seat);
                System.out.println("Ticket type: ");
                System.out.println("[1] Adult ($" + pricing.computePricing(TicketType.STANDARD, showTime.getCinema().isPlatinum(), LocalDate.from(showTime.getDateTime()), showTime.getMovie().getMovieType(), ticket.getSeatType()) + ")");
                System.out.println("[2] Child ($" + pricing.computePricing(TicketType.CHILD, showTime.getCinema().isPlatinum(), LocalDate.from(showTime.getDateTime()), showTime.getMovie().getMovieType(), ticket.getSeatType()) + ")");
                System.out.println("[3] Senior ($" + pricing.computePricing(TicketType.SENIOR, showTime.getCinema().isPlatinum(), LocalDate.from(showTime.getDateTime()), showTime.getMovie().getMovieType(), ticket.getSeatType()) + ")");
                int ticketType = sc.nextInt();
                if(ticketType == 1){
                    ticket.setTicketType(TicketType.STANDARD);
                }
                else if(ticketType == 2){
                    ticket.setTicketType(TicketType.CHILD);
                }
                else if(ticketType == 3){
                    ticket.setTicketType(TicketType.SENIOR);
                }
                System.out.println("Seat " + seat + " added");
                showTime.getSeats()[ticket.getSeatNumber().charAt(0) - 65][Integer.parseInt(ticket.getSeatNumber().substring(1)) - 1].setSelected();

                new_booking.addTicket(ticket);
                float price = pricing.computePricing(ticket.getTicketType(), showTime.getCinema().isPlatinum(), LocalDate.from(showTime.getDateTime()), showTime.getMovie().getMovieType(), ticket.getSeatType());
                new_booking.addPrice(price);

                System.out.println("=".repeat(40));
                System.out.println("Selected seats: " + new_booking.printSeats());
                System.out.println("Total price: $" + new_booking.getTotalPrice());
                System.out.println("Book another seat? (y/N): ");
                sc.skip("\\R?");
                if (sc.nextLine().equalsIgnoreCase("n")) {
                    selectSeat = false;
                }
            }
        }

        cinemas.printSeatingLayout(showTime.getSeats(), true, showTime.getCinema());
        System.out.println("Selected seats: " + new_booking.printSeats());
        System.out.println("Total price: $" + new_booking.getTotalPrice());
        System.out.println("Confirm booking? (y/N): ");
        sc.skip("\\R?");
        if (sc.nextLine().equalsIgnoreCase("y")) {

            while(Account.UserDetail.user == null){
                System.out.println("Please login or signup to continue");
                System.out.println("=".repeat(40));
                System.out.println("[1] Login");
                System.out.println("[2] Register");
                switch (sc.nextInt()) {
                    case 1 -> { // LOGIN
                        Account account = new Account();
                        Account.UserDetail.user = account.login();
                    }
                    case 2 -> { // REGISTER
                        Account account = new Account();
                        Account.UserDetail.user = account.register();
                    }
                }
            }
            new_booking.setUser(Account.UserDetail.user);

            Payment payment = new Payment();
            float final_price = payment.pay(new_booking.getTotalPrice());
            if(final_price >= 0){
                new_booking.setTotalPrice(final_price);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
                LocalDateTime now = LocalDateTime.now();
                String transactionID = showTime.getCinema().getCinemaCode() + now.format(formatter);
                new_booking.setTransactionID(transactionID);
                System.out.println("Transaction ID " + "123456789");
                System.out.println("Your booking is confirmed!");

                //deselect all seats
                // add to booking record
                for(Ticket ticket : new_booking.getTickets()){
                    showTime.getSeats()[ticket.getSeatNumber().charAt(0) - 65][Integer.parseInt(ticket.getSeatNumber().substring(1)) - 1].setDeselected();
                    showTime.getSeats()[ticket.getSeatNumber().charAt(0) - 65][Integer.parseInt(ticket.getSeatNumber().substring(1)) - 1].setOccupied();
                }

                //increment movie sales
                Movies movies = new Movies();
                movies.incrementScales(showTime.getMovie(), new_booking.getTickets().size());

                for(int i = 0; i < showtime_list.size(); i++) {
                    if(showtime_list.get(i).getShowTimeId().equals(showTime.getShowTimeId())) {
                        showtime_list.set(i, showTime);
                        break;
                    }
                }
                FileHelper.write(showtime_list, "data/showtimes.dat");

                booking_list.add(new_booking);
                FileHelper.write(booking_list, "data/bookings.dat");
                writeToShowtimeFile();
            }
            else{
                System.out.println("Payment unsuccessful");
            }
        }
        else{
            System.out.println("Booking cancelled");
        }
    }

    /**
     * Function that display all the showtime of a specific movie
     * @param movie - Movie object to view all its correspoding showtime
     */
    public void displayShowtimesByMovie(Movie movie){
        System.out.println("=".repeat(40));
        System.out.println("Showtimes for " + movie.getTitle());
        System.out.println("=".repeat(40));
        Map<String, List<ShowTime>> movie_group = showtime_list.stream().collect(Collectors.groupingBy(nigel -> nigel.getMovie().getTitle()));

        int showtime_count = 0;
        Map<Integer, ShowTime> showtimeMap = new HashMap<>();
        for (Map.Entry<String, List<ShowTime>> entry : movie_group.entrySet()) {
            if(entry.getKey().equals(movie.getTitle())){
                System.out.println("■ " + entry.getKey()  + (movie.getMovieType() == MovieType.threeD ? " (3D)" : ""));
                Map<String, List<ShowTime>> cinema_group =
                        entry.getValue().stream().collect(Collectors.groupingBy(nigel -> nigel.getCinema().getCineplex()));

                for (Map.Entry<String, List<ShowTime>> entryz : cinema_group.entrySet()) {
                    System.out.println("  ▬ " + entryz.getKey() + " ▬");
                    for (ShowTime showtimez : entryz.getValue()) {
                        System.out.println("    [" + ++showtime_count + "] " + showtimez.getDay() + ", " + showtimez.getDate() + ", " + showtimez.getTime() + (showtimez.getCinema().isPlatinum() ? " (Platinum Cinema)" : ""));
                        showtimeMap.put(showtime_count, showtimez);
                    }
                }
            }
        }
        if(showtime_count == 0){
            System.out.println("No showtimes found");
            return;
        }
        int choice = -1;
        while (choice != 0) {
            System.out.println("=".repeat(40));
            System.out.println("[1-" + showtime_count + "] Buy tickets");
            System.out.println("[0] Go back");
            System.out.print("Enter option: ");
            choice = sc.nextInt();
            if (choice > 0 && choice <= showtime_count) {
                ShowTime showTime = showtimeMap.get(choice);
                bookShowtime(showTime);
                return;
            } else if (choice != 0) {
                System.out.println("Invalid option");
            }
        }
    }

    /**
     * Class that displays all the Showtimes by Cinema
     * @param cinema - Cinema object to display all its correspoding showtimes
     */
    public void displayShowtimesByCinema(Cinema cinema){
        System.out.println("=".repeat(40));
        System.out.println("Showtimes at " + cinema.getCineplex());
        System.out.println("=".repeat(40));
        Map<String, List<ShowTime>> movie_group = showtime_list.stream().collect(Collectors.groupingBy(nigel -> nigel.getMovie().getTitle()));

        int showtime_count = 0;
        Map<Integer, ShowTime> showtimeMap = new HashMap<>();
        for (Map.Entry<String, List<ShowTime>> entry : movie_group.entrySet()) {
            System.out.println("■ " + entry.getKey());
            Map<String, List<ShowTime>> cinema_group =
                    entry.getValue().stream().collect(Collectors.groupingBy(nigel -> nigel.getCinema().getCineplex()));

            for (Map.Entry<String, List<ShowTime>> entryz : cinema_group.entrySet()) {
                if(entryz.getKey().equals(cinema.getCineplex())){
                    System.out.println("  ▬ " + entryz.getKey() + " ▬");
                    for (ShowTime showtimez : entryz.getValue()) {
                        System.out.println("    [" + ++showtime_count + "] " + showtimez.getDay() + ", " + showtimez.getDate() + ", " + showtimez.getTime() + (showtimez.getCinema().isPlatinum() ? " (Platinum Cinema)" : ""));
                        showtimeMap.put(showtime_count, showtimez);
                    }
                }
            }
        }
        if(showtime_count == 0){
            System.out.println("No showtimes found");
            return;
        }
        int choice = -1;
        while (choice != 0) {
            System.out.println("=".repeat(40));
            System.out.println("[1-" + showtime_count + "] Buy tickets");
            System.out.println("[0] Go back");
            System.out.print("Enter option: ");
            choice = sc.nextInt();
            if (choice > 0 && choice <= showtime_count) {
                ShowTime showTime = showtimeMap.get(choice);
                bookShowtime(showTime);
                return;
            } else if (choice != 0) {
                System.out.println("Invalid option");
            }
        }
    }

    /**
     * Function that allows user to select ShowTime
     * @return selected showtime
     */
    public ShowTime selectShowTime(boolean isBooking){
        System.out.println("=".repeat(40));
        System.out.println("All showtimes");
        System.out.println("=".repeat(40));
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
        }
        int choice = -1;
        while (choice != 0) {
            System.out.println("=".repeat(40));
            System.out.println("[1-" + showtime_count + "] " + (isBooking ? "View movie information or buy tickets" : "Select showtime"));
            System.out.println("[0] Go back");
            System.out.print("Enter option: ");
            choice = sc.nextInt();
            if (choice > 0 && choice <= showtime_count) {
                return showtimeMap.get(choice);
            } else if (choice != 0) {
                System.out.println("Invalid option");
            }
        }
        return null;
    }

    /**
     * Function that adds a showtime to a movie
     * @param movie - Movie object to add a showtime to
     */
    public void addShowtime(Movie movie){
        System.out.println("=".repeat(40));
        System.out.println("Adding new showtime for " + movie.getTitle());
        ShowTime showTime = new ShowTime(movie);

        Cinemas cinemas = new Cinemas();
        Cinema cinema = cinemas.selectCinema();
        showTime.setCinema(cinema);
        showTime.setSeats(cinema.getSeatLayout().clone());

        System.out.println("=".repeat(40));
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

        System.out.println("=".repeat(40));
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
        addToShowtimeFile(showTime);
        System.out.println("Showtime added successfully.");
    }

    /**
     * Function that updates a showtime and its corresponding information
     * @param showTime - showtime object to be updated
     */

    public void updateShowtime(ShowTime showTime){
        boolean edit = true;
        while(edit){
            System.out.println("=".repeat(40));
            System.out.println("Updating " + showTime);
            System.out.println("Select field to edit");
            System.out.println("[1] Cinema: " + showTime.getCinema().getCineplex());
            System.out.println("[2] Date: " + showTime.getDate());
            System.out.println("[3] Time: " + showTime.getTime());
            System.out.println("[4] Duration: " + showTime.getMovie().getDuration() + " minutes");
            System.out.print("Enter option: ");
            switch (sc.nextInt()) {
                case 1 -> {
                    Cinemas cinemas = new Cinemas();
                    Cinema cinema = cinemas.selectCinema();
                    showTime.setCinema(cinema);
                    showTime.setSeats(cinema.getSeatLayout().clone());
                    System.out.println("Cinema updated successfully.");
                }
                case 2 -> {
                    System.out.println("=".repeat(40));
                    System.out.println("Enter movie date (DD/MM/YYYY): ");
                    sc.skip("\\R?");
                    while (true) {
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
                }
                case 3 -> {
                    System.out.println("=".repeat(40));
                    System.out.println("Enter movie start time (HH:mm): ");
                    sc.skip("\\R?");
                    while (true) {
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
                }
                case 4 -> {
                    System.out.println("=".repeat(40));
                    System.out.println("REMOVED");
                }
                default -> {
                    System.out.println("Invalid option. Please try again.");
                    continue;
                }
            }
            writeToShowtimeFile();
            System.out.println("Showtime updated successfully");
            System.out.println("Update another field? (y/N): ");
            sc.skip("\\R?");
            if (!sc.nextLine().equalsIgnoreCase("y")) {
                edit = false;
            }
        }
    }

    /**
     * Function that adds a new showTime into showtime_list
     * @param showTime - showTime object to be added into showtimes.dat
     */
    public void addToShowtimeFile(ShowTime showTime){
        showtime_list.add(showTime);
        FileHelper.write(showtime_list, "data/showtimes.dat");
    }

    /**
     * Function that writes showtime_list into showtimes.dat
     */
    public void writeToShowtimeFile(){
        FileHelper.write(showtime_list, "data/showtimes.dat");
    }

    /**
     * Function that removes a showtime from showtimes.dat
     * @param showTime
     */
    public void removeFromShowtimeFile(ShowTime showTime){
        showtime_list.remove(showTime);
        FileHelper.write(showtime_list, "data/showtimes.dat");
        System.out.println("Showtime successfully removed.");
    }
}
