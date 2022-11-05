package MOBLIMA.Displays;

import MOBLIMA.FileHelper;
import MOBLIMA.Movie;

import java.util.*;
import java.util.Map.Entry;


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

        while (set)
        {
            System.out.println("Dear User, Please Select Option that you wish to view");
            System.out.println("1: View all Ticket Price");
            System.out.println("2: Configure Ticket Price");
            int choice = sc.nextInt();
            switch(choice)
            {
                case 1:
                    printTicket();
                    set = Boolean.FALSE;
                    break;


            }
        }
    }

    public void printTicket()
    {
        String type=""; String cinema ="";String age =""; String day="";int price=0;
        try{
            Scanner x = new Scanner("C://Java programming//MOBLIMA//MOBLIMA//Displays//TicketPrice.csv");
            x.useDelimiter("[,\n]");

            while(x.hasNext())
            {
                type= x.next();
                cinema=x.next();
                age= x.next();
                day = x.next();
                price = x.nextInt();

                System.out.println("Type "+type +" Cinema " + cinema + " age "+age +"day "+day + " price "+price);
            }
        }
        catch(Exception e)
        {
            System.out.println("Wrong file");
        }



    }

    public void printTop5Ranking(int choice) //NEED CHANGE TO PRIVATE LATER and sales for each movie //Ticket sales for all are zeroes quite sus
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
