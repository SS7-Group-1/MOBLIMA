package MOBLIMA.Tests;

import MOBLIMA.*;

import java.util.ArrayList;

public class Cinema_test {
        public static void main(String args[]) {
                ArrayList<Cinema> cinema_list;
                cinema_list = (ArrayList<Cinema>) FileHelper.read("data/cinema.dat");
                for (Cinema cinema : cinema_list) {
                        System.out.println(cinema);
                }
        }
    }

