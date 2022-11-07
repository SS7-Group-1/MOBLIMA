package MOBLIMA;

/**
 * Enumeration class for different types (locations) of Cineplex.
 * @author SS7 Group 1
 * @version 1.0
 * @since 2022-11-07
 */
public enum Cineplex {
    /**
     * SHAW Theatres Lido
     */
    LIDO("Lido"),
    /**
     * SHAW Theatres Jewel
     */
    JEWEL("Jewel"),
    /**
     * SHAW Theatres Paya Lebar Quarter
     */
    PAYA_LEBAR_QUARTER("Paya Lebar Quarter"),
    /**
     * SHAW Theatres Waterway Point
     */
    WATERWAY_POINT("Waterway Point"),
    /**
     * SHAW Theatres NEX
     */
    NEX("nex"),
    /**
     * SHAW Theatres Seletar Mall
     */
    SELETAR_MALL("Seletar Mall"),
    /**
     * SHAW Theatres JCube
     */
    JCUBE("JCube"),
    /**
     * SHAW Theatres Lot One
     */
    LOT_ONE("Lot One");

    /**
     * Name of Cineplex
     */
    private final String name;

    /**
     * Creates Cineplex class with attribute initialised.
     * @param name Name of Cineplex to change to.
     */
    Cineplex(String name){
        this.name = name;
    }
}
