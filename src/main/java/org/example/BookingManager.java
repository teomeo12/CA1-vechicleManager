package org.example;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class BookingManager
{
    private final ArrayList<Booking> bookingList;
    private PassengerStore passengerStore;
    private VehicleManager vehicleManager;

    // Constructor
    public BookingManager(String fileName) {
        this.bookingList = new ArrayList<>();
        loadBookingFromFile(fileName);
    }

    //TODO implement functionality as per specification
    //load booking from file bookings.txt
    public void loadBookingFromFile(String fileName){
        try{
            Scanner sc = new Scanner(new File(fileName));
            sc.useDelimiter("[,\r\n]+");
            while(sc.hasNext()){
                int bookID = sc.nextInt();
                int vehicleID = sc.nextInt();
                int passengerID = sc.nextInt();
               // LocalDateTime = sc.
                //how to do the time
                double startLoc = sc.nextDouble();
                double endLoc = sc.nextDouble();
            }
            sc.close();
        }catch(IOException e){
            System.out.println("Exception thrown. " + e);
        }
    }

    public BookingManager(ArrayList<Booking> bookingList, PassengerStore passengerStore, VehicleManager vehicleManager) {
        this.bookingList = bookingList;
        this.passengerStore = passengerStore;
        this.vehicleManager = vehicleManager;
    }

    //public BookingManager()

    //display All bookings
    public void displayAllBookings() {
        for (Booking b : bookingList)
            System.out.println(b.toString());
    }
    //add booking

    public static void addBooking(){


    }
    //find booking
    public static void findBooking(){


    }


}
