package org.example;

import java.io.File;
import java.io.FileWriter;
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

    //ADD Booking in the file -  booking.txt
    public void addBookingInFile() throws IOException {
        FileWriter writer = new FileWriter("bookings.txt");
        for (Booking b : bookingList) {
            String data = b.getBookingId()+","+ b.getPassengerId() +","+ b.getVehicleId() +","+ b.getBookingDateTime().getYear() +","+ b.getBookingDateTime().getDayOfMonth() +","
                    + b.getBookingDateTime().getDayOfWeek() +","+ b.getBookingDateTime().getHour() +","+ b.getBookingDateTime().getMinute() +","+ b.getStartLocation().getLatitude()+ ","
                    + b.getStartLocation().getLongitude() + "," + b.getEndLocation().getLatitude() + "," + b.getEndLocation().getLongitude() +","+ b.getCost();

            writer.append(data+"\n");

        }
        writer.close();
        System.out.println("The data in the booking file is updated and saved!!!");

    }

    //Edit Booking
    public void editBooking(int id) {

        Booking b = findBooking(id);

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter passenger ID: ");
        int passengerId = Integer.parseInt(keyboard.nextLine()) ;
        System.out.println("Please enter vehicle ID: ");
        int vehicleId = Integer.parseInt(keyboard.nextLine()) ;
        System.out.println("Please enter year of the booking: ");
        int yearOfbooking = Integer.parseInt(keyboard.nextLine()) ;
        System.out.println("Please enter month of the booking: ");
        int monthOfbooking = Integer.parseInt(keyboard.nextLine()) ;
        System.out.println("Please enter day of the booking: ");
        int dayOfbooking = Integer.parseInt(keyboard.nextLine()) ;
        System.out.println("Please enter hour of the booking: ");
        int hourOfbooking = Integer.parseInt(keyboard.nextLine()) ;
        System.out.println("Please enter minute of the booking: ");
        int minuteOfbooking = Integer.parseInt(keyboard.nextLine()) ;

        //2021,2,1
        LocalDateTime dateBooking = LocalDateTime.of(yearOfbooking, monthOfbooking, dayOfbooking,
                hourOfbooking, minuteOfbooking);
        System.out.println("Please enter start latitude of the booking: ");
        double startLatitude = Double.parseDouble(keyboard.nextLine());
        System.out.println("Please enter start longtitude of the booking: ");
        double startLongtitude = Double.parseDouble(keyboard.nextLine());

        LocationGPS startLocation = new LocationGPS(startLatitude,startLongtitude);
        System.out.println("Please enter end latitude of the booking: ");
        double endLatitude = Double.parseDouble(keyboard.nextLine());
        System.out.println("Please enter end longtitude of the booking: ");
        double endLongtitude = Double.parseDouble(keyboard.nextLine());

        LocationGPS endLocation = new LocationGPS(endLatitude,endLongtitude);
        System.out.println("Please enter end cost of the booking: ");
        double cost = Double.parseDouble(keyboard.nextLine());

        b.setPassengerId(passengerId);
        b.setVehicleId(vehicleId);
        b.setBookingDateTime(dateBooking);
        b.setStartLocation(startLocation);
        b.setEndLocation(endLocation);
        b.setCost(cost);

        System.out.println("*--------------------------------------------------*");
        System.out.println("*      The Booking with ID " + id + " is edited.   *");
        System.out.println("*--------------------------------------------------*");

    }
    //Calculate the cost of the booking
    public double costBooking(double startLatitude, double startLongtitude,
                              double endLatitude, double endLongtitude){
       double cost=10;
        double bookLatitude =(endLatitude- startLatitude) ;
        double bookLongtitude =(endLongtitude- startLongtitude) ;



        return cost;

    }
//    The cost is worked out as the distance from the vehicle depot to the booking start point
//    plus the distance from the booking start point to the booking end point plus
//    the distance from the booking end point back to the vehicle depot.

    //Find booking by ID
    public Booking findBooking(int id) {
        for (Booking b : bookingList) {
            if (b.getBookingId() == id) {
                return b;
            }else{
                System.out.println("\n~~##  There is no booking with id " + id + " in the list!   ##~~");
            }
        }
        return null;

    }
    //Delete booking
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
    @Override
    public String toString() {
        String output ="";
        for (Booking b : bookingList)

            output = b.getBookingId() + b.getPassengerId() + b.getVehicleId() + b.getBookingDateTime().getYear() + b.getBookingDateTime().getDayOfMonth() + String.valueOf(b.getBookingDateTime().getDayOfWeek())+
                    b.getStartLocation().getLatitude() + b.getStartLocation().getLongitude() + b.getEndLocation().getLatitude() + b.getEndLocation().getLongitude();

        // System.out.printf("%-5s %-15s %-25s %-15s %-15s %-15s\n","ID" ," Name"," Email"," Phone" ,"Latitude","Longtitude");

        return "BookingManager{" +
                "bookingList=" + bookingList +
                ", passengerStore=" + passengerStore +
                ", vehicleManager=" + vehicleManager +
                '}';
    }

}



