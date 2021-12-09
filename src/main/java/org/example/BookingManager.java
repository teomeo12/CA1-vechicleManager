package org.example;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class BookingManager {
    private final ArrayList<Booking> bookingList;
    private PassengerStore passengerStore;
    private VehicleManager vehicleManager;

    // Constructor
    public BookingManager(String fileName, VehicleManager vehicleManager,
                          PassengerStore passengerStore) {
        this.bookingList = new ArrayList<>();
        this.vehicleManager = vehicleManager;
        this.passengerStore = passengerStore;
        loadBookingFromFile(fileName);
    }


    //TODO implement functionality as per specification
    //load booking from file bookings.txt
    public void loadBookingFromFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
            sc.useDelimiter("[,\r\n]+");
            while (sc.hasNext()) {
                int bookID = sc.nextInt();
                int passengerID = sc.nextInt();
                int vehicleID = sc.nextInt();
                int year = sc.nextInt();   // last service date
                int month = sc.nextInt();
                int day = sc.nextInt();
                int hours = sc.nextInt();
                int min = sc.nextInt();
                double startLatitude = sc.nextDouble();
                double startLongtitude = sc.nextDouble();
                double endLatitude = sc.nextDouble();
                double endLongtitude = sc.nextDouble();
            }
            sc.close();
        } catch (IOException e) {
            System.out.println("Exception thrown. " + e);
        }
    }

    //public BookingManager()

    //display All bookings
    public void displayAllBookings() {
        for (Booking b : bookingList)
            System.out.println(b.toString());
    }

    public Booking createBooking(int passengerId, int vehicleId, LocalDateTime bookingDateTime,
                           LocationGPS startLocation, LocationGPS endLocation, double cost) {
        if (passengerStore.findPassengerByID(passengerId) != null) {
            if (vehicleManager.findVehicleByID(vehicleId) != null) {


                Booking booking = new Booking(passengerId, vehicleId, bookingDateTime, startLocation, endLocation, cost);
                bookingList.add(booking);

                System.out.println("The booking is added!");
            }

        } else {
            System.out.println("There is no such passenger in the file");
        }
          return null;
    }
    public double costBooking(double startLatitude,double startLongtitude,
                              double endLatitude, double endLongtitude){
       double cost=0;
        double latitude =(endLatitude- startLatitude) ;
        double longtitude =(endLongtitude- startLongtitude) ;
        latitude = latitude;

        return cost;

    }

    //find booking
    public Booking findBooking(int id) {
        for (Booking b : bookingList) {
            if (b.getBookingId() == id) {
                return b;
            }
        }
        return null;

    }

    public void editBooking(int id) {


    }

    public void deleteBooking(int id) {
        for (Booking b : bookingList) {
            if (b.getBookingId() == id) {

                bookingList.remove(b);
                System.out.println("The booking with id " + id + " is deleted.");
                break;
            } else {
                System.out.println("There is no booking with id " + id + " in the list!");
            }

        }
    }

}



