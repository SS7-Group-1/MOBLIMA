package MOBLIMA.Displays;

import MOBLIMA.FileHelper;
import MOBLIMA.Movie;
//import MOBLIMA.Tests.generateDataFiles;

import java.io.*;

import java.io.File;
import java.util.*;
import java.util.Map.Entry;
import java.io.FileNotFoundException;



public class SystemSettingStaff {
    ArrayList<Movie> movie_list = new ArrayList<>();

    public void DisplayMenu()
    {
        Boolean set = Boolean.TRUE;
        Scanner sc = new Scanner(System.in);

        while(set)
        {
                System.out.println("Hello staff");
                System.out.println("Please Choose the item that you wish to view/edit");
                System.out.println("1. Configure ticket price");
                System.out.println("2. Print top 5 Ranking movies");
                System.out.println("3. To exit Display");
                System.out.println("");
                try {
                    int choice = sc.nextInt();
                    switch (choice) {
                        case 1:
                            configureTicketPrice();
                            break;
                        case 2:
                            System.out.println("Please Choose 1 for ranking by Ratings");
                            System.out.println("Please Choose 2 for ranking by Ticket Sales");
                            int ranking = sc.nextInt();
                            printTop5Ranking(ranking);
                            break;
                        case 3:
                            set = Boolean.FALSE;
                            break;
                    }
                }
                catch (Exception e)
                    {
                        System.out.println("Please enter valid entry");
                        set=Boolean.FALSE;

                    }


            }
    }
    public void configureTicketPrice()
    {
        Scanner sc = new Scanner(System.in);
        Boolean set = Boolean.TRUE;
        Boolean slut = Boolean.TRUE;

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
                        System.out.println("1) Adult / Child / Senior");
                        System.out.println("2) Weekday / Weekends / Public Holiday");
                        System.out.println("3) 2D / 3D");
                        System.out.println("4) Standard / Premium Seats");
                        System.out.println("5) Standard/ Platinum Cinema");
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
            x.useDelimiter("[,\s\n]");

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

    public void printTop5Ranking(int choice)
    {
        movie_list = (ArrayList<Movie>) FileHelper.read("data/movie.dat");
        switch (choice)
        {
            case 1: //top 5 movies by rating
                System.out.println("*".repeat(40));
                System.out.println("Top 5 movies by rating");
                HashMap<String,Float>Map = new HashMap<>();
                LinkedHashMap<String, Float> sortedMap = new LinkedHashMap<>();
                ArrayList<Float> list = new ArrayList<>();
                //add to map.
                for (Movie movie : movie_list) {
                    Map.put(movie.getTitle(), movie.getRating().getAverageRating());
                }
                for (Map.Entry<String, Float> entry : Map.entrySet()) {
                    list.add(entry.getValue());
                }
                list.sort(Collections.reverseOrder());
                for (Float num : list) {
                    for (Entry<String, Float> entry : Map.entrySet()) {
                        if (entry.getValue().equals(num)) {
                            sortedMap.put(entry.getKey(), num);
                        }
                    }
                }
                //System.out.println(sortedMap);
                int print_count =0;
                for (SortedMap.Entry<String, Float> entry : sortedMap.entrySet()) {
                    if (print_count!=5) {
                        System.out.println(" " + ++print_count + ". " + entry.getKey() + " rating: " + entry.getValue());
                    }else{
                        break;
                    }
                }
                break;
            case 2: //Top 5 movies by ticket sales
                System.out.println("*".repeat(40));
                System.out.println("Top 5 movies by sales");
                HashMap<String, Integer> map1 = new HashMap<>();
                LinkedHashMap<String, Integer> sortedMap1 = new LinkedHashMap<>();
                ArrayList<Integer> list1 = new ArrayList<>();

                //add to map.
                for (Movie movie : movie_list) {
                    map1.put(movie.getTitle(), movie.getSales());
                }
                for (Map.Entry<String, Integer> entry : map1.entrySet()) {
                    list1.add(entry.getValue());
                }
                list1.sort(Collections.reverseOrder());
                for (int num : list1) {
                    for (Entry<String, Integer> entry : map1.entrySet()) {
                        if (entry.getValue().equals(num)) {
                            sortedMap1.put(entry.getKey(), num);
                        }
                    }
                }
                int print_count1=0;
                //System.out.println(sortedMap1);
                for (SortedMap.Entry<String, Integer> entry : sortedMap1.entrySet()) {
                    if (print_count1!=5) {
                        System.out.println(" " + ++print_count1 + ". " + entry.getKey() + " Ticket sales: " + entry.getValue());
                    }else{
                        break;
                    }
                }
                break;
        }
    }



}
