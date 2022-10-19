package Moblima;

public class Cineplex {
	
	private Cinema[] cinemas;
	private int amt;
	
	public Cineplex(int data) {
		// TODO Auto-generated constructor stub
		amt=data;
		cinemas = new Cinema[data];
		for (int i=0;i<data;i++) {
			
			cinemas[i]=new Cinema(i);
		}
	}
	
	public void ListCinemas() {
		for (int i=0;i<amt;i++) {
			cinemas[i].getId();
			cinemas[i].getName();
		}
	}
	
	public String getName (int cinema_id) {
		return cinemas[cinema_id].getName();
	}
	public void setCinemaName(int cinema_id,String Name) {
		cinemas[cinema_id].setName(Name);
	}
	public void setCinemaType(int cinema_id, String type) {
		cinemas[cinema_id].settype(type);
	}
	public void setCinemaHalls(int cinema_id, int hallamt) {
		cinemas[cinema_id].setHallamt(hallamt);
	}
	public void getCinemaHalls(int cinema_id) {
		//System.out.printf("test");
		cinemas[cinema_id].ListHalls();
	}

	/*
	 * public void getCinemaSeats(int cinema_id, int hall_id) {
	 * cinemas[cinema_id].getHallSeats(hall_id); }
	 */
	public void createCinemaHalls(int cinema_id,int hall_amt,int seatamt) {
		cinemas[cinema_id].createHalls(hall_amt, seatamt);
		//cinemas[cinema_id].getHallSeats(hall_id);
	}
	public void setCinemaPremiere(int cinema_id, Premiere premiere) {
		cinemas[cinema_id].setPremiere(premiere);
	}
	public void getCinemaPremiere(int cinema_id) {
		cinemas[cinema_id].getPremiere();
	}
	public void addCinemaPremiere(int cinema_id,String data) {
		cinemas[cinema_id].addPremiere(data);
		//cinemas[cinema_id].addPremieretiming(timing);
	}
	public void delCinemaPremiere(String title) {
		for(int i=0;i<amt;i++) {
			cinemas[i].delPremiere(title);
		}
	}
	
	public void editCinemaPremiereTitle(String old_title,String new_title) {
		for(int i=0;i<amt;i++) {
			cinemas[i].editPremiereTitle(old_title,new_title);
		}
		
	}
	
	public void setAllCinemaMovie_Timing(String movie,String timing) { //allcinemas, can choose particular
		for(int i=0;i<amt;i++) {
			cinemas[i].setHallMovie_Timing(movie, timing);
		}
		
	}
	public void setOneCinemaMovie_Timing(int cinema_id,String movie,String timing) { //allcinemas, can choose particular
			cinemas[cinema_id].setHallMovie_Timing(movie, timing);
		
		
	}
	
	public void getCinemaAllMovie_Timing(int cinema_id) {
		System.out.format("Cinema ID: %d Movie Timings\n" ,cinema_id);
		cinemas[cinema_id].getHallAllMovie_Timing();
	}
	public void getCinemaSeats_Timing(int cinema_id, int hall_id, String timing) {
		cinemas[cinema_id].getHallSeats_Timing(hall_id, timing);
	}
	
	//timing
	
	public void getCinemaMovie_Timing(int cinema_id,String movie) {
		cinemas[cinema_id].getHallMovie_Timing(movie);
	}
	
	public void delCinemaMovie_Timing(String movie) {
		//check all the cinemas and delete the movie
		for(int i=0;i<amt;i++) {
			cinemas[i].delMovie_Timing(movie);
		}
		
	}
	public boolean Booking(int cinema_id, int hall_id, String timing, int seat_no, int step) {
		if (cinemas[cinema_id].Booking(hall_id, timing, seat_no, step)==true) {
			return true;
		}
		else {
			return false;
		}
		
	}
	

}
