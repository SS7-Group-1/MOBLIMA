package MOBLIMA.App;

import MOBLIMA.*;
import java.util.*;

/**
 *Class that handles all movies and their respective information
 * @since 2022-11-07
 * @author Lim Jia Wei
 * @version 1.3
 */
public class Movies {
    ArrayList<Movie> movie_list;
    Scanner sc = new Scanner(System.in);

    /**
     * Default Constructor
     * initialises an array list of movie type by reading from movies.dat
     */
    public Movies() {
        this.movie_list = (ArrayList<Movie>) FileHelper.read("data/movies.dat");
    }

    /**
     *Function that displays currently available movies and allows users to purchase tickets
     */

    public void displayMovies() {
        System.out.println("=".repeat(40));
        System.out.println("| List of all movies |");
        IdentityHashMap<String, ArrayList<Integer>> moviesByStatus = new IdentityHashMap<>();
        for (int i = 0; i < movie_list.size(); i++) {
            String status = movie_list.get(i).getStatus().toString();
            if (!moviesByStatus.containsKey(status) && movie_list.get(i).getStatus() != MovieStatus.END_OF_SHOWING) {
                moviesByStatus.put(status, new ArrayList<>());
            }
            moviesByStatus.get(status).add(i);
        }
        int movie_count = 0;
        IdentityHashMap<Integer, Integer> movieMap = new IdentityHashMap<>();
        for (Map.Entry<String, ArrayList<Integer>> entry : moviesByStatus.entrySet()) {
            System.out.println("(" + entry.getKey() + ")");
            for (Integer i : entry.getValue()) {
                System.out.println(" [" + ++movie_count + "] " + movie_list.get(i).getTitle());
                movieMap.put(movie_count, i);
            }
        }
        int choice = -1;
        while (choice != 0) {
            System.out.println("=".repeat(40));
            System.out.println("[1-" + movie_count + "] View movie information or buy tickets");
            System.out.println("[0] Go back");
            System.out.print("Enter option: ");
            choice = sc.nextInt();
            if (choice > 0 && choice <= movie_count) {
                int position = movieMap.get(choice);
                displayMovieInformation(movie_list.get(position));
                return;
            } else if (choice != 0) {
                System.out.println("Invalid option");
            }
        }
    }

    /**
     * Function that displays specific movie information and allows user to purchase tickets
     * @param movie - parameter that will be passed in to retrieve movie information
     */

    public void displayMovieInformation(Movie movie) {
        System.out.println("=".repeat(40));
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Synopsis: " + movie.getSynopsis());
        System.out.println("Director: " + movie.getDirector());
        System.out.println("Cast(s): ");
        movie.printCasts();
        System.out.println("Status: " + movie.getStatus().toString());
        if (movie.getRating().getRatings().size() < 2) {
            System.out.println("Average Rating: N/A");
        } else {
            System.out.println("Average Rating: " + String.format("%.2f", movie.getRating().getAverageRating()));
        }
        if (movie.getRating().getRatings().size() == 0) {
            System.out.println("No reviews yet");
        } else {
            System.out.println("Review(s): ");
            movie.getReview().printReviews();
        }
        System.out.println("Total Sales: " + movie.getSales());
        if(movie.getStatus() == MovieStatus.PREVIEW || movie.getStatus() == MovieStatus.SHOWING){
            int choice = -1;
            while (choice != 0) {
                System.out.println("=".repeat(40));
                System.out.println("[1] Buy tickets for this movie");
                System.out.println("[0] Go back");
                System.out.print("Enter option: ");
                choice = sc.nextInt();
                switch (choice) {
                    case 0 -> {
                    }
                    case 1 -> {
                        ShowTimes showTimes = new ShowTimes();
                        showTimes.displayShowtimesByMovie(movie);
                        return;
                    }
                    default -> System.out.println("Invalid option");
                }
            }
        }
    }

