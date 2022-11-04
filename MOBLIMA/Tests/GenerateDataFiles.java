package MOBLIMA.Tests;

import MOBLIMA.Cinema;
import MOBLIMA.Cineplex;
import MOBLIMA.FileHelper;
import MOBLIMA.Seat;

import java.util.ArrayList;

public class GenerateDataFiles {
    public static void main(String args[]) {
        generateCinema();
    }

    public static void generateCinema() {
        //ArrayList<Cinema> cinema_list = (ArrayList<Cinema>) FileHelper.read("data/cinema.dat");
        ArrayList<Cinema> cinema_list = new ArrayList<>();
        Seat[][] seats = new Seat[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                seats[i][j] = new Seat(false);
            }
        }
        cinema_list.add(new Cinema(Cineplex.CATHAY, false, "C01", seats));
        FileHelper.write(cinema_list, "data/cinema.dat");
    }
}
