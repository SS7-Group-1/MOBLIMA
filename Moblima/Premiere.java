package Moblima;

import java.util.ArrayList;

public class Premiere {
	
	private ArrayList<String> movies; //need movies to be dynamically growable
	private int premiereamt;
	//private ArrayList<String> timing;
	
	
	public Premiere(ArrayList<Movie> data,int movie_amt) {
		//initialise with all movies that are "Showing"
		this.movies = new ArrayList<>();
		//this.timing = new ArrayList<>();
		
		for (int i=0;i<movie_amt;i++) {
			if(data.get(i).getstatus()==1) { //add if they are showing
				//System.out.println(data[i].getTitle());
				String movie_title = data.get(i).getTitle();
				this.movies.add(movie_title);
				premiereamt++;
				
			}
		}
		
		
	}
	public void getAllshowing() {
		for (int i =0; i<premiereamt;i++) {
			System.out.println(movies.get(i));
			
		}
		
	}
	public void addMovies(String title) {
		premiereamt++;
		movies.add(title);
		
	}
	public void delMovies(String title) {
		premiereamt--;
		movies.remove(title);
	}
	public void editMovieTitle(String old_title,String new_title) {
		
		movies.set(movies.indexOf(old_title),new_title);
		
	}

	/*
	 * public void addTiming(String data) { timing.add(data); }
	 */
	/*
	 * public void showTiming() { System.out.println("ShowTimes are"+timing); }
	 */

}
