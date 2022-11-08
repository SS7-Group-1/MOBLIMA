package MOBLIMA.App;

import MOBLIMA.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that handles all the cinemas
 * @since 2022-11-07
 * @author Lim Jia Wei
 * @version 1.5
 */
public class Cinemas {
    ArrayList<Cinema> cinema_list;
    Scanner sc = new Scanner(System.in);

    /**
     * Default Constructor
     * Initialises an array list of Cinema Objects by reading from cinemas.dat
     */
    public Cinemas(){
        this.cinema_list = (ArrayList<Cinema>) FileHelper.read("data/cinemas.dat");
    }

    /**
     * Function that allows user to select Cinema
     * @return Cinema object that was selected
     */

    public Cinema selectCinema(){
        // select cinema
        System.out.println("Select a cinema");
        int cinema_count = 0;
        for (Cinema cinema: cinema_list){
            System.out.println(cinema_count + ". " + cinema);
            cinema_count++;
        }
        System.out.print("Enter cinema number: ");
        int cinema_number = sc.nextInt();
        return cinema_list.get(cinema_number);
    }

    /**
     * Function that displays all available Cinema
     */
    public void displayCinemas(){
        for (Cinema cinema: cinema_list){
            System.out.println(cinema);
        }
    }

    /**
     * Function that adds a Cinema into its respective Cineplex
     * Added Cinema Consist of Cinema type (Platinum/ Non-platinum) , Choice of cineplex , Cinema Code , Seat arrangement
     * Added Cinema will be written back into cinemas.dat
     */

    public void addCinema(){
        Cinema cinema = new Cinema();
        // select cineplex
        //loop cineplex
        System.out.println("=".repeat(40));
        System.out.println("Cineplex list");
        int cineplex_count = 0;
        for (Cineplex cineplex : Cineplex.values()) {
            System.out.println("[" + (cineplex_count + 1) + "] " + cineplex);
            cineplex_count++;
        }
        if(cineplex_count == 0){
            System.out.println("Unable to add cinema. No cineplex found");
            return;
        }
        System.out.print("Select cineplex: ");
        int add_choice;
        while (true) {
            add_choice = sc.nextInt();
            if (add_choice < 0 || add_choice > cineplex_count+1) {
                System.out.println("Invalid option. Please try again.");
            } else {
                cinema.setCineplex(Cineplex.values()[add_choice - 1]);
                break;
            }
        }

        // is platinum?
        System.out.println("Is this a platinum cinema? (y/N)");
        sc.skip("\\R?");
        String type = sc.nextLine();
        boolean isPlatinum = type.equalsIgnoreCase("y");
        cinema.setPlatinum(isPlatinum);

        // select cinema code
        System.out.println("Enter cinema code: ");
        sc.skip("\\R?");
        String cinemaCode = sc.nextLine();
        cinema.setCinemaCode(cinemaCode);

        // set seats
        System.out.println("Enter number of rows: ");
        int rows = sc.nextInt();
        System.out.println("Enter number of columns: ");
        int cols = sc.nextInt();
        cinema.setSeatingLayout(rows, cols);
        cinema_list.add(cinema);
        FileHelper.write(cinema_list, "data/cinemas.dat");
        System.out.println("Cinema added successfully");
    }

    /**
     * Function that allows the update of Cinemas
     * Allows update of Cinemas' Cineplex , Cinema Code and Premium Seats
     * Updated Cinema will be written back into cinemas.dat
     * @param cinema - cinema object to be updated
     */
    public void updateCinema(Cinema cinema){
        boolean edit = true;
        System.out.println("=".repeat(40));
        System.out.println("Updating " + cinema);
        while (edit) {
            System.out.println("=".repeat(40));
            System.out.println("Select field to update");
            System.out.println("[1] Cineplex: " + cinema.getCineplex());
            System.out.println("[2] Cinema Code: " + cinema.getCinemaCode());
            System.out.println("[3] Premium status: " + cinema.isPlatinum());
            System.out.println("[0] Exit");
            System.out.print("Select option: ");
            switch (sc.nextInt()) {
                case 1:
                    System.out.println("=".repeat(40));
                    System.out.println("Cineplex list");
                    int cineplex_count = 0;
                    for (Cineplex cineplex : Cineplex.values()) {
                        System.out.println("[" + (cineplex_count + 1) + "] " + cineplex);
                        cineplex_count++;
                    }
                    if(cineplex_count == 0){
                        System.out.println("No cineplex found");
                        break;
                    }
                    System.out.print("Select cineplex: ");
                    int add_choice;
                    while (true) {
                        add_choice = sc.nextInt();
                        if (add_choice < 0 || add_choice > cineplex_count+1) {
                            System.out.println("Invalid option. Please try again.");
                        } else {
                            cinema.setCineplex(Cineplex.values()[add_choice - 1]);
                            break;
                        }
                    }
                    break;
                case 2:
                    System.out.println("Enter new cinema code: ");
                    sc.skip("\\R?");
                    String cinemaCode = sc.nextLine();
                    cinema.setCinemaCode(cinemaCode);
                    break;
                case 3:
                    System.out.println("Set platinum status? (y/N)");
                    sc.skip("\\R?");
                    String type = sc.nextLine();
                    boolean isPremium = type.equalsIgnoreCase("y");
                    cinema.setPlatinum(isPremium);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    continue;
            }
            FileHelper.write(cinema_list, "data/cinemas.dat");
        }
    }

