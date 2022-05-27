/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airport.coursework;

import java.io.*;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author w1712616
 */
public class PassengerQueue {

    int first = -1;
    int BoardingFirst = 0;
    int last = -1;
    int BoardingLast = -1;
    int maxStayInQueue;
    final int capacity = 30;
    final int capacity2 = 20;
    final int extendedcapacity = 26;
    int currentsize = 0;
    int BoardingQueueSize = 0;
    int seconds = 0;

    /* Add does NOT WORK YET - try and fix. */
    public void add(Passenger passengerQueueArray[], Passenger passengerArray[], Passenger PassengerBoarding[]) {
        // Adds a ramdon person from the passenger manifest to the end of the queue with a maximum of 26 spaces in the queue.
        System.out.println("You have chosen to add someone to end of the simulation.");
        System.out.println("-----------------------------------------------------------------------------------");
        if (isFull()) {
            System.out.println("The queue is full. No passengers can be added.");
        } else {
            try {
                Random rand = new Random();
                String temp;
                last = (last + 1) % passengerQueueArray.length;
                int n = rand.nextInt(30);
                temp = passengerArray[n].getName();
                System.out.println("Passenger added : " + passengerArray[n].getName());
                String nameArray[] = temp.split(" ");
                passengerQueueArray[last].setName(nameArray[0], nameArray[1]);
                passengerQueueArray[last].setSecondsInQueue(seconds);
                int PassengerSeconds = passengerQueueArray[last].getSecondsInQueue();
                System.out.println(passengerQueueArray[last].getName() + "'s wait time is " + PassengerSeconds + " seconds.");
                setMaxStay(PassengerSeconds);
                currentsize++;
                System.out.println("");

                System.out.println("Passenger Array:");
                System.out.println("");

                if (last > first) {
                    for (int i = first; i <= last; i++) {
                        System.out.println(i + " | " + passengerArray[i].getName());
                        System.out.println("");
                    }
                } else {
                    for (int i = first; i < extendedcapacity; i++) {
                        System.out.println(i + " | " + passengerArray[i].getName());
                        System.out.println("");
                    }
                    for (int i = 0; i <= last; i++) {
                        System.out.println((extendedcapacity + i) + " | " + passengerArray[i].getName());
                        System.out.println("");
                    }
                }
                System.out.println("The maximum wait time now is " + getMaxStay() + " seconds.");

                if (isBoardingEmpty() == false) {

                    System.out.println("Boarding Array:");
                    System.out.println("");

                    if (BoardingLast >= BoardingFirst) {
                        for (int i = BoardingFirst; i <= BoardingLast; i++) {
                            System.out.println(i + " | " + PassengerBoarding[i].getName());
                            System.out.println("");
                        }
                    } else {
                        for (int i = BoardingFirst; i < extendedcapacity; i++) {
                            System.out.println(i + " | " + PassengerBoarding[i].getName());
                            System.out.println("");
                        }
                        for (int i = 0; i <= BoardingLast; i++) {
                            System.out.println((extendedcapacity + i) + " | " + PassengerBoarding[i].getName());
                            System.out.println("");
                        }
                    }
                    System.out.println("All of these passengers are in the boarding queue.");
                }
            } catch (java.lang.ArrayIndexOutOfBoundsException e){
                System.out.println("Please load some data and run the simulation before you add anyone to the queue. Thank you.");
            }
        }
    }

