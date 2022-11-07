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

/**
 * A class that represents the showtime for movie
 * @author SS7 Group 1
 * @version 1.0
 * @since 2022-11-07
 */
public class ShowTime implements Serializable {

    /**
     * Represents both a date and a time
     */
    private LocalDateTime dateTime;
    /**
     * Represents a cinema object created using Cinema class
     */
    private Cinema cinema;
    /**
     * Represents a movie object created using Movie class
     */
    private Movie movie;
    /**
     * Represents the seatings for a showtime of a movie
     */
    private Seat[][] seats;
    /**
     * Unique showTime ID in the form of a randomly generated UUID
     */
    private String showTimeId;

    /**
     * creates a new ShowTime with movie object created with Movie class being passed in as an argument
     * movie object and dateTime object has been initialised below
     * @param movie represents a movie that is being screened in cinema
     * calls the mutator for the showTimeId
     */
    public ShowTime(Movie movie){
        this.movie = movie;
        this.dateTime = LocalDateTime.now();
        setShowTimeId();
    }

    /**
     * creates a new ShowTime with movie object created with Movie class, cinema object created with Cinema class, dateTime objected with LocalDateTime being passed in as an argument
     * @param cinema represents a cinema object created using Cinema class
     * @param movie represents a movie object created using Movie class
     * @param dateTime represents both a date and a time
     * calls the mutator for the showTimeId
     */
    public ShowTime(Cinema cinema, Movie movie, LocalDateTime dateTime) {
        this.movie = movie;
        this.cinema = cinema;
        this.seats = cinema.getSeatLayout().clone();
        this.dateTime = dateTime;
        setShowTimeId();
    }

    /**
     * Accessor to get showTimeId
     * @return
     */
    public String getShowTimeId() {
        return showTimeId;
    }

    /**
     * Mutator to set showTimeId to a randomly generated UUID
     */
    private void setShowTimeId() {
        this.showTimeId = String.valueOf(java.util.UUID.randomUUID());
    }

    /**
     * Mutator to set movie to a movie object passed into the method
     * @param movie represents a movie object created using Movie class
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    /**
     * Accessor to get the seatings for a showtime of a movie
     * @return
     */
    public Seat[][] getSeats(){
        return seats;
    }

    /**
     * Mutator to set the seatings for a showtime of a movie
     * @param seats seatings for a showtime of a movie
     */
    public void setSeats(Seat[][] seats) {
        this.seats = seats;
    }

    /**
     * checks if the seat chosen is occupied or not occupied
     * @param row the row of the seat chosen
     * @param col the column of the seat chosen
     * @return if the seat chosen is occupied or not occupied
     */
    public boolean checkIfOccupied(int row, int col) {
        return seats[row][col].isOccupied();
    }

    /**
     * Accessor to get the movie object created using a Movie class
     * @return
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * Accessor to get the cinema object created using a Cinema class
     * @return
     */
    public Cinema getCinema() {
        return cinema;
    }

    /**
     * Mutator to set the cinema object created using a Cinema class
     * @param cinema represents a cinema object created using the Cinema class
     */
    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
        this.seats = cinema.getSeatLayout().clone();
    }

    /**
     * Accessor to get the dateTime object created using LocalDateTime class
     * @return
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Mutator to set the dateTime object created using LocalDateTime class
     * @param dateTime
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Accessor to get the DateTime value
     * @return a DateTime value that is converted from UTC to local time
     */
    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    /**
     * Mutator to set the DateTime value
     * @param time time object created using LocalTime class
     */
    public void setTime(LocalTime time) {
        this.dateTime = LocalDateTime.of(dateTime.toLocalDate(), time);
    }

    /**
     * Accessor to get the date in the format of "dd MMM yy"
     * @return
     */
    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yy");
        return dateTime.toLocalDate().format(formatter);
    }

    /**
     * Mutator to change the date
     * @param date
     */
    public void setDate(LocalDate date) {
        this.dateTime = LocalDateTime.of(date, dateTime.toLocalTime());
    }

    /**
     * Accessor to get the day
     * @return
     */
    public String getDay() {
        String day = dateTime.getDayOfWeek().toString();
        return day.substring(0,1).toUpperCase() + day.substring(1).toLowerCase();
    }

    /**
     *
     * @return a string containing details of the movie, consisting of movie title, cinema that it is being screened at, its showtime as well as its showTimeId
     */
    @Override
    public String toString() {
        return movie.getTitle() + " " + cinema.toString() + " " + getDay() + ", " + getDate() + " - " + getTime() + " (" + showTimeId + ")";
    }
}
