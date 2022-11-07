package MOBLIMA;

import java.io.Serializable;

/**
 * A class that represents seats for a movie
 * @author SS7 Group 1
 * @version 1.0
 * @since 2022-11-07
 */
public class Seat implements Serializable {
    /**
     * whether the seat is occupied
     */
    private boolean isOccupied;
    /**
     * whether the seat is selected
     */
    private boolean isSelected;
    /**
     * denoting the type of the seat
     */
    private SeatType seatType;
    /**
     * denoting the column of the seat
     */
    private int col;
    /**
     * denoting the row of the seat
     */
    private int row;

    /**
     * creates a new Seat (default constructor)
     */
    public Seat(){
    }

    /**
     * creates a new Seat with seatType, row, col initialised to the arguments passed in
     * also initialises isSelected and isOccupied to false
     */
    public Seat(SeatType seatType, int row, int col){
        this.isSelected=false;
        this.isOccupied=false;
        this.seatType = seatType;
        this.row = row;
        this.col = col;
    }

    /**
     * returns if the seat is occupied or unoccupied
     * @return isOccupied: whether the seat is occupied
     */
    public boolean isOccupied(){
        return isOccupied;
    }

    /**
     * Mutator to set if the seat is occupied or unoccupied
     */
    public void setOccupied(){
        if (!isOccupied){
            isOccupied=true;
        }
    }

    /**
     * returns if the seat is selected
     * @return isSelected: whether the seat is selected
     */
    public boolean isSelected(){
        return isSelected;
    }

    /**
     * Mutator to set seat to "selected"
     */
    public void setSelected(){
        if (!isSelected){
            isSelected=true;
        }
    }

    /**
     * Mutator to deselect the seat
     */
    public void setDeselected(){
        if (isSelected){
            isSelected=false;
        }
    }

    /**
     * Accessor to get seat number
     * @return seat number
     */
    public String getSeatNumber(){
        return "" + ((char)(row + 65)) + col;
    }

    /**
     * Accessor to get row of the seat
     * @return row: row of the seat
     */
    public int getSeatRow(){
        return row;
    }

    /**
     * Accessor to get column of the seat
     * @return col: column of the seat
     */
    public int getSeatCol(){
        return col;
    }

    /**
     * Mutator to set seatType to the seatType passed in as argument
     * @param seatType type of the seat
     */
    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    /**
     * Accessor to get seatType of the seat
     * @return seatType: type of the seat
     */
    public SeatType getSeatType() {
        return seatType;
    }

    /**
     *
     * @return a string containing details of the seat, consisting of whether the seat is occupied or unoccupied
     */
    @Override
    public String toString() {
        int occupy = isOccupied ? 1 : 0;

        return "Seat{" +
                ", isOccupied=" + occupy +
                '}';
    }
}
