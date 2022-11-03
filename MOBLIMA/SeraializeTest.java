package MOBLIMA;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SeraializeTest {
    public static void main(String[] args) {

        // List to store list of movies
        List list = new ArrayList();

        // Create a new movie
        Reviews review_list = new Reviews();
        review_list.setReview(new ArrayList<String>(Arrays.asList("Good", "Bad")));
        Ratings rating = new Ratings();
        rating.setRating(new ArrayList<Float>(Arrays.asList(1.0f, 2.0f, 3.0f, 4.0f, 5.0f)));
        Movie test_movie = new Movie(
                "Kung Fu Nigel",
                MovieStatus.SHOWING,
                "A movie about a kung fu master named Nigel",
                "Jia Wei",
                new ArrayList<String>(Arrays.asList("Nigel Chok", "Some random guy")),
                rating,
                AgeRating.PG,
                review_list,
                MovieType.threeD,
                0);

        // Add the created movie to the list
        list.add(test_movie);

        // Write the list of movies to a file
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream("movie.dat");
            out = new ObjectOutputStream(fos);
            out.writeObject(list);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Read list of movies from file
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream("movie.dat");
            in = new ObjectInputStream(fis);
            list = (ArrayList) in.readObject();
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        // Print out all movies in file
        for (int i = 0; i < list.size(); i++) {
            Movie p = (Movie) list.get(i);
            System.out.println("title is " + p.getTitle());
        }
    }
}
