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

    public void newBooking(ShowTime showTime){
        System.out.println("New Booking for " + showTime.getMovie().getTitle());
        // select movie
        // select showtime

        Pricing pricing = new Pricing();
        float price = pricing.computePricing(TicketType.STANDARD, showTime.getCinema().isPlatinum(), LocalDate.from(showTime.getDateTime()), showTime.getMovie().getMovieType(), SeatType.STANDARD);

        System.out.println("Price: $" + price);
    }

    public void viewBooking(){
        // view user booking records
        System.out.println("Your booking records");
        int record_count = 0;
        for (BookingRecord booking_record: booking_record_list){
            if (booking_record.getUser().equals(user)){
                System.out.println(booking_record);
                record_count++;
            }
        }
        if(record_count == 0){
            System.out.println("No booking records found");
        }
    }

    public float computePrice(ShowTime showTime){
        // TODO: COMPUTE TICKET PRICE AND RETURN
        user = user;
        showTime = showTime;
        return 8.50f;
    }

    public String generateTid(){
//        String timestamp = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
//
//        System.out.println(showtime.getCinema().getCinemaCode() + timestamp);
//        this.transactionID=showtime.getCinema().getCinemaCode() + timestamp;
//        return transactionID;
        return "";
    }
}
