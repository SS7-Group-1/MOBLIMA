package MOBLIMA.App;

import MOBLIMA.MovieType;
import MOBLIMA.SeatType;
import MOBLIMA.TicketType;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * CLass that calculates ticket pricing
 * @since 2022-11-07
 * @author Nigel Chok
 * @version 1.4
 */
public class Pricing {
    /**
     * Function that tabulates the ticket pricing
     * @param ticketType - type of ticket
     * @param isPlatinum - Boolean that determines whether a cinema is Platinum or not
     * @param date -  date of watching movie
     * @param movieType - type of movie
     * @param seatType - type of seat
     * @return ticket pricing
     */
    public float computePricing(TicketType ticketType, boolean isPlatinum, LocalDate date, MovieType movieType, SeatType seatType) {

        // Read base price
        int price = 0; //Total Price
        int base = 0;
        try { //Reading CSV file
            File file = new File("data/Modifier.txt");
            Scanner x = new Scanner(file);
            x.useDelimiter("[,\n]");

            //System.out.println(ticketType.toString());
            while (x.hasNextLine()) {
                String data = x.nextLine();
                String[] res = data.split(",");

                String modifier = res[0];
                String symbol = res[1];
                String INT = res[2];

                int pricing = Integer.parseInt(INT);

                if (modifier.equals("Base")) {
                    base = pricing; //Getting the base Price of object
                } else if (modifier.equals(movieType.toString()) || modifier.equals(seatType.getType()) || modifier.equals(ticketType.toString()) || modifier.equals(Day(date)))  //Calculating Price from Movietype, Seat type, Ticket type and Day of the week
                {
                    if (symbol.equals("+")) {
                        //System.out.println(modifier);
                        //System.out.println(symbol);
                        //System.out.println("Hit here1");
                        base += pricing;
                    } else {
                        base -= pricing;
                        //System.out.println("Hit here2");
                    }
                } else if ((isPlatinum && modifier.equals("PlatinumCinema")) || (!isPlatinum && modifier.equals("StandardCinema"))) //Calculating Price from type of cinema
                {
                    if (symbol.equals("+")) {
                        //System.out.println("Hit Platinum1");
                        base += pricing;
                    } else {
                        //System.out.println("Hit platinum2");
                        base -= pricing;
                    }
                } else if (isPublicHoliday(date) && modifier.equals("PublicHoliday")) {

                    if (symbol.equals("+")) {
                        //System.out.println("Hit Public Holiday1");
                        base += pricing;
                    } else {
                        //System.out.println("Hit publicholiday2");
                        base -= pricing;
                    }
                }

            }
            x.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


        return base;
    }


    /**
     * Function that returns the Day of the week (Weekday , Weekend) from date object
      * @param date - Date of the week
     * @return (Weekday / Weekends) based on the date of the week
     */


    public String Day(LocalDate date)
    {
        int Day = date.getDayOfWeek().getValue(); //check value of day
        if (Day <6)
        {
            return "Weekday";
        }
        else
        {
            return "Weekend";
        }
    }

    /**
     * Function that check whether a date is a public holiday
     * @param date - date to bechecked whether is a public holiday
     * @return True if its public holiday , False if its not
     */

    public Boolean isPublicHoliday(LocalDate date)
    {
        try {



            File file = new File("data/date.txt");
            Scanner x = new Scanner(file);
            x.useDelimiter("[,\n]");


            while (x.hasNextLine()) {
                String data = x.nextLine();
                String[] res = data.split(",");

                String Holiday =res[0];
                String check = res[1];

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                LocalDate localDate = LocalDate.parse(check, formatter); //converting Edate to local date format

                if(localDate.isEqual(date))
                {
                    return Boolean.TRUE;
                }

            }
            x.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }
}
