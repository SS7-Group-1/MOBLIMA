package MOBLIMA.Displays;

import MOBLIMA.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner; // Import the Scanner class to read text files
import java.lang.*;
import java.util.stream.Collectors;
//NIGEL WAS HERE

public class BookingDisplay {
    Scanner sc = new Scanner(System.in);
    private float ticketPrice;
    private boolean bookingFinished;
    private ShowTime showtime;
    private ArrayList<ShowTime> showtime_List ;

    public BookingDisplay(ArrayList<ShowTime> showtime_list){
        this.showtime_List=showtime_list;
        this.bookingFinished=false;
    }
    public void computePrice(){
        // from showtime class
        this.ticketPrice=7.05f;

    }
    public void DisplayMenu(){
        Character c;
        int choice = 0;
        while (choice !=5) {
            System.out.println("*".repeat(40));
            System.out.println("[1] View all showtimes");
            System.out.println("[2] Select movie");
            System.out.println("[3] Select cinema");
            System.out.println("[4] View Booking History");
            System.out.println("[5] Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.println("*".repeat(40));
                    System.out.println("All showtimes");
                    System.out.println("*".repeat(40));
                    Map<String, List<ShowTime>> movie_group =
                            showtime_List.stream().collect(Collectors.groupingBy(nigel -> nigel.getMovie().getTitle()));
                    for (Map.Entry<String, List<ShowTime>> entry : movie_group.entrySet()) {
                        System.out.println("■ " + entry.getKey());
                        Map<String, List<ShowTime>> cinema_group =
                                entry.getValue().stream().collect(Collectors.groupingBy(nigel -> nigel.getCinema().getName()));

                        for (Map.Entry<String, List<ShowTime>> entryz : cinema_group.entrySet()) {
                            System.out.println("  [" + entryz.getKey() + "]");
                            for (ShowTime showtimez : entryz.getValue()) {
                                System.out.println("    " + showtimez.getDate() + " " + showtimez.getTime());
                            }
                        }
                    }
                }
                case 2 -> {
                    System.out.println("*".repeat(40));
                    System.out.println("Currently showing movies");
                    System.out.println("*".repeat(40));
                    Map<String, List<ShowTime>> movie_group =
                            showtime_List.stream().collect(Collectors.groupingBy(nigel -> nigel.getMovie().getTitle()));
                    for (Map.Entry<String, List<ShowTime>> entry : movie_group.entrySet()) {
                        System.out.println("[0] " + entry.getKey());
                    }
                }
                case 3 -> {
                    System.out.println("*".repeat(40));
                    System.out.println("Cinema outlet locations");
                    System.out.println("*".repeat(40));
                    Map<String, List<ShowTime>> cinema_group =
                            showtime_List.stream().collect(Collectors.groupingBy(nigel -> nigel.getCinema().getName()));
                    for (Map.Entry<String, List<ShowTime>> entry : cinema_group.entrySet()) {
                        System.out.println("[0] " + entry.getKey());
                    }
                }
                case 4 -> {
                    System.out.println("Booking History");
                }
                default -> {
                    System.out.println("Invalid choice");
                }
            }
        }
    }


    public Customer getCustomerInfo(){
        boolean isSenior=false;
        boolean isChild=false;
        System.out.println("Please proceed to insert information");
        //get Email
        System.out.println("Email: ");
        sc.skip("\\R?");
        String Email = sc.nextLine();

        //get Name
        System.out.println("Name: ");
        sc.skip("\\R?");
        String name = sc.nextLine();

        //get phone.
        System.out.println("Mobile number: ");
        sc.skip("\\R?");
        String phone = sc.nextLine();
        //get age ...
        System.out.println("Age: ");
        int age = sc.nextInt();
        //get book
        if (age>=65){
            isSenior = true;
        }
        else if (age <16){
            isChild=true;
        }
        Customer cust = new Customer(name,phone,Email,isSenior,isChild);
        return cust;
    }

    public void printSeatAvailability(ShowTime showtime){ //show seat availability.
        showtime.getAllSeats();

    }
}
