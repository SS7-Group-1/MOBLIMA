package MOBLIMA;

public class BookingRecord {
    private Customer customer;
    private String transactionID;
    private Seat seat;

    public BookingRecord(Customer customer, String tid, Seat seat){
        this.customer=customer;
        this.transactionID=tid;
        this.seat=seat;

    }

    @Override
    public String toString() {
        return "BookingRecord{" +
                "customer=" + customer +
                ", transactionID='" + transactionID + '\'' +
                ", seat=" + seat +
                '}';
    }
}
