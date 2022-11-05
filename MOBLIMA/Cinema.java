package MOBLIMA;

import java.io.Serializable;

public class Cinema implements Serializable {
    private boolean isPlatinum;
    private String cinemaCode;
    private Cineplex cineplex;
    private Seat[][] seatLayout;

    public Cinema(){
    }

    public Cinema(Cineplex cineplex, boolean isPlatinum, String cinemaCode, Seat[][] seatLayout) {
        this.cineplex = cineplex;
        this.isPlatinum = isPlatinum;
        this.cinemaCode = cinemaCode;
        this.seatLayout = seatLayout;
    }

    public boolean isPlatinum() {
        return isPlatinum;
    }

    public void setPlatinum(boolean isPremium) {
        this.isPlatinum = isPremium;
    }

    public String getCinemaCode() {
        return cinemaCode;
    }

    public void setCinemaCode(String cinemaCode) {
        this.cinemaCode = cinemaCode;
    }

    public Seat[][] getSeatLayout() {
        return seatLayout;
    }

    public void setSeatingLayout(int rows, int cols) {
        Seat[][] seats = new Seat[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                seats[row][col] = new Seat(SeatType.STANDARD, row, col);
            }
            this.seatLayout = seats;
        }
    }

    public String getCineplex() {
        return cineplex.toString();
    }

    public void setCineplex(Cineplex cineplex) {
        this.cineplex = cineplex;
    }

    @Override
    public String toString() {
        return cineplex + " (" + cinemaCode + ")" + (isPlatinum ? " (Platinum)" : "");
    }
}
