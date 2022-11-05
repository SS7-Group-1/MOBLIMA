package MOBLIMA;

public enum MovieStatus {
    COMING_SOON("Coming Soon"),
    PREVIEW("Preview"),
    SHOWING("Showing"),
    
    END_OF_SHOWING("End of showing");

    private final String status;

    //constructor
    MovieStatus(String stat){
        this.status = stat;
    }

    @Override
    public String toString() {
        return status;
    }
}
