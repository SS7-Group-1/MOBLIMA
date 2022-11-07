package MOBLIMA;

/**
 * Enumeration class for different types of movies.
 * @author SS7 Group 1
 * @version 1.0
 * @since 2022-11-07
 */
public enum MovieType {
    /**
     * Movie is shown in 3D format.
     */
    threeD("3D"),
    /**
     * Movie is shown in 2D format.
     */
    twoD("2D");

    /**
     * This movie's type.
     */
    private final String type;

    /**
     * Creates MovieType with attribute initialised.
     * @param type This movie's type.
     */
    MovieType(String type){
        this.type = type;
    }
}
