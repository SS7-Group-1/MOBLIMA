package MOBLIMA.Displays;

import java.io.IOException;
import java.util.Scanner;

public class AllStaffDisplay {
    public AllStaffDisplay(){

    }

    public static void displayMenu(){
        Scanner sc = new Scanner(System.in);

        System.out.println("*".repeat(40));
        System.out.println("Staff Display Menu");

        int choice = 0;

        while (choice != 3){
            System.out.println("[1] Add/Remove/Update Movie");
            System.out.println("[2] Add/Remove/Update Show Timings");
            System.out.println("[3] Configure System Settings");
            System.out.println("[4] Exit");

            choice = sc.nextInt();
            switch(choice){
                case 1:
                    try{
                        MovieListingStaff bilbo = new MovieListingStaff();
                        bilbo.DisplayMenu();
                    } catch(IOException e){
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    ShowTimeStaff frodo = new ShowTimeStaff();
                    frodo.DisplayMenu();
                    break;
                case 3:
                    SystemSettingStaff sam = new SystemSettingStaff();
                    sam.DisplayMenu();
                    break;
            }
        }
    }

}
