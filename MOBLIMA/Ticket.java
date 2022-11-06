package MOBLIMA;

import java.io.Serializable;

public class Ticket implements Serializable {
    private String seatNumber;
    private SeatType seatType;
    private TicketType ticketType;

    public Ticket(){
    }

    public Ticket(String seatNumber, SeatType seatType, TicketType ticketType){
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.ticketType = ticketType;
    }

    public String getSeatNumber(){
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber){
        this.seatNumber = seatNumber;
    }

    public SeatType getSeatType(){
        return seatType;
    }

    public void setSeatType(SeatType seatType){
        this.seatType = seatType;
    }

    public TicketType getTicketType(){
        return ticketType;
    }

    public void setTicketType(TicketType ticketType){
        this.ticketType = ticketType;
    }
}
