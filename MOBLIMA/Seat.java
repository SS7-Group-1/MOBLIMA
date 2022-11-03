package MOBLIMA;

public class Seat {
    private int row;
    private int col;
    private boolean isOccupied;

    public Seat(){
    }

    public Seat(int row, int col){
        this.row=row;
        this.col=col;
        this.isOccupied=false;
    }

    public boolean getOccupied(){
        return isOccupied;
    }

    public void setOccupied(){
        if (!isOccupied){
            isOccupied=true;
        }
    }

    @Override
    public String toString() {
        int occupy = isOccupied ? 1 : 0;

        return "Seat{" +
                "row=" + row +
                ", col=" + col +
                ", isOccupied=" + occupy +
                '}';
    }
}