    public void remove(Passenger passengerArray[], Passenger[] PassengerBoarding) throws Exception { // The delete function
        System.out.println("You have chosen to remove someone from the beginning of the simulation.");
        System.out.println("-----------------------------------------------------------------------------------");
        if (isEmpty()) {
            System.out.println("The queue is empty. No passengers can be removed.");
        } else {
            if (BoardingFirst == -1) {
                BoardingFirst++;
            }
            
            
            if (isBoardingFull()){
                System.out.println("This boarding queue is full. No more passengers can be added to this flight. Thank You for using this aiplane queue simulator.");
            } else{
                BoardingLast = (BoardingLast + 1) % PassengerBoarding.length;
                BoardingQueueSize++;
            }

            String temp = passengerArray[first].getName();
            String nameArray[] = temp.split(" ");
            PassengerBoarding[BoardingLast].setName(nameArray[0], nameArray[1]);

            System.out.println("Passenger removed : " + passengerArray[first].getName() + ". They have been added to the Boarding Queue.");
            passengerArray[first].setName("", "");

            first = (first + 1) % passengerArray.length;
            currentsize--;

            int PassengerSeconds = passengerArray[first].getSecondsInQueue();
            setStay(PassengerSeconds);

            System.out.println("Passenger Array:");
            System.out.println("");

            if (last > first) {
                for (int i = first; i <= last; i++) {
                    System.out.println(i + " | " + passengerArray[i].getName());
                    System.out.println("");
                }
            } else {
                for (int i = first; i < extendedcapacity; i++) {
                    System.out.println(i + " | " + passengerArray[i].getName());
                    System.out.println("");
                }
                for (int i = 0; i <= last; i++) {
                    System.out.println((extendedcapacity + i) + " | " + passengerArray[i].getName());
                    System.out.println("");
                }
            }
            System.out.println("The maximum wait time now is " + getMaxStay() + " seconds.");

            System.out.println("Boarding Array:");
            System.out.println("");

            if (BoardingLast >= BoardingFirst) {
                for (int i = BoardingFirst; i <= BoardingLast; i++) {
                    System.out.println(i + " | " + PassengerBoarding[i].getName());
                    System.out.println("");
                }
            } else {
                for (int i = BoardingFirst; i < extendedcapacity; i++) {
                    System.out.println(i + " | " + PassengerBoarding[i].getName());
                    System.out.println("");
                }
                for (int i = 0; i <= BoardingLast; i++) {
                    System.out.println((extendedcapacity + i) + " | " + PassengerBoarding[i].getName());
                    System.out.println("");
                }
            }
        }

    }

    public void load(Passenger[] PassengerQueueArray, Passenger[] passenger) {
        // loads in all of the passenger names for the system o use in the simulation.
        System.out.println("");
        System.out.println("You have chosen to load the plane data.");
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("Note: You must load the full passenger list data before you can run the queue simulation; or you may load the most previously saved queue session.");
        
        Scanner input = new Scanner(System.in); 
        try {
            String temp;
            Scanner PassengerLoad = new Scanner(new File("PassengerList")); //load from the file that it was saved in
            if (first == -1) {
                first++;
            }
            while (PassengerLoad.hasNextLine()) {
                for (int i = 0; i < capacity; i++) {
                    temp = (PassengerLoad.nextLine());
                    String nameArray[] = temp.split(" ");
                    passenger[i].setName(nameArray[0], nameArray[1]);
                    System.out.println(i + " | " + passenger[i].getName());
                    last = last + 1;
                }
            }
            last = last - 1;
            System.out.println("");
            System.out.println("You have successfully loaded the file data.");

        } catch (FileNotFoundException ex) {
            System.out.println("Sorry, we were unable to find the file you want to use. Please create some data.");
        }        
    }

    public void save(Passenger[] PassengerQueueArray, Passenger[] passengerArray) { //The Save function
        //Saves the queue list with all of the passengers in the order of the queue array when saved.
        System.out.println("");
        System.out.println("You have chosen to save the plane data.");
        System.out.println("-----------------------------------------------------------------------------------");
        try {
            FileWriter PassengerQueueListSave = new FileWriter("PassengerQueueList");
            FileWriter PassengerListSave = new FileWriter("PassengerList(1)");
            try (PrintWriter PassengerQueuelist = new PrintWriter(PassengerQueueListSave)) { // saves in a new file
                for (int i = 0; i < extendedcapacity; i++) {
                    PassengerQueuelist.write(passengerArray[i].getName() + "\n");
                }
            }
            try (PrintWriter Passengerlist = new PrintWriter(PassengerListSave)) { // saves in a new file
                for (int i = 0; i < capacity; i++) {
                    Passengerlist.write(passengerArray[i].getName() + "\n");
                }
            }
            System.out.println("");
            System.out.println("You have successfully saved the plane data.");
        } catch (IOException e) {
            System.out.println("Invalid Entry");
        }

    }

