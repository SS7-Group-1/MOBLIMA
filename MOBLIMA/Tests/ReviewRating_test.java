package MOBLIMA.Tests;

import MOBLIMA.*;
import MOBLIMA.Displays.ReviewRatingDisplay;

import java.util.ArrayList;

public class ReviewRating_test {
    public static void main(String[] args) {
        //Test reviewRatingdisplay:
        ArrayList<Movie> movie_list = (ArrayList<Movie>) FileHelper.read("data/movie.dat");
        ReviewRatingDisplay rwrd = new ReviewRatingDisplay(movie_list.get(0));
        rwrd.DisplayMenu();
    }
}
