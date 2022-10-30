package MOBLIMA;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.Map.Entry;

public class MovieListingDisplay {
    List<String> lines = null;
    ArrayList<Movie> movie_list = new ArrayList<Movie>();

    public void DisplayMenu() {
        // Populating Movie database
        try {
            //System.out.println("Trying to populate the Movie database");
            File myObj = new File("C:\\Users\\Ryan\\Documents\\Cinema\\movie.txt");
            Scanner myReader = new Scanner(myObj);
            try {
                lines = Files.readAllLines(Paths.get("C:\\Users\\Ryan\\Documents\\Cinema\\movie.txt"), Charset.defaultCharset());
            } catch (IOException e) {
                e.printStackTrace();
            }

            int count1 = 0;
            String movie_title;
            String movie_status;
            String movie_synopsis;
            String movie_director;
            int movie_cast_amt;
            int rating_amt;
            int review_amt;

            Movie movie;
            // ArrayList<String> movie_cast = new ArrayList<String>();
            while (myReader.hasNextLine()) {
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
                //
                String data = myReader.nextLine();
                if (count1 != 0) {
                    String[] res = data.split(",");
                    movie_title = res[0];
                    movie_status = res[1];
                    switch(movie_status){
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
                    movie_synopsis = res[2];
                    movie_director = res[3];
                    movie_cast_amt = Integer.parseInt(res[4]);
                    for(int i=0;i<movie_cast_amt;i++){
                        movie_cast.add(res[5+i]);
                        //System.out.println(res[5 +i]);
                    }
                    //System.out.println(res[5 +movie_cast_amt]);

                    review_amt = Integer.parseInt(res[5+movie_cast_amt]);
                    //System.out.println(res[5 +movie_cast_amt]);
                    for(int i=0;i<review_amt;i++) {
                        review.add(res[6 +movie_cast_amt+ i]);
                        //System.out.println(res[6 +movie_cast_amt+ i]);
                    }
                    //review_list.setReview(review);
                    String movie_rating = res[6+review_amt+movie_cast_amt];
                    switch(movie_rating){
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

                    rating_amt = Integer.parseInt(res[7+review_amt+movie_cast_amt]);
                    for(int i=0;i<rating_amt;i++) {
                        ratings_list.add(Float.parseFloat(res[8+review_amt+movie_cast_amt+i]));
                    }

                    String movie_type = res[8+rating_amt+review_amt+movie_cast_amt];
                    switch(movie_type){
                        case "3D":
                            type = MovieType.threeD;
                            break;
                        case "2D":
                            type = MovieType.twoD;
                            break;
                    }
                    int sale = Integer.parseInt(res[9 + rating_amt + review_amt + movie_cast_amt]);
                    review_list.setReview(review);
                    rating.setRating(ratings_list);
                    //movie_list.add(new Movie(movie_title,movie_status,movie_synopsis,movie_director,movie_cast,movie_rating,past_review,movie_reviewrrating,movie_type));
                    /*
                    System.out.format("Title %s: Status %s, Synopsis %s, Director %s\n", movie_title, movie_status, movie_synopsis,movie_director);
                    for(int i=0;i<movie_cast_amt;i++) {
                        System.out.println(movie_cast.get(i));
                    }
                    for(int i=0;i<review_amt;i++) {
                        System.out.println(review.get(i));
                    }
                    for(int i=0;i<rating_amt;i++) {
                        System.out.println(ratings_list.get(i));
                    }

                    System.out.format("Rating %s, movie_type %s",movie_rating,movie_type);
                    */
                    //adding to class
                    movie  = new Movie(movie_title,status,movie_synopsis,movie_director,movie_cast,rating,age,review_list,type,sale);
                    //test class
                    movie_list.add(movie);

                }

                // System.out.println(data);
                count1 += 1;
            }


            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();


        }

        //read from file?
        Scanner sc = new Scanner(System.in);



        int choice = 0;
        while (choice != 5) {
            System.out.println("1. Display All movies");
            System.out.println("2. Top 5 Movies by rating");
            System.out.println("3. Top 5 Movies by ticket sales");
            System.out.println("4. Search Movie");
            System.out.println("5. Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    for (int i=0;i< movie_list.size();i++){
                        System.out.println(movie_list.get(i).getTitle());
                    }
                    break;
                case 2:
                    HashMap<String,Float>Map = new HashMap<String,Float>();
                    LinkedHashMap<String, Float> sortedMap = new LinkedHashMap<>();
                    ArrayList<Float> list = new ArrayList<>();
                    //add to map.
                    for (int i=0;i< movie_list.size();i++){
                        Map.put(movie_list.get(i).getTitle(),movie_list.get(i).getRating().getAverageRating());
                    }



                    for (Map.Entry<String, Float> entry : Map.entrySet()) {
                        list.add(entry.getValue());
                    }
                    Collections.sort(list,Collections.reverseOrder());
                    for (Float num : list) {
                        for (Entry<String, Float> entry : Map.entrySet()) {
                            if (entry.getValue().equals(num)) {
                                sortedMap.put(entry.getKey(), num);
                            }
                        }
                    }
                    System.out.println(sortedMap);
                    int print_count =0;
                    for (SortedMap.Entry<String, Float> entry : sortedMap.entrySet()) {
                        if (print_count!=5) {
                            System.out.println("Movie : " + entry.getKey());
                            print_count++;
                        }else{
                            break;
                        }
                    }

                    break;
                case 3:
                    HashMap<String, Integer> map1 = new HashMap<>();
                    LinkedHashMap<String, Integer> sortedMap1 = new LinkedHashMap<>();
                    ArrayList<Integer> list1 = new ArrayList<>();

                    //add to map.
                    for (int i=0;i< movie_list.size();i++){
                        map1.put(movie_list.get(i).getTitle(),movie_list.get(i).getSales());
                    }
                    for (Map.Entry<String, Integer> entry : map1.entrySet()) {
                        list1.add(entry.getValue());
                    }
                    Collections.sort(list1,Collections.reverseOrder());
                    for (int num : list1) {
                        for (Entry<String, Integer> entry : map1.entrySet()) {
                            if (entry.getValue().equals(num)) {
                                sortedMap1.put(entry.getKey(), num);
                            }
                        }
                    }
                    int print_count1=0;
                    System.out.println(sortedMap1);
                    for (SortedMap.Entry<String, Integer> entry : sortedMap1.entrySet()) {
                        if (print_count1!=5) {
                            System.out.println("Movie : " + entry.getKey());
                            print_count1++;
                        }else{
                            break;
                        }
                    }



                    break;
                case 4:
                    //create new booking.
                    System.out.println("Which Movie?");
                    sc.skip("\\R?");
                    String chosen_movie = sc.nextLine();
                    int found =0;
                    for (int i=0;i< movie_list.size();i++){
                        if (movie_list.get(i).getTitle().equals(chosen_movie)){
                            found =1;
                        }
                    }
                    if (found ==1){
                        System.out.println("Found movie.");

                    }else{
                        System.out.println("Movie doesn't exist.");

                    }


                    break;

            }


        }
    }
}