    /**
     * Functions that display movies sorted by their ratings (Top 5 Rating)
     */
    public void displayTop5rating() {
        System.out.println("=".repeat(40));
        System.out.println("| Top 5 movies by rating |");
        HashMap<Movie, Float> Map = new HashMap<>();
        LinkedHashMap<Movie, Float> sortedMap = new LinkedHashMap<>();
        ArrayList<Float> list = new ArrayList<>();
        //add to map.
        for (Movie movie : movie_list) {
            if(movie.getRating().getRatings().size() < 2){
                continue;
            }
            Map.put(movie, movie.getRating().getAverageRating());
        }
        for (java.util.Map.Entry<Movie, Float> entry : Map.entrySet()) {
            list.add(entry.getValue());
        }
        list.sort(Collections.reverseOrder());
        for (Float num : list) {
            for (java.util.Map.Entry<Movie, Float> entry : Map.entrySet()) {
                if (entry.getValue().equals(num)) {
                    sortedMap.put(entry.getKey(), num);
                }
            }
        }
        int print_count = 0;
        Map<String, Movie> movieMap = new HashMap<>();
        for (SortedMap.Entry<Movie, Float> entry : sortedMap.entrySet()) {
            if (print_count != 5 && entry.getKey().getStatus() != MovieStatus.END_OF_SHOWING) {
                System.out.println(" [" + ++print_count + "] " + entry.getKey().getTitle() + " (" + String.format("%.2f", entry.getValue()) + ")");
                movieMap.put(String.valueOf(print_count), entry.getKey());
            } else {
                break;
            }
        }
        if(print_count == 0){
            System.out.println("No movies available");
            return;
        }
        int choice = -1;
        while (choice != 0) {
            System.out.println("=".repeat(40));
            System.out.println("[1-" + print_count + "] View movie information or buy tickets");
            System.out.println("[0] Go back");
            System.out.print("Enter option: ");
            choice = sc.nextInt();
            if (choice > 0 && choice <= print_count) {
                Movie movie = movieMap.get(String.valueOf(choice));
                displayMovieInformation(movie);
                return;
            } else if (choice != 0) {
                System.out.println("Invalid option");
            }
        }
    }

    /**
     * Function that display movies sorted by their sales (Top 5 Sales)
     */
    public void displayTop5sales() {
        System.out.println("=".repeat(40));
        System.out.println("Top 5 movies by sales");
        HashMap<Movie, Integer> map1 = new HashMap<>();
        LinkedHashMap<Movie, Integer> sortedMap1 = new LinkedHashMap<>();
        ArrayList<Integer> list1 = new ArrayList<>();

        //add to map.
        for (Movie movie : movie_list) {
            map1.put(movie, movie.getSales());
        }
        for (Map.Entry<Movie, Integer> entry : map1.entrySet()) {
            list1.add(entry.getValue());
        }
        list1.sort(Collections.reverseOrder());
        for (int num : list1) {
            for (Map.Entry<Movie, Integer> entry : map1.entrySet()) {
                if (entry.getValue().equals(num)) {
                    sortedMap1.put(entry.getKey(), num);
                }
            }
        }
        int print_count = 0;
        Map<String, Movie> movieMap = new HashMap<>();
        for (SortedMap.Entry<Movie, Integer> entry : sortedMap1.entrySet()) {
            if (print_count != 5 && entry.getKey().getStatus() != MovieStatus.END_OF_SHOWING) {
                System.out.println(" [" + ++print_count + "] " + entry.getKey().getTitle() + " (" + entry.getValue() + " sales)");
                movieMap.put(String.valueOf(print_count), entry.getKey());
            } else {
                break;
            }
        }
        int choice = -1;
        while (choice != 0) {
            System.out.println("=".repeat(40));
            System.out.println("[1-" + print_count + "] View movie information or buy tickets");
            System.out.println("[0] Go back");
            System.out.print("Enter option: ");
            choice = sc.nextInt();
            if (choice > 0 && choice <= print_count) {
                Movie movie = movieMap.get(String.valueOf(choice));
                displayMovieInformation(movie);
                return;
            } else if (choice != 0) {
                System.out.println("Invalid option");
            }
        }
    }

