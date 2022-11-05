package MOBLIMA;

import java.io.Serializable;
import java.util.ArrayList;

public class Reviews implements Serializable {
    private ArrayList<String> reviews;

    public Reviews(){
        reviews = new ArrayList<>();
    }

    public Reviews(ArrayList<String> reviews){
        this.reviews = reviews;
    }

    public void addReview(String rev){
        reviews.add(rev);
        System.out.println("Review successfully added");
    }

    public void printReviews(){
        for (String review : reviews) {
            System.out.println(review);
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


