package MOBLIMA;

import java.io.Serializable;

public class Seat implements Serializable {
    private boolean isOccupied;
    private SeatType seatType;
    private int col;
    private int row;

    public Seat(){
    }

    public Seat(SeatType seatType, int row, int col){
        this.isOccupied=false;
        this.seatType = seatType;
        this.row = row;
        this.col = col;
    }

    public boolean isOccupied(){
        return isOccupied;
    }

    public void setOccupied(){
        if (!isOccupied){
            isOccupied=true;
        }
    }

    public String getSeatNumber(){
        return "" + ((char)(row + 65)) + col;
    }

    public int getSeatRow(){
        return row;
    }

    public int getSeatCol(){
        return col;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    @Override
    public String toString() {
        int occupy = isOccupied ? 1 : 0;

        return "Seat{" +
                ", isOccupied=" + occupy +
                '}';
    }
}
