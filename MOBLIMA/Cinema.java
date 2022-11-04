package MOBLIMA;

import java.io.Serializable;
import java.util.Map;

public class Cinema implements Serializable {
    private boolean isPlatinum;
    private String cinemaCode;
    private Cineplex cineplex;
    private Seat[][] seatLayout;

    public Cinema(Cineplex cineplex, boolean isplat, String code, Seat[][] seatLayout) {
        this.cineplex = cineplex;
        this.isPlatinum = isplat;
        this.cinemaCode = code;
        this.seatLayout = seatLayout;
    }

    public boolean isPlatinum() {
        return isPlatinum;
    }

    public String getCinemaCode() {
        return cinemaCode;
    }

    public Seat[][] getSeatLayout() {
        return seatLayout;
    }

    public String getName() {
        return cineplex.toString();
    }

    @Override
    public String toString() {
        return cineplex + " (" + cinemaCode + ")" + (isPlatinum ? " (Platinum)" : "");
    }

    public void setSeatingLayout(int rows, int cols) {
        Seat[][] seats = new Seat[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                seats[i][j] = new Seat(false);
            }
            this.seatLayout = seats;
        }
    }

    public void setSeatPremium (int row, int col, boolean isPremium) {
        seatLayout[row][col].setPremium(isPremium);
    }
}
