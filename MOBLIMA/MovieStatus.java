package MOBLIMA;

public enum MovieStatus {
    COMING_SOON("Coming Soon", "··"),
    PREVIEW("Preview", "◯◯"),
    SHOWING("Showing", "▶▶"),
    
    END_OF_SHOWING("End of showing", "✖✖");

    private final String status;
    private final String icon;

    //constructor
    MovieStatus(String stat, String icon){
        this.status = stat;
        this.icon = icon;
    }

    @Override
    public String toString() {
        return status;
    }

    public String getIcon(){
        return icon;
    }
}