    /**
     * Function that Allows user to select movie
     * @param showEndOfShowing - parameter that determines whether movie is showing
     * @return selected movie
     */
    public Movie selectMovie(boolean showEndOfShowing) {
        for (int i = 0; i < movie_list.size(); i++) {
            if(movie_list.get(i).getStatus() == MovieStatus.END_OF_SHOWING && !showEndOfShowing) {
                continue;
            }
            System.out.println(" [" + (i + 1) + "] " + movie_list.get(i).getTitle());
        }
        System.out.print("Enter selection: ");
        int add_choice;
        while (true) {
            add_choice = sc.nextInt();
            if (add_choice <= 0 || add_choice > movie_list.size() + 1) {
                System.out.println("Invalid option. Please try again.");
            } else {
                return movie_list.get(add_choice - 1);
            }
        }
    }

    /**
     * Function that increases the sales of the movie
     * @param movie - movie which sales will be increased
     * @param sales - number of sales to be increased
     */
    public void incrementScales(Movie movie, int sales) {
        //get movie index by title
        int index = -1;
        for (int i = 0; i < movie_list.size(); i++) {
            if (movie_list.get(i).getTitle().equals(movie.getTitle())) {
                index = i;
                break;
            }
        }
        if(index == -1) {
            System.out.println("Internal error");
            return;
        }

        movie_list.get(index).addSales(movie.getSales() + sales);
        FileHelper.write(movie_list, "data/movies.dat");
    }

    /**
     * Function that searches for a movie using its title and display its information
     */
    public void searchForMovie() {
        System.out.println("=".repeat(40));
        System.out.println("| Search for movie |");
        System.out.print("Enter movie title: ");
        String title = sc.nextLine();
        for (Movie movie : movie_list) {
            if (movie.getTitle().equals(title)) {
                displayMovieInformation(movie);
                return;
            }
        }
        System.out.println("Movie not found!");
    }

