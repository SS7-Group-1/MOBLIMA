package MOBLIMA.Displays;

import MOBLIMA.*;

import java.io.File;
import java.util.*;
import java.util.Map.Entry;

public class MovieListingDisplay {
    ArrayList<Movie> movie_list = new ArrayList<>();

    public void DisplayMenu() {
        // Populating Movie database
        movie_list = (ArrayList<Movie>) FileHelper.read("data/movie.dat");
        Scanner sc = new Scanner(System.in);
        System.out.println("*".repeat(40));
        System.out.println("Movie Listing Menu");

        int choice;
        while (true) {
            System.out.println("*".repeat(40));
            System.out.println("[1] Display All movies");
            System.out.println("[2] Top 5 Movies by rating");
            System.out.println("[3] Top 5 Movies by ticket sales");
            System.out.println("[4] Search Movie");
            System.out.println("[5] Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1: // Display All movies
                    System.out.println("*".repeat(40));
                    System.out.println("List of all movies");
                    for (Movie movie : movie_list) {
                        System.out.println(" - " + movie.getTitle());
                    }
                    break;
                case 2: // Top 5 Movies by rating
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
                            System.out.println(" " + ++print_count + ". " + entry.getKey());
                        }else{
                            break;
                        }
                    }
                    break;
                case 3: // Top 5 Movies by ticket sales
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
                            System.out.println(" " + ++print_count1 + ". " + entry.getKey());
                        }else{
                            break;
                        }
                    }
                    break;
                case 4: // Search Movie
                    System.out.println("Which Movie?");
                    sc.skip("\\R?");
                    String chosen_movie = sc.nextLine();
                    int found =0;
                    for (Movie movie : movie_list) {
                        if (movie.getTitle().equals(chosen_movie)) {
                            System.out.println(movie);
                            found = 1;
                            break;
                        }
                    }
                    if (found ==1){
                        System.out.println("Found movie.");

                    }else{
                        System.out.println("Movie doesn't exist.");
                    }
                    break;
                case 5: // Exit
                    System.out.println("Exiting application...");
                    System.exit(69);
                    break;
                default:
                    System.out.println("Invalid choice woi");
                    break;
            }
        }
    }
}
