package MOBLIMA;

import java.util.Arrays;

public class ShowTime {

    private String time;
    private Movie movie;
    private Seat[][] seats;
    private Day day;
    private Cinema cinema;


    public ShowTime(){

    }
    public ShowTime(String time, Movie movie, Seat[][] seats, Day day, Cinema cinema){
        this.time=time;
        this.movie=movie;
        this.seats=seats;
        this.day=day;
        this.cinema=cinema;

    }

    @Override
    public String toString() {
        return "ShowTime{" +
                "time='" + time + '\'' +
                ", movie=" + movie +
                ", seats=" + Arrays.toString(seats) +
                ", day=" + day +
                '}';
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setSeats(Seat[][] seats) {
        this.seats = seats;
    }

    public Movie getMovie() {
        return movie;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



    public boolean getSpecificSeats(int row, int col) {
        return seats[row][col].getOccupied();
    }
    public void getAllSeats(){
        for (int i=0;i<cinema.getSeats_row();i++){
            for (int j=0;j< cinema.getSeats_col();j++) {
                System.out.println(seats[i][j]);
            }
        }


    }

}
