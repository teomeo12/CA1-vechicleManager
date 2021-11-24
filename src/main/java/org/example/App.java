package org.example;

/** Teodor Donchev SD2a
 * This Vehicle Bookings Management Systems manages the booking of Vehicles
 * by Passengers.
 *
 * This program reads from 3 text files:
 * "vehicles.txt", "passengers.txt", and "next-id-store.txt"
 * You should be able to see them in the project pane.
 * You will create "bookings.txt" at a later stage, to store booking records.
 *
 * "next-id-store.txt" contains one number ("201"), which will be the
 * next auto-generated id to be used to when new vehicles, passengers, or
 * bookings are created.  The value in the file will be updated when new objects
 * are created - but not when objects are recreated from records in
 * the files - as they already have IDs.  Dont change it - it will be updated by
 * the IdGenerator class.
 */

public class App
{
    public static void main(String[] args)
    {
        System.out.println("\nWelcome to the VEHICLE BOOKINGS MANAGEMENT SYSTEM - CA1 for OOP\n");

        // create PassengerStore and load it with passenger records from text file
        PassengerStore passengerStore = new PassengerStore("passengers.txt");
        System.out.println("List of all passengers:");

        //creating new passangers
        passengerStore.addPassenger("Iggy Pop","iggy@gmail.com","0838691644",34.3623,-23.2345);
        passengerStore.displayAllPassengers();

        //vehicle manager file reading
        VehicleManager vehicleManager = new VehicleManager("vehicles.txt");
        System.out.println("List of all Vehicles:");
        vehicleManager.displayAllVehicles();

        //find vehicle by registration number
       System.out.println("\n****************Vechicle with regNumber 151D987105************ ");
       String reg="151D987105";
       Vehicle v = vehicleManager.findvehicleByRegNumber(reg);
        //vehicleManager.findvehicleByRegNumber(reg);

        if(v != null){
            System.out.println(v);
        }else{
            System.out.println("Vehicle not found");
        }

        //creating new booking
       BookingManager bookingManager = new BookingManager("bookings.txt");

        //create new cars


        Vehicle car = new Car("Car","Hyundai","I40",4,"161MN3238107",6.00,2016,05,24,156000,54.2543,-16.4444,5);


        System.out.println("Program exiting... Goodbye");
    }
}
