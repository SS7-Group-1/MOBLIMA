package MOBLIMA.App;

import MOBLIMA.*;

import java.util.*;

public class Movies
{
    ArrayList<Movie> movie_list;
    Scanner sc = new Scanner(System.in);

    public Movies()
    {
        this.movie_list = (ArrayList<Movie>) FileHelper.read("data/movies.dat");
    }

    public void displayMovies()
    {
        System.out.println("▭".repeat(40));
        System.out.println("| List of all movies |");
        Map<String, List<Movie>> moviesByStatus = new HashMap<>();
        for (Movie movie : movie_list) {
            String status = movie.getStatus().toString();
            if (!moviesByStatus.containsKey(status) && movie.getStatus() != MovieStatus.END_OF_SHOWING) {
                moviesByStatus.put(status, new ArrayList<>());
            }
            moviesByStatus.get(status).add(movie);
        }
        for (Map.Entry<String, List<Movie>> entry : moviesByStatus.entrySet()) {
            System.out.println("[" + entry.getKey() + "]");
            for (Movie movie : entry.getValue()) {
                System.out.println(" - " + movie.getTitle());
            }
        }
    }

    public void displayTop5rating()
    {
        System.out.println("▭".repeat(40));
        System.out.println("| Top 5 movies by rating |");
        HashMap<String,Float> Map = new HashMap<>();
        LinkedHashMap<String, Float> sortedMap = new LinkedHashMap<>();
        ArrayList<Float> list = new ArrayList<>();
        //add to map.
        for (Movie movie : movie_list) {
            Map.put(movie.getTitle(), movie.getRating().getAverageRating());
        }
        for (java.util.Map.Entry<String, Float> entry : Map.entrySet()) {
            list.add(entry.getValue());
        }
        list.sort(Collections.reverseOrder());
        for (Float num : list) {
            for (java.util.Map.Entry<String, Float> entry : Map.entrySet()) {
                if (entry.getValue().equals(num)) {
                    sortedMap.put(entry.getKey(), num);
                }
            }
        }
        int print_count =0;
        for (SortedMap.Entry<String, Float> entry : sortedMap.entrySet()) {
            if (print_count!=5) {
                System.out.println(" " + ++print_count + ". " + entry.getKey() + " (" + String.format("%.2f", entry.getValue()) + ")");
            }else{
                break;
            }
        }
    }

    public void displayTop5sales()
    {
        System.out.println("▭".repeat(40));
        System.out.println("Top 5 movies by sales");
        HashMap<String, Integer> map1 = new HashMap<>();
        LinkedHashMap<String, Integer> sortedMap1 = new LinkedHashMap<>();
        ArrayList<Integer> list1 = new ArrayList<>();

        //add to map.
        for (Movie movie : movie_list) {
            map1.put(movie.getTitle(), movie.getSales());
        }
        for (Map.Entry<String, Integer> entry : map1.entrySet()) {
            list1.add(entry.getValue());
        }
        list1.sort(Collections.reverseOrder());
        for (int num : list1) {
            for (Map.Entry<String, Integer> entry : map1.entrySet()) {
                if (entry.getValue().equals(num)) {
                    sortedMap1.put(entry.getKey(), num);
                }
            }
        }
        int print_count=0;
        for (SortedMap.Entry<String, Integer> entry : sortedMap1.entrySet()) {
            if (print_count!=5) {
                System.out.println(" " + ++print_count + ". " + entry.getKey() + " (" + entry.getValue() + " sales)");
            }else{
                break;
            }
        }
    }

    public Movie selectMovie(){
        return null;
    }

    public void searchForMovie(){
        System.out.println("▭".repeat(40));
        System.out.println("| Search for movie |");
        System.out.print("Enter movie title: ");
        String title = sc.nextLine();
        for (Movie movie : movie_list) {
            if (movie.getTitle().equals(title)) {
                System.out.println("Movie found!");
                System.out.println("Title: " + movie.getTitle());
                System.out.println("Synopsis: " + movie.getSynopsis());
                System.out.println("Director: " + movie.getDirector());
                System.out.println("Cast(s): ");
                movie.printCasts();
                System.out.println("Status: " + movie.getStatus());
                System.out.println("Average Rating: " + String.format("%.2f", movie.getRating().getAverageRating()));
                System.out.println("Review(s): ");
                movie.getReview().printReviews();
                System.out.println("Total Sales: " + movie.getSales());
                return;
            }
        }
        System.out.println("Movie not found!");
    }

