package Moblima;

public class Movie {
	private String title;
	private String status;
	private String synopsis;
	private String director;
	private String cast;
	private String rating;
	private String pastreviews;
	private float reviewerrating;
	private String type;
	
	public Movie (String titl, String stat,String synop, String dir, String cast, String rating, String pastreview, float reviewer,String type) {
		this.title = titl;
		this.status = stat;
		this.synopsis = synop;
		this.director = dir;
		this.cast = cast;
		this.rating = rating;
		this.pastreviews =pastreview;
		this.reviewerrating= reviewer;
		this.type = type;
		
	}
	public String getTitle() {
		//System.out.println("Movie Title: "+this.title);
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStatus() {
		return this.status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSynopsis() {
		return this.synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis=synopsis;
	}
	public String getDirector() {
		return this.director;
	}
	public void setDirector(String director) {
		this.director=director;
	}
	public String getCast() {
		return this.cast;
	}
	public void setCast(String cast) {
		this.cast = cast;
	}
	public String getRating() {
		return this.rating;
	}
	public void setRating(String rating) {
		this.rating=rating;
	}
	
	public String pastreviews() {
		return this.pastreviews;
	}
	public void setPastreviews(String pastreviews) {
		this.pastreviews=pastreviews;
	}
	public float reviewerrating() {
		return this.reviewerrating;
	}
	public void setreviwerrating(float review) {
		this.reviewerrating=review;
	}
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public int getstatus() {
		if (this.status.equals("Showing")) {
			return 1;
		}
		else {
			return 0;
		}
	}
	public String getAlldetails() {
		return this.title +","+this.status+","+this.synopsis+","+this.director+","+this.cast+","+this.rating+","+this.pastreviews+","+this.reviewerrating+","+this.type;
	}
	

}
