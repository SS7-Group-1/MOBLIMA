package MOBLIMA;

public enum SeatType {
    STANDARD("Standard", "▢"),
    PREMIUM("Premium", "▤"),
    HANDICAP("Handicap", "H");

    private String type;
    private String icon;

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
