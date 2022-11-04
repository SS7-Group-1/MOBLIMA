package MOBLIMA;

import java.io.Serializable;

public class Seat implements Serializable {
    private boolean isOccupied;
    private boolean isPremium;

    public Seat(){
    }

    public Seat(boolean isPremium){
        this.isOccupied=false;
        this.isPremium = isPremium;
    }

    public boolean getOccupied(){
        return isOccupied;
    }

    public void setOccupied(){
        if (!isOccupied){
            isOccupied=true;
        }
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }

    public boolean isPremium(){
        return isPremium;
    }

    @Override
    public String toString() {
        int occupy = isOccupied ? 1 : 0;

        return "Seat{" +
                ", isOccupied=" + occupy +
                '}';
    }
}
