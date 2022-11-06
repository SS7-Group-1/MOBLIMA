package MOBLIMA.App;

import MOBLIMA.User;

import java.util.Scanner;

public class Payment {
    Scanner sc = new Scanner(System.in);
    public boolean pay(float amount){
        System.out.println("▭".repeat(40));
        System.out.println("Payment for $" + amount);

        System.out.println("Redeem voucher code? (y/N): ");
        sc.skip("\\R?");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Enter voucher code: ");
            String voucherCode = sc.nextLine();
        }

        System.out.println("Card number:");
        sc.skip("\\R?");
        String credit_card = sc.nextLine();

        System.out.println("CVV");
        sc.skip("\\R?");
        String pin = sc.nextLine();

        System.out.println("Expiry date:");
        sc.skip("\\R?");
        String expiry_date = sc.nextLine();
        System.out.println("Payment successful.");
        return true;
    }
}
