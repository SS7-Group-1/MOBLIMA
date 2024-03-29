package MOBLIMA;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * A class that represents reviews for the movie
 * @author SS7 Group 1
 * @version 1.0
 * @since 2022-11-07
 */
public class Review implements Serializable {
    /**
     * array list to store reviews for the movie
     */
    private ArrayList<String> reviews;

    /**
     * creates a new Review and initialises reviews to a new array list
     */
    public Review(){
        reviews = new ArrayList<>();
    }

    /**
     * creates a new Review with reviews (array list) passed in as argument
     * @param reviews array list to store reviews for the movie
     */
    public Review(ArrayList<String> reviews){
        this.reviews = reviews;
    }

    /**
     * to add review to the array list that is storing reviews for the movie
     * @param rev the review to be added for the movie
     */
    public void addReview(String rev){
        reviews.add(rev);
        System.out.println("Review successfully added");
    }

    /**
     * to print all reviews of the movie
     */
    public void printReviews(){
        int total_count = 0;
        for (String review : reviews) {
            total_count++;
            System.out.println(" - " + review);
        }
        if(total_count == 0){
            System.out.println("No reviews yet!");
        }
    }

    /**
     * Mutator to set reviews to reviews (array list)
     * @param reviews to set reviews to reviews (array list)
     */
    public void setReviews(ArrayList<String> reviews) {
        this.reviews = reviews;
    }

    /**
     * Accessor to get all reviews of the movie
     * @return reviews: array list to store reviews for the movie
     */
    public ArrayList<String> getReviews(){
        return reviews;
    }
}


