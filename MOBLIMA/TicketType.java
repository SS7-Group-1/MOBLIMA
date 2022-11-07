package MOBLIMA;

import java.io.Serializable;

/**
 * Enumeration class to hold the different ticket types for movies
 * @author SS7 Group 1
 * @version 1.0
 * @since 2022-11-07
 */
public enum TicketType implements Serializable {
    /**
     * Ticket Type: Adult Ticket
     * Ticket for the adult
     */
    STANDARD("Adult"),
    /**
     * Ticket Type: Child Ticket
     * Ticket for the child
     */
    CHILD("Child"),
    /**
     * Ticket Type: Senior Ticket
     * Ticket for the senior
     */
    SENIOR("Senior");

    /**
     * denoting type of ticket (i.e adult/child/senior)
     */
    private final String type;

    /**
     * enum constructor that takes in type as a parameter and assigns value to the variable type
     * @param type denoting type of ticket (i.e adult/child/senior)
     */
    TicketType(String type){
        this.type = type;
    }

    /**
     *
     * @return type: type of ticket (i.e adult/child/senior)
     */
    @Override
    public String toString(){
        return type;
    }
}
