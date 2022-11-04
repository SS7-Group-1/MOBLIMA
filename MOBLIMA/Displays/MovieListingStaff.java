package MOBLIMA.Displays;

import MOBLIMA.*;
import MOBLIMA.Displays.MovieListingDisplay;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MovieListingStaff {

    List<String> lines = null;
    ArrayList<Movie> movie_list = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    public void DisplayMenu() throws IOException {
        movie_list = (ArrayList<Movie>) FileHelper.read("data/movie.dat");

        int choice =0;
        while(choice!=4) {
            System.out.println("1. Add Movie");
            System.out.println("2. Update Movie");
            System.out.println("3. Remove Movie");
            System.out.println("4. Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1: //Add movie
                    ArrayList<String> movie_cast = new ArrayList<String>();
                    ArrayList<String> review = new ArrayList<String>();
                    ArrayList<Float> ratings_list = new ArrayList<Float>();
                    //ENUM
                    MovieStatus status = null;
                    MovieType type = null;
                    AgeRating age = null;
                    //
                    //Class
                    Reviews review_list = new Reviews();
                    Ratings rating = new Ratings();
                    String title;
                    String status_find;
                    String director;
                    String cast;
                    String reviews;
                    String all_cast ="";
                    String all_reviews="";
                    String rated;
                    String movie_synopsis;
                    String all_rating="";
                    int movie_rating_amt;
                    int movie_cast_amt;
                    int movie_review_amt;
                    float rate;
                    String movie_type;
                    int sales;
                    System.out.println("Add Movie title");
                    sc.skip("\\R?");
                    title = sc.nextLine();
                    System.out.println("Add Movie status");
                    sc.skip("\\R?");
                    status_find = sc.nextLine();
                    switch(status_find){
                        case "Showing":
                            status = MovieStatus.SHOWING;
                            break;
                        case "End Of Showing":
                            status = MovieStatus.END_OF_SHOWING;
                            break;
                        case "Preview":
                            status = MovieStatus.PREVIEW;
                            break;
                        case "Coming Soon":
                            status = MovieStatus.COMING_SOON;
                            break;
                    }
                    System.out.println("Add Movie synopsis");
                    sc.skip("\\R?");
                    movie_synopsis = sc.nextLine();
                    System.out.println("Add Movie director");
                    sc.skip("\\R?");
                    director = sc.nextLine();
                    System.out.println("Add Movie cast amount");
                    movie_cast_amt = sc.nextInt();

                    for(int i=0;i<movie_cast_amt;i++){
                        System.out.println("New cast name: ");
                        sc.skip("\\R?");
                        cast = sc.nextLine();
                        if (i==0){
                            all_cast = cast;
                        }else {
                            all_cast = all_cast + "," + cast;
                        }
                        movie_cast.add(cast);
                    }
                    System.out.println(all_cast);

                    //review_amt and review
                    System.out.println("Add Movie Review amount");
                    movie_review_amt = sc.nextInt();

                    for(int i=0;i<movie_review_amt;i++){
                        System.out.println("New Review name: ");
                        sc.skip("\\R?");
                        reviews = sc.nextLine();
                        if (i==0){
                            all_reviews = reviews;
                        }else {
                            all_reviews = all_reviews + "," + reviews;
                        }
                        review.add(reviews); //add to array
                    }

                    System.out.println(all_reviews);
                    System.out.println("Add Movie Rated e.g PG13");
                    sc.skip("\\R?");
                    rated = sc.nextLine();
                    switch(rated){
                        case "G":
                            age = AgeRating.G;
                            break;
                        case "PG":
                            age = AgeRating.PG;
                            break;
                        case "PG13":
                            age = AgeRating.PG13;;
                            break;
                        case "NC16":
                            age = AgeRating.NC16;
                            break;
                        case "M18":
                            age = AgeRating.M18;;
                            break;
                        case "R21":
                            age = AgeRating.R21;
                            break;
                    }

                    //rating and rating amt

                    System.out.println("Add Movie Rating amount");
                    movie_rating_amt = sc.nextInt();

                    for(int i=0;i<movie_rating_amt;i++){
                        System.out.println("New Rating: ");
                        rate=sc.nextFloat();
                        if (i==0){
                            all_rating= String.valueOf(rate);
                        }else {
                            all_rating = all_rating + "," + String.valueOf(rate);
                        }
                        ratings_list.add(rate); //add to array
                    }
                    System.out.println(all_reviews);
                    //type
                    System.out.println("Add Movie type");
                    sc.skip("\\R?");
                    movie_type = sc.nextLine();
                    switch(movie_type){
                        case "3D":
                            type = MovieType.threeD;
                            break;
                        case "2D":
                            type = MovieType.twoD;
                            break;
                    }
                    //sales
                    System.out.println("Add Movie Sales");
                    sales = sc.nextInt();
                    review_list.setReview(review);
                    rating.setRating(ratings_list);

                    //add to movie list.
                    Movie movie  = new Movie(title,status,movie_synopsis,director,movie_cast,rating,age,review_list,type,sales);
                    System.out.println(movie);
                    addMovie(movie);


                    break;
                case 2:
                    Movie chosen = null;
                    System.out.println("Choose movie to edit");
                    sc.skip("\\R?");
                    String chosen_movie = sc.nextLine();
                    int found =0;
                    for (int i=0;i< movie_list.size();i++){
                        if (movie_list.get(i).getTitle().equals(chosen_movie)){
                            chosen = movie_list.get(i);
                            found =1;
                        }
                    }
                    if (found ==1) {
                        System.out.println("Found movie.");
                        updateMovie(chosen);

                    }
                    else{
                        System.out.println("Movie doesn't exist.");

                    }

                    break;
                case 3:
                    //choose movie
                    Movie chosen_remove = null;
                    System.out.println("Choose movie to remove");
                    sc.skip("\\R?");
                    chosen_movie = sc.nextLine();
                    found =0;
                    for (int i=0;i< movie_list.size();i++){
                        if (movie_list.get(i).getTitle().equals(chosen_movie)){
                            //chosen_remove = movie_list.get(i);
                            found =1;
                        }
                    }
                    if (found ==1) {
                        System.out.println("Removed movie.");
                        //removeMovie(chosen_remove);
                    }
                    else{
                        System.out.println("Movie doesn't exist.");

                    }

                    break;
                case 5: //test to print all movies
                    MovieListingDisplay mld = new MovieListingDisplay();
                    mld.DisplayMenu();
                    break;



            }
        }
    }
        public void addMovie (Movie movie){
            movie_list.add(movie);
            FileHelper.write(movie_list, "data/movie.dat");
        }

        public void updateMovie (Movie movie){

                System.out.println("What do you want to edit about the movie?\n");
                System.out.println("1. title ");
                System.out.println("2. status ");
                System.out.println("3. synopsis ");
                System.out.println("4. director ");
                System.out.println("9. type");

                int edit_choice = sc.nextInt();
                String new_title = null;
                String new_status = null;
                String new_synopsis=null;
                String new_director = null;

                switch(edit_choice) {
                    case 1:
                        sc.skip("\\R?");
                        new_title = sc.nextLine();
                        //reinsert.
                }
        }


        public void removeMovie (Movie movie) throws IOException {
            //Find movie in text database.
            String title = movie.getTitle();
            String status = String.valueOf(movie.getStatus());
            String synopsis = movie.getSynopsis();
            String director = movie.getDirector();
            String movie_cast_amt = String.valueOf(movie.getcast().size());
            ArrayList<String> movie_cast = movie.getcast();
            ArrayList<String> reviews = movie.getReview().toArray();
            String review_amt = String.valueOf(reviews.size());
            String ageRating = movie.getAgeRating();
            ArrayList<Float> rating = movie.getRating().toArray();
            String rating_amt = String.valueOf(rating.size());
            String movie_type = String.valueOf(movie.getMovieType());
            String sales = String.valueOf(movie.getSales());
            String all_cast = "";
            String all_reviews = "";
            String all_rating = "";
            System.out.println(status);
            //System.out.format("%s %s %s %s %s %s %s %s",title,status,synopsis,director,movie_cast_amt,review_amt,ageRating,rating_amt);
            for (int i = 0; i < Integer.parseInt(movie_cast_amt); i++) {

                String cast = movie_cast.get(i);
                if (i == 0) {
                    all_cast = cast;
                } else {
                    all_cast = all_cast + "," + cast;
                }

            }
            System.out.println(all_cast);
            for (int i = 0; i < Integer.parseInt(review_amt); i++) {
                String review = reviews.get(i);
                if (i == 0) {
                    all_reviews = review;
                } else {
                    all_reviews = all_reviews + "," + review;
                }
            }
            System.out.println(all_reviews);


            for (int i = 0; i < Integer.parseInt(rating_amt); i++) {
                Float rate = rating.get(i);
                if (i == 0) {
                    all_rating = String.valueOf(rate);
                } else {
                    all_rating = all_rating + "," + rate;
                }
            }

            String lineToRemove =title + "," + status + "," + synopsis + "," + director + "," + movie_cast_amt + "," + all_cast + "," + review_amt + "," + all_reviews + "," + ageRating + "," + rating_amt + "," + all_rating + "," + movie_type + "," + sales;
            System.out.println(lineToRemove);
            //find movie in the movie text.

            File input_file = new File("C:\\Users\\jwlim\\Documents\\GitHub\\MOBLIMA\\Cinema\\movie.txt");
            File temp_file = new File("C:\\Users\\jwlim\\Documents\\GitHub\\MOBLIMA\\Cinema\\temp.txt");
            BufferedReader my_reader = new BufferedReader(new FileReader(input_file));
            BufferedWriter my_writer = new BufferedWriter(new FileWriter(temp_file));
            //String lineToRemove = "string to remove";
            String current_line;
            while((current_line = my_reader.readLine()) != null) {
                //String trimmedLine = current_line;
                if(current_line.equals(lineToRemove)) continue;

                System.out.println(current_line);
                my_writer.write(current_line + System.getProperty("line.separator"));
            }
            my_writer.close();
            my_reader.close();
            input_file.delete();
            boolean is_success = temp_file.renameTo(input_file);

            if(is_success) {
                System.out.println("Movie deleted");

                //remove from array;
                for (int i = 0; i < movie_list.size(); i++) {
                    if (movie_list.get(i).getTitle().equals(movie.getTitle())) {
                        movie_list.remove(i);
                    }
                }
            }else{
                System.out.println("Error occurred, Movie not deleted");

            }

        }
    }