    public void addMovie(){
        int add_choice = 0;
        Movie movie = new Movie();

        // Show pretty header
        System.out.println("Add new movie");
        System.out.println("*".repeat(40));

        // Set movie title
        System.out.println("Movie title: ");
        sc.skip("\\R?");
        movie.setTitle(sc.nextLine());

        // Set movie status
        System.out.println("*".repeat(40));
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

        // Set movie synopsis
        System.out.println("*".repeat(40));
        System.out.println("Movie synopsis: ");
        sc.skip("\\R?");
        movie.setSynopsis(sc.nextLine());

        // Set movie director
        System.out.println("*".repeat(40));
        System.out.println("Movie director: ");
        sc.skip("\\R?");
        movie.setDirector(sc.nextLine());

        // Set movie casts
        System.out.println("*".repeat(40));
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
        System.out.println("*".repeat(40));
        ArrayList<Float> ratings_list = new ArrayList<>();
        Rating rating = new Rating();
        add_another = true;
        do {
            System.out.println("Enter a rating score (0-5): ");
            sc.skip("\\R?");
            ratings_list.add(sc.nextFloat());
            System.out.println("Add another rating? (y/N): ");
            sc.skip("\\R?");
            if (!sc.nextLine().equalsIgnoreCase("y")) {
                add_another = false;
            }
        } while (add_another);
        rating.setRatings(ratings_list);
        movie.setRating(rating);

        // Set movie age
        System.out.println("*".repeat(40));
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
        System.out.println("*".repeat(40));
        ArrayList<String> movie_reviews = new ArrayList<>();
        Review review = new Review();
        add_another = true;
        do {
            System.out.println("Enter a review: ");
            sc.skip("\\R?");
            movie_reviews.add(sc.nextLine());
            System.out.println("Add another review? (y/N): ");
            sc.skip("\\R?");
            if (!sc.nextLine().equalsIgnoreCase("y")) {
                add_another = false;
            }
        } while (add_another);
        review.setReviews(movie_reviews);
        movie.setReview(review);

        // Set movie type
        System.out.println("*".repeat(40));
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
        System.out.println("Movie successfully added");
        System.out.println(movie);
        //addMovie(movie);
    }

    public void updateMovie(Movie movie){
        boolean edit = true;
        while (edit) {
            System.out.println("*".repeat(40));
            System.out.println("Updating " + movie.getTitle());
            System.out.println("Select field to edit");
            System.out.println("[1] Title ");
            System.out.println("[2] Status ");
            System.out.println("[3] Synopsis ");
            System.out.println("[4] Director ");
            System.out.println("[5] Type");
            System.out.print("Enter option: ");
            switch (sc.nextInt()){
                case 1:
                    System.out.println("*".repeat(40));
                    System.out.println("Enter new title: " );
                    sc.skip("\\R?");
                    movie.setTitle(sc.nextLine());
                    break;
                case 2:
                    int add_choice;
                    System.out.println("*".repeat(40));
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
                    break;
                case 3:
                    System.out.println("*".repeat(40));
                    System.out.println("Enter new synopsis: ");
                    sc.skip("\\R?");
                    movie.setSynopsis(sc.nextLine());
                    break;
                case 4:
                    System.out.println("*".repeat(40));
                    System.out.println("Enter new director: ");
                    sc.skip("\\R?");
                    movie.setDirector(sc.nextLine());
                    break;
                case 5:
                    System.out.println("*".repeat(40));
                    System.out.println("New movie type: ");
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
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    continue;
            }
            FileHelper.write(movie_list, "data/movie.dat");
            System.out.println("Movie updated successfully");
            System.out.println("Update another field? (y/N): ");
            sc.skip("\\R?");
            if (!sc.nextLine().equalsIgnoreCase("y")) {
                edit = false;
            }
        }
    }

    public void removeMovie(Movie movie){
        movie_list.remove(movie);
        FileHelper.write(movie_list, "movies.dat");
        System.out.println("Movie successfully removed");
    }
}