    /**
     * Function that adds a movie and its corresponding information
     */
    public void addMovie() {
        int add_choice = 0;
        Movie movie = new Movie();

        // Show pretty header
        System.out.println("Add new movie");
        System.out.println("=".repeat(40));

        // Set movie title
        System.out.println("Movie title: ");
        sc.skip("\\R?");
        movie.setTitle(sc.nextLine());

        // Set movie synopsis
        System.out.println("=".repeat(40));
        System.out.println("Movie synopsis: ");
        sc.skip("\\R?");
        movie.setSynopsis(sc.nextLine());

        // Set movie director
        System.out.println("=".repeat(40));
        System.out.println("Movie director: ");
        sc.skip("\\R?");
        movie.setDirector(sc.nextLine());

        // Set movie casts
        System.out.println("=".repeat(40));
        ArrayList<String> movie_cast = new ArrayList<>();
        boolean add_another = true;
        do {
            System.out.println("Enter a movie cast: ");
            sc.skip("\\R?");
            movie_cast.add(sc.nextLine());
            System.out.println("Add another cast? (y/N): ");
            sc.skip("\\R?");
            if (!sc.nextLine().equalsIgnoreCase("y")) {
                add_another = false;
            }
        } while (add_another);
        movie.setCast(movie_cast);

        // Set ratings
//        System.out.println("=".repeat(40));
//        ArrayList<Float> ratings_list = new ArrayList<>();
//        Rating rating = new Rating();
//        add_another = true;
//        do {
//            System.out.println("Enter a rating score (0-5): ");
//            sc.skip("\\R?");
//            ratings_list.add(sc.nextFloat());
//            System.out.println("Add another rating? (y/N): ");
//            sc.skip("\\R?");
//            if (!sc.nextLine().equalsIgnoreCase("y")) {
//                add_another = false;
//            }
//        } while (add_another);
//        rating.setRatings(ratings_list);
//        movie.setRating(rating);

        // Set movie age
        System.out.println("=".repeat(40));
        System.out.println("Movie age rating: ");
        System.out.println("[1] G");
        System.out.println("[2] PG");
        System.out.println("[3] PG13");
        System.out.println("[4] NC16");
        System.out.println("[5] M18");
        System.out.println("[6] R21");
        System.out.print("Enter option: ");
        while (true) {
            add_choice = sc.nextInt();
            if (add_choice == 1) {
                movie.setAgeRating(AgeRating.G);
                break;
            } else if (add_choice == 2) {
                movie.setAgeRating(AgeRating.PG);
                break;
            } else if (add_choice == 3) {
                movie.setAgeRating(AgeRating.PG13);
                break;
            } else if (add_choice == 4) {
                movie.setAgeRating(AgeRating.NC16);
                break;
            } else if (add_choice == 5) {
                movie.setAgeRating(AgeRating.M18);
                break;
            } else if (add_choice == 6) {
                movie.setAgeRating(AgeRating.R21);
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
        // Set movie reviews
//        System.out.println("=".repeat(40));
//        ArrayList<String> movie_reviews = new ArrayList<>();
//        Review review = new Review();
//        add_another = true;
//        do {
//            System.out.println("Enter a review: ");
//            sc.skip("\\R?");
//            movie_reviews.add(sc.nextLine());
//            System.out.println("Add another review? (y/N): ");
//            sc.skip("\\R?");
//            if (!sc.nextLine().equalsIgnoreCase("y")) {
//                add_another = false;
//            }
//        } while (add_another);
//        review.setReviews(movie_reviews);
//        movie.setReview(review);

        // Set movie type
        System.out.println("=".repeat(40));
        System.out.println("Movie type: ");
        System.out.println("[1] 2D");
        System.out.println("[2] 3D");
        System.out.print("Enter option: ");
        while (true) {
            add_choice = sc.nextInt();
            if (add_choice == 1) {
                movie.setMovieType(MovieType.twoD);
                break;
            } else if (add_choice == 2) {
                movie.setMovieType(MovieType.threeD);
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }

        // Set movie status
        System.out.println("=".repeat(40));
        System.out.println("Movie status: ");
        System.out.println("[1] Coming Soon");
        System.out.println("[2] Preview");
        System.out.println("[3] Showing");
        System.out.print("Enter option: ");
        while (true) {
            add_choice = sc.nextInt();
            if (add_choice == 1) {
                movie.setStatus(MovieStatus.COMING_SOON);
                break;
            } else if (add_choice == 2) {
                movie.setStatus(MovieStatus.PREVIEW);
                break;
            } else if (add_choice == 3) {
                movie.setStatus(MovieStatus.SHOWING);
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }

        System.out.println("Movie successfully added");
        movie_list.add(movie);
        FileHelper.write(movie_list, "data/movies.dat");
    }

    /**
     * Function that updates the specific information of a movie
     * @param movie - movie object for its information to be updated
     */
    public void updateMovie(Movie movie) {
        boolean edit = true;
        while (edit) {
            System.out.println("=".repeat(40));
            System.out.println("Updating " + movie.getTitle());
            System.out.println("Select field to edit");
            System.out.println("[1] Title: " + movie.getTitle());
            System.out.println("[2] Status: " + movie.getStatus());
            System.out.println("[3] Synopsis: " + movie.getSynopsis());
            System.out.println("[4] Director: " + movie.getDirector());
            System.out.println("[5] Type: " + movie.getMovieType());
            System.out.print("Enter option: ");
            switch (sc.nextInt()) {
                case 1 -> {
                    System.out.println("=".repeat(40));
                    System.out.println("Enter new title: ");
                    sc.skip("\\R?");
                    movie.setTitle(sc.nextLine());
                }
                case 2 -> {
                    int add_choice;
                    System.out.println("=".repeat(40));
                    System.out.println("New movie status: ");
                    System.out.println("[1] Coming Soon");
                    System.out.println("[2] Preview");
                    System.out.println("[3] Showing");
                    System.out.print("Enter option: ");
                    while (true) {
                        add_choice = sc.nextInt();
                        if (add_choice == 1) {
                            movie.setStatus(MovieStatus.COMING_SOON);
                            break;
                        } else if (add_choice == 2) {
                            movie.setStatus(MovieStatus.PREVIEW);
                            break;
                        } else if (add_choice == 3) {
                            movie.setStatus(MovieStatus.SHOWING);
                            break;
                        } else {
                            System.out.println("Invalid option. Please try again.");
                        }
                    }
                }
                case 3 -> {
                    System.out.println("=".repeat(40));
                    System.out.println("Enter new synopsis: ");
                    sc.skip("\\R?");
                    movie.setSynopsis(sc.nextLine());
                }
                case 4 -> {
                    System.out.println("=".repeat(40));
                    System.out.println("Enter new director: ");
                    sc.skip("\\R?");
                    movie.setDirector(sc.nextLine());
                }
                case 5 -> {
                    System.out.println("=".repeat(40));
                    System.out.println("New movie type: ");
                    System.out.println("[1] 2D");
                    System.out.println("[2] 3D");
                    System.out.print("Enter option: ");
                    int add_choice;
                    while (true) {
                        add_choice = sc.nextInt();
                        if (add_choice == 1) {
                            movie.setMovieType(MovieType.twoD);
                            break;
                        } else if (add_choice == 2) {
                            movie.setMovieType(MovieType.threeD);
                            break;
                        } else {
                            System.out.println("Invalid option. Please try again.");
                        }
                    }
                }
                default -> {
                    System.out.println("Invalid option. Please try again.");
                    continue;
                }
            }
            for(int i = 0; i < movie_list.size(); i++) {
                if(movie_list.get(i).getTitle().equals(movie.getTitle())) {
                    movie_list.set(i, movie);
                    break;
                }
            }
            FileHelper.write(movie_list, "data/movies.dat");
            System.out.println("Movie updated successfully");
            System.out.println("Update another field? (y/N): ");
            sc.skip("\\R?");
            if (!sc.nextLine().equalsIgnoreCase("y")) {
                edit = false;
            }
        }
    }

    /**
     * Function that Removes a movie and updates movies.dat file
     * @param movie - movie object to be removed
     */
    public void removeMovie(Movie movie) {
        System.out.println("=".repeat(40));
        System.out.println("[1] Delete movie permanently");
        System.out.println("[2] Set movie status to end of showing");
        System.out.println("[0] Go back");
        System.out.print("Enter option: ");
        while(true){
            switch (sc.nextInt()) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    movie_list.remove(movie);
                    FileHelper.write(movie_list, "data/movies.dat");
                    System.out.println("Movie successfully removed");
                    return;
                }
                case 2 -> {
                    movie.setStatus(MovieStatus.END_OF_SHOWING);
                    for(int i = 0; i < movie_list.size(); i++) {
                        if(movie_list.get(i).getTitle().equals(movie.getTitle())) {
                            movie_list.set(i, movie);
                        }
                    }
                    FileHelper.write(movie_list, "data/movies.dat");
                    System.out.println("Movie status updated successfully");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Function that adds a rating for a movie
     * @param movie - movie object for its rating to be added
     */
    public void addRating(Movie movie){
        System.out.println("=".repeat(40));
        System.out.println("Add rating for " + movie.getTitle());
        System.out.print("Enter rating: ");
        while(true){
            float rating = sc.nextFloat();
            if(rating >= 1 && rating <= 5){
                for(int i = 0; i < movie_list.size(); i++){
                    if(movie_list.get(i).getTitle().equals(movie.getTitle())){
                        movie_list.get(i).getRating().addRating(rating);
                        movie_list.get(10).getRating().printRatings();
                        FileHelper.write(movie_list, "data/movies.dat");
                        System.out.println("Rating successfully added");
                        break;
                    }
                }
            } else {
                System.out.println("Rating must be between 1-5. Please try again.");
            }
        }
    }

    /**
     * Function that adds a review for a movie
     * @param movie - movie object to be added a review
     */
    public void addReview(Movie movie){
        System.out.println("=".repeat(40));
        System.out.println("Add review for " + movie.getTitle());
        System.out.print("Enter review: ");
        sc.skip("\\R?");
        String review = sc.nextLine();

        for(int i = 0; i < movie_list.size(); i++){
            if(movie_list.get(i).getTitle().equals(movie.getTitle())){
                movie_list.get(i).getReview().addReview(review);
                movie_list.get(i).getReview().printReviews();
                FileHelper.write(movie_list, "data/movies.dat");
                System.out.println("Review successfully added");
                return;
            }
        }
    }

    public void viewReviews(Movie movie){
        System.out.println("=".repeat(40));
        System.out.println("Reviews for " + movie.getTitle());
        movie.getReview().printReviews();
        System.out.println("=".repeat(40));
    }
    /**
     * Function that views the rating of a movie
     * @param movie - movie object for its rating to be viewed
     */
    public void viewRatings(Movie movie){
        System.out.println("=".repeat(40));
        System.out.println("Ratings for " + movie.getTitle());
        movie.getRating().printRatings();
        System.out.println("=".repeat(40));
    }
}
