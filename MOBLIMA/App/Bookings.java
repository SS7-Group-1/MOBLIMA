package MOBLIMA.App;

import MOBLIMA.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that handles all the movie bookings
 * @since 2022-11-07
 * @author Lim Jia Wei
 * @version 1.5
 *
 */
public class Bookings {
    User user;
    ArrayList<BookingRecord> booking_record_list;

    /**
     * Default Constructor
     * Creates a new Booking
     * Set user object attribute from Account user Detail
     * Initialise an array list of BookingRecord type by reading from bookings.dat
     */
    public Bookings(){
        this.user = Account.UserDetail.user;
        this.booking_record_list = (ArrayList<BookingRecord>) FileHelper.read("data/bookings.dat");
    }

    /**
     *Function that allows user to view their booking records
     */
    public void viewRecords(){
        // view user booking records
        System.out.println("=".repeat(40));
        System.out.println("Your booking records");
        int record_count = 0;
        for (BookingRecord booking_record: booking_record_list){
            if (booking_record.getUser().getEmail().equals(user.getEmail())){
                System.out.println();
                booking_record.printBookingRecord();
                record_count++;
            }
        }
        if(record_count == 0){
            System.out.println("No booking records found");
        }
    }
}
