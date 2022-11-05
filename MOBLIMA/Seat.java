package MOBLIMA;

import java.io.Serializable;

public class Seat implements Serializable {
    private boolean isOccupied;
    private SeatType seatType;

    public Seat(){
    }

    public Seat(SeatType seatType){
        this.isOccupied=false;
        this.seatType = seatType;
    }

    public boolean getOccupied(){
        return isOccupied;
    }

    public void setOccupied(){
        if (!isOccupied){
            isOccupied=true;
        }
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    @Override
    public String toString() {
        int occupy = isOccupied ? 1 : 0;

        return "Seat{" +
                ", isOccupied=" + occupy +
                '}';
    }

    public SeatType getSeatType() {
        return seatType;
    }
}
