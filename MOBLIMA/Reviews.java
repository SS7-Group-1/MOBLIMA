package MOBLIMA;

import java.io.Serializable;
import java.util.ArrayList;

public class Reviews implements Serializable {
    private ArrayList<String> review;

    public Reviews(){ //constructor
        review = new ArrayList<String>();

    }
    //add review will add into the arraylist/
    public void addReview(String rev){
        review.add(rev);
        System.out.println("Review successfully added");
    }

    //print reviews.

    public void printReview(){
        for(int i=0;i<review.size();i++){
            System.out.println(review.get(i));
        }
    }

    public void setReview(ArrayList<String> review) {
        this.review = review;
    }

    public ArrayList<String> toArray(){
        return review;
    }

    @Override
    public String toString() {
        return "Reviews{" +
                "review=" + review +
                '}';
    }
}


