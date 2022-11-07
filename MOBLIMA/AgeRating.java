package MOBLIMA;

/**
 * Enumeration class to hold the different age ratings for movies.
 * @author SS7 Group 1
 * @version 1.0
 * @since 2022-11-07
 */
public enum AgeRating {
    /**
     * Age rating: General.
     * Suitable for all ages.
     */
    G("G"),
    /**
     * Age rating: Parental Guidance.
     * Suitable for most but parents should guide their young.
     */
    PG("PG"),
    /**
     * Age Rating: Parental Guidance 13.
     * Suitable for persons aged 13 and above but parental guidance is advised for children below 13.
     */
    PG13("PG13"),
    /**
     * Age Rating: No Children under 16.
     * Restricted to persons over 16 years of age.
     */
    NC16("NC16"),
    /**
     * Age Rating: Mature 18.
     * Restricted to persons 18 years and above.
     */
    M18("M18"),
    /**
     * Age Rating: Restricted 21.
     * Strictly for adults aged 21 and above. Films under this category are restricted to be screen in licensed venues only.
     */
    R21("R21");

    /**
     * String that holds the age rating for a movie.
     */
    private final String ageRating;

    /**
     * Creates AgeRating class with attribute initialised.
     * @param age This movie's age rating.
     */
    AgeRating(String age){
        this.ageRating=age;
    }
}
