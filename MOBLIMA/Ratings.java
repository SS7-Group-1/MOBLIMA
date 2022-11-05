package MOBLIMA;
import java.io.Serializable;
import java.util.ArrayList;

public class Ratings implements Serializable {

    private ArrayList<Float> ratings;

    public Ratings(){ //constructor
        ratings = new ArrayList<>();
    }
    public Ratings(ArrayList<Float> ratings){ //constructor
        this.ratings = ratings;
    }

    public void addRating(Float rat){
        ratings.add(rat);
        System.out.println("Rating successfully added");
    }

    public Float getAverageRating(){
        Float sum=0.0f;

        for (Float rating : ratings) {
            sum += rating;
        }

        return sum/ ratings.size();
    }
    public void printRatings(){
        for (Float aFloat : ratings) {
            System.out.println(aFloat);
        }
    }

    public ArrayList<Float> getRatings() {
        return ratings;
    }

    public void setRatings(ArrayList<Float> ratings) {
        this.ratings = ratings;
    }

    @Override
    public String toString() {
        return "Ratings{" +
                "rating=" + ratings +
                '}';
    }
}
