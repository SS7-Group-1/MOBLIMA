package MOBLIMA;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class BookingRecord {
    private String transactionID;
    private LocalDateTime transactionDateTime;
    private ShowTime showTime;
    private ArrayList<Seat> seats;

    public BookingRecord(String transactionID, ShowTime showTime, ArrayList<Seat> seats) {
        this.transactionID = transactionID;
        this.transactionDateTime = LocalDateTime.now();
        this.showTime = showTime;
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "BookingRecord{" +
                "transactionID='" + transactionID + '\'' +
                ", transactionDateTime=" + transactionDateTime +
                ", showTime=" + showTime +
                ", seats=" + seats +
                '}';
    }
}
