package MOBLIMA;

import java.io.Serializable;

public enum TicketType implements Serializable {
    STANDARD("Adult"),
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
