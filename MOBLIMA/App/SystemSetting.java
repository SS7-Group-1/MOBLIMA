package MOBLIMA.App;

import MOBLIMA.FileHelper;
import MOBLIMA.Movie;
import MOBLIMA.User;
//import MOBLIMA.Tests.generateDataFiles;

import java.io.*;

import java.io.File;
import java.util.*;
import java.util.Map.Entry;


public class SystemSetting {
    ArrayList<Movie> movie_list = new ArrayList<>();

    public void DisplayMenu()
    {
        Boolean set = Boolean.TRUE;
        Scanner sc = new Scanner(System.in);

        while(set)
        {
             System.out.println("▭".repeat(40));
                System.out.println("│ System setting menu │");
                System.out.println("[1] Configure ticket price");
                System.out.println("[2] Show top 5 movies by rating");
                System.out.println("[3] Show top 5 movies by sales");
                System.out.println("[4] Configure User Top 5 permission");
                System.out.println("[5] Configure User Role");
                System.out.println("[6] Manage vouchers");
                System.out.println("[0] Go back");
                System.out.println("Select an option");
                try {
                    int choice = sc.nextInt();
                    switch (choice) {
                        case 1 -> configureTicketPrice();
                        case 2 -> {
                            Movies movies = new Movies();
                            movies.displayTop5rating();
                        }
                        case 3 ->{
                            Movies movies = new Movies();
                            movies.displayTop5sales();
                        }
                        case 4 -> configureUserPermission();
                        case 5->{
                            Account account = new Account();
                            User user = account.selectAccount();
                            if(user != null){
                                account.setUserRole(user);
                            }
                        }
                        case 6->{
                            int choice2 = -1;
                            while(choice2 != 0){
                                System.out.println("▭".repeat(40));
                                System.out.println("[1] View all vouchers");
                                System.out.println("[2] Add voucher");
                                System.out.println("[3] Update voucher");
                                System.out.println("[4] Remove voucher");
                                System.out.println("[0] Go back");
                                System.out.println("Select an option");
                                choice2 = sc.nextInt();
                                Payment payment = new Payment();
                                switch (choice2) {
                                    case 1 -> {
                                        payment.printAllVoucherCodes();
                                    }
                                    case 2 -> {
                                        payment.addVoucherCode();
                                    }
                                    case 3 -> {
                                        payment.updateVoucherCode();
                                    }
                                    case 4 -> {
                                        payment.removeVoucherCode();
                                    }
                                    case 0 -> set = Boolean.FALSE;
                                    default -> System.out.println("Invalid option");
                                }
                            }
                        }
                        case 0 -> set = Boolean.FALSE;
                    }
                }
                catch (Exception e)
                    {
                        System.out.println("Invalid option");
                        set=Boolean.FALSE;
                    }
            }
    }

