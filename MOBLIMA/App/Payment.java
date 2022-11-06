package MOBLIMA.App;

import java.util.Scanner;

public class Payment {
    Scanner sc = new Scanner(System.in);
    public boolean pay(float amount){
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
        return true;
    }
}
