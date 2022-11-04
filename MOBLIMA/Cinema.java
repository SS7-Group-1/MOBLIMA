package MOBLIMA;

import java.io.Serializable;

public class Cinema implements Serializable {
    private boolean isPlatinum;
    private String cinemaCode;
    private Cineplex cineplex;
    private Seat[][] seatLayout;

    public Cinema(Cineplex cineplex, boolean isplat, String code,Seat[][] seatLayout){
        this.cineplex =cineplex;
        this.isPlatinum=isplat;
        this.cinemaCode=code;
        this.seatLayout=seatLayout;
    }

    public boolean isPlatinum(){
        return isPlatinum;
    }

    public String getCinemaCode(){
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
}
