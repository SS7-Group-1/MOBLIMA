package MOBLIMA.App;

import MOBLIMA.FileHelper;
import MOBLIMA.Movie;
import MOBLIMA.User;
import MOBLIMA.Voucher;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that handles all the payment of a movie
 * @since 2022-11-07
 * @author Lim Jia Wei
 * @version 1.4
 */
public class Payment {
    Scanner sc = new Scanner(System.in);
    ArrayList<Voucher> voucher_list;

    /**
     * Default Constructor
     * Initialises an array of list of Voucher type by reading from voucher.dat
     */
    public Payment(){
        this.voucher_list = (ArrayList<Voucher>) FileHelper.read("data/vouchers.dat");
    }

    /**
     * function that request information from users for payment
     * @param amount - amount of money to be paid after discounts
     * @return total amount of money to be paid by user
     */
    public float pay(float amount){
        System.out.println("▭".repeat(40));
        System.out.println("Payment for $" + amount);

        System.out.println("Redeem voucher code? (y/N): ");
        sc.skip("\\R?");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            boolean found = false;
            while(!found){
                System.out.print("Enter voucher code: ");
                String voucherCode = sc.nextLine();
                for (Voucher voucher : voucher_list) {
                    if (voucher.getVoucherCode().equals(voucherCode)) {
                        if(voucher.isValid()){
                            found = true;
                            System.out.println("Voucher code successfully redeemed");
                            System.out.println("Previous amount: $" + amount);
                            amount -= voucher.getAmount();
                            System.out.println("Voucher amount: -$" + voucher.getAmount());
                            if(amount < 0){
                                amount = 0;
                            }
                            System.out.println("New total amount: $" + amount);
                            voucher.setMaxUses(voucher.getMaxUses() - 1);
                            FileHelper.write(voucher_list, "data/vouchers.dat");
                        }
                    }
                }
                if(!found){
                    System.out.println("Voucher code not found. Try again? (Y/n)");
                    sc.skip("\\R?");
                    if (sc.nextLine().equalsIgnoreCase("n")) {
                        found = true;
                    }
                }
            }
        }
        if(amount > 0){
            System.out.println("▭".repeat(40));
            System.out.println("Give us your money");
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
        }
        else{
            System.out.println("No payment required.");
        }
        return amount;
    }

    /**
     * Function that prints all voucher codes and their corresponding information
     */
    public void printAllVoucherCodes(){
        System.out.println("▭".repeat(40));
        System.out.println("Voucher codes");
        for (Voucher voucher: voucher_list){
            System.out.println(voucher.getVoucherCode() + ": $" + voucher.getAmount() + " (" + (voucher.unlimitedUses() ? "Unlimited uses" : voucher.getMaxUses() + " uses left") + ")");
        }
    }

    /**
     * Functions that adds a new voucher code and their corresponding information
     * Updates voucher.dat after adding a new voucher code
     */
    public void addVoucherCode(){
        Voucher voucher = new Voucher();
        System.out.println("▭".repeat(40));
        System.out.println("Add voucher code");
        System.out.print("Enter voucher code: ");
        String code = sc.nextLine();
        for (Voucher nigel : voucher_list) {
            if (nigel.getVoucherCode().equals(code)) {
                System.out.println("Voucher code already exists");
                return;
            }
        }
        voucher.setVoucherCode(sc.nextLine());
        System.out.println("Enter voucher amount: ");
        sc.skip("\\R?");
        voucher.setAmount(sc.nextFloat());
        System.out.println("Unlimited redemptions? (y/N): ");
        sc.skip("\\R?");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            voucher.setMaxUses(-1);
        }
        else{
            System.out.println("Enter max redemptions: ");
            voucher.setMaxUses(sc.nextInt());
            sc.skip("\\R?");
        }

        System.out.println("Voucher code added.");
        voucher_list.add(voucher);
        FileHelper.write(voucher_list, "data/vouchers.dat");
    }

    /**
     * Function that allows the update of voucher code and their corresponding information
     * updates voucher.dat after updating voucher information
     */
    public void updateVoucherCode(){
        System.out.println("▭".repeat(40));
        System.out.println("Select voucher to update");
        Voucher voucher = selectVoucher();
        System.out.println("▭".repeat(40));
        System.out.println("Updating " + voucher.getVoucherCode());
        System.out.println("Select field to edit");
        System.out.println("[1] Voucher code ");
        System.out.println("[2] Redemptions ");
        System.out.println("[3] Amounts ");
        System.out.print("Enter option: ");
        switch (sc.nextInt()) {
            case 1:
                System.out.print("Enter new voucher code: ");
                String code = sc.nextLine();
                for (Voucher nigel : voucher_list) {
                    if (nigel.getVoucherCode().equals(code)) {
                        System.out.println("Voucher code already exists");
                        return;
                    }
                }
                voucher.setVoucherCode(sc.nextLine());
                break;
            case 2:
                System.out.print("Enter new max redemptions: ");
                System.out.println("Unlimited redemptions? (y/N): ");
                sc.skip("\\R?");
                if (sc.nextLine().equalsIgnoreCase("y")) {
                    voucher.setMaxUses(-1);
                }
                else{
                    System.out.println("Enter max redemptions: ");
                    voucher.setMaxUses(sc.nextInt());
                    sc.skip("\\R?");
                }
                break;
            case 3:
                System.out.print("Enter new amount: ");
                voucher.setAmount(sc.nextFloat());
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
        System.out.println("Voucher code updated.");
        FileHelper.write(voucher_list, "data/vouchers.dat");
    }

    /**
     * Function that removes a Voucher
     * updates voucher.dat after removing voucher
     */

    public void removeVoucherCode(){
        System.out.println("Select voucher to update");
        Voucher voucher = selectVoucher();
        voucher_list.remove(voucher);
        FileHelper.write(voucher_list, "data/vouchers.dat");
        System.out.print("Voucher removed");
    }

    /**
     * Allows user to select a specific voucher
     * @return selected voucher
     */
    public Voucher selectVoucher(){
        for (int i = 0; i < voucher_list.size(); i++) {
            System.out.println(" [" + (i + 1) + "] " + voucher_list.get(i).getVoucherCode());
        }
        System.out.print("Enter selection: ");
        int add_choice;
        while (true) {
            add_choice = sc.nextInt();
            if (add_choice < 0 || add_choice > voucher_list.size() + 1) {
                System.out.println("Invalid option. Please try again.");
            } else {
                return voucher_list.get(add_choice - 1);
            }
        }
    }
}
