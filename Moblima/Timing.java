package Moblima;

public class Timing {
	private String timing; // the time 
	private int amtofSeats;
	private Seat[] seats;
	
	
	public Timing(String timing,int seatamt) {
		this.timing = timing;
		this.amtofSeats=seatamt;
		//create seats
		seats = new Seat[seatamt];
		for (int i=0;i<seatamt;i++) {
			seats[i]=new Seat(i);
				}
	}
	
	
	public void getTiming_seats() { //return seats for that timing.
		for (int i=0;i<amtofSeats;i++) {
			seats[i].getSeats();
		}
	}
	
	public boolean verifySeats_empty(int seat_no) { //make sure seat is not booked.
		if (seats[seat_no].getOccupied()==true) {
			return false;
		}else {
			return true;
		}
		
	}
	
	
	public boolean bookSeats(int seat_no,int step) {
		if (verifySeats_empty(seat_no)==true) {
			if (step==0) { // if step is 0 do not book. this is just to verify if the seat is available.
				return true;
			}
			seats[seat_no].Booking();
			return true;
		}else {
			return false;
		}
	}
	
	public String getTiming() {
		return timing;
	}
	public int getTiming_seats_left() {
		int count=0;
		for (int i=0;i<amtofSeats;i++) {
			if (seats[i].getOccupied()==false) {
				count++;
			}
		}
		return count;
	}

}
