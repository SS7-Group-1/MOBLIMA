package MOBLIMA.Displays;

import java.util.Scanner;
import java.io.*;

public class LoginDisplay {

    public LoginDisplay(){

    };

    public void displayMenu(){
        int valid = 0;
        int validStaff = 0;

        Scanner sc = new Scanner(System.in);

        System.out.println("*".repeat(40));
        System.out.println("Welcome to MOBLIMA");
        System.out.println("Where Dreams Become Reality");

        int choice = 0;
        while (choice != 4){
            System.out.println("[1] Staff Login");
            System.out.println("[2] Create New Staff Account");
            System.out.println("[3] Continue as Guest");
            System.out.println("[4] Exit");

            choice = sc.nextInt();
            switch(choice){
                case 1:
                    System.out.print("Username: ");
                    String user = sc.next();
                    System.out.print("Password: ");
                    String password = sc.next();
                    String userpass = user + ":" + password;
                    System.out.println("Logging in....");
                    try {
                        Scanner reader = new Scanner(new File("C:\\Users\\yinji\\Documents\\GitHub\\MOBLIMA\\Cinema.old\\cinema_staff_login.txt"));
                        while (reader.hasNext()) {
                            String line = reader.nextLine();
                            if (line.equals(userpass)) {
                                System.out.println("Successful Login");
                                System.out.println("Welcome " + user + "!");
                                valid = 1;
                                break;
                            }
                        }
                        if (valid == 0){
                            System.out.println("No valid account found.");
                            System.out.println("Try again or Create an account");
                        }
                        reader.close();
                    } catch(IOException e){
                        e.printStackTrace();
                    }
                    if (valid == 1){
                        //call display that lists all staff displays menu
                        AllStaffDisplay.displayMenu();
                        choice = 4;
                    }
                    break;
                case 2:
                    System.out.println("Enter New Username: ");
                    String newUser = sc.next();
                    System.out.println("Enter New Password");
                    String newPass = sc.next();
                    String newUserpass = newUser + ":" + newPass;

                    //verifying whether it is actually staff
                    try {
                        Scanner reader = new Scanner(new File("C:\\Users\\yinji\\Documents\\GitHub\\MOBLIMA\\Cinema.old\\staff_names.txt"));
                        while (reader.hasNext()) {
                            String s = reader.nextLine();
                            if (s.equals(newUser)) {
                                System.out.println("Valid Staff");
                                System.out.println("Creating new account...");
                                validStaff = 1;
                                break;
                            }
                        }
                        if (validStaff == 0){
                            System.out.println("Invalid Staff ID");
                            System.out.println("Try again or Continue as Guest");
                        }
                        reader.close();
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                    if (validStaff == 1){
                        try{
                            FileWriter fw = new FileWriter("C:\\Users\\yinji\\Documents\\GitHub\\MOBLIMA\\Cinema.old\\cinema_staff_login.txt", true);
                            PrintWriter pw = new PrintWriter(fw);
                            pw.println();
                            pw.println(newUserpass);
                            System.out.println("Account Successfully Created!");
                            pw.close();
                        } catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    //call class that displays all guest displays?

                    choice = 4;
                    break;
            }
        }
        System.out.println("Thanks for using MOBLIMA");
    }

}
