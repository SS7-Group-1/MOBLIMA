package MOBLIMA;

public enum SeatType {
    STANDARD("StandardSeat", "▢"),
    PREMIUM("PremiumSeat", "★"),
    HANDICAP("HandicapSeat", "H");

    private final String type;
    private final String icon;

    SeatType(String type, String icon){
        this.type = type;
        this.icon = icon;
    }

    public String getType(){
        return type;
    }

    public String getIcon(){
        return icon;
    }
}
