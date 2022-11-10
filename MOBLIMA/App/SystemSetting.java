package MOBLIMA.App;

import MOBLIMA.Movie;
import MOBLIMA.User;

import java.io.*;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * Class where the staff controls the administrative side of the cinema
 */

public class SystemSetting {
    ArrayList<Movie> movie_list = new ArrayList<>();

    /**
     * Function that act as a display Menu for staff to do the necessary adjustment
     */
    public void DisplayMenu()
    {
        Boolean set = Boolean.TRUE;
        Scanner sc = new Scanner(System.in);

        while(set)
        {
             System.out.println("=".repeat(40));
                System.out.println("│ System Setting Menu │");
                System.out.println("[1] Manage ticket price");
                System.out.println("[2] Show top 5 movie by rating");
                System.out.println("[3] Show top 5 movie by sales");
                System.out.println("[4] Configure User Top 5 permission");
                System.out.println("[5] Configure User Role");
                System.out.println("[6] Manage vouchers");
                System.out.println("[7] Manage Public Holidays");
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
                                System.out.println("=".repeat(40));
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
                        case 7->{
                            int choice3 = 1;
                            while(choice3!=0) {
                                System.out.println("=".repeat(40));
                                System.out.println("[1] View all Public Holiday");
                                System.out.println("[2] Add Public Holiday");
                                System.out.println("[3] Update Public Holiday");
                                System.out.println("[4] Remove Public Holiday");
                                System.out.println("[0] Go back");
                                System.out.println("Select an option");
                                choice3 = sc.nextInt();
                                switch (choice3) {
                                    case 1 -> {
                                        ViewHoliday();
                                    }
                                    case 2 -> {
                                        AddHoliday();
                                    }
                                    case 3 -> {
                                        UpdateHoliday();
                                    }
                                    case 4 -> {
                                        DeleteHoliday();
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

    /**
     * Function that controls the user accessibility to top 5 movies by ratings and sales
     */
    public void configureUserPermission(){
        Scanner sc = new Scanner(System.in);
        try{
            FileWriter fw = new FileWriter("data/UserPermission.txt");
            System.out.println("=".repeat(40));
            System.out.println("[1] Allow user to view top 5 movies by ratings only");
            System.out.println("[2] Allow user to view top 5 movies by sales only");
            System.out.println("[3] Allow both options");
            System.out.println("[4] Disable both options");
            System.out.println("Select an option");

            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    fw.write('1');
                    System.out.println("User can only view top 5 movies by ratings");
                }
                case 2 -> {
                    fw.write('2');
                    System.out.println("User can only view top 5 movies by sales");
                }
                case 3 -> {
                    fw.write('0');
                    System.out.println("User can view top 5 movies by ratings or sales");
                }
                case 4 -> {
                    fw.write('3');
                    System.out.println("User cannot view top 5 by ratings or sales");
                }
                default -> System.out.println("Invalid option");
            }
            fw.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Function that controls the ticket price
     */
    public void configureTicketPrice()
    {
        Scanner sc = new Scanner(System.in);
        Boolean set = Boolean.TRUE;
        while (set)
        {
            System.out.println("=".repeat(40));
            System.out.println("| Ticket Price Menu |");
            System.out.println("[1] View all TicketConfiguration");
            System.out.println("[2] Configure Ticket Price");
            System.out.println("[3] Go Back");
            int choice = sc.nextInt();
            String EModifier=""; String EINT="";String ESymbol="";
            switch(choice)
            {
                case 1:
                    printTicketModifier();
                    break;
                case 2:
                        System.out.println("Please Enter Modifier too be edited");
                        System.out.println("[1] Standard / Child / Senior");
                        System.out.println("[2] Weekday / Weekends / Public Holiday");
                        System.out.println("[3] 2D / 3D");
                        System.out.println("[4] Standard / Premium Seats");
                        System.out.println("[5] Standard/ Platinum Cinema");
                        int pick = sc.nextInt();
                        switch (pick) {
                            case 1:
                                System.out.println("Please select [1] for Standard");
                                System.out.println("Please select [2] for Child");
                                System.out.println("Please select [3] for Senior");
                                int adult = sc.nextInt();
                                switch (adult) {
                                    case 1:
                                        EModifier = "STANDARD";
                                        break;
                                    case 2:
                                        EModifier = "CHILD";
                                        break;
                                    case 3:
                                        EModifier = "SENIOR";
                                        break;
                                }
                                break;
                            case 2:
                                System.out.println("Please select [1] for Weekday");
                                System.out.println("Please select [2] for Weekends");
                                System.out.println("Please select [3] for Public Holiday");
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
                                System.out.println("Please select [1] for 2D");
                                System.out.println("Please select [2] for 3D");
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
                                System.out.println("Please select [1] for Standard Seats");
                                System.out.println("Please select [2] for Premium Seats");
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
                                System.out.println("Please select [1] for Standard Cinema");
                                System.out.println("Please select [2] for Platinum Cinema");
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
                    System.out.println("[1] Addition");
                    System.out.println("[2] Subtraction");
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

                    if(TicketConfiguration(EModifier,ESymbol,EINT) ==1)
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
                    System.out.println("Go Back");
                    break;
            }
        }
    }

    /**
     * Function that configures the modifiers for ticket price in Modifier.txt
     * @param EModifier - Modifier to be edited
     * @param ESymbol - Symbol to be editied (either + or -)
     * @param EINT - New edited number
     * @return 1 if configuration successful , 0 if configuration fail
     */
    public int TicketConfiguration(String EModifier, String ESymbol, String EINT)
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
                String data = x.nextLine();
                String[] res = data.split(",");
                String modifier = res[0];
                String symbol = res[1];
                String INT = res[2];

                if (modifier.equals(EModifier)) {
                    pw.println(EModifier + "," + ESymbol + "," + EINT);
                    count = 1;

                } else {
                    pw.println(modifier + "," + symbol + "," + INT);
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

    /**
     * Function that prints all the modifiers for tickets
     */
    public void printTicketModifier()
    {
        try {
            File file = new File("data/Modifier.txt");
            Scanner x = new Scanner(file);
            x.useDelimiter("[,\n]");

            while (x.hasNextLine()) {
                String data = x.nextLine();
                String[] res = data.split(",");

                String modifier =res[0];
                String symbol = res[1];
                String INT = res[2];

                System.out.println(modifier + " price: " + symbol + INT);
            }
            x.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Function that allows the addition of public holiday into holiday text file
     * @return 1 if successful , 0 if fail
     */
    public int AddHoliday()
    {
        int count =1;
        String date;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Name of Holiday");
        String holiday = sc.nextLine();
        System.out.println("Enter Date to be set as a holiday in the format of DD/MM/YYYY");
        sc.skip("\\R?");
        while (true) {
            try {
                date = sc.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                LocalDate localDate = LocalDate.parse(date, formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date. Please try again.");
            }
        }
        try {
            FileWriter fw = new FileWriter("data/date.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(holiday+","+ date);
            pw.flush();
            pw.close();

        }
        catch(Exception e)
        {
            count =0;
            System.out.println("Error");
        }
        return count;
    }

    /**
     * Function that allows all the public holiday to be viewed
     */
    public void ViewHoliday()
    {
        try {
            File file = new File("data/date.txt");
            Scanner x = new Scanner(file);
            x.useDelimiter("[,\n]");

            while (x.hasNextLine()) {
                String data = x.nextLine();
                String[] res = data.split(",");

                String Holiday =res[0];
                String date = res[1];

                System.out.println("Holiday is "+ Holiday + ", date is " + date);
            }
            x.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Function that allows holidays to be updated
     * @return 1 if successful, 0 if unsuccessful
     */
    public int UpdateHoliday()
    {
        String EHoliday= HolidayName();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Date to update (dd/mm/yyyy)");
        String EDate;
        sc.skip("\\R?");
        while (true) {
            try {
                EDate = sc.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                LocalDate localDate = LocalDate.parse(EDate, formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date. Please try again.");
            }
        }

        File oldFile = new File("data/date.txt");
        File newFile = new File("temp.txt");
        int count=0;

        try {
            FileWriter fw = new FileWriter("temp.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Scanner x = new Scanner(oldFile);
            //x.useDelimiter(",");

            while (x.hasNext()) {
                String data = x.nextLine();
                String[] res = data.split(",");
                String Holiday = res[0];
                String Date = res[1];

                if (Holiday.equals(EHoliday)) {
                    pw.println(EHoliday + "," + EDate);
                    count = 1;

                } else {
                    pw.println(Holiday + "," + Date);
                }
            }
            x.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File dump = new File("data/date.txt");
            newFile.renameTo(dump);

        }
        catch(Exception e)
        {
            System.out.println("Error");
        }
        return count;
    }

    /**
     * Function that deletes holiday
     * @return 1 if true ,  0 if false
     */
    public int DeleteHoliday()
    {
        String DHoliday = HolidayName();
        Scanner sc = new Scanner(System.in);

        File oldFile = new File("data/date.txt");
        File newFile = new File("temp.txt");
        int count=0;
        try {
            FileWriter fw = new FileWriter("temp.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Scanner x = new Scanner(oldFile);
            //x.useDelimiter("[,\s\n]");

            while (x.hasNext()) {
                String data = x.nextLine();
                String[] res = data.split(",");
                String Holiday = res[0];
                String Date = res[1];

                if (Holiday.equals(DHoliday)) {
                    count =1;
                    continue;

                } else {
                    pw.println(Holiday + "," + Date);
                }
            }
            x.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File dump = new File("data/date.txt");
            newFile.renameTo(dump);

        }
        catch(Exception e)
        {
            System.out.println("Error");
        }
        return count;


    }

    /**
     * Helper function that displays holiday names and allows user to select holiday
     * @return Holiday name that was selected
     */
    public String HolidayName()
    {
        ArrayList<String> chok = new ArrayList<String>();
        int i=1;
        try {
            File file = new File("data/date.txt");
            Scanner x = new Scanner(file);
            x.useDelimiter("[,\n]");

            while (x.hasNextLine()) {
                String data = x.nextLine();
                String[] res = data.split(",");

                String Holiday =res[0];
                String date = res[1];

                System.out.println("[" + i++ + "]" + Holiday + ", date: " + date);
                chok.add(Holiday);
            }
            x.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Select Holiday");
        String EHoliday = chok.get(sc.nextInt()-1);

        return EHoliday;


    }


}
