package MOBLIMA.Tests;

import MOBLIMA.*;
import MOBLIMA.Displays.BookingDisplay;

import java.util.ArrayList;

public class Booking_test {
    public static void main(String[] args){
        ArrayList<ShowTime> showtime_list = new ArrayList<>();

        //Test for booking.
        BookingDisplay bk = new BookingDisplay(showtime_list);
        bk.DisplayMenu();
    }
}


