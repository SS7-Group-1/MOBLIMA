package MOBLIMA;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class BookingRecord {
    private String transactionID;
    private LocalDateTime transactionDateTime;
    private ShowTime showTime;
    private float totalPrice;
    private ArrayList<Ticket> tickets;
    private User user;

    public BookingRecord(){
        this.transactionDateTime = LocalDateTime.now();
        this.tickets = new ArrayList<>();
    }

    public BookingRecord(String transactionID, ShowTime showTime, ArrayList<Ticket> tickets, User user, float totalPrice) {
        this.transactionID = transactionID;
        this.transactionDateTime = LocalDateTime.now();
        this.showTime = showTime;
        this.tickets = tickets;
        this.user = user;
        this.totalPrice = totalPrice;
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

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public float getTotalPrice(){
        return this.totalPrice;
    }

    public void setTotalPrice(float totalPrice){
        this.totalPrice = totalPrice;
    }

    public void addPrice(float price){
        this.totalPrice += price;
    }

    public void addTicket(Ticket ticket){
        tickets.add(ticket);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String printSeats(){
        String seats = "";
        for (Ticket ticket: tickets){
            seats += ticket.getSeatNumber() + " ";
        }
        return seats;
    }

    @Override
    public String toString() {
        return "BookingRecord{" +
                "transactionID='" + transactionID + '\'' +
                ", transactionDateTime=" + transactionDateTime +
                ", showTime=" + showTime +
                '}';
    }
}
