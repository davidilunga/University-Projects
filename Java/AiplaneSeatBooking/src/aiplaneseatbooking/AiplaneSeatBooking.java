/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiplaneseatbooking;

import java.io.*;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.InputMismatchException;

/**
 *
 * @author w1712616
 */
public class AiplaneSeatBooking {

    static final int SEATING_CAPACITY = 12;
    static String[] airplane = new String[SEATING_CAPACITY];
    static int flag = 0;
    static String regex = "^[a-zA-Z\\s]+$";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        String userChoice = "C";
        airplane[0] = "Pilot";
        airplane[1] = "MARY DREW";
        for (int i = 2; i < airplane.length; i++) {
            airplane[i] = "EMPTY";
        }

        System.out.println(" A: Add a new passenger\n"
                + " E: Display Empty seats\n"
                + " D: Delete passenger from seat\n"
                + " F: Find the seat for a given passengers name\n"
                + " S: Store program data in to file\n"
                + " L: Load program data from file\n"
                + " O: View seats Ordered alphabetically by name\n"
                + " R: Reset all the seats to empty\n"
                + " V: to view all seats \n"
                + " Q: to quit");

        while (!userChoice.equals("Q")) {
            System.out.println("");
            System.out.println("Press C to view all commands");
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.print("Please enter a following letter to carry out an instruction: ");

            userChoice = input.next();
            userChoice = userChoice.toUpperCase();
            switch (userChoice) {
                case ("A"):
                    append(airplane, SEATING_CAPACITY);
                    break;
                case ("C"):
                    command();
                    break;
                case ("D"):
                    delete(airplane);
                    break;
                case ("E"):
                    empty(airplane);
                    break;
                case ("F"):
                    find(airplane, SEATING_CAPACITY);
                    break;
                case ("L"):
                    load(airplane, SEATING_CAPACITY);
                    break;
                case ("O"):
                    String[] Airplane_BackupSeats = Arrays.copyOf(airplane, SEATING_CAPACITY);
                    BubbleSort(Airplane_BackupSeats);
                    break;
                case ("Q"):
                    break;
                case ("S"):
                    save(airplane, SEATING_CAPACITY);
                    break;
                case ("R"):
                    reset(airplane, SEATING_CAPACITY);
                    break;
                case ("V"):
                    view(airplane);
                    break;
                default:
                    System.out.println("-------------------------------------------------------------------------------");
                    System.out.println("This is not a valid function. Please enter a valid function: ");
                    break;
            }
        }
    }

    private static void append(String airplane[], int SEATING_CAPACITY) {

        Scanner input = new Scanner(System.in);
        String name;
        int SeatPos;

        System.out.println("");
        System.out.println("You have chosen to add someone to the plane.");
        System.out.println("--------------------------------------------------------------");

        for (int i = 0; i < SEATING_CAPACITY; i++) {
            System.out.print((i + 1) + ":" + airplane[i] + " | ");
        }

        if (flag == SEATING_CAPACITY) {
            System.err.println("All of the seats are already booked.");
        } else {
            System.out.println("");
            System.out.println("EMPTY means an empty seat.");
            System.out.println("Please enter your name: ");
            name = CheckName();
            name = name.toUpperCase();

            for (String airplane1 : airplane) {
                while (name.equals(airplane1)) {
                    System.out.println("This person is already on the plane. Please enter another name: ");
                    name = input.nextLine();
                    name = name.toUpperCase();
                }
            }

            SeatPos = 1;

            while (true) {
                try {
                    System.out.print("What seat would you like to have on the plane? ");
                    SeatPos = input.nextInt() - 1;
                    flag++;
                    break;
                } catch (InputMismatchException ex) {
                    System.err.println("This is an invalid input. Please enter a valid seat number: ");
                    SeatPos = input.nextInt() - 1;
                }
            }

            while (SeatPos < 2 || SeatPos >= SEATING_CAPACITY) {
                System.err.println("That is an invalid position. Please enter another position: ");
                SeatPos = input.nextInt() - 1;
            }

            while (!"EMPTY".equals(airplane[SeatPos])) {
                System.err.println("This seat is already booked. Please select another seat: ");
                SeatPos = input.nextInt() - 1;
            }

            if (SeatPos >= 2 && SeatPos < SEATING_CAPACITY) {
                airplane[SeatPos] = (name); // seat number excluding the pilot and Mary Drew
            }

            for (int i = 0; i < SEATING_CAPACITY; i++) {
                System.out.print((i + 1) + ":" + airplane[i] + " | ");
            }

        }
    }

    private static void delete(String airplane[]) {

        Scanner input = new Scanner(System.in);
        int SeatPos;

        System.out.println("");
        System.out.println("You have chosen to remove someone to the plane.");
        System.out.println("--------------------------------------------------------------");

        SeatPos = 6;

        while (true) {
            try {
                System.out.println("What seat number would you like to remove?");
                SeatPos = input.nextInt() - 1;
                break;
            } catch (InputMismatchException ex) {
                System.err.println("This is an invalid input");
                input.next();
            }
        }

        while ("EMPTY".equals(airplane[SeatPos])) {
            System.out.println("This seat is already empty. Please select another seat: ");
            SeatPos = input.nextInt() - 1;
        }

        for (int i = 3; i <= SEATING_CAPACITY; i++) {
            // i starts from 0 to te length of the array.
            if (!"EMPTY".equals(airplane[SeatPos])) {
                airplane[SeatPos] = "EMPTY";
                flag--;
            }
        }

        for (int i = 0; i < SEATING_CAPACITY; i++) {
            System.out.print((i + 1) + ":" + airplane[i] + " | ");
        }
    }

    private static void save(String airplane[], int SEATING_CAPACITY) {
        System.out.println("");
        System.out.println("You have chosen to save the plane data.");
        System.out.println("--------------------------------------------------------------");
        try {
            FileWriter AirplaneSave = new FileWriter("CourseworkLoadAirplane");
            try (PrintWriter CourseworkLoadAirplane = new PrintWriter(AirplaneSave)) {
                for (int i = 0; i < SEATING_CAPACITY; i++) {
                    CourseworkLoadAirplane.write(airplane[i] + "\n");
                }
            }
            System.out.println("");
            System.out.println("You have successfully saved the plane data.");
        } catch (IOException e) {
            System.out.println("Invalid Entry");
        }
    }

    private static void load(String airplane[], int SEATING_CAPACITY) {
        flag = 0;

        System.out.println("");
        System.out.println("You have chosen to save the plane data.");
        System.out.println("--------------------------------------------------------------");
        try {
            Scanner AirplaneLoad = new Scanner(new File("CourseworkLoadAirplane"));
            while (AirplaneLoad.hasNextLine()) {
                for (int i = 0; i < SEATING_CAPACITY; i++) {
                    airplane[i] = (AirplaneLoad.nextLine());
                    System.out.print((i + 1) + ":" + airplane[i] + " | ");
                }
            }
            System.out.println("");
            System.out.println("You have successfully saved the plane data.");

            for (int a = 0; a < SEATING_CAPACITY; a++) {
                if (!"EMPTY".equals(airplane[a])) {
                    flag = flag + 1;
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(AiplaneSeatBooking.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void empty(String airplane[]) {

        System.out.println("");
        System.out.println("You have chosen to show all the empty seats on the plane.");
        System.out.println("--------------------------------------------------------------");

        for (int i = 0; i < SEATING_CAPACITY; i++) { // i starts from 0 to te length of the array.
            if ("EMPTY".equals(airplane[i])) {
                System.out.println("Empty Seat number: " + (i + 1));
            }//display all the empty seats.
        }
        if (flag == SEATING_CAPACITY) {
            System.err.println("There are no empty seats on this flight.");
        }
    }

    private static void find(String airplane[], int SEATING_CAPACITY) {
        String name;
        int count = 0;

        System.out.println("");
        System.out.println("You have chosen to find someone on this plane.");
        System.out.println("--------------------------------------------------------------");

        System.out.println("Please enter the name of the person you are trying to find: ");
        name = CheckName();
        name = name.toUpperCase();

        for (int i = 0; i < airplane.length; i++) {
            if (name.equals(airplane[i])) {
                System.out.println("");
                System.out.println(airplane[i] + " is sitting in seat number " + (i + 1));
            } else {
                count++;
            }
        }

        if (count == SEATING_CAPACITY) {
            System.out.println("");
            System.out.println("Sorry, we couldn't find anyone by the name of " + name + " on this plane.");
        }

    }

    private static void view(String airplane[]) {
        for (int i = 0; i < airplane.length; i++) {
            // i starts from 0 to the length of the array.
            System.out.print((i + 1) + ":" + airplane[i] + " | "); //display what is in that position of the array.
            //display what is in that position of the array.
        }
    }

    private static void command() {
        System.out.println("");
        System.out.println(
                " A: Add a new passenger\n"
                + " E: Display Empty seats\n"
                + " D: Delete passenger from seat\n"
                + " F: Find the seat for a given passengers name\n"
                + " S: Store program data in to file\n"
                + " L: Load program data from file\n"
                + " O: View seats Ordered alphabetically by name \n"
                + " V: to view all seats \n"
                + " Q: to quit");
    }

    private static void reset(String airplane[], int SEATING_CAPACITY) {
        System.out.print("1 :" + airplane[0] + " | ");
        System.out.print("2 :" + airplane[1] + " | ");
        for (int i = 2; i < SEATING_CAPACITY; i++) {
            airplane[i] = "EMPTY";
            System.out.print((i + 1) + ":" + airplane[i] + " | ");
        }
    }

    private static void BubbleSort(String Airplane_BackupSeats[]) {
        for (int i = 1; i < SEATING_CAPACITY; i++) {
            for (int j = 1; j < (SEATING_CAPACITY - 1); j++) {
                if (Airplane_BackupSeats[j + 1].compareTo(Airplane_BackupSeats[j]) < 0) {
                    String temp;
                    temp = Airplane_BackupSeats[j];
                    Airplane_BackupSeats[j] = Airplane_BackupSeats[j + 1];
                    Airplane_BackupSeats[j + 1] = temp;
                }
            }
        }
        BubbleSortPrint(Airplane_BackupSeats);
    }

    private static void BubbleSortPrint(String Airplane_BackupSeats[]) {
        for (int t = 0; t < SEATING_CAPACITY; t++) {
            System.out.print((t + 1) + ":" + Airplane_BackupSeats[t] + " | ");
        }
    }

    private static String CheckName() {

        Scanner input = new Scanner(System.in);
        String name = " ";
        while (!"".equals(name)) {
            name = input.nextLine();
            name = name.toUpperCase();
            if (name.matches(regex)) {
                break;
            } else {
                System.err.println("That is an invalid name entry. The name should not include a number or special characters.");
                System.out.println("Please enter your name: ");
            }
        }
        return name;
    }

}
