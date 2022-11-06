package MOBLIMA;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class ShowTime implements Serializable {

    private LocalDateTime dateTime;
    private Cinema cinema;
    private Movie movie;
    private Seat[][] seats;
    private String showTimeId;

    public ShowTime(Movie movie){
        this.movie = movie;
        this.dateTime = LocalDateTime.now();
        setShowTimeId();
    }

    public ShowTime(Cinema cinema, Movie movie, LocalDateTime dateTime) {
        this.movie = movie;
        this.cinema = cinema;
        this.seats = cinema.getSeatLayout().clone();
        this.dateTime = dateTime;
        setShowTimeId();
    }

    public String getShowTimeId() {
        return showTimeId;
    }

    private void setShowTimeId() {
        this.showTimeId = String.valueOf(java.util.UUID.randomUUID());
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Seat[][] getSeats(){
        return seats;
    }

    public void setSeats(Seat[][] seats) {
        this.seats = seats;
    }

    public boolean checkIfOccupied(int row, int col) {
        return seats[row][col].isOccupied();
    }

    public Movie getMovie() {
        return movie;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
        this.seats = cinema.getSeatLayout().clone();
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public void setTime(LocalTime time) {
        this.dateTime = LocalDateTime.of(dateTime.toLocalDate(), time);
    }

    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yy");
        return dateTime.toLocalDate().format(formatter);
    }

    public void setDate(LocalDate date) {
        this.dateTime = LocalDateTime.of(date, dateTime.toLocalTime());
    }

    public String getDay() {
        String day = dateTime.getDayOfWeek().toString();
        return day.substring(0,1).toUpperCase() + day.substring(1).toLowerCase();
    }

    @Override
    public String toString() {
        return movie.getTitle() + " " + cinema.toString() + " " + getDay() + ", " + getDate() + " - " + getTime() + " (" + showTimeId + ")";
    }
}
