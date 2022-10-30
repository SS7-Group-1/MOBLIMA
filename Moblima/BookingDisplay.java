package MOBLIMA;

import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files
import java.lang.*;


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
        System.out.println("1. See All ShowTimes");
        System.out.println("2. New Booking");
        System.out.println("3. Previous Booking Records");
        System.out.println("4. Exit Booking Menu");
        int choice = 0;
        while (choice !=4) {
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Filter by Movie? Y/N?");
                    c = sc.next().charAt(0);
                    if (c.equals('Y')) {
                        System.out.println("Which Movie?");
                        sc.skip("\\R?");
                        String chosen_movie = sc.nextLine();
                        //filter for movie
                        System.out.println("Filter by Cinema? Y/N?");
                        c = sc.next().charAt(0);
                        if (c.equals('Y')) {
                            System.out.println("Which Cinema?");
                            sc.skip("\\R?");
                            String chosen_cinema = sc.nextLine();

                            //Filter by both cinema and movie;
                            for (int i = 0; i < showtime_List.size(); i++) {
                                // System.out.format("chosen %s, list %s\n",chosen_cinema,showtime_List.get(i).getCinema().getName());
                                //System.out.format("chosen %s, list %s\n",chosen_movie,showtime_List.get(i).getMovie().getTitle());
                                if (showtime_List.get(i).getCinema().getName().equals(chosen_cinema) && showtime_List.get(i).getMovie().getTitle().equals(chosen_movie)) {
                                    System.out.println(showtime_List.get(i));

                                }
                            }
                        } else {
                            //filter by movie only.

                            for (int i = 0; i < showtime_List.size(); i++) {
                                if (showtime_List.get(i).getMovie().getTitle().equals(chosen_movie)) {
                                    System.out.println(showtime_List.get(i));

                                }
                            }

                        }


                    } else {
                        System.out.println("Filter by Cinema? Y/N?");
                        c = sc.next().charAt(0);
                        if (c.equals('Y')) {
                            System.out.println("Which Cinema?");
                            sc.skip("\\R?");
                            String chosen_cinema = sc.nextLine();

                            //Filter by both cinema;
                            for (int i = 0; i < showtime_List.size(); i++) {
                                if (showtime_List.get(i).getCinema().getName().equals(chosen_cinema)) {
                                    System.out.println(showtime_List.get(i));

                                }
                            }

                        } else {
                            //nofilter
                            for (int i = 0; i < showtime_List.size(); i++) {

                                System.out.println(showtime_List.get(i));


                            }
                        }

                    }
                    break;
                case 2:
                    ShowTime chosen = null;
                    //create new booking.
                    System.out.println("Which Movie?");
                    sc.skip("\\R?");
                    String chosen_movie = sc.nextLine();
                    //filter for movie
                    System.out.println("Which Cinema?");
                    sc.skip("\\R?");
                    String chosen_cinema = sc.nextLine();
                    //timing.
                    System.out.println("Which Timing? (YYYYMMDDhhmm)");
                    sc.skip("\\R?");
                    String chosen_timing = sc.nextLine();


                    for (int i = 0; i < showtime_List.size(); i++) {
                        // System.out.format("chosen %s, list %s\n",chosen_cinema,showtime_List.get(i).getCinema().getName());
                        //System.out.format("chosen %s, list %s\n",chosen_movie,showtime_List.get(i).getMovie().getTitle());
                        if (showtime_List.get(i).getCinema().getName().equals(chosen_cinema) && showtime_List.get(i).getMovie().getTitle().equals(chosen_movie) && showtime_List.get(i).getTime().equals(chosen_timing)) {
                            System.out.println(showtime_List.get(i));
                            chosen = showtime_List.get(i);

                        }
                    }
                    //call printseatavail
                    printSeatAvailability(chosen);
                    //Choose seats;
                    System.out.println("Which row?");
                    int row = sc.nextInt();
                    System.out.println("Which number?");
                    int col = sc.nextInt();

                    //double check seat availability
                    if (chosen.getSpecificSeats(row, col) == false) {
                        //compute price
                        computePrice();
                        //make new seat object.
                        Seat seat = new Seat(row, col);
                        //if available, get customer info
                        System.out.println("Seat is available.");
                        Customer cust = getCustomerInfo(); //customer info to be saved into customer object.
                        // call payment display.
                        PaymentDisplay payment = new PaymentDisplay(this.ticketPrice, chosen);
                        String tid = payment.generateTid();
                        payment.displayMenu();
                        if (payment.isPaymentSuccessful() == true) { //payment went through.
                            //make seats occupied.
                            seat.setOccupied();
                            //call bookingrecord
                            BookingRecord bk = new BookingRecord(cust, tid, seat);
                            //test print booking record.
                            System.out.println(bk);
                            //UPdate ticket sale by 1
                            chosen.getMovie().addSales();
                        }

                    } else {
                        System.out.println("That seat is already taken.");
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
