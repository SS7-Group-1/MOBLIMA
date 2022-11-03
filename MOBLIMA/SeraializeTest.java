package MOBLIMA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SeraializeTest {
    public static void main(String[] args) {

        // List to store list of movies
        List<Movie> list = new ArrayList<>();

        // Create a new movie
        Reviews review_list = new Reviews();
        review_list.setReview(new ArrayList<>(Arrays.asList("Good", "Bad")));
        Ratings rating = new Ratings();
        rating.setRating(new ArrayList<>(Arrays.asList(1.0f, 2.0f, 3.0f, 4.0f, 5.0f)));
        Movie test_movie = new Movie(
                "Kung Fu Nigel",
                MovieStatus.SHOWING,
                "A movie about a kung fu master named Nigel",
                "Jia Wei",
                new ArrayList<>(Arrays.asList("Nigel Chok", "Some random guy")),
                rating,
                AgeRating.PG,
                review_list,
                MovieType.threeD,
                0);

        // Add the created movie to the list
        list.add(test_movie);

        // Add list to file
        FileHelper.write(list, "movie.dat");

        // Read from file
        list = FileHelper.read("movie.dat");

        // Print out all movies in file
        for (Movie movie : list) {
            System.out.println(movie.toString());
        }
    }
}
