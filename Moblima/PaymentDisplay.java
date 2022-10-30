package MOBLIMA;
import java.util.Scanner; // Import the Scanner class to read text files
import java.text.SimpleDateFormat;
import java.util.Date;

public class PaymentDisplay {
    Scanner sc = new Scanner(System.in);

    private String transactionID;
    private float ticketPrice;
    private ShowTime showtime;
    private boolean paymentSuccessful;

    public PaymentDisplay(float ticketPrice,ShowTime showtime){
        //this.transactionID=transactionID;
        this.ticketPrice=ticketPrice;
        this.showtime=showtime;
        this.paymentSuccessful=false;
    }

    public void confirmPayment(ShowTime showtime){
        System.out.println("Credit card number:");
        sc.skip("\\R?");
        String credit_card = sc.nextLine();

        System.out.println("Pin code:");
        sc.skip("\\R?");
        String pin = sc.nextLine();

        System.out.println("Expiry date:");
        sc.skip("\\R?");
        String expiry_date = sc.nextLine();
        System.out.println("Payment successful.");
        //make paymentsuccess true;
        this.paymentSuccessful=true;
        //call bookingRecord
        //Customer cust = new Customer();

    }

    public String generateTid(){
        String timestamp = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());

        System.out.println(showtime.getCinema().getCinemaCode() + timestamp);
        this.transactionID=showtime.getCinema().getCinemaCode() + timestamp;
        return transactionID;
    }

    public void displayMenu() {

        Scanner sc = new Scanner(System.in);
        int choice = 0;
        while(choice!=3) {
            System.out.println("1. Review selected showtime");
            System.out.println("2. Make Payment");
            System.out.println("3. Exit Payment Menu");

            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println(showtime);
                    break;
                case 2:
                    confirmPayment(showtime);
                    if (this.paymentSuccessful) {
                        choice=3;
                    }


                    break;
                case 3:
                    break;


            }
        }
    }

    @Override
    public String toString() {
        return "PaymentDisplay{" +
                "transactionID='" + transactionID + '\'' +
                ", ticketPrice=" + ticketPrice +
                ", showtime=" + showtime +
                '}';
    }

    public boolean isPaymentSuccessful() {
        return paymentSuccessful;
    }
}
