package MOBLIMA.Displays;

import java.util.Scanner;

public class AllGuestDisplay {
    public AllGuestDisplay(){

    }

    public static void displayMenu(){
        Scanner sc = new Scanner(System.in);

        System.out.println("*".repeat(40));
        System.out.println("Guest Display Menu");

        int choice = 0;

        while (choice != 6){
            System.out.println("[1] Movies");
            System.out.println("[2] Show Times");
            System.out.println("[3] Reviews and Ratings");
            System.out.println("[4] Booking");
            System.out.println("[5] Payment");
            System.out.println("[6] Exit");

            choice = sc.nextInt();
            switch(choice){
                case 1:
                    //MovieListingDisplay menu1 = new MovieListingDisplay();
                    //menu1.DisplayMenu();
                    break;
                case 2:
                    System.out.println("Show time Menu");
                    //create movie class
                    //ShowTimeDisplay menu2 = new ShowTimeDisplay();
                    //menu2.DisplayMenu();
                    break;
                case 3:
                    System.out.println("Review and rating menu");
                    //create movie class
                    //ReviewRatingDisplay menu3 = new ReviewRatingDisplay();
                    //menu3.DisplayMenu();
                    break;
                case 4:
                    System.out.println("Booking menu");
                    //create ArrayList<ShowTime> showtime_list
                    //BookingDisplay menu4 = new BookingDisplay();
                    //menu4.DisplayMenu();
                    break;
                case 5:
                    System.out.println("Payment Menu");
                    //float ticketPrice,ShowTime showtime
                    //PaymentDisplay menu5 = new PaymentDisplay();
                    //menu5.displayMenu();
                    break;
            }
        }
    }
}
