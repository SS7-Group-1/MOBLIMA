package MOBLIMA.App;

import MOBLIMA.FileHelper;
import MOBLIMA.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that is responsible for the account of all users
 * @author Lim Jia Wei
 * @version 1.5
 * @since 2022-11-07
 */
public class Account {
    ArrayList<User> user_list;
    Scanner sc = new Scanner(System.in);

    /**
     * User class as an attribute in Account class
     */
    public class UserDetail {
        public static User user;
    }

    /**
     * Default Constructor
     * Creates a new account
     * Initialise an array list of User objects by reading from users.dat
     */
    public Account(){
        this.user_list = (ArrayList<User>) FileHelper.read("data/users.dat");
    }

    /**
     * Function that ask user to log in using their email and password
     * @return User object if login successful and NULL if login fails
     */
    public User login(){
        Scanner sc = new Scanner(System.in);
        boolean login = true;
        while(login){
            System.out.println("=".repeat(40));
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

    /**
     * Function that allows user to register their credentials for MOBLIMA account , writes back created user into users.dat
     * Credentials include :
     * String type Email
     * String type Password
     * String type  Phone number
     * String type Date of Birth
     *
     *
     * @return User object  if successful and NULL if unsuccessful
     */
    public User register(){
        Scanner sc = new Scanner(System.in);
        User user = new User();
        System.out.println("=".repeat(40));
        System.out.println("| Create a MOBLIMA account |");

        System.out.print("Name: ");
        String name = sc.next();
        user.setName(name);

        sc.skip("\\R?");
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
        String number = sc.next();
        user.setPhone(number);

        user_list.add(user);
        FileHelper.write(user_list, "data/users.dat");
        System.out.println("Account created!");

        return user;
    }

    /**
     * Function that selects a user from the list and returns that user object
     * @return the selected user object from the list
     */

    public User selectAccount(){
        System.out.println("=".repeat(40));
        System.out.println("User List");
        for (int i = 0; i < user_list.size(); i++) {
            System.out.println(" [" + (i + 1) + "] " + user_list.get(i).getEmail() + (user_list.get(i).isAdmin() ? " (Admin)":""));
        }
        System.out.print("Enter selection: ");
        int add_choice;
        while (true) {
            add_choice = sc.nextInt();
            if (add_choice < 0 || add_choice > user_list.size() + 1) {
                System.out.println("Invalid option. Please try again.");
            } else {
                return user_list.get(add_choice - 1);
            }
        }
    }

    public void updateAccount(){
        User user = UserDetail.user;
        String existingEmail = user.getEmail();
        System.out.println("=".repeat(40));
        System.out.println("Update your account");
        System.out.println(" [1] Email: " + user.getEmail());
        System.out.println(" [2] Password: ********");
        System.out.println(" [3] Phone Number: " + user.getPhone());
        System.out.println(" [0] Go back");
        System.out.print("Enter selection: ");
        int add_choice;
        while (true) {
            add_choice = sc.nextInt();
            if (add_choice < 0 || add_choice > 6) {
                System.out.println("Invalid option. Please try again.");
            } else {
                break;
            }
        }
        switch (add_choice) {
            case 1:
                System.out.print("Enter new email: ");
                sc.skip("\\R?");
                String email = sc.nextLine();
                for (User u : user_list) {
                    if (u.getEmail().equals(email)) {
                        System.out.println("Email already exists. Please try again");
                        return;
                    }
                }
                user.setEmail(email);
                break;
            case 2:
                System.out.print("Enter new password: ");
                sc.skip("\\R?");
                String password = sc.nextLine();
                user.setPassword(password);
                break;
            case 3:
                System.out.print("Enter new phone number: ");
                sc.skip("\\R?");
                String phone = sc.nextLine();
                user.setPhone(phone);
                break;
            case 0:
                return;
        }

        for(int i = 0; i < user_list.size(); i++){
            if(user_list.get(i).getEmail().equals(existingEmail)){
                user_list.set(i, user);
                FileHelper.write(user_list, "data/users.dat");
                System.out.println("Account updated!");
                return;
            }
        }
    }

    /**
     * Functions that set whether a user is an admin
     * @param user - user object that will be used to set to admin
     */
    public void setUserRole(User user){
        System.out.print("Set " + user.getEmail() + " as an admin? (y/N)");
        sc.skip("\\R?");
        user.setAdmin(sc.nextLine().equalsIgnoreCase("y"));
        FileHelper.write(user_list, "data/users.dat");
    }
}
