package MOBLIMA;

/**
 * Enumeration class for the different types of movie status.
 * @author SS7 Group 1
 * @version 1.0
 * @since 2022-11-07
 */
public enum MovieStatus {
    /**
     * Movie is coming soon to Cinemas.
     */
    COMING_SOON("Coming Soon", "··"),
    /**
     * Movie can be previewed in Cinemas.
     */
    PREVIEW("Preview", "◯◯"),
    /**
     * Movie is currently showing in Cinemas.
     */
    SHOWING("Showing", "▶▶"),
    /**
     * Movie is no longer showing in Cinemas.
     */
    END_OF_SHOWING("End of showing", "✖✖");
    /**
     * This movie's status.
     */
    private final String status;
    /**
     * This movie's status icon.
     */
    private final String icon;

    /**
     * Creates a MovieStatus class with attributes initialised.
     * @param stat This movie's status.
     * @param icon This movie's status icon.
     */
    MovieStatus(String stat, String icon){
        this.status = stat;
        this.icon = icon;
    }

    /**
     * Accessor for movie status.
     * @return Movie's status.
     */
    public String getStatus() {
        return status;
    }
}
