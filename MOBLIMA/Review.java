package MOBLIMA;

import java.io.Serializable;
import java.util.ArrayList;

public class Review implements Serializable {
    private ArrayList<String> reviews;

    public Review(){
        reviews = new ArrayList<>();
    }

    public Review(ArrayList<String> reviews){
        this.reviews = reviews;
    }

    public void addReview(String rev){
        reviews.add(rev);
        System.out.println("Review successfully added");
    }

    public void printReviews(){
        for (String review : reviews) {
            System.out.println(" - " + review);
        }
    }

    public void setReviews(ArrayList<String> reviews) {
        this.reviews = reviews;
    }

    public ArrayList<String> getReviews(){
        return reviews;
    }

    @Override
    public String toString() {
        return "Reviews{" +
                "review=" + reviews +
                '}';
    }
}


