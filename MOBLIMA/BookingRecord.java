package MOBLIMA;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class BookingRecord {
    private String transactionID;
    private LocalDateTime transactionDateTime;
    private ShowTime showTime;
    private ArrayList<Seat> seats;
    private User user;

    public BookingRecord(String transactionID, ShowTime showTime, ArrayList<Seat> seats, User user) {
        this.transactionID = transactionID;
        this.transactionDateTime = LocalDateTime.now();
        this.showTime = showTime;
        this.seats = seats;
        this.user = user;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public ShowTime getShowTime() {
        return showTime;
    }

    public void setShowTime(ShowTime showTime) {
        this.showTime = showTime;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
