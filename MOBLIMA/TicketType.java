package MOBLIMA;

public enum TicketType {
    STANDARD("Standard"),
    CHILD("Child"),
    SENIOR("Senior");

    private final String type;

    TicketType(String type){
        this.type = type;
    }

    @Override
    public String toString(){
        return type;
    }
}
