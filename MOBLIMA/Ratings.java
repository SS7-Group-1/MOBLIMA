package MOBLIMA;
import java.io.Serializable;
import java.util.ArrayList;

public class Ratings implements Serializable {

    private ArrayList<Float> rating;

    public Ratings(){ //constructor
        rating = new ArrayList<Float>();
    }

    public void addRating(Float rat){
        rating.add(rat);
        System.out.println("Rating successfully added");
    }

    public Float getAverageRating(){
        Float sum=0.0f;

        for(int i=0;i<rating.size();i++){
            sum+=rating.get(i);
        }

        return sum/rating.size();
    }
    public void printRatings(){
        for(int i=0;i<rating.size();i++){
            System.out.println(rating.get(i));
        }
    }public void printTopFive(){
        for(int i=0;i<5;i++){
            System.out.println(rating.get(i));
        }
    }

    public ArrayList<Float> toArray() {
        return rating;
    }

    public void setRating(ArrayList<Float> rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Ratings{" +
                "rating=" + rating +
                '}';
    }
}
