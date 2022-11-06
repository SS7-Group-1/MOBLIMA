package MOBLIMA;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class User implements Serializable {
    private String email;
    private String phone;
    private String password;
    private ArrayList<BookingRecord> bookingRecords;
    private LocalDate dateOfBirth;
    private boolean isAdmin;

    public User(){
    }

    public User(String email, String phone, String password, LocalDate dateOfBirth, boolean isAdmin) {
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.isAdmin = isAdmin;
        this.bookingRecords = new ArrayList<>();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public boolean isChild() {
        return dateOfBirth.isAfter(LocalDate.now().minusYears(12));
    }

    public boolean isSenior() {
        return dateOfBirth.isBefore(LocalDate.now().minusYears(60));
    }

    public ArrayList<BookingRecord> getBookingRecords() {
        return bookingRecords;
    }

    public void setBookingRecords(ArrayList<BookingRecord> bookingRecords) {
        this.bookingRecords = bookingRecords;
    }

    public Boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "Customer{" +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", isSenior=" + isSenior() +
                ", isChild=" + isChild() +
                '}';
    }
}