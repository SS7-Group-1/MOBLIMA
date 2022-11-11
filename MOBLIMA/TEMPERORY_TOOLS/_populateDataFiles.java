package MOBLIMA.TEMPERORY_TOOLS;

import MOBLIMA.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class _populateDataFiles {
    public static void main(String args[]) {
        System.out.println("Use this function to generate new cinema.dat and movie.dat files if needed");
        System.out.println("Confirm to overwrite all data files to default values? (y/N): ");
        Scanner sc = new Scanner(System.in);
        sc.skip("\\R?");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.println("Populating cinemas...");
            populateCinemas();
            System.out.println("Populating movies...");
            populateMovies();
            System.out.println("Populating showtimes...");
            populateShowTimes();
            System.out.println("Populating users...");
            populateUsers();
            System.out.println("Resetting booking file...");
            ArrayList<BookingRecord> booking_list = new ArrayList<>();
            FileHelper.write(booking_list, "data/bookings.dat");
            System.out.println("Populating vouchers...");
            populateVouchers();
            System.out.println("Done!");
        } else {
            System.out.println("Aborted.");
        }
    }

    static Cinema showTimeCinema1;
    static Cinema showTimeCinema2;
    static Cinema showTimeCinema3;
    static Cinema showTimeCinema4;
    static Cinema showTimeCinema5;
    static Movie showTimeMovie1;
    static Movie showTimeMovie2;
    static Movie showTimeMovie3;
    static Movie showTimeMovie4;
    static Movie showTimeMovie5;

    public static void populateCinemas() {
        ArrayList<Cinema> cinema_list = new ArrayList<>();
        Seat[][] seats = new Seat[6][8];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                seats[i][j] = new Seat(SeatType.STANDARD, i, j);
            }
        }
        ArrayList<Integer> aisle= new ArrayList<>();
        cinema_list.add(new Cinema(Cineplex.LIDO, false, "LD1", seats,aisle));
        cinema_list.add(new Cinema(Cineplex.LIDO, false, "LD2", seats,aisle));
        cinema_list.add(new Cinema(Cineplex.LIDO, false, "LD3", seats,aisle));
        seats = new Seat[7][9];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++) {
                seats[i][j] = new Seat(SeatType.STANDARD, i, j);
            }
        }
        cinema_list.add(new Cinema(Cineplex.PAYA_LEBAR_QUARTER, false, "PQ1", seats,aisle));
        cinema_list.add(new Cinema(Cineplex.PAYA_LEBAR_QUARTER, false, "PQ2", seats,aisle));
        cinema_list.add(new Cinema(Cineplex.PAYA_LEBAR_QUARTER, false, "PQ3", seats,aisle));
        seats = new Seat[7][8];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 8; j++) {
                seats[i][j] = new Seat(SeatType.STANDARD, i, j);
            }
        }
        cinema_list.add(new Cinema(Cineplex.NEX, false, "NX1", seats,aisle));
        cinema_list.add(new Cinema(Cineplex.NEX, false, "NX2", seats,aisle));
        cinema_list.add(new Cinema(Cineplex.NEX, false, "NX3", seats,aisle));
        seats = new Seat[4][6];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                seats[i][j] = new Seat(SeatType.STANDARD, i, j);
            }
        }
        cinema_list.add(new Cinema(Cineplex.NEX, true, "NXP", seats,aisle));
        seats = new Seat[8][9];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 9; j++) {
                seats[i][j] = new Seat(SeatType.STANDARD, i, j);
            }
        }
        cinema_list.add(new Cinema(Cineplex.JEWEL, false, "JW1", seats,aisle));
        cinema_list.add(new Cinema(Cineplex.JEWEL, false, "JW2", seats,aisle));
        cinema_list.add(new Cinema(Cineplex.JEWEL, false, "JW3", seats,aisle));
        seats = new Seat[5][6];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                seats[i][j] = new Seat(SeatType.STANDARD, i, j);
            }
        }
        cinema_list.add(new Cinema(Cineplex.JEWEL, true, "JWP", seats,aisle));
        FileHelper.write(cinema_list, "data/cinemas.dat");

        showTimeCinema1 = cinema_list.get(0);
        showTimeCinema2 = cinema_list.get(3);
        showTimeCinema3 = cinema_list.get(5);
        showTimeCinema4 = cinema_list.get(7);
        showTimeCinema5 = cinema_list.get(9);
    }

    public static void populateMovies() {
        ArrayList<Movie> movie_list = new ArrayList<>();
        Movie movie;
        ArrayList<String> cast;
        ArrayList<String> reviews;
        Review review;
        ArrayList<Float> ratings;
        Rating rating;

        // MOVIE 1/10 : Kung Fu Nigel
        movie = new Movie();
        reviews = new ArrayList<>();
        ratings = new ArrayList<>();
        cast = new ArrayList<>();
        movie.setTitle("Kung Fu Nigel");
        movie.setStatus(MovieStatus.SHOWING);
        movie.setSynopsis("A movie about a kung fu master named Nigel");
        movie.setDirector("Ariana Venti");
        cast.add("Emily Willis");
        cast.add("Brandi Love");
        cast.add("Mia Khalifa");
        reviews.add("Kong Fu Nigel is a fantastic story that creates some cool mythology.");
        reviews.add("A near perfect balance of deep philosophy & comedy.");
        reviews.add("This movie was absolutely out of this world. The musical score, the scenery, the plot, the story, the characters, everything. I didn't expect to like this and it is indeed fantastic");
        ratings.add(5.0f);
        ratings.add(4.5f);
        ratings.add(4.2f);
        ratings.add(2.7f);
        movie.setAgeRating(AgeRating.PG);
        movie.setMovieType(MovieType.threeD);
        movie.setDuration(120);
        movie.setCast(cast);
        movie.setReview(new Review(reviews));
        movie.setRating(new Rating(ratings));
        showTimeMovie1 = movie;
        movie_list.add(movie);

        // MOVIE 2/10 : Journey to the Center of NTU
        movie = new Movie();
        reviews = new ArrayList<>();
        ratings = new ArrayList<>();
        cast = new ArrayList<>();
        movie.setTitle("Journey to the Center of NTU");
        movie.setStatus(MovieStatus.SHOWING);
        movie.setSynopsis("During an expedition to NTU's Business Library, Nigel and his girlfriend gets trapped in a handicap toilet.");
        movie.setDirector("Max Hardcore");
        cast.add("Don Fernando");
        cast.add("Casey Calvert");
        reviews.add("For those who delight in this niche or those who are game for it, Journey to the Center of NTU will no doubt be a pleasing full-on sound and color experience.");
        reviews.add("While its ideas might fail to fully coalesce, the film is unnervingly beautiful; an immersive and mesmeric aural and visual experience.");
        reviews.add("The documentary has a great flow, and Nigel's curiosity often becomes our own. The film is a great example of how to make a documentary that is both entertaining and informative.");
        ratings.add(3.4f);
        ratings.add(4.7f);
        ratings.add(4.8f);
        ratings.add(3.9f);
        ratings.add(2.7f);
        movie.setAgeRating(AgeRating.R21);
        movie.setMovieType(MovieType.twoD);
        movie.setDuration(105);
        movie.setCast(cast);
        movie.setReview(new Review(reviews));
        movie.setRating(new Rating(ratings));
        showTimeMovie2 = movie;
        movie_list.add(movie);

        // MOVIE 3/10 : Train to NTU
        movie = new Movie();
        reviews = new ArrayList<>();
        ratings = new ArrayList<>();
        cast = new ArrayList<>();
        movie.setTitle("Train to NTU");
        movie.setStatus(MovieStatus.SHOWING);
        movie.setSynopsis("Nigel and his girlfriend are on a train to NTU. However, the journey turns into a nightmare when they are trapped amidst a monkey outbreak in NTU.");
        movie.setDirector("Snoop Catt");
        cast.add("Mia Malkova");
        cast.add("Riley Reid");
        cast.add("Eva Elfie");
        reviews.add("It could not be done better. They pulled no punches with this movie.");
        reviews.add("Train to NTU is horror as it should be: intelligent, fun, and celebrating life with every mad twist and new take on death that it can find.");
        reviews.add("Its good characters, deeper themes, and impeccable execution helps it to defy any dismissive genre perceptions some folks may have.");
        reviews.add("The movie is a great example of how to make a horror movie that is both scary and fun at the same time.");
        ratings.add(4.7f);
        ratings.add(3.0f);
        ratings.add(3.7f);
        ratings.add(4.8f);
        ratings.add(4.7f);
        movie.setAgeRating(AgeRating.R21);
        movie.setMovieType(MovieType.twoD);
        movie.setDuration(95);
        movie.setCast(cast);
        movie.setReview(new Review(reviews));
        movie.setRating(new Rating(ratings));
        showTimeMovie3 = movie;
        movie_list.add(movie);

        // MOVIE 4/10 : Fantastic Nigel: The Secrets of GPA 5.0
        movie = new Movie();
        reviews = new ArrayList<>();
        ratings = new ArrayList<>();
        cast = new ArrayList<>();
        movie.setTitle("Fantastic Nigel: The Secrets of GPA 5.0");
        movie.setStatus(MovieStatus.PREVIEW);
        movie.setSynopsis("NTU students are determined to find out how a student can possibly get a perfect GPA of 5.0.");
        movie.setDirector("Subra Suresh");
        cast.add("Abella Danger");
        cast.add("Lana Rhoades");
        reviews.add("While perhaps the best Fantastic Nigel film, The Secrets of GPA 5.0 remains a dour movie with lots to say but not enough imagination or understanding to say it well.");
        reviews.add("It's very clear, watching these movies, that they're adapted from a branded guidebook. They have all the dramatic interest and excitement of a manual.");
        reviews.add("The sequence of events that take place in this film just don't build up to anything that feels like it matters.");
        ratings.add(1.0f);
        ratings.add(1.1f);
        ratings.add(1.5f);
        ratings.add(2.0f);
        ratings.add(1.0f);
        movie.setAgeRating(AgeRating.NC16);
        movie.setMovieType(MovieType.threeD);
        movie.setDuration(100);
        movie.setCast(cast);
        movie.setReview(new Review(reviews));
        movie.setRating(new Rating(ratings));
        showTimeMovie4 = movie;
        movie_list.add(movie);

        // MOVIE 5/10 : GPA5: Impossible - ICC Mods
        movie = new Movie();
        reviews = new ArrayList<>();
        ratings = new ArrayList<>();
        cast = new ArrayList<>();
        movie.setTitle("GPA5: Impossible - ICC Mods");
        movie.setStatus(MovieStatus.COMING_SOON);
        movie.setSynopsis("NTU student GPAs comes under threat from the ICC Office. Faced with the intense workloads, NTU students assembles a team to bring the ICC office down by any means necessary.");
        movie.setDirector("Melissa Hill");
        cast.add("Lorelei Lee");
        cast.add("Miles Long");
        reviews.add("This action blockbuster succeeds the impossible job most action movies do; seducing the audience without fear.");
        reviews.add("This is the best GPA5 Impossible film due to its originality and flair.");
        reviews.add("Probably one of my favourite action films ever, i just finished watching this for the third time. Full of action, suspense and great stunts");
        ratings.add(5.0f);
        ratings.add(5.0f);
        ratings.add(4.9f);
        ratings.add(4.8f);
        ratings.add(5.0f);
        movie.setAgeRating(AgeRating.PG13);
        movie.setMovieType(MovieType.threeD);
        movie.setDuration(90);
        movie.setCast(cast);
        movie.setReview(new Review(reviews));
        movie.setRating(new Rating(ratings));
        showTimeMovie5 = movie;
        movie_list.add(movie);

        // MOVIE 6/10 : GPA5: Boku no Nigel
        movie = new Movie();
        reviews = new ArrayList<>();
        ratings = new ArrayList<>();
        cast = new ArrayList<>();
        movie.setTitle("Boku no Nigel");
        movie.setStatus(MovieStatus.COMING_SOON);
        movie.setSynopsis("Nigel is a white-collar worker looking for an escape from the mundanity of his everyday life.");
        movie.setDirector("Michael Morrison");
        cast.add("Ed Powers");
        cast.add("Steven Scarborough");
        cast.add("Noel C. Bloom");
        reviews.add("Best anime I've ever seen in my life. My friend first showed me anime a couple years back and this was the one he showed me and I've been obsessed with it ever since.");
        reviews.add("Boku no Nigel was pretty good overall. I actually thought it had a decent plot. It has sequencial rising acition and an exciting climax.");
        reviews.add("Let's just make this quick. I have holy water. I used it. On my eyes.");
        reviews.add("Now hear me out this show is clearly a 10/10 masterpiece and anyone who tells you otherwise is lying.");
        ratings.add(5.0f);
        ratings.add(5.0f);
        ratings.add(4.9f);
        ratings.add(4.8f);
        ratings.add(4.9f);
        movie.setAgeRating(AgeRating.R21);
        movie.setMovieType(MovieType.threeD);
        movie.setDuration(80);
        movie.setCast(cast);
        movie.setReview(new Review(reviews));
        movie.setRating(new Rating(ratings));
        movie_list.add(movie);

        // MOVIE 7/10 : My Hero Nigel
        movie = new Movie();
        reviews = new ArrayList<>();
        ratings = new ArrayList<>();
        cast = new ArrayList<>();
        movie.setTitle("My Hero Nigel");
        movie.setStatus(MovieStatus.COMING_SOON);
        movie.setSynopsis("A superhero-loving boy without any powers is determined to enroll in a prestigious hero academy and learn what it really means to be a hero.");
        movie.setDirector("Viv Thomas");
        cast.add("Chris P. Bacon");
        cast.add("J. D. Slater");
        cast.add("Wakefield Poole");
        reviews.add("I love this show so much it was my first anime I have ever watched I would totally recommend watching it.");
        reviews.add("My Hero Nigel was my first anime I watched recently, and I can say without a doubt it is my favorite");
        reviews.add("Love My Hero Nigel because it is such an amazing, nice, brave, and funny story that teaches you to become a true Hero.");
        ratings.add(3.7f);
        ratings.add(4.7f);
        ratings.add(3.8f);
        ratings.add(2.9f);
        ratings.add(3.7f);
        movie.setAgeRating(AgeRating.PG);
        movie.setMovieType(MovieType.threeD);
        movie.setDuration(150);
        movie.setCast(cast);
        movie.setReview(new Review(reviews));
        movie.setRating(new Rating(ratings));
        movie_list.add(movie);

        // MOVIE 8/10 : Attack on Nigel
        movie = new Movie();
        reviews = new ArrayList<>();
        ratings = new ArrayList<>();
        cast = new ArrayList<>();
        movie.setTitle("Attack on Nigel");
        movie.setStatus(MovieStatus.SHOWING);
        movie.setSynopsis("Jia Wei is determined to help save NTU when Nigels re-appear and being to feast on low GPA students.");
        movie.setDirector("Bruno Earth");
        cast.add("Angela White");
        cast.add("Lena Paul");
        reviews.add("I am not the regular anime fan, I've seen some really good ones but Attack on Nigel sits on a whole other level of amazing writing.");
        reviews.add("This series is really ahead of its time, its brilliant and its definitely the best show ive ever watched in my life.");
        reviews.add("Incredible, I honestly have to say that this could be the best anime ever due to its development and plot.");
        ratings.add(2.7f);
        ratings.add(3.7f);
        ratings.add(1.0f);
        ratings.add(3.7f);
        ratings.add(4.8f);
        movie.setAgeRating(AgeRating.PG13);
        movie.setMovieType(MovieType.twoD);
        movie.setDuration(120);
        movie.setCast(cast);
        movie.setReview(new Review(reviews));
        movie.setRating(new Rating(ratings));
        movie_list.add(movie);

        // MOVIE 9/10 : The Fault in Our Nigel
        movie = new Movie();
        reviews = new ArrayList<>();
        ratings = new ArrayList<>();
        cast = new ArrayList<>();
        movie.setTitle("The Fault in Our Nigel");
        movie.setStatus(MovieStatus.SHOWING);
        movie.setSynopsis("Nigel, a 21-year-old NTU student, meets and falls in love with Rae Lil Black, another NTU student.");
        movie.setDirector("Scott Masters");
        cast.add("Sharon Mitchell");
        cast.add("John T. Bone");
        cast.add("Pat Myne");
        reviews.add("It's a carpe diem love story that overcomes its own faults, and earns its tempered hopefulness, by maturely recognizing that love and pain are equal, and often intertwined, parts of life.");
        reviews.add("It actually presents us with situations and a relationship that feels genuine yet uncertain because of the dark cloud hanging over it.");
        reviews.add("The Fault In Our Nigel could indeed become this generation's Love Story - mildly implausible, but genuinely moving.");
        ratings.add(3.7f);
        ratings.add(2.7f);
        ratings.add(4.7f);
        ratings.add(2.8f);
        ratings.add(4.8f);
        movie.setAgeRating(AgeRating.NC16);
        movie.setMovieType(MovieType.twoD);
        movie.setDuration(90);
        movie.setCast(cast);
        movie.setReview(new Review(reviews));
        movie.setRating(new Rating(ratings));
        movie_list.add(movie);

        // MOVIE 10/10 : Tentacle Game
        movie = new Movie();
        reviews = new ArrayList<>();
        ratings = new ArrayList<>();
        cast = new ArrayList<>();
        movie.setTitle("Tentacle Game");
        movie.setStatus(MovieStatus.SHOWING);
        movie.setSynopsis("Nigel, a broke NTU student and who lives with his roomie, is invited to play a series of children's games for a chance at a large cash prize.");
        movie.setDirector("Jenna Haze");
        cast.add("Steve Drake");
        cast.add("Patrick Collins");
        reviews.add("Tentacle Game is a well-produced thriller with a couple of decent twists and a whole lot of violence.");
        reviews.add("This is a series that needs to be experienced, not discussed or analysed. It is one that will stay on in your mind long after you've watched it.");
        reviews.add("A masterful show that truly changed the game in storytelling.");
        ratings.add(3.7f);
        ratings.add(4.5f);
        ratings.add(4.6f);
        ratings.add(3.8f);
        ratings.add(2.8f);
        movie.setAgeRating(AgeRating.PG13);
        movie.setMovieType(MovieType.threeD);
        movie.setDuration(110);
        movie.setCast(cast);
        movie.setReview(new Review(reviews));
        movie.setRating(new Rating(ratings));
        movie_list.add(movie);

        FileHelper.write(movie_list, "data/movies.dat");
    }

    public static void populateShowTimes(){
        ArrayList<ShowTime> showTime_list = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

        showTime_list.add(new ShowTime(showTimeCinema4, showTimeMovie1, LocalDateTime.parse("13:00 11/11/2022", formatter)));
        showTime_list.add(new ShowTime(showTimeCinema5, showTimeMovie1, LocalDateTime.parse("18:40 16/11/2022", formatter)));

        showTime_list.add(new ShowTime(showTimeCinema1, showTimeMovie2, LocalDateTime.parse("14:20 11/11/2022", formatter)));
        showTime_list.add(new ShowTime(showTimeCinema2, showTimeMovie2, LocalDateTime.parse("15:10 17/11/2022", formatter)));
        showTime_list.add(new ShowTime(showTimeCinema3, showTimeMovie2, LocalDateTime.parse("14:50 15/11/2022", formatter)));

        showTime_list.add(new ShowTime(showTimeCinema2, showTimeMovie3, LocalDateTime.parse("17:10 18/11/2022", formatter)));
        showTime_list.add(new ShowTime(showTimeCinema3, showTimeMovie3, LocalDateTime.parse("14:50 10/11/2022", formatter)));
        showTime_list.add(new ShowTime(showTimeCinema4, showTimeMovie3, LocalDateTime.parse("16:00 13/11/2022", formatter)));
        showTime_list.add(new ShowTime(showTimeCinema5, showTimeMovie3, LocalDateTime.parse("13:40 12/11/2022", formatter)));

        showTime_list.add(new ShowTime(showTimeCinema1, showTimeMovie4, LocalDateTime.parse("09:20 15/11/2022", formatter)));
        showTime_list.add(new ShowTime(showTimeCinema2, showTimeMovie4, LocalDateTime.parse("17:10 17/11/2022", formatter)));
        showTime_list.add(new ShowTime(showTimeCinema5, showTimeMovie4, LocalDateTime.parse("12:40 15/11/2022", formatter)));

        showTime_list.add(new ShowTime(showTimeCinema1, showTimeMovie5, LocalDateTime.parse("14:20 08/11/2022", formatter)));
        showTime_list.add(new ShowTime(showTimeCinema2, showTimeMovie5, LocalDateTime.parse("13:10 13/11/2022", formatter)));
        showTime_list.add(new ShowTime(showTimeCinema5, showTimeMovie5, LocalDateTime.parse("21:40 14/11/2022", formatter)));

        FileHelper.write(showTime_list, "data/showtimes.dat");
    }

    public static void populateUsers(){
        ArrayList<User> user_list = new ArrayList<>();
        user_list.add(new User("user@moblima.com", "User", "87456874", "password", false));
        user_list.add(new User("admin@moblima.com", "Admin", "95447824", "password", true));

        FileHelper.write(user_list, "data/users.dat");
    }

    public static void populateVouchers(){
        ArrayList<Voucher> voucher_list = new ArrayList<>();
        voucher_list.add(new Voucher("UNLIMITED2OFF", 2f, -1));
        voucher_list.add(new Voucher("ONETIME10OFF", 10f, 1));
        voucher_list.add(new Voucher("LIMITED5OFF", 5f, 10));
        FileHelper.write(voucher_list, "data/vouchers.dat");
    }
}
