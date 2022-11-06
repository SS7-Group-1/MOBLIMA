package MOBLIMA.App;

import MOBLIMA.MovieType;
import MOBLIMA.SeatType;
import MOBLIMA.TicketType;

import java.io.File;
import java.time.LocalDate;
import java.util.Scanner;

public class Pricing {
    public float computePricing(TicketType ticketType, boolean isPlatinum, LocalDate date, MovieType movieType, SeatType seatType){

        // Read base price
        int price =0; //Total Price
        int base =0;
        try { //Reading CSV file
            File file = new File("data/Modifier.txt");
            Scanner x = new Scanner(file);
            x.useDelimiter("[\s\n]");

            while (x.hasNextLine()) {
                String data = x.nextLine();
                String[] res = data.split(" ");

                String modifier =res[0];
                String symbol = res[1];
                String INT = res[2];

                int pricing = Integer.parseInt(INT);

                if(modifier.equals("Base"))
                {
                    base=pricing; //Base Price of object
                }

                else if(modifier.equals(movieType.toString())||modifier.equals(seatType.getType())||modifier.equals(ticketType.toString())||modifier.equals(Day(date))) //calculating 2D or 3D movie
                {
                    if(symbol == "+")
                    {
                        base += pricing;
                    }
                    else
                    {
                        base -= pricing;
                    }
                }

                    else if ((isPlatinum&&modifier.equals("PlatinumCinema"))||(!isPlatinum&&modifier.equals("StandardCinema")))
                    {
                        if(symbol == "+")
                        {
                            base += pricing;
                        }
                        else
                        {
                            base -= pricing;
                        }
                    }

                }

            x.close();
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }


        return 0f;
    }

    public String Day(LocalDate date)
    {
        int Day = date.getDayOfWeek().getValue(); //check value of day
        if (Day <5)
        {
            return "Weekday";
        }
        else
        {
            return "Weekend";
        }
    }
}
