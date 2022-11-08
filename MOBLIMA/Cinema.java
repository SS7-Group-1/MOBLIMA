package MOBLIMA;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Cinema class
 * @author SS7 Group 1
 * @version 1.0
 * @since 2022-11-07
 */
public class Cinema implements Serializable {
    /**
     * Whether Cinema is Platinum suite or not.
     */
    private boolean isPlatinum;
    /**
     * Unique Cinema code in format XXX.
     */
    private String cinemaCode;
    /**
     * Cineplex that Cinema belongs to.
     */
    private Cineplex cineplex;
    /**
     * Seat layout within the cinema.
     */
    private Seat[][] seatLayout;

    /**
     * Aisle location
     */
    private ArrayList<Integer> aisle;

    /**
     * Default Constructor.
     */


    public Cinema(){
    }

    /**
     *Creates a Cinema class with attributes initialised.
     * @param cineplex Cineplex that this Cinema belongs to.
     * @param isPlatinum Whether Cinema is a platinum suite or not.
     * @param cinemaCode Unique Cinema code.
     * @param seatLayout Cinema seat layout.
     */
    public Cinema(Cineplex cineplex, boolean isPlatinum, String cinemaCode, Seat[][] seatLayout,ArrayList<Integer> aisle) {
        this.cineplex = cineplex;
        this.isPlatinum = isPlatinum;
        this.cinemaCode = cinemaCode;
        this.seatLayout = seatLayout;
        this.aisle= aisle;
    }

    /**
     * Accessor for IsPlatinum.
     * @return Boolean: Whether Cinema is platinum suite.
     */
    public boolean isPlatinum() {
        return isPlatinum;
    }

    /**
     * Mutator for IsPlatinum.
     * @param isPremium Boolean to change to.
     */
    public void setPlatinum(boolean isPremium) {
        this.isPlatinum = isPremium;
    }

    /**
     * Accessor for Cinema code.
     * @return String: Cinema code in string format.
     */
    public String getCinemaCode() {
        return cinemaCode;
    }

    /**
     * Mutator for Cinema code.
     * @param cinemaCode Cinema code to change to.
     */
    public void setCinemaCode(String cinemaCode) {
        this.cinemaCode = cinemaCode;
    }

    /**
     * Accessor for seat layout.
     * @return Seat[][]: Cinema seat layout.
     */
    public Seat[][] getSeatLayout() {
        return seatLayout;
    }

    /**
     * Mutator for seat layout.
     * @param rows Number of rows in new seat layout.
     * @param cols Number of columns in new seat layout.
     */
    public void setSeatingLayout(int rows, int cols) {
        Seat[][] seats = new Seat[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                seats[row][col] = new Seat(SeatType.STANDARD, row, col);
            }
            this.seatLayout = seats;
        }
    }

    /**
     * Accessor for aisle
     * @return array list for aisle
     */
    public ArrayList<Integer> getAisle()
    {
        return this.aisle;
    }

    /**
     * Accessor for Cineplex.
     * @return String: Cineplex in String format.
     */
    public String getCineplex() {
        return cineplex.toString();
    }

    /**
     * Mutator for Cineplex.
     * @param cineplex Cineplex to change to.
     */
    public void setCineplex(Cineplex cineplex) {
        this.cineplex = cineplex;
    }

    /**
     * Mutator for aisle
     * @param aisle aisle to change to
     */
    public void setAisle(ArrayList<Integer> aisle)
    {
        this.aisle=aisle;
    }

    @Override
    public String toString() {
        return cineplex + " - " + cinemaCode + " " + (isPlatinum ? "(Platinum)" : "");
    }
}
