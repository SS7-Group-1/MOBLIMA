package MOBLIMA.Displays;

import MOBLIMA.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class MovieListingStaff {

    ArrayList<Movie> movie_list = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public void DisplayMenu() throws IOException {
        movie_list = (ArrayList<Movie>) FileHelper.read("data/movie.dat");

        System.out.println("*".repeat(40));
        System.out.println("Movie Listing Staff Menu");
        int choice = 0;
        while (choice != 4) {
            System.out.println("*".repeat(40));
            System.out.println("[1] Add Movie");
            System.out.println("[2] Update Movie");
            System.out.println("[3] Remove Movie");
            System.out.println("[4] Exit");
            System.out.print("Enter option: ");
            choice = sc.nextInt();
            System.out.println("*".repeat(40));
            switch (choice) {
                case 1: //Add movie
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
                    Ratings rating = new Ratings();
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
                    rating.setRating(ratings_list);
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
                    Reviews review = new Reviews();
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
                    review.setReview(movie_reviews);
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
                    addMovie(movie);
                    break;
                case 2: // Edit movie detail
                case 3: // Delete movie
                    System.out.println("Edit movie details");
                    System.out.println("*".repeat(40));
                    Movie chosen = null;
                    System.out.println("Select movie to " + (choice == 2 ? "edit" : "delete"));
                    // loop movie_list
                    for (int i = 0; i < movie_list.size(); i++) {
                        System.out.println(" [" + (i + 1) + "] " + movie_list.get(i).getTitle());
                    }
                    System.out.print("Enter selection: ");
                    while (true) {
                        add_choice = sc.nextInt();
                        if (add_choice < 0 || add_choice > movie_list.size() + 1) {
                            System.out.println("Invalid option. Please try again.");
                        } else {
                            if (choice == 2) {
                                updateMovie(movie_list.get(add_choice - 1));
                            } else {
                                removeMovie(movie_list.get(add_choice - 1));
                            }
                            break;
                        }
                    }
                    break;
                case 4: // Exit
                    System.out.println("Exiting application...");
                    System.exit(69);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void addMovie(Movie movie) {
        movie_list.add(movie);
        FileHelper.write(movie_list, "data/movie.dat");
    }

    public void updateMovie(Movie movie) {
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

    public void removeMovie(Movie movie) {
        System.out.println(movie.getTitle() + " has been removed");
        movie_list.remove(movie);
        FileHelper.write(movie_list, "data/movie.dat");
    }
}