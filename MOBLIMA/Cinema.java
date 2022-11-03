package MOBLIMA;

public class Cinema {
    private boolean isPlatinum;
    private String cinemaCode;
    private Cineplex cineplex;
    private int seats_col;
    private int seats_row;

    public Cinema(Cineplex cineplex, boolean isplat, String code,int seats_col,int seats_row){
        this.cineplex =cineplex;
        this.isPlatinum=isplat;
        this.cinemaCode=code;
        this.seats_col=seats_col;
        this.seats_row=seats_row;
    }

    public boolean isPlatinum(){
        return isPlatinum;
    }

    public String getCinemaCode(){
        return cinemaCode;
    }

    public int getSeats_col() {
        return seats_col;
    }

    public int getSeats_row() {
        return seats_row;
    }

    public String getName() {
        return cineplex.toString();
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "isPlatinum=" + isPlatinum +
                ", cinemaCode='" + cinemaCode + '\'' +
                ", cineplex=" + cineplex +
                ", seats_col=" + seats_col +
                ", seats_row=" + seats_row +
                '}';
    }
}
