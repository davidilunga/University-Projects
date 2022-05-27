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
public class Passenger {
    private String FirstName,Surname;
    private int SecondsinQueue;
    private int totalSeconds;
    
    private String regex = "^[a-zA-Z\\s]+$";
    
    public Passenger(){
        FirstName = "";
        Surname = "";
        SecondsinQueue = 0;
    }
    
    public String getName(){
        return FirstName +" "+ Surname;
    }
    
    public void setName(String fName,String sName){
        FirstName = fName;
        Surname = sName;
    }
    
    public int getSecondsInQueue(){
        return SecondsinQueue;
    }
    
    public void setSecondsInQueue(int seconds){
        Random rand = new Random();
        seconds = rand.nextInt(15) + 3;
        totalSeconds = totalSeconds + seconds;
        SecondsinQueue = SecondsinQueue + seconds;
    }
    
    private void display(){
    
    }
}