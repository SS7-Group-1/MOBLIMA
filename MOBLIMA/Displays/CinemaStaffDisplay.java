package MOBLIMA.Displays;

import MOBLIMA.*;

import java.util.ArrayList;
import java.util.Scanner;

public class CinemaStaffDisplay {

    ArrayList<Cinema> cinema_list = new ArrayList<>();
    public CinemaStaffDisplay(ArrayList<Cinema> cinema_list) {
        this.cinema_list = cinema_list;
    }
    Scanner sc = new Scanner(System.in);

    public void DisplayMenu(){
        System.out.println("*".repeat(40));
        System.out.println("Cinema Management Menu");

        int choice = 0;
        while (choice != 69) {
            System.out.println("*".repeat(40));
            System.out.println("[1] View all cinemas");
            System.out.println("[2] Add new cinema");
            System.out.println("[3] Update cinema");
            System.out.println("[4] Remove cinema");
            System.out.print("Enter option: ");

            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("*".repeat(40));
                    System.out.println("List of cinemas");
                    int cinema_count = 0;
                    for (Cinema cinema : cinema_list) {
                        System.out.println(cinema);
                        cinema_count++;
                    }
                    if (cinema_count == 0) {
                        System.out.println("No cinemas found");
                    }
                    break;
                case 2:
                    Cinema cinema = new Cinema();
                    // select cineplex
                    //loop cineplex
                    System.out.println("*".repeat(40));
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

                    // is platinum?
                    System.out.println("Is this a platinum cinema? (y/N)");
                    sc.skip("\\R?");
                    String type = sc.nextLine();
                    boolean isPremium = type.equalsIgnoreCase("y");
                    cinema.setPremium(isPremium);

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

                    addCinema(cinema);
                    System.out.println("Cinema added successfully");
                    break;
                case 3:
                case 4:
                    System.out.println("*".repeat(40));
                    System.out.println((choice == 3? "Update" : "Remove") + " cinema");

                    System.out.println("*".repeat(40));
                    System.out.println("Cinema List");
                    cinema_count = 0;
                    for (int i = 0; i < cinema_list.size(); i++) {
                            System.out.println(" [" + (i + 1) + "] " + cinema_list.get(i));
                        cinema_count++;
                    }
                    if(cinema_count == 0){
                        System.out.println("No cinemas found");
                        break;
                    }
                    System.out.print("Select cinema to " + (choice == 3 ? "update" : "remove") + ": ");

                    while (true) {
                        add_choice = sc.nextInt();
                        if (add_choice < 0 || add_choice > cinema_list.size()) {
                            System.out.println("Invalid option. Please try again.");
                        } else {
                            if(choice == 3){
                                System.out.println("*".repeat(40));
                                System.out.println("[1] Update cinema detail");
                                System.out.println("[2] Update seating layout");
                                switch (sc.nextInt()){
                                    case 1:
                                        updateCinemaDetail(cinema_list.get(add_choice-1));
                                        break;
                                    case 2:
                                        updateCinemaSeating(cinema_list.get(add_choice - 1));
                                        break;
                                    default:
                                        System.out.println("Invalid option. Please try again.");
                                        continue;
                                }
                            }
                            else{
                                removeCinema(cinema_list.get(add_choice - 1));
                                System.out.println("Cinema removed.");
                            }
                            break;
                        }
                    }
                    break;
            }
        }
    }
    public void updateCinemaDetail(Cinema cinema){
        boolean edit = true;
        System.out.println("*".repeat(40));
        System.out.println("Updating " + cinema);
        while (edit) {
            System.out.println("*".repeat(40));
            System.out.println("Select field to update");
            System.out.println("[1] Cineplex");
            System.out.println("[2] Cinema Code");
            System.out.println("[3] Premium status");
            System.out.print("Select option: ");
            switch (sc.nextInt()) {
                case 1:
                    System.out.println("*".repeat(40));
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
                    cinema.setPremium(isPremium);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    continue;
            }
            updateCinema(cinema);
        }
    }

    public void updateCinemaSeating(Cinema cinema) {
        boolean edit = true;
        while (edit) {
            printSeatingLayout(cinema);
            System.out.println("*".repeat(40));
            System.out.println("[1] Update cinema size");
            System.out.println("[2] Update seat type");
            switch (sc.nextInt()) {
                case 1:
                    System.out.println("*".repeat(40));
                    System.out.println("Update seat layout");
                    System.out.println("Warning: This will reset all seats to normal");

                    System.out.println("Enter number of rows: ");
                    int rows = sc.nextInt();
                    System.out.println("Enter number of columns: ");
                    int cols = sc.nextInt();
                    cinema.setSeatingLayout(rows, cols);
                    updateCinema(cinema);
                    System.out.println("Seat layout updated");
                    printSeatingLayout(cinema);
                    edit = false;
                    break;
                case 2:
                    System.out.println("Select seat number (e.g. E1): ");
                    sc.skip("\\R?");
                    String seat = sc.nextLine();

                    System.out.println("Select seat type: WORK IN PROGRESS");
                    sc.skip("\\R?");
                    String type = sc.nextLine();
                    boolean isPremium = type.equalsIgnoreCase("p");

                    //cinema.setSeatPremium(seat.charAt(0) - 65, Integer.parseInt(seat.substring(1)) - 1, isPremium);

                    updateCinema(cinema);
                    printSeatingLayout(cinema);
                    System.out.println("Update another seat? (y/N): ");
                    sc.skip("\\R?");
                    if (!sc.nextLine().equalsIgnoreCase("y")) {
                        edit = false;
                    }
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    continue;
            }
        }
    }
    public void printSeatingLayout(Cinema cinema){
        System.out.println("*".repeat(40));
        System.out.println("Seating layout for " + cinema);
        Seat[][] seatLayout = cinema.getSeatLayout();
        System.out.println(" ".repeat((seatLayout.length / 2) + 5) + "SCREEN");
        for (int i = 0; i < seatLayout.length; i++) {
            System.out.print(" " + ((char)(i + 65)) + "  ");
            for (int j = 0; j < seatLayout[i].length; j++) {
                String seatIcon;
                seatIcon = seatLayout[i][j].getSeatType().getIcon();
                System.out.print(seatIcon + " ");
            }
            if(i == 1){
                System.out.print("   ▢ Normal Seat");
            }
            else if(i == 2){
                System.out.print("   ▤ Premium Seat");
            }
            System.out.println();
        }
        System.out.print("    ");
        for (int i = 0; i < seatLayout[0].length;) {
            System.out.print(++i + " ");
        }
        System.out.println();
    }

    public void addCinema(Cinema cinema){
        cinema_list.add(cinema);
        FileHelper.write(cinema_list, "data/cinema.dat");
    }

    public void updateCinema(Cinema cinema){
        FileHelper.write(cinema_list, "data/cinema.dat");
    }
    public void removeCinema(Cinema cinema){
        cinema_list.remove(cinema);
        FileHelper.write(cinema_list, "data/cinema.dat");
    }
}
