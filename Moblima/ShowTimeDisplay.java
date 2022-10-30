package MOBLIMA;

import java.util.ArrayList;
import java.util.Scanner;

public class ShowTimeDisplay {
    private ArrayList<ShowTime> showtime_list;

    public ShowTimeDisplay(ArrayList<ShowTime> showtime_list){
        this.showtime_list=showtime_list;
    }

    public void DisplayMenu(){
        int choice = 0;
        while (choice != 3) {
            System.out.println("1.Get All timings for movie");
            System.out.println("2.Get Seat availability for each timing");
            System.out.println("3.Exit");

            Scanner sc = new Scanner(System.in);

            choice = sc.nextInt();
            switch (choice) {

                case 1:
                    for (int i=0;i < showtime_list.size();i++){
                        //create new SHOWTIME FILE TMR.

                    }


            }
        }
    }


}
