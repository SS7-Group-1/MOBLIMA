package Moblima;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Hall {
	
	private int amtofSeats;
	private int hall_id;
	//private Seat[] seats;
	//add hash map.
	HashMap<String, String>movie_timing = new HashMap<String, String>();
	private ArrayList<Timing> timing; //need timing to be dynamically growable

	
	
	public Hall(int id,int seatamt) {
		//init timing
		this.timing = new ArrayList<>();
		//Create class
		this.hall_id=id;
		this.amtofSeats=seatamt;
		/*
		 * //create seats 
		 * seats = new Seat[seatamt]; 
		 * for (int i=0;i<seatamt;i++) {
		 * seats[i]=new Seat(i); }
		 */
		
	}
	public void getHallstats() {
		//System.out.println("h");
		System.out.println("Hall: "+hall_id+ " Seats: " +amtofSeats);
	}

	/*
	 * public void getHallseats() { for (int i=0;i<amtofSeats;i++) {
	 * seats[i].getSeats(); } }
	 */
	public void DisplayAllMovieTimings() {
		for (String i: movie_timing.keySet()) {
			  System.out.println("Movie: " + movie_timing.get(i)  + " Timing: " + i );
		}
	}
	public void DisplayAllMovieTimingsandseats() {
		for (String i: movie_timing.keySet()) {
			for (int y=0;y<timing.size();y++) {
				if (i.equals(timing.get(y).getTiming())) {
					System.out.println("Movie: " + movie_timing.get(i)  + " Timing: " + i + " Seats left: "+ timing.get(y).getTiming_seats_left());
				}
				
			}
			  
		}
	}
	public void DisplayCertainMovieTiming(String movie) {
		for (String i: movie_timing.keySet()) {
			if (movie_timing.get(i).equals(movie)) {
			  System.out.println("Movie: " + movie_timing.get(i)  + " Timing: " + i);
			}
		}
		
	}
	public void addMovieTimings(String movie, String timing) {
		movie_timing.put(timing,movie);
		//call createHall timings after creating movie timings
		//System.out.println("created hall timings");
		createHallTimings(timing,amtofSeats);
		
	}
	public void createHallTimings(String timing,int amtofSeats) {
		this.timing.add(new Timing(timing,amtofSeats));
		
	}
	public void getHallseats_Timing(String Timing) {
		for(int i=0;i<timing.size();i++) {
			if (timing.get(i).getTiming().equals(Timing)) {
				//return seats
				timing.get(i).getTiming_seats();
			}
		}
		
	}
	public void delAllMovieTimings(String movie) {
		
		
		//https://stackoverflow.com/questions/1884889/iterating-over-and-removing-from-a-map
		//cannot use normal for loop;
		
		for(Iterator<Map.Entry<String, String>> it = movie_timing.entrySet().iterator(); it.hasNext(); ) {
		    Map.Entry<String, String> entry = it.next();
		    if(entry.getValue().equals(movie)) {
		        it.remove();
		    }
		}

		
		
	}
	public boolean Booking(String Timing, int seat_no, int step) {
		for(int i=0;i<timing.size();i++) {
			if (timing.get(i).getTiming().equals(Timing)) {
				//return seats
				if (timing.get(i).bookSeats(seat_no,step) == true) {
					return true;
				}
				else {
					return false;
				}
			}
		}
		return false;
		
	}
	
	
	
	
}