    public void display(Passenger[] passengerArray, Passenger[] PassengerBoarding) {
        System.out.println("Queue display: ");
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("");

        Scanner input = new Scanner(System.in);

        String choice;
        do {
            System.out.println("Would you like to view the passenger queue or the bording queue? Please type either Passenger of Boarding.");
            choice = input.next();
            choice = choice.toUpperCase();
        } while ((!"PASSENGER".equals(choice)) || (!"BOARDING".equals(choice)));

        if ("PASSENGER".equals(choice)) {
            if (last > first) {
                for (int i = first; i <= last; i++) {
                    System.out.println(i + " | " + passengerArray[i].getName());
                    System.out.println("");
                }
            } else {
                for (int i = first; i < extendedcapacity; i++) {
                    System.out.println(i + " | " + passengerArray[i].getName());
                    System.out.println("");
                }
                for (int i = 0; i <= last; i++) {
                    System.out.println((extendedcapacity + i) + " | " + passengerArray[i].getName());
                    System.out.println("");
                }
            }
            System.out.println("");
        } else if ("BOARDING".equals(choice)) {
            System.out.println("Boarding Array:");
            System.out.println("");

            if (BoardingLast >= BoardingFirst) {
                for (int i = BoardingFirst; i <= BoardingLast; i++) {
                    System.out.println(i + " | " + PassengerBoarding[i].getName());
                    System.out.println("");
                }
            } else {
                for (int i = BoardingFirst; i < extendedcapacity; i++) {
                    System.out.println(i + " | " + PassengerBoarding[i].getName());
                    System.out.println("");
                }
                for (int i = 0; i <= BoardingLast; i++) {
                    System.out.println((extendedcapacity + i) + " | " + PassengerBoarding[i].getName());
                    System.out.println("");
                }
            }
        }
    }

    public Passenger[] simulation(Passenger[] passengerArray) {
        try {

            String temp;
            Random rand = new Random();
            int PassengerSeconds;
            Passenger[] PQueue = new Passenger[extendedcapacity];
            first = 0;
            last = -1;
            for (int i = 0; i < extendedcapacity; i++) {
                PQueue[i] = new Passenger();
            }
            for (int i = 0; i < capacity2; i++) {
                int n = rand.nextInt(30);
                for (int j = 1; j <= i; j++) {
                    if (passengerArray[n].getName().equals(passengerArray[j].getName())) {
                        n = rand.nextInt(30);
                    }
                }
                temp = passengerArray[n].getName();
                String nameArray[] = temp.split(" ");
                PQueue[i].setName(nameArray[0], nameArray[1]);
                System.out.println(i + " | " + PQueue[i].getName());
                PQueue[i].setSecondsInQueue(seconds);
                PassengerSeconds = PQueue[i].getSecondsInQueue();
                System.out.println(PQueue[i].getName() + "'s wait time is " + PassengerSeconds + " seconds.");
                setMaxStay(PassengerSeconds);
                System.out.println("This passenger's maximum waiting time is " + getMaxStay() + " seconds.");
                System.out.println("");
                last = last + 1;
                currentsize++;
            }
            System.out.println("");
            System.out.println("The maximum wait time is " + getMaxStay() + " seconds.");

            return PQueue;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("");
            System.out.println("There is no data for you to run the simulatiom Please load the full passenger list before running the simulation.");
        }
        return passengerArray;
    }

    public void clear(Passenger[] passengerArray) {
        last = 0;
        for (int i = 0; i < capacity; i++) {
            passengerArray[i].setName("", "");
            System.out.println((i + 1) + " | " + passengerArray[i].getName());
        }
    }

    public void setMaxStay(int seconds) {

        maxStayInQueue = maxStayInQueue + seconds;
    }

    public void setStay(int seconds) {

        maxStayInQueue = maxStayInQueue - seconds;
    }

    public int getMaxStay() {

        return maxStayInQueue;
    }

    private boolean isEmpty() {
        if (currentsize == 0) {
            return true;
        }
        return false;
    }

    private boolean isFull() {
        if (currentsize == extendedcapacity) {
            return true;
        }
        return false;
    }

    private boolean isBoardingFull() {
        if (BoardingQueueSize == capacity) {
            return true;
        }
        return false;
    }
    
    private boolean isBoardingEmpty() {
        if (BoardingQueueSize == 0) {
            return true;
        }
        return false;
    }

}
