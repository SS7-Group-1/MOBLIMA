package MOBLIMA;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Booking Record class.
 * @author SS7 Group 1
 * @version 1.0
 * @since 2022-11-07
 */
public class BookingRecord implements Serializable {
    /**
     * Unique ID given to each transaction in the format XXXYYYYMMDDhhmm
     * XXX: Cinema code
     * YYYY: Year
     * MM: Month
     * DD: Day
     * hh: Hour
     * mm: Minute
     */
    private String transactionID;
    /**
     * Date and Time of transaction.
     */
    private LocalDateTime transactionDateTime;
    /**
     * Information of the showtime of the movie booked.
     */
    private ShowTime showTime;
    /**
     * Total price of all the movie tickets during transaction.
     */
    private float totalPrice;
    /**
     * Information of all the tickets purchased.
     */
    private ArrayList<Ticket> tickets;
    /**
     * Information of the customer who made the ticket purchase.
     */
    private User user;

    /**
     * Default Constructor:
     * Creates a new BookingRecord at current local time.
     * Initialise an ArrayList of tickets.
     */
    public BookingRecord(){
        this.transactionDateTime = LocalDateTime.now();
        this.tickets = new ArrayList<>();
    }

    /**
     * Creates a new BookingRecord with attributes initialised.
     * @param transactionID Unique transaction ID in XXXYYYYMMDDhhmm format
     * @param showTime ShowTime of movie
     * @param tickets Tickets purchased
     * @param user Customer information
     * @param totalPrice Total price of tickets purchased
     */
    public BookingRecord(String transactionID, ShowTime showTime, ArrayList<Ticket> tickets, User user, float totalPrice) {
        this.transactionID = transactionID;
        this.transactionDateTime = LocalDateTime.now();
        this.showTime = showTime;
        this.tickets = tickets;
        this.user = user;
        this.totalPrice = totalPrice;
    }

    /**
     * Accessor for TransactionID.
     * @return String: TransactionID.
     */
    public String getTransactionID() {
        return transactionID;
    }

    /**
     * Mutator for TransactionID.
     * @param transactionID TransactionID to change to.
     */
    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    /**
     * Accessor for TransactionDateTime.
     * @return LocalDateTime: Date and time of transaction.
     */
    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    /**
     * Mutator for TransactionDate Time
     * @param transactionDateTime Date and time of transaction to change to.
     */
    public void setTransactionDateTime(LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    /**
     * Accessor for ShowTime.
     * @return ShowTime: ShowTime.
     */
    public ShowTime getShowTime() {
        return showTime;
    }

    /**
     * Mutator for ShowTime.
     * @param showTime ShowTime to change to.
     */
    public void setShowTime(ShowTime showTime) {
        this.showTime = showTime;
    }

    /**
     * Accessor for all Tickets made in transaction.
     * @return ArrayList<Ticket>: Array of Tickets.
     */
    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    /**
     * Mutator for Tickets made in transaction.
     * @param tickets ArrayList of Tickets to change to.
     */
    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    /**
     * Accessor for Total Price of tickets.
     * @return float: Total price.
     */
    public float getTotalPrice(){
        return this.totalPrice;
    }

    /**
     * Mutator for Total Price of tickets.
     * @param totalPrice Total price in float to change to.
     */
    public void setTotalPrice(float totalPrice){
        this.totalPrice = totalPrice;
    }

    /**
     * Increase total price by a specified amount.
     * @param price Amount to increase total price by.
     */
    public void addPrice(float price){
        this.totalPrice += price;
    }

    /**
     * Include new Ticket class into ArrayList of Tickets.
     * @param ticket Ticket to be added.
     */
    public void addTicket(Ticket ticket){
        tickets.add(ticket);
    }

    /**
     * Accessor for User, to get customer information.
     * @return User: User for this transaction.
     */
    public User getUser() {
        return user;
    }

    /**
     * Mutator for User.
     * @param user User to be changed to.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Information about Seat Number and Seat Type of tickets purchased.
     * @return String: Information on Seat number and type of all tickets purchased.
     */
    public String printSeats(){
        String seats = "";
        for (Ticket ticket: tickets){
            seats += "\n  " + ticket.getSeatNumber() + " (" + ticket.getSeatType() + ") ";
        }
        return seats;
    }

    /**
     * Prints out Transaction ID, Transaction Date, Movie, Cinema, Show Time, Seats purchased and total price.
     */
    public void printBookingRecord() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yy, HH:mm");
        System.out.println("Transaction ID: " + transactionID);
        System.out.println("Transaction Date: " + transactionDateTime.format(formatter));
        System.out.println("Movie: " + showTime.getMovie().getTitle());
        System.out.println("Cinema: " + showTime.getCinema().getCinemaCode() + " " + (showTime.getCinema().isPlatinum() ? "(Platinum)" : ""));
        System.out.println("Show Time: " + showTime.getDay() + ", " + showTime.getDate() + ", " + showTime.getTime());
        System.out.println("Seats: " + printSeats());
        System.out.println("Total Price: $" + totalPrice);
    }
}
