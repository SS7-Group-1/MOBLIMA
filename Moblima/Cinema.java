package Moblima;

public class Cinema {
	

	private String typeofcinema;
	private int amtofhalls;
	private Hall[] halls;
	private int id;
	private String Name;
	private Premiere premiere;
	
	public Cinema(int i) {
		// TODO Auto-generated constructor stub
		id = i;
		
	}
	
	public void getId() {
		System.out.println("Cinema ID: "+this.id);
	}
	
	
	public String getName() {
		//System.out.println("Cinema Name: "+this.Name);
		return this.Name;
	}
	
	public void setName(String data) {
		this.Name = data;
	}
	
	
	public void settype(String data) {
		this.typeofcinema = data;
	}
	public void setPremiere (Premiere premiere) {
		this.premiere=premiere;
	}
	public void getPremiere () {
		premiere.getAllshowing();
	}
	public void addPremiere (String data) {
		premiere.addMovies(data);
	}
	public void delPremiere (String title) {
		premiere.delMovies(title);
	}
	
	/*
	 * public void addPremieretiming(String data) { premiere.addTiming(data); }
	 */
	
	public void createHalls(int hallamt,int seatamt) {
		halls = new Hall[hallamt];
		for (int i=0;i<hallamt;i++) { 
			halls[i]= new Hall(i,seatamt); 
			//halls[i].getHallseats();
		
			//System.out.printf("Created hall: %d with Seats: %d\n",i,seatamt);
		}
		
	}
	public void ListHalls() {
		//System.out.println(amtofhalls);
		for (int i=0;i<this.amtofhalls;i++) {
			halls[i].getHallstats();
		}
	}
	public void getHallSeats_Timing(int hall_id,String timing) {
		halls[hall_id].getHallseats_Timing(timing);
		
	}
	public void setHallamt(int hallamt) {
		this.amtofhalls=hallamt;
	}
	
	public void setHallMovie_Timing(String movie,String timing) {
		for (int i=0;i<this.amtofhalls;i++) {
			halls[i].addMovieTimings(movie, timing);
		}
	}
	public void getHallAllMovie_Timing() { //get all halls
		for (int i=0;i<this.amtofhalls;i++) {
			System.out.format("Hall %d\n" ,i);
			halls[i].DisplayAllMovieTimingsandseats();
		}
	}
	public void getHallMovie_Timing(String movie) { //get all halls
		for (int i=0;i<this.amtofhalls;i++) {
			System.out.format("Hall %d\n" ,i);
			halls[i].DisplayCertainMovieTiming(movie);
		}
	}
	public void delMovie_Timing(String movie) {
		for (int i=0;i<this.amtofhalls;i++) {
			halls[i].delAllMovieTimings(movie);
		}
		
	}
	public void editPremiereTitle(String old_title, String new_title) {
		premiere.editMovieTitle(old_title, new_title);
		
	}
	
	public boolean Booking(int hall_id, String timing, int seat_no, int step) {
		if (halls[hall_id].Booking( timing, seat_no, step) == true) {
			return true;
		}else {
			return false;
		}
		
	}
	
	
	
	
	
	
}
