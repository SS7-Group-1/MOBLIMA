package Moblima;

public class Seat {
	
	private int seatID;
	private boolean isOccupied;
	
	public Seat(int id) {
		this.seatID=id;
		this.isOccupied=false;
		
	}
	public void getSeats() {
		System.out.format("Seat ID %d, Occupied %b\n",seatID,isOccupied);
	}
	public boolean getOccupied() {
		return this.isOccupied;
		
	}
	public void Booking() {
		this.isOccupied=true;
	}
		
	

}
