package MOBLIMA.App;

import MOBLIMA.*;

import java.util.ArrayList;
import java.util.Scanner;

public class _readDataFiles {
    static ArrayList<Cinema> cinema_list_test = new ArrayList<>();
    static ArrayList<Movie> movie_list_test = new ArrayList<>();
    static ArrayList<ShowTime> showtime_list_test = new ArrayList<>();
    static ArrayList<User> user_list_test = new ArrayList<>();

    public static void main(String[] args) {
        int choice = 1;
        System.out.println("Choice: ");
        Scanner sc = new Scanner(System.in);
        while (choice != 0){
            System.out.println("[0] Exit");
            System.out.println("[1] Read cinemas.dat");
            System.out.println("[2] Read movies.dat");
            System.out.println("[3] Read showtimes.dat");
            System.out.println("[4] Read users.dat");
            choice = sc.nextInt();
            switch (choice){
                case 1:
                    cinema_list_test = (ArrayList<Cinema>) FileHelper.read("data/cinemas.dat");
                    for (Cinema cinema : cinema_list_test) {
                        System.out.println(cinema);
                    }
                    System.out.println();
                    break;
                case 2:
                    movie_list_test = (ArrayList<Movie>) FileHelper.read("data/movies.dat");
                    for (Movie movie: movie_list_test){
                        System.out.println(movie);
                    }
                    System.out.println();
                    break;
                case 3:
                    showtime_list_test = (ArrayList<ShowTime>) FileHelper.read("data/showtimes.dat");
                    for (ShowTime showtime: showtime_list_test){
                        System.out.println(showtime);
                    }
                case 4:
                    user_list_test = (ArrayList<User>) FileHelper.read("data/users.dat");
                    for (User user: user_list_test){
                        System.out.println(user);
                    }
                    System.out.println();
                    break;
            }

        }

    }

}