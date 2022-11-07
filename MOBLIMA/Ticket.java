package MOBLIMA;

import java.io.Serializable;

/**
 * A class that represents movie ticket
 * @author SS7 Group 1
 * @version 1.0
 * @since 2022-11-07
 */
public class Ticket implements Serializable {
    /**
     * denoting the seat number for the ticket
     */
    private String seatNumber;
    /**
     * denoting the seat type for the ticket
     */
    private SeatType seatType;
    /**
     * denoting the type for the ticket (i.e adult/child/senior)
     */
    private TicketType ticketType;

    /**
     * creates a new Ticket (default constructor)
     */
    public Ticket(){
    }

    /**
     * creates a new Ticket with seatNumber, seatType, ticketType initialised to the respective arguments passed in
     * @param seatNumber denoting the seat number for the ticket
     * @param seatType denoting the seat type for the ticket
     * @param ticketType denoting the type for the ticket (i.e adult/child/senior)
     */
    public Ticket(String seatNumber, SeatType seatType, TicketType ticketType){
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.ticketType = ticketType;
    }

    /**
     * Accessor for seat number for the ticket
     * @return seatNumber: seat number for the ticket
     */
    public String getSeatNumber(){
        return seatNumber;
    }

    /**
     * Mutator for seat number for the ticket
     * @param seatNumber seat number for the ticket
     */
    public void setSeatNumber(String seatNumber){
        this.seatNumber = seatNumber;
    }

    /**
     * Accessor for seat type for the ticket
     * @return seatType: seat type for the ticket
     */
    public SeatType getSeatType(){
        return seatType;
    }

    /**
     * Mutator for seat type for the ticket
     * @param seatType seat type for the ticket
     */
    public void setSeatType(SeatType seatType){
        this.seatType = seatType;
    }

    /**
     * Accessor for type for the ticket (i.e adult/child/senior)
     * @return ticketType: type for the ticket (i.e adult/child/senior)
     */
    public TicketType getTicketType(){
        return ticketType;
    }

    /**
     * Mutator for type for the ticket (i.e adult/child/senior)
     * @param ticketType type for the ticket (i.e adult/child/senior)
     */
    public void setTicketType(TicketType ticketType){
        this.ticketType = ticketType;
    }
}
