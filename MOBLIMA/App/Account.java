package MOBLIMA.App;

import MOBLIMA.FileHelper;
import MOBLIMA.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Account {
    ArrayList<User> user_list;

    public class UserDetail {
        public static User user;
    }

    public Account(){
        this.user_list = (ArrayList<User>) FileHelper.read("data/users.dat");
    }

    public User login(){
        Scanner sc = new Scanner(System.in);
        boolean login = true;
        while(login){
            System.out.println("▭".repeat(40));
            System.out.println("| Login to MOBLIMA |");
            System.out.print("Email: ");
            String email = sc.next();
            System.out.print("Password: ");
            String password = sc.next();
            for (User user : user_list) {
                if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                    return user;
                }
            }
            System.out.println("Invalid login credentials. Try again? (Y/n)");
            sc.skip("\\R?");
            if (sc.nextLine().equalsIgnoreCase("n")) {
                login = false;
            }
        }
        return null;
    }

    public User register(){
        Scanner sc = new Scanner(System.in);
        User user = new User();
        System.out.println("▭".repeat(40));
        System.out.println("| Create a MOBLIMA account |");

        System.out.print("Email: ");
        String email = sc.next();
        for (User u : user_list) {
            if (u.getEmail().equals(email)) {
                System.out.println("Email already exists. Please try again");
                return null;
            }
        }
        user.setEmail(email);

        System.out.print("Password: ");
        sc.skip("\\R?");
        String password = sc.next();
        user.setPassword(password);

        System.out.print("Phone Number: ");
        sc.skip("\\R?");
        String name = sc.next();
        user.setPhone(name);

        System.out.print("Date of Birth (DD/MM/YYYY): ");
        sc.skip("\\R?");
        String dob = sc.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate localDate = LocalDate.parse(dob, formatter);
        user.setDateOfBirth(localDate);

        user_list.add(user);
        FileHelper.write(user_list, "data/users.dat");
        System.out.print("Account created!");

        return user;
    }

    public void updateAccount(){

    }

}