    public void configureUserPermission(){
        Scanner sc = new Scanner(System.in);
        try{
            FileWriter fw = new FileWriter("data/UserPermission.txt");
            System.out.println("*".repeat(40));
            System.out.println("[1] Allow user to view top 5 movies by ratings only");
            System.out.println("[2] Allow user to view top 5 movies by sales only");
            System.out.println("[3] Allow both");

            int choice = sc.nextInt();
            switch(choice){
                case 1:
                    fw.write('1');
                    System.out.println("User can only view top 5 movies by ratings");
                    break;
                case 2:
                    fw.write('2');
                    System.out.println("Usre can only view top 5 movies by sales");
                    break;
                case 3:
                    fw.write('0');
                    System.out.println("User can view top 5 movies by ratings or sales");
                    break;
            }
            fw.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void configureTicketPrice()
    {
        Scanner sc = new Scanner(System.in);
        Boolean set = Boolean.TRUE;
        while (set)
        {
            System.out.println("Dear User, Please Select Option that you wish to view");
            System.out.println("1: View all Configuration");
            System.out.println("2: Configure Ticket Price");
            System.out.println("3: To terminate programme");
            int choice = sc.nextInt();
            String EModifier=""; String EINT="";String ESymbol="";
            switch(choice)
            {
                case 1:
                    printTicket();
                    break;
                case 2:
                        System.out.println("Please Enter Modifier too be edited");
                        System.out.println("[1] Adult / Child / Senior");
                        System.out.println("[2] Weekday / Weekends / Public Holiday");
                        System.out.println("[3] 2D / 3D");
                        System.out.println("[4] Standard / Premium Seats");
                        System.out.println("[5] Standard/ Platinum Cinema");
                        int pick = sc.nextInt();
                        switch (pick) {
                            case 1:
                                System.out.println("Please select 1 for Adult");
                                System.out.println("Please select 2 for Child");
                                System.out.println("Please select 3 for Senior");
                                int adult = sc.nextInt();
                                switch (adult) {
                                    case 1:
                                        EModifier = "Adult";
                                        break;
                                    case 2:
                                        EModifier = "Child";
                                        break;
                                    case 3:
                                        EModifier = "Senior";
                                        break;
                                }
                                break;
                            case 2:
                                System.out.println("Please select 1 for Weekday");
                                System.out.println("Please select 2 for Weekends");
                                System.out.println("Please select 3 for Public Holiday");
                                int day = sc.nextInt();
                                switch (day) {
                                    case 1:
                                        EModifier = "Weekday";
                                        break;
                                    case 2:
                                        EModifier = "Weekend";
                                        break;
                                    case 3:
                                        EModifier = "PublicHoliday";
                                        break;
                                }
                                break;
                            case 3:
                                System.out.println("Please select 1 for 2D");
                                System.out.println("Please select 2 for 3D");
                                int D = sc.nextInt();
                                switch (D) {
                                    case 1:
                                        EModifier = "2D";
                                        break;
                                    case 2:
                                        EModifier = "3D";
                                        break;
                                }
                                break;
                            case 4:
                                System.out.println("Please select 1 for Standard Seats");
                                System.out.println("Please select 2 for Premium Seats");
                                int seat = sc.nextInt();
                                switch (seat) {
                                    case 1:
                                        EModifier = "StandardSeat";
                                        break;
                                    case 2:
                                        EModifier = "PremiumSeat";
                                        break;
                                }
                                break;
                            case 5:
                                System.out.println("Please select 1 for Standard Cinema");
                                System.out.println("Please select 2 for Platinum Cinema");
                                int cin = sc.nextInt();
                                switch (cin) {
                                    case 1:
                                        EModifier = "StandardCinema";
                                        break;
                                    case 2:
                                        EModifier = "PlatinumCinema";
                                        break;
                                }
                                break;
                        }

                    System.out.println("Select Operations to be done on modifier");
                    System.out.println("1) Addition");
                    System.out.println("2) Subtraction");
                    int arith = sc.nextInt();
                    switch(arith)
                    {
                        case 1:
                            ESymbol="+";
                            break;
                        case  2:
                            ESymbol="-";
                            break;
                    }

                    System.out.println("Enter Number to be modified");
                    EINT = sc.next();

                    if(Configuration(EModifier,ESymbol,EINT) ==1)
                    {
                        System.out.println("Modification Succesful");
                    }
                    else
                    {
                        System.out.println("Error");
                    }
                    break;

                case 3:
                    set= Boolean.FALSE;
                    System.out.println("Terminating Programme");
                    break;
            }
        }
    }

    public int Configuration(String EModifier,  String ESymbol, String EINT)
    {
        File oldFile = new File("data/Modifier.txt");
        File newFile = new File("temp.txt");
        int count=0;
        try {
            FileWriter fw = new FileWriter("temp.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Scanner x = new Scanner(oldFile);
            //x.useDelimiter("[,\s\n]");

            while (x.hasNext()) {
                /*String data = x.nextLine();
                String[] res = data.split(",");*/
                String modifier = x.next();
                String symbol = x.next();
                String INT = x.next();

                if (modifier.equals(EModifier)) {
                    pw.println(EModifier + " " + ESymbol + " " + EINT);
                    count = 1;

                } else {
                    pw.println(modifier + " " + symbol + " " + INT);
                }
            }
                x.close();
                pw.flush();
                pw.close();
                oldFile.delete();
                File dump = new File("data/Modifier.txt");
                newFile.renameTo(dump);

        }
            catch(Exception e)
            {
                System.out.println("Error");
            }
        return count;
    }

    public void printTicket()
    {
        try {
            File file = new File("data/Modifier.txt");
            Scanner x = new Scanner(file);
            x.useDelimiter("[\s\n]");

            while (x.hasNextLine()) {
                String data = x.nextLine();
                String[] res = data.split(" ");

                String modifier =res[0];
                String symbol = res[1];
                String INT = res[2];

                System.out.println("Modifier type is "+ modifier + ", Arithmetic symbol is " + symbol + ", Amount to be modified is "+ INT);
            }
            x.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
