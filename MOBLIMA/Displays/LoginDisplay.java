package MOBLIMA.Displays;

import java.util.Scanner;
import java.io.*;

public class LoginDisplay {

    public LoginDisplay(){

    };

    public void displayMenu(){
        int validStaff = 0;

        Scanner sc = new Scanner(System.in);
        int choice = 0;
        while (choice != 3){
            System.out.println("*".repeat(40));
            System.out.println("Welcome to MOBLIMA");
            System.out.println("Where Dreams Become Reality apa ini sia");
            System.out.println("*".repeat(40));
            System.out.println("[1] Continue as user");
            System.out.println("[2] Staff login");
            System.out.println("[3] Exit");
            System.out.print("Enter option: ");

            choice = sc.nextInt();
            switch(choice){
                case 1:
                    UserMenuDisplay oompaloompa = new UserMenuDisplay();
                    oompaloompa.displayMenu();
                    break;
                case 2:
                    System.out.println("*".repeat(40));
                    System.out.println("Staff login");
                    System.out.print("Username: ");
                    String user = sc.next();
                    System.out.print("Password: ");
                    String password = sc.next();
                    String userpass = user + ":" + password;
                    try {
                        boolean valid = false;
                        Scanner reader = new Scanner(new File("data/cinema_staff_login.txt"));
                        while (reader.hasNext()) {
                            String line = reader.nextLine();
                            if (line.equals(userpass)) {
                                valid = true;
                                break;
                            }
                        }
                        if(valid){
                            System.out.println("Welcome " + user + "!");
                            StaffMenuDisplay.displayMenu();
                        }
                        else{
                            System.out.println("Invalid login credentials. Please try again");
                        }
                        reader.close();
                    } catch(IOException e){
                        e.printStackTrace();
                    }
                    break;
                case 69:
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
            }
        }
        System.out.println("Thanks for using MOBLIMA");
        System.exit(69);
    }

}
