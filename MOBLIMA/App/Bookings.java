package MOBLIMA.App;

import MOBLIMA.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Bookings {
    User user;
    ArrayList<BookingRecord> booking_record_list;
    Scanner sc = new Scanner(System.in);
    public Bookings(){
        this.user = Account.UserDetail.user;
        this.booking_record_list = (ArrayList<BookingRecord>) FileHelper.read("data/bookings.dat");
    }

    public void viewRecords(){
        // view user booking records
        System.out.println("▭".repeat(40));
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