    /**
     * Allows the removal of Cinema
     * writes back updated cinema list without removed cinema into cinemas.dat
     * @param cinema - cinema object to be removed
     */

    public void removeCinema(Cinema cinema){
        cinema_list.remove(cinema);
        FileHelper.write(cinema_list, "data/cinemas.dat");
    }

    /**
     * Function that updates the seating arrangement of Cinemas
     * @param cinema - cinema object to have its seating arrangement to be updated
     */

    public void updateCinemaSeating(Cinema cinema) {
        boolean edit = true;
        while (edit) {
            printSeatingLayout(cinema.getSeatLayout(), false, cinema);
            System.out.println("[1] Update cinema size: " + cinema.getSeatLayout().length + "rows, " + cinema.getSeatLayout()[0].length + "columns");
            System.out.println("[2] Update seat type");
            switch (sc.nextInt()) {
                case 1 -> {
                    System.out.println("=".repeat(40));
                    System.out.println("Update seat layout");
                    System.out.println("Warning: This will reset all seats to normal");
                    System.out.println("Enter number of rows: ");
                    int rows = sc.nextInt();
                    System.out.println("Enter number of columns: ");
                    int cols = sc.nextInt();
                    cinema.setSeatingLayout(rows, cols);
                    updateCinema(cinema);
                    System.out.println("Seat layout updated");
                    printSeatingLayout(cinema.getSeatLayout(), false, cinema);
                    edit = false;
                }
                case 2 -> {
                    System.out.println("Select seat number (e.g. E1): ");
                    sc.skip("\\R?");
                    String seat = sc.nextLine();
                    System.out.println("Select seat type:");
                    System.out.println("[1] Normal");
                    System.out.println("[2] Wheelchair");
                    System.out.println("[3] Premium");
                    System.out.print("Select option: ");
                    sc.skip("\\R?");
                    int seatType = sc.nextInt();
                    SeatType seatType1 = SeatType.STANDARD;
                    switch (seatType){
                        case 2 -> seatType1 = SeatType.HANDICAP;
                        case 3 -> seatType1 = SeatType.PREMIUM;
                    }
                    cinema.getSeatLayout()[seat.charAt(0) - 65][Integer.parseInt(seat.substring(1)) - 1].setSeatType(seatType1);

                    FileHelper.write(cinema_list, "data/cinemas.dat");
                    printSeatingLayout(cinema.getSeatLayout(), false, cinema);
                    System.out.println("Update another seat? (y/N): ");
                    sc.skip("\\R?");
                    if (!sc.nextLine().equalsIgnoreCase("y")) {
                        edit = false;
                    }
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Allows the Printing of Seating Layout for users to see
     * @param seatLayout - Array of Seat type to be used to print seating layout
     * @param cinema - Cinema which the seating layout belongs to
     * @param showSelection - Parameter that shows the selected seats by the user
     */
    public void printSeatingLayout(Seat[][] seatLayout, boolean showSelection, Cinema cinema){
        System.out.println("=".repeat(40));
        System.out.println("Seats at " + cinema.getCineplex() + " - " + cinema.getCinemaCode() + (cinema.isPlatinum() ? " (Platinum Cinema)" : ""));
        System.out.println();
        System.out.println(" ".repeat((seatLayout.length / 2) + 5) + "SCREEN");
        for (int i = 0; i < seatLayout.length; i++) {
            System.out.print(" " + ((char)(i + 65)) + "  ");
            for (int j = 0; j < seatLayout[i].length; j++) {
                    String seatIcon;
                    seatIcon = seatLayout[i][j].getSeatType().getIcon();
                if(seatLayout[i][j].isOccupied()){
                    seatIcon = "x";
                }
                else if(seatLayout[i][j].isSelected()) {
                    seatIcon = "S";
                }
                    System.out.print(seatIcon + " ");
            }
            if(i == 0){
                System.out.print("   O - Normal Seat");
            }
            else if(i == 1){
                System.out.print("   P - Premium Seat");
            }
            else if(i == 2){
                System.out.print("   H - Handicap Seat");
            }
            else if(i == 3 && showSelection){
                System.out.print("   S - Selected Seat");
            }
            System.out.println();
        }
        System.out.print("    ");
        for (int i = 0; i < seatLayout[0].length;) {
            System.out.print(++i + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("=".repeat(40));
    }

}
