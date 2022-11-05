package MOBLIMA;

import java.time.LocalDate;
import java.util.ArrayList;

public class User {
    private String name;
    private String phone;
    private String email;
    private ArrayList<BookingRecord> bookingRecords;
    private LocalDate dateOfBirth;

    private boolean isAdmin;

    public User(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", isSenior=" + isSenior() +
                ", isChild=" + isChild() +
                '}';
    }
}