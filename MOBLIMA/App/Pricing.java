package MOBLIMA.App;

import MOBLIMA.MovieType;
import MOBLIMA.SeatType;
import MOBLIMA.TicketType;

import java.time.LocalDate;

public class Pricing {
    public float computePricing(TicketType ticketType, boolean isPlatinum, LocalDate date, MovieType movieType, SeatType seatType){

        // Read base price

        // Ticket Type Modifier (Standard/Child/Senior)
        ticketType = ticketType;

        // Cinema Type Modifier (Standard/Platinum)
        isPlatinum = isPlatinum;

        // Date Modifier (PH/Weekend/Weekday)
        date = date;

        // Movie Type Modifier (2D/3D)
        movieType = movieType;

        // Seat Type Modifier (Standard/Handicap/Premium) [Handicap same pricing as standard]
        seatType = seatType;

        return 0f;
    }
}
