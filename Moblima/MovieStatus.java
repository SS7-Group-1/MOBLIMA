package MOBLIMA;

public enum MovieStatus {
    COMING_SOON("Coming_soon"), PREVIEW("Preview"), SHOWING("Showing"),
    END_OF_SHOWING("End_Of_Showing");

    private String status;
    //constructor
    MovieStatus(String stat){
        this.status = stat;
    }

    @Override
    public String toString() {
        return status;
    }
}
