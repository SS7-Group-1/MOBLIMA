package MOBLIMA.App;

import MOBLIMA.ShowTime;
import MOBLIMA.User;

public class Bookings {
    User user;
    public Bookings(User user){
        this.user = user;
    }

    public void newBooking(){
        // select movie
        // select showtime
    }

    public void viewBooking(){
        // select booking
        // view booking details
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
