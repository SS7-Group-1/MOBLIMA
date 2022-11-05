package MOBLIMA;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;

public class ShowTime implements Serializable {

    private LocalTime time;
    private Movie movie;
    private Seat[][] seats;
    private LocalDate date;
    private Cinema cinema;

    private int duration;
    public ShowTime(Movie movie){
        this.movie = movie;
    }

    public ShowTime(LocalTime time, Movie movie, Seat[][] seats, LocalDate date, Cinema cinema, int duration){
        this.time=time;
        this.movie=movie;
        this.seats=seats;
        this.date=date;
        this.cinema=cinema;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "ShowTime{" +
                "time='" + time + '\'' +
                ", movie=" + movie +
                ", seats=" + Arrays.toString(seats) +
                ", day=" + date +
                ", cinema=" + cinema +
                '}';
    }

    public void setDay(LocalDate day) {
        this.date = date;
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

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public boolean getSpecificSeats(int row, int col) {
        return seats[row][col].getOccupied();
    }
    public void getAllSeats(){
        //for (int i=0;i<cinema.getSeats_row();i++){
        //    for (int j=0;j< cinema.getSeats_col();j++) {
        //        System.out.println(seats[i][j]);
        //    }
        //}


    }

    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yy");
        return formatter.format(date);
    }

    public String getDay() {
        String day = date.getDayOfWeek().toString();
        return day.substring(0,1).toUpperCase() + day.substring(1).toLowerCase();
    }
}
