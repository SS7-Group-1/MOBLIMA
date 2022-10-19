package Moblima;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.IOException;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*; // to count file lines
import java.util.ArrayList;

class ReadFile {
  public static void main(String[] args) {
 
  }
}

public class CinemaCLI {
	//to edit files
	public static void setVariable(int lineNumber, String data, String filepath) throws IOException {
	    Path path = Paths.get(filepath);
	    List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
	    lines.set(lineNumber, data);
	    Files.write(path, lines, StandardCharsets.UTF_8);
	}
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//Loading Cineplex
		Cineplex Cine = null;
		//Loading Movie_list
		ArrayList<Movie> movie_list = null; // we use arraylist because we want it to be dynamic
		List<String> lines =null;
		int movie_amt =0;
		int cinema_amt=0;
		
		//Populating Cinemas.
		try {
		      File myObj = new File("C:\\Users\\Ryan\\Documents\\Cinema\\cineplex.txt");
		      Scanner myReader = new Scanner(myObj);
		      int count =0;
		      //Path file = Paths.get("C:\\Users\\Ryan\\Documents\\Cinema\\test.txt");
		      
		     //find amount of lines in the file. 
			try {
				lines = Files.readAllLines(Paths.get("C:\\Users\\Ryan\\Documents\\Cinema\\cineplex.txt"), Charset.defaultCharset());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		      cinema_amt = lines.size()-1;
		      Cine = new Cineplex(cinema_amt);
		      System.out.println(cinema_amt);
		      while (myReader.hasNextLine()) {
		    	  String data = myReader.nextLine();
		    	  System.out.println(count);
		    	  if (count!=0) {
		    		  String[] res = data.split(",");
		    		  //System.out.println(res);
		    		  String Name =res[0];
		    		  String type =res[1];
		    		  int hallamt =Integer.valueOf(res[2]);
		    		  int hallseats=Integer.valueOf(res[3]);
		    		  Cine.setCinemaHalls(count-1, hallamt);
		    		  Cine.setCinemaType(count-1,type);
		    		  Cine.setCinemaName(count-1, Name);
		    		  Cine.createCinemaHalls(count-1, hallamt, hallseats);
		    	  }
		    		  
		    	  
		    	  //System.out.println(data);
		    	  count++;
		    	  
		    	 
	        }
		      myReader.close();
			} catch (FileNotFoundException e) {
				System.out.println("An error occurred.");
			     e.printStackTrace();
			}
		
		// Populating Movie database
		try {
			System.out.println("Trying to populate the Movie database");
		      File myObj = new File("C:\\Users\\Ryan\\Documents\\Cinema\\movie.txt");
		      Scanner myReader = new Scanner(myObj);
		      try {
					lines = Files.readAllLines(Paths.get("C:\\Users\\Ryan\\Documents\\Cinema\\movie.txt"), Charset.defaultCharset());
				} catch (IOException e) {
					e.printStackTrace();
				}
		      movie_amt = lines.size()-1;
		      movie_list = new ArrayList<Movie>(); // we need an array list of movies.
		      
		      int count1=0;
		      while (myReader.hasNextLine()) {
		    	  String data = myReader.nextLine();
		    	  if (count1!=0) {
		    			  String[] res = data.split(",");
		    			  String movie_title = res[0];
		    			  String movie_status = res[1];
		    			  String movie_synopsis = res[2];
		    			  String movie_director = res[3];
		    			  String movie_cast = res[4];
		    			  String movie_rating = res[5];
		    			  String past_review = res[6];
		    			  float movie_reviewrrating = Float.parseFloat(res[7]);
		    			  String movie_type = res[8];
		    			  
		    			  movie_list.add(new Movie(movie_title,movie_status,movie_synopsis,movie_director,movie_cast,movie_rating,past_review,movie_reviewrrating,movie_type));
		    			  
		    		  }
		    		  
		    		 // System.out.println(data);
		    		  count1+=1;
		      }
		
		      
		myReader.close();
		//Lets check through all the movies and see which are now Showing, and add it into our premiere list.
		
		
		//assign the premiere to all cinemas.
		for (int i=0;i<cinema_amt;i++) {
			Premiere premiere = new Premiere(movie_list,movie_amt);
			Cine.setCinemaPremiere(i, premiere);
			}
				
		//testing premire;
		Cine.getCinemaPremiere(0);
		
		
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
		     e.printStackTrace();
		}
		//Populating Hall_Timings with Movies;
		try {
		      File myObj = new File("C:\\Users\\Ryan\\Documents\\Cinema\\movie_timings.txt");
		      Scanner myReader = new Scanner(myObj);
		      
		      myReader.nextLine(); //get rid of dummy
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        String[] res = data.split(",");
		        String hall_movie=res[0];
		        String hall_movie_timing=res[1];
		        Cine.setAllCinemaMovie_Timing(hall_movie, hall_movie_timing);
		        //System.out.println(data);
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		 }
		//test 
		//Cine.getCinemaMovie_Timing(0);
		//Cine.getCinemaMovie_Timing(1);
		//Cine.getCinemaMovie_Timing(2);
		
		//Cine.getCinemaSeats_Timing(1, 0, "1000");
		
		
		
		// TODO Auto-generated method stub
		
		//test
		/*
		 * Cine.ListCinemas();
		 * 
		 * for (int i =0;i<cinema_amt;i++) { Cine.getCinemaHalls(i); }
		 */
		
		
   	  //test end
		
		int staff=0;
		System.out.print("Cinema Online System\n");
		System.out.println("1. Staff login");
		System.out.println("2. Non-Staff");
		
			
		int choice = sc.nextInt();
			
		switch(choice) {
			case 1:
				System.out.println("Enter username");
				String user = sc.next();
				System.out.println("Enter password");
				String pass = sc.next();
				String userpass = user+":"+pass;					
				System.out.println(userpass);
				//find from file the user and password
				 try {
				      File myObj = new File("C:\\\\Users\\\\Ryan\\\\Documents\\\\Cinema\\\\cinema_staff_login.txt");
				      Scanner myReader = new Scanner(myObj);
				      while (myReader.hasNextLine()) {
				        String data = myReader.nextLine();
				        if (data.equals(userpass)) {
				        	//successful login
				        	staff=1;
				        	System.out.println("Logged in as: "+user);
				        }
				        //System.out.println(data);
				      }
				      if (staff==0) {
				    	  System.out.println("Unsuccessful Login");
				    	  break;
				      }
				      myReader.close();
				    } catch (FileNotFoundException e) {
				      System.out.println("An error occurred.");
				      e.printStackTrace();
				    }
				 break;
				
			case 2:
				while(true) {
					System.out.println("Cinema Customer System");
					System.out.println("1. Search Movies");
					System.out.println("2. View movie details");
					System.out.println("3. Check Seats");
					System.out.println("4. Booking");
					System.out.println("5. Booking history");
					System.out.println("6. List Top 5");
					System.out.println("7. Exit");
					
					int choice_customer = sc.nextInt();
					
					//init some variables so we can reuse
					String chosen_title = null;
					int chosen_cinema = -1;
					int chosen_hall =-1;
					String chosen_timing = null;
					String chosen_movie=null;
					int which_seat =-1;
					
					switch(choice_customer) {
						
						case 1:
							System.out.println("1. Print all movies");
							System.out.println("2. Print currently showing movies");
							int choice_1 = sc.nextInt();
							switch(choice_1) {
							case 1:
								for (int i=0;i<movie_amt;i++) {
									System.out.format("Movie %d : %s\n",i+1,movie_list.get(i).getTitle());
								}
								break;
							case 2:
								for (int i=0;i<movie_amt;i++) {
									if (movie_list.get(i).getstatus()==1) {
										System.out.format("Movie %d : %s\n",i+1,movie_list.get(i).getTitle());
									}
									
								}
								break;
								
							
							}
							break;
						case 2:
							System.out.println("2. Enter movie title you want more details on");
							sc.skip("\\R?");
							chosen_title = sc.nextLine();
							for (int i=0;i<movie_amt;i++) {
								if (movie_list.get(i).getTitle().equals(chosen_title)) {
									System.out.print("Details: "+movie_list.get(i).getAlldetails());
								}
							}
							break;
						case 3:
							System.out.println("Which Cinema? (0:Newtown | 1:Ridgewater | 2:PoorLand)");
							chosen_cinema = sc.nextInt();
							//Show them the movies;
							Cine.getCinemaAllMovie_Timing(chosen_cinema);
							//show them the halls
							//Cine.getCinemaHalls(chosen_cinema);
							System.out.println("Which Hall?");
							chosen_hall = sc.nextInt();
							System.out.println("Which Timing?");
							sc.skip("\\R?");
							chosen_timing = sc.nextLine();
							//System.out.format("%d , %d , %s",cinema_amt, chosen_hall, chosen_timing);
							Cine.getCinemaSeats_Timing(chosen_cinema, chosen_hall, chosen_timing);
							break;
						
						case 4:
							//booking and purchase ticket.
							System.out.println("Which Cinema? (0:Newtown | 1:Ridgewater | 2:PoorLand)");
							chosen_cinema = sc.nextInt();
							System.out.println("Which Movie?");
							Cine.getCinemaPremiere(chosen_cinema); //different cinemas CAN have different movies.
							sc.skip("\\R?");
							chosen_movie =sc.nextLine();
							Cine.getCinemaMovie_Timing(chosen_cinema, chosen_movie);
							System.out.println("Which Hall?");
							chosen_hall=sc.nextInt();
							System.out.println("Which Timing?");
							sc.skip("\\R?");
							chosen_timing =sc.nextLine();
							Cine.getCinemaSeats_Timing(chosen_cinema, chosen_hall, chosen_timing);
							System.out.println("Which Seat?");
							which_seat = sc.nextInt();
							if (Cine.Booking(chosen_cinema, chosen_hall, chosen_timing, which_seat,0)==true) {
								System.out.println("Review your information");
								System.out.println("===============");
								System.out.println("Movie: " + chosen_hall);
								System.out.println("Cinema "+chosen_cinema);
								System.out.println("Timing " + chosen_timing);
								System.out.format("Hall %s Seat %s" ,chosen_hall,which_seat);
								System.out.println("===============");
								
								System.out.println("Confirm booking? (0:No | 1:Yes)");
								int confirm =sc.nextInt();
								switch (confirm) {
								case 0:
									break;
								case 1:
									System.out.println("Email: ");
									sc.skip("\\R?");
									String Email = sc.nextLine();
									sc.skip("\\R?");
									System.out.println("Name: ");
									String name = sc.nextLine();
									System.out.println("Mobile number: ");
									int phone = sc.nextInt();
									
									//make payment.
									
									
									//book.
									if (Cine.Booking(chosen_cinema, chosen_hall, chosen_timing, which_seat,1)==true) {
										//successful booking.
										
										
										//save to file
										
										//print transaction id to movie go-er.
									}
									
								}
								
								

								
							}else {
								System.out.println("Invalid option.");
							}
							
						
							
							break;
							
							
						
						case 7:
							break;
				
					}
			
				}
		}
		//handle STAFF functions:
		if (staff==1) {
			
			System.out.print("Cinema Staff System\n");
			
			while(true) {
				System.out.println("1. Configure settings");
				System.out.println("2. Insert new movies");
				System.out.println("3. Update movies");
				System.out.println("4. Edit Showtimes");
				System.out.println("5. List Top 5 Movies");
				System.out.println("6. Exit");
				
				
				int choice_staff = sc.nextInt();
				
				switch(choice_staff) {
					case 1:
						System.out.println("What would you like to configure?\n");
						System.out.println("1.Ticket prices");
						System.out.println("2.Holiday dates");
						System.out.println("3.Exit");
						int choice_settings = sc.nextInt();
						switch(choice_settings) {
							case 1:
								System.out.println("Configuring ticket prices:\n");
								System.out.println("What movie type? (3D/Blockbuster)\n");
								String movie_type = sc.next();
								System.out.println("What cinema class? (Poor/Economy/Premium)\n");
								String cinema_class = sc.next();
								System.out.println("Which age group? (child/adult/senior)\n");
								String age_group = sc.next();
								System.out.println("Is it a weekday? (0 for no, 1 for yes)\n");
								int weekday = sc.nextInt();
								String search = movie_type+","+cinema_class+","+age_group+","+weekday;
								System.out.println("Search string "+search);
								
								try {
								      File myObj = new File("C:\\\\Users\\\\Ryan\\\\Documents\\\\Cinema\\\\cinema_settings.txt");
								      Scanner myReader = new Scanner(myObj);
								      int line_no=1;
								      myReader.nextLine(); //clear dummy
								      while (myReader.hasNextLine()) {
								        String data = myReader.nextLine();
								       // System.out.println(data);
								        String[] res= data.split(",");
								       // System.out.println(res);
								        if (res[1].equals(movie_type) && res[2].equals(cinema_class) && res[3].equals(age_group) && Integer.valueOf(res[4])==weekday) {
								        	System.out.println("Found an entry with our search string "+search);
								        	System.out.println("What would you like to edit the ticket price to? Old ticket price: "+res[0]);
								        	String new_price = sc.next();
								        	String new_data = new_price+","+search;
								        	setVariable(line_no,new_data,"C:\\\\Users\\\\Ryan\\\\Documents\\\\Cinema\\\\cinema_settings.txt");
								        }
								        line_no++;
								     }
								 }catch (FileNotFoundException e) {
								      System.out.println("An error occurred.");
								      e.printStackTrace();
								  } catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								
							case 3:
								break;
							
					
						
						}
		
					case 2:
						System.out.println("Movie insertion?\n");
						System.out.println("Title of Movie: ");
						sc.skip("\\R?");
						String new_title = sc.nextLine();
						System.out.println("Type of Movie (Blockbuster/3D): ");
						sc.skip("\\R?");
						String new_type = sc.nextLine();
						System.out.println("Synopsis of Movie: ");
						sc.skip("\\R?");
						String new_synopsis = sc.nextLine();
						System.out.println("Director of Movie: ");
						sc.skip("\\R?");
						String new_director = sc.nextLine();
						System.out.println("Cast of Movie: ");
						sc.skip("\\R?");
						String new_cast = sc.nextLine();
						System.out.println("Rating of Movie: ");
						sc.skip("\\R?");
						String new_rating = sc.nextLine();
						System.out.println("Review of Movie: ");
						sc.skip("\\R?");
						String new_review = sc.nextLine();
						System.out.println("Reviewer rating of Movie: ");
						sc.skip("\\R?");
						int new_reviewerrating = sc.nextInt();
						System.out.println("Status of Movie (Preview/Showing/Coming Soon/End Of Showing)");
						String new_status = sc.next();
						if (new_status.equals("Showing")) {
							//if it is showing, we ask them which cinema to add it to.
							System.out.println("Which cinema to premiere this? (0:Newtown | 1:Ridgewater | 2:PoorLand)");
							int cinema_to_show =sc.nextInt();

							Cine.addCinemaPremiere(cinema_to_show,new_title);
							
							//Handling hall timing
							System.out.println("Add timing (HHMM format, example: 2359)");
							String timing = sc.next();
							//We will add the timing to ALL halls.
					        Cine.setOneCinemaMovie_Timing(cinema_to_show,new_title, timing);
					        //testing
							System.out.format("Fetching Cinema: %s Movie: %s Timings\n",Cine.getName(cinema_to_show),new_title);
					        Cine.getCinemaMovie_Timing(cinema_to_show, new_title);

							
						
						}
						//add to list and to file.
						//add to list
		    			movie_list.add(new Movie(new_title,new_status,new_synopsis,new_director,new_cast,new_rating,new_review,new_reviewerrating,new_type));
		    			//increment the amount
		    			movie_amt++;
		    			//if it is "Showing", add to premiere.
		    			String to_insert =  System.lineSeparator() + new_title+","+new_status+","+new_synopsis+","+new_director+","+new_cast+","+new_rating+","+new_review+","+new_reviewerrating+","+new_type;
		    			try {
		    			    Files.write(Paths.get("C:\\\\Users\\\\Ryan\\\\Documents\\\\Cinema\\\\movie.txt"), to_insert.getBytes(), StandardOpenOption.APPEND);
		    			}catch (IOException e) {
		    			    //exception handling left as an exercise for the reader
		    			}
		    			//Cine.getCinemaPremiere(0);
		    			//Cine.getCinemaPremiere(1);
						break;
						
						
					case 3:
						System.out.println("These are your current movies\n");
						for (int i=0;i<movie_amt;i++) {
							System.out.println(movie_list.get(i).getTitle());
						}
						
						System.out.println("Which movie title do you want to edit? \n");
						sc.skip("\\R?");
						String edit_title = sc.nextLine();
						int found =0;
						for (int i=0;i<movie_amt;i++) {
							if (movie_list.get(i).getTitle().equals(edit_title)) {
								found =1;
								System.out.println("What do you want to edit about the movie?\n");
								System.out.println("1. title ");
								System.out.println("2. status ");
								System.out.println("3. synopsis ");
								System.out.println("4. director ");
								System.out.println("5. cast");
								System.out.println("6. rating");
								System.out.println("7. pastreviews");
								System.out.println("8. reviewerrating");
								System.out.println("9. type");
								
								int edit_choice = sc.nextInt();
								String new_data =null;
								switch(edit_choice) {
								
								
								//To-DO edit the choices in the file. (done).
								//Right now its just edited in the class file.
								
								case 1:
									System.out.format("Current Title is %s: ",movie_list.get(i).getTitle());
									String old_title = movie_list.get(i).getTitle();
									System.out.println("Enter new title?");
									sc.skip("\\R?");
									String new_edit_title = sc.nextLine();
									movie_list.get(i).setTitle(new_edit_title);
									new_data = movie_list.get(i).getAlldetails();
									if (movie_list.get(i).getStatus().equals("Showing")) {
										//change it in the Premiere list.
										Cine.editCinemaPremiereTitle(old_title, new_edit_title);
										//test
										Cine.getCinemaPremiere(0);
										
									}
									//to add into the file, delete the old line and append the new one in.
						        	try {
										setVariable(i+1,new_data,"C:\\\\Users\\\\Ryan\\\\Documents\\\\Cinema\\\\movie.txt");
										System.out.println("Successfully edited");
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
										System.out.println("Something went wrong!");
									}
						        	break;
						        
								case 2:
									System.out.format("Current status for %s : %s %n",movie_list.get(i).getTitle(),movie_list.get(i).getStatus());
									System.out.println("Change movie status (Preview/Showing/Coming Soon/End Of Showing)?");
									sc.skip("\\R?");
									String new_edit_status = sc.nextLine();
									//System.out.format("%s , %s",new_edit_status,movie_list.get(i).getStatus() );
									if ((movie_list.get(i).getStatus().equals("End Of Showing") || movie_list.get(i).getStatus().equals("Coming Soon")) && new_edit_status.equals("Showing")) {
										//End Of Showing -> to Showing. Or Coming Soon -> Showing
										//Append title to selected cinema's premiere list.
										//System.out.println("End Of showing -> Showing");
										
										System.out.println("Which cinema to premiere this? (0:Newtown | 1:Ridgewater | 2:PoorLand)");
										int cinema_to_show =sc.nextInt();
										Cine.addCinemaPremiere(cinema_to_show,movie_list.get(i).getTitle());
										
										//Handling hall timing
										System.out.println("Add timing (HHMM format, example: 2359)");
										String timing = sc.next();
										//We will add the timing to ALL halls.
								        Cine.setOneCinemaMovie_Timing(cinema_to_show,movie_list.get(i).getTitle(), timing);
								        //testing
										System.out.format("Fetching Cinema: %s Movie: %s Timings\n",Cine.getName(cinema_to_show),movie_list.get(i).getTitle());
								        Cine.getCinemaMovie_Timing(cinema_to_show, movie_list.get(i).getTitle());
										
										
										
										
									}
									if (movie_list.get(i).getStatus().equals("Showing") && new_edit_status.equals("End Of Showing")) {
										
										//test
										//Cine.getCinemaMovie_Timing(0,"avengers");
										
										//remove title from all cinema's premiere list.
										Cine.delCinemaPremiere(movie_list.get(i).getTitle());
										Cine.delCinemaMovie_Timing(movie_list.get(i).getTitle());
										
										//Verifying if removed
										System.out.println("Current List Of Movies Premiering\n");
										Cine.getCinemaPremiere(0);
										
										System.out.format("Fetching Cinema Movie: %s Timings\n",movie_list.get(i).getTitle());
										Cine.getCinemaMovie_Timing(0,movie_list.get(i).getTitle());
										
										
										
										
									}
									//Now we can edit the file.
									
									movie_list.get(i).setStatus(new_edit_status);
									new_data = movie_list.get(i).getAlldetails();
									try {
										setVariable(i+1,new_data,"C:\\\\Users\\\\Ryan\\\\Documents\\\\Cinema\\\\movie.txt");
										System.out.println("Successfully edited");
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
										System.out.println("Something went wrong!");
									}
									//Test
									//Cine.getCinemaPremiere(0);
									//Cine.getCinemaPremiere(1);
									//Cine.getCinemaPremiere(2);
									
								case 5:
									break;

								
								}
								
							}
							
						}if (found ==0) {
							System.out.println("Couldnt Find a movie with that title");
						}

	
				}
				
			}
			
			
		}
		
		
		
		
		

	}

}
