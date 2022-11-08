package MOBLIMA;

/**
 * Enumeration class to hold the different seat types
 * @author SS7 Group 1
 * @version 1.0
 * @since 2022-11-07
 */
public enum SeatType {
    /**
     * Seat Type: Standard Seat
     */
    STANDARD("StandardSeat", "O"),
    /**
     * Seat Type: Premium Seat
     */
    PREMIUM("PremiumSeat", "P"),
    /**
     * Seat Type: Handicap Seat
     */
    HANDICAP("HandicapSeat", "H");

    /**
     * type of the seat
     */
    private final String type;
    /**
     * icon that is associated to the seat
     */
    private final String icon;

    /**
     * creates a new SeatType with type, icon passed in as arguments
     * @param type type of the seat
     * @param icon icon that is associated to the seat
     */
    SeatType(String type, String icon){
        this.type = type;
        this.icon = icon;
    }

    /**
     * Accessor to get the type of the seat
     * @return type: type of the seat
     */
    public String getType(){
        return type;
    }

    /**
     * Accessor to get the icon of the seat
     * @return icon: icon that is associated to the seat
     */
    public String getIcon(){
        return icon;
    }
}
