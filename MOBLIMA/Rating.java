package MOBLIMA;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Rating class
 * @author SS7 Group 1
 * @version 1.0
 * @since 2022-11-07
 */
public class Rating implements Serializable {
    /**
     * ArrayList holding all movie ratings.
     */
    private ArrayList<Float> ratings;

    /**
     * Default constructor.
     * Creates an empty ArrayList.
     */
    public Rating(){ //constructor
        ratings = new ArrayList<>();
    }

    /**
     * Creates Rating class with attribute initialised.
     * @param ratings Movie ratings.
     */
    public Rating(ArrayList<Float> ratings){ //constructor
        this.ratings = ratings;
    }

    /**
     * Add a rating to existing ratings.
     * @param rat Movie rating to be added.
     */
    public void addRating(Float rat){
        ratings.add(rat);
        System.out.println("Rating successfully added");
    }

    /**
     * Calculate average rating of movie from existing ratings.
     * @return Float: Movie's average rating.
     */
    public Float getAverageRating(){
        Float sum=0.0f;

        for (Float rating : ratings) {
            sum += rating;
        }

        return sum/ ratings.size();
    }

    /**
     * Print out all existing movie's ratings.
     */
    public void printRatings(){
        for (Float aFloat : ratings) {
            System.out.println(aFloat);
        }
    }

    /**
     * Accessor for movie's ratings.
     * @return ArrayList<Float>: Array of ratings.
     */
    public ArrayList<Float> getRatings() {
        return ratings;
    }

    /**
     * Mutator for movie's ratings.
     * @param ratings Array of ratings to be changed to.
     */
    public void setRatings(ArrayList<Float> ratings) {
        this.ratings = ratings;
    }
}
