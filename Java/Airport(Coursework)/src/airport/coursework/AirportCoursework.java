/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airport.coursework;

import java.util.*;

/**
 *
 * @author w1712616
 */
public class AirportCoursework {
    static Scanner input = new Scanner(System.in);
    static final int capacity = 30;
    static final int capacity2 = 26;
    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        Passenger[] PassengerArray = new Passenger[capacity];
        Passenger[] PassengerQueueArray = new Passenger[capacity];
        Passenger[] PassengerBoarding = new Passenger[capacity];
        // create passenger objects here
        for (int i = 0; i < capacity; i++) {
            PassengerArray[i] = new Passenger();
            PassengerQueueArray[i] = new Passenger();
            PassengerBoarding[i] = new Passenger();
        }
        
        PassengerQueue queue = new PassengerQueue();
        
        System.out.println(" Press 'A' to add a new passenger\n"
                + " Press 'D' to delete passenger from seat\n"
                + " Press 'S' to store PassengerQueue data into a plain text file\n"
                + " Press 'L' to load program data back from file into the PassengerQueue.\n"
                + " Press 'R' to run the simulation and produce a report\n"
                + " Press 'V' to view the current Array\n"
                + " Press 'C' to clear the current Array\n"
                + " Press 'Q' to quit");
        
        String userChoice;
         
        do {
            System.out.println("");
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println("Note: You must load the data before you can add anyone to the queue.");
            System.out.print("Please enter a following command to carry out an instruction: ");

            userChoice = input.next();   // Finds out what command the User wants to execute.
            userChoice = userChoice.toUpperCase();
            
            switch (userChoice) { // depending on the character, it will execute one of teh command prompts
                case ("A"):
                    queue.add(PassengerQueueArray,PassengerArray,PassengerBoarding);
                    break;
                case ("D"):
                    queue.remove(PassengerQueueArray,PassengerBoarding);
                    break;
                case ("S"):
                    queue.save(PassengerQueueArray,PassengerArray);
                    break;
                case ("L"):
                    queue.load(PassengerQueueArray,PassengerArray);
                    break;
                case ("V"):
                    queue.display(PassengerArray,PassengerBoarding);
                    break;
                case ("R"):
                    PassengerQueueArray = queue.simulation(PassengerArray);
                    break;
                case ("Q"):
                    break;
                case ("C"):
                    queue.clear(PassengerArray);
                    break;
                default: // if none of the commands define above a called, this is the default response.
                    System.out.println("-------------------------------------------------------------------------------");
                    System.out.println("This is not a valid function. Please enter a valid function: ");
                    break;
            }   
        } while (!userChoice.equals("Q")); 
    }
}
