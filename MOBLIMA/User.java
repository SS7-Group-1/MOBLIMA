package MOBLIMA;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A class that represents Users who are visiting the cinema website
 * @author SS7 Group 1
 * @version 1.0
 * @since 2022-11-07
 */
public class User implements Serializable {
    /**
     * email of the user
     */
    private String email;
    /**
     * phone number of the user
     */
    private String phone;
    /**
     * password for user's account
     */
    private String password;
    /**
     * array list storing booking records of user
     */
    private ArrayList<BookingRecord> bookingRecords;
    /**
     * birthdate of user
     */
    private LocalDate dateOfBirth;
    /**
     * flag to denote if user is an admin or not an admin
     */
    private boolean isAdmin;

    /**
     * creates a new User (default constructor)
     */
    public User(){
    }

    /**
     * creates a new User with email, phone, password, dateOfBirth, isAdmin initialised to the respective arguments passed in
     * @param email email of the user
     * @param phone phone number of the user
     * @param password password for user's account
     * @param dateOfBirth birthdate of user
     * @param isAdmin flag to denote if user is an admin or not an admin
     * also initialises bookingRecords to a new array list
     */
    public User(String email, String phone, String password, LocalDate dateOfBirth, boolean isAdmin) {
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.isAdmin = isAdmin;
        this.bookingRecords = new ArrayList<>();
    }

    /**
     * Accessor for phone number of the user
     * @return phone: phone number of the user
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Mutator for phone number of the user
     * @param phone phone number of the user
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Accessor for email of the user
     * @return email: email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Mutator for email of the user
     * @param email email of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Accessor for password for user's account
     * @return password: password for user's account
     */
    public String getPassword() {
        return password;
    }

    /**
     * Mutator for password for user's account
     * @param password password for user's account
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Mutator for birthdate of user
     * @param dateOfBirth birthdate of user
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Accessor for birthdate of user
     * @return dateOfBirth: birthdate of user
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * checks if user is a child (12 years old and below)
     * @return boolean value to denote if user is a child
     */
    public boolean isChild() {
        return dateOfBirth.isAfter(LocalDate.now().minusYears(12));
    }

    /**
     * checks if user is a senior (60 years and above)
     * @return boolean value to denote if user is a senior
     */
    public boolean isSenior() {
        return dateOfBirth.isBefore(LocalDate.now().minusYears(60));
    }

    /**
     * returns booking records of user
     * @return bookingRecords: array list storing booking records of user
     */
    public ArrayList<BookingRecord> getBookingRecords() {
        return bookingRecords;
    }

    /**
     * Mutator for booking records of user
     * @param bookingRecords array list storing booking records of user
     */
    public void setBookingRecords(ArrayList<BookingRecord> bookingRecords) {
        this.bookingRecords = bookingRecords;
    }

    /**
     * return if user is an admin or not an admin
     * @return boolean value to denote if user is an admin
     */
    public Boolean isAdmin() {
        return isAdmin;
    }

    /**
     * Mutator for boolean flag to denote if user is an admin
     * @param admin boolean value to denote if user is an admin or not an admin
     */
    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

}