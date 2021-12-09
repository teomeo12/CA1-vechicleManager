package org.example;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Teodor Donchev SD2a
 * This Vehicle Bookings Management Systems manages the booking of Vehicles
 * by Passengers.
 * <p>
 * This program reads from 3 text files:
 * "vehicles.txt", "passengers.txt", and "next-id-store.txt"
 * You should be able to see them in the project pane.
 * You will create "bookings.txt" at a later stage, to store booking records.
 * <p>
 * "next-id-store.txt" contains one number ("201"), which will be the
 * next auto-generated id to be used to when new vehicles, passengers, or
 * bookings are created.  The value in the file will be updated when new objects
 * are created - but not when objects are recreated from records in
 * the files - as they already have IDs.  Dont change it - it will be updated by
 * the IdGenerator class.
 */

public class App {
    // Components (objects) used in this App
    PassengerStore passengerStore;  // encapsulates access to list of Passengers
    VehicleManager vehicleManager;  // encapsulates access to list of Vehicles
    BookingManager bookingManager;  // deals with all bookings
    private static final String INPUT_MIS_MATCH = "Input is not a Number - Please enter number";

    public static void main(String[] args) throws IOException {
        App app = new App();
        app.start();
    }

    public void start() throws IOException {

        System.out.println("\nWelcome to the VEHICLE BOOKINGS MANAGEMENT SYSTEM - CA1 for OOP\n");



        //*******************************For the menu**********************************************
        // create PassengerStore and load all passenger records from text file
        passengerStore = new PassengerStore("passengers.txt");
       // passengerStore = new PassengerStore("passangerNew.txt");

        // create VehicleManager, and load all vehicles from text file
        vehicleManager = new VehicleManager("vehicles.txt");
        // create BookingManager, and load all bookings from text file
         bookingManager = new BookingManager("bookings.txt",vehicleManager,passengerStore);

       // passengerStore.addPassenger("Iggy Pop", "iggy@gmail.com", "0448691644", 34.3623, -23.2345);
         //passengerStore.addPassenger("Jony Rotten", "JohnRott@gmail.com", "04458691644", 54.3623, -13.2345);

        Vehicle car = new Car("Car", "Mercedes", "E220", 4,
                "171D23507", 7.00, 2017, 8, 2,
                186000, 54.2543, -16.4444, 6);

        Vehicle car1 = new Car("4x4", "Mercedes", "G550", 4,
                "101D23507", 7.00, 2010, 3, 23,
                245600, 54.2543, -16.4444, 7);

        Vehicle van1 = new Van("VAN", "Mercedes", "Sprinter", 4,
                "121D23507", 7.00, 2012, 3, 23,
                345600, 24.2543, -6.4444, 700);

        vehicleManager.addNewVehicle(car);
        vehicleManager.addNewVehicle(car1);
        vehicleManager.addNewVehicle(van1);

        //2021,2,1
        LocalDateTime dateBooking = LocalDateTime.of(2021, 2, 1, 12, 0);

        LocationGPS startLocation = new LocationGPS(23.23,56.34);
        LocationGPS endLocation = new LocationGPS(12.34,67.89);

        //Booking book1 = new Booking(101,115,dateBooking,startLocation,endLocation,314.34);

        bookingManager.createBooking(101,115,dateBooking,
                startLocation,endLocation,314.34);


        try {
            displayMainMenu();        // User Interface - Menu
        } catch (IOException e) {
            e.printStackTrace();
        }

        //*******************************For the menu**********************************************

    }

    private void displayMainMenu() throws IOException {

        final String MENU_ITEMS =
                "*----------------------------------*\n"
                        + "*   *** MAIN MENU OF OPTIONS ***   *\n"
                        + "*----------------------------------*\n"
                        + "*  1. Passengers                   *\n"
                        + "*  2. Vehicles                     *\n"
                        + "*  3. Bookings                     *\n"
                        + "*  4. Exit                         *\n"
                        + "*----------------------------------*\n"
                        + "*        Enter Option [1,4]        *\n"
                        + "*----------------------------------*";

        final int PASSENGERS = 1;
        final int VEHICLES = 2;
        final int BOOKINGS = 3;
        final int EXIT = 4;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case PASSENGERS:
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("~ ## Passengers option chosen  ##~");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                        displayPassengerMenu();
                        break;
                    case VEHICLES:
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("~ ## Vehicles option chosen  ##~");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        displayVehicleMenu();
                        break;
                    case BOOKINGS:
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("~ ##  Bookings option chosen   ##~");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        displayBookingMenu();
                        break;
                    case EXIT:
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("~ ##  Exit Menu option chosen  ##~");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);

        System.out.println("\nExiting Main Menu, goodbye.");

    }

    // Sub-Menu for Passenger operations
    //
    private void displayPassengerMenu() {
        final String MENU_ITEMS = "*-----------------------------*\n"
                + "*   *** PASSENGER MENU ***    *\n"
                + "*-----------------------------*\n"
                + "*  1. Show all Passengers     *\n"
                + "*  2. Find Passenger by Name  *\n"
                + "*  3. Add new Passenger       *\n"
                + "*  4. Add new PassengerFile   *\n"
                + "*  5. Edit Passenger          *\n"
                + "*  6. Delete Passenger        *\n"
                + "*  7. Exit                    *\n"
                + "*-----------------------------*\n"
                + "*     Enter Option [1,7]      *\n"
                + "*-----------------------------*";

        final int SHOW_ALL = 1;
        final int FIND_BY_NAME = 2;
        final int ADD_NEW_PASSENGER = 3;
        final int ADD_NEW_PASSENGERFILE = 4;
        final int EDIT_PASSENGER = 5;
        final int DELETE_PASSENGER = 6;
        final int EXIT = 7;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case SHOW_ALL:

                        System.out.println("*-----------------------------*");
                        System.out.println("*    Display ALL Passengers   *");
                        System.out.println("*-----------------------------*");

                        passengerStore.displayAllPassengers();
                        System.out.println("");
                        break;
                    case FIND_BY_NAME:
                        System.out.println("*-----------------------------*");
                        System.out.println("*    Find Passenger by Name   *");
                        System.out.println("*-----------------------------*");
                        System.out.println("\nEnter passenger name: ");
                        String name = keyboard.nextLine();
                        Passenger p = passengerStore.findPassengerByName(name);
                        if (p == null)
                            System.out.println("No passenger matching the name \"" + name + "\"");
                        else
                            System.out.println("Found passenger: \n" + p.toString());
                        break;
                    case ADD_NEW_PASSENGER:
                        System.out.println("*-----------------------------*");
                        System.out.println("*      Add new passenger      *");
                        System.out.println("*-----------------------------*");
                        System.out.println("\nEnter passenger name: ");

                        String pName = keyboard.nextLine();
                        System.out.println("\nEnter passenger Email: ");
                        String pEmail = keyboard.nextLine();
                        System.out.println("\nEnter passenger telephone number: ");
                        String pPhone = keyboard.nextLine();


                        try {
                            System.out.println("\nEnter passenger latitude coordinate: ");
                            double platitude = Double.parseDouble(keyboard.nextLine());
                            System.out.println("\nEnter passenger longitude coordinate: ");
                            double plongitude = Double.parseDouble(keyboard.nextLine());
                            passengerStore.addPassenger(pName, pEmail, pPhone, platitude, plongitude);

                        } catch (Exception e) {

                            System.out.println(INPUT_MIS_MATCH);

                        }
                        break;
                    case ADD_NEW_PASSENGERFILE:
                        System.out.println("*-----------------------------*");
                        System.out.println("*      Add new passenger      *");
                        System.out.println("*-----------------------------*");
                        System.out.println("\nEnter passenger name: ");

                        String pName1 = keyboard.nextLine().trim();
                        System.out.println("\nEnter passenger Email: ");
                        String pEmail1 = keyboard.nextLine().trim();
                        System.out.println("\nEnter passenger telephone number: ");
                        String pPhone1 = keyboard.nextLine().trim();


                        try {
                            System.out.println("\nEnter passenger latitude coordinate: ");
                            double platitudeFile = Double.parseDouble(keyboard.nextLine().trim());
                            System.out.println("\nEnter passenger longitude coordinate: ");
                            double plongitudeFile = Double.parseDouble(keyboard.nextLine().trim());

                            passengerStore.addPassengerInFile(pName1, pEmail1, pPhone1, platitudeFile, plongitudeFile);

//                            Passenger p1 = new Passenger(pName1, pEmail1, pPhone1, platitudeFile, plongitudeFile);
//                            if(passengerStore.chekForDuplicates(p1)){
//                                passengerStore.addPassengerInFile(pName1, pEmail1, pPhone1, platitudeFile, plongitudeFile);
//                            }


                        } catch (Exception e) {

                            System.out.println(INPUT_MIS_MATCH);

                        }
                        break;
                    case EDIT_PASSENGER:
                        System.out.println("*-----------------------------*");
                        System.out.println("*      Edit passenger      *");
                        System.out.println("*-----------------------------*");
                        //Display all passengers
                        passengerStore.displayAllPassengers();

                        System.out.println("\nEnter passenger ID to edit: ");
                        try{
                            int passID = keyboard.nextInt();
                            passengerStore.editPassenger(passID);
                            break;
                        }catch (InputMismatchException e){

                            System.out.println("Please enter a number for ID!!!");
                        }
                        break;

                    case DELETE_PASSENGER:
                        System.out.println("*-----------------------------*");
                        System.out.println("*    Delete Passenger by ID   *");
                        System.out.println("*-----------------------------*");

                        //Display all passengers
                        passengerStore.displayAllPassengers();
                        System.out.println("\nEnter passenger ID to delete: ");
                        int passIDdel = Integer.parseInt(keyboard.nextLine());
                        //Delete passenger by ID - passing ID
                       passengerStore.deletePassenger(passIDdel);
                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);
    }

    private void displayVehicleMenu() {
        final String MENU_ITEMS =
                          "*--------------------------------------------*\n"
                        + "*            *** VEHICLE MENU ***            *\n"
                        + "*--------------------------------------------*\n"
                        + "*   1. Show all Vehicles                     *\n"
                        + "*   2. Find Vehicles by Registration Number  *\n"
                        + "*   3. Find Vehicles by Type                 *\n"
                        + "*   4. Find Vehicles by Number of Seats      *\n"
                        + "*   5. Add new VehicleFile                   *\n"
                        + "*   6. Exit                                  *\n"
                        + "*--------------------------------------------*\n"
                        + "*             Enter Option [1,6]             *\n"
                        + "*--------------------------------------------*";

        final int SHOW_ALL = 1;
        final int FIND_BY_REG = 2;
        final int FIND_BY_TYPE = 3;
        final int FIND_BY_NUMSEATS = 4;
        final int ADD_NEW_VEHICLE = 5;
        final int EXIT = 6;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case SHOW_ALL:
                        System.out.println("*-----------------------------*");
                        System.out.println("*    Display ALL Vehicles     *");
                        System.out.println("*-----------------------------*");
                        vehicleManager.displayAllVehicles();

                        break;
                    case FIND_BY_REG:
                        System.out.println("*--------------------------------------------*");
                        System.out.println("*    Find Vehicle by Registration number     *");
                        System.out.println("*--------------------------------------------*");
                        System.out.println("Please enter Registration number of the vehicle: ");
                        String name = keyboard.nextLine();
                        Vehicle v = vehicleManager.findvehicleByRegNumber(name);
                        if (v == null)
                            System.out.println("No Vehicle matching the make \"" + name + "\"");
                        else
                            System.out.println("Found Vehicle: \n" + v.toString());
                        break;
                    case FIND_BY_TYPE:
                        System.out.println("*-----------------------------*");
                        System.out.println("*    Find Vehicle by Type     *");
                        System.out.println("*-----------------------------*");
                        System.out.println("Please enter type of the vehicle: ");
                        String type = keyboard.nextLine();

                        ArrayList<Vehicle> listType = vehicleManager.findvehicleByType(type);
                        if (listType.isEmpty())
                            System.out.println("No Vehicle matching the make \"" + type + "\"");
                        else
                            for (Vehicle vtype : listType) {
                                System.out.println(vtype);
                            }
                        break;
                    case FIND_BY_NUMSEATS:
                        System.out.println("*----------------------------------------*");
                        System.out.println("*    Find Vehicle by Number of Seats     *");
                        System.out.println("*----------------------------------------*");
                        System.out.println("Please enter type of the vehicle: ");
                        int numOfSeats = Integer.parseInt(keyboard.nextLine());

                        ArrayList<Vehicle> listNumSeat = vehicleManager.findvehicleByNumSeat(numOfSeats);
                        if (listNumSeat.isEmpty())
                            System.out.println("No Vehicle with \"" + numOfSeats + "\" number of seats!!");
                        else
                            for (Vehicle vNumSeat : listNumSeat) {
                                System.out.println(vNumSeat);
                            }
                        break;
                    case ADD_NEW_VEHICLE:
                        System.out.println("*-----------------------------*");
                        System.out.println("*      Add new Vehicle      *");
                        System.out.println("*-----------------------------*");
                        System.out.println("\nEnter vehicle type(Car, 4x4, Van or Truck): ");

                        String enterType = keyboard.nextLine();
                        System.out.println("\nEnter vehicle make: ");
                        String enterMake = keyboard.nextLine();
                        System.out.println("\nEnter vehicle model: ");
                        String enterModel = keyboard.nextLine();
                        try {
                            System.out.println("\nEnter vehicle milesPerKwH: ");
                            double milesPerKwH = Double.parseDouble(keyboard.nextLine());
                            System.out.println("\nEnter vehicle registration number: ");
                            String registration = keyboard.nextLine();

                            System.out.println("\nEnter vehicle cost per mile: ");
                            double costPerMile = Double.parseDouble(keyboard.nextLine());

                            System.out.println("\nEnter vehicle year of registration: ");
                            int year = Integer.parseInt(keyboard.nextLine());
                            System.out.println("\nEnter vehicle month of registration: ");
                            int month = Integer.parseInt(keyboard.nextLine());
                            System.out.println("\nEnter vehicle day of registration: ");
                            int day = Integer.parseInt(keyboard.nextLine());
                            System.out.println("\nEnter vehicle mileage: ");
                            int mileage = Integer.parseInt(keyboard.nextLine());
                            System.out.println("\nEnter vehicle latitude coordinate: ");
                            double latitude = Double.parseDouble(keyboard.nextLine());
                            System.out.println("\nEnter vehicle longitude coordinate: ");
                            double longitude = Double.parseDouble(keyboard.nextLine());


                            if (enterType.equalsIgnoreCase("Van") ||
                                    enterType.equalsIgnoreCase("Truck") ){
                                System.out.println("\nEnter vehicle loadSpace: ");
                                double loadSpace = keyboard.nextDouble();
                                Vehicle v1 = new Van(enterType,enterMake, enterModel, milesPerKwH,
                                        registration,costPerMile,year, month, day,
                                        mileage, latitude, longitude, loadSpace);
                                //add New vehicle
                                vehicleManager.addNewVehicle(v1);
                            }
                            if(enterType.equalsIgnoreCase("Car") || enterType.equalsIgnoreCase("4x4")) {

                                System.out.println("\nEnter vehicle seat number: ");
                                int numSeats = keyboard.nextInt();
                                Vehicle v1 = new Van(enterType,enterMake, enterModel, milesPerKwH,
                                        registration,costPerMile,year, month, day,
                                        mileage, latitude, longitude, numSeats);

                                vehicleManager.addNewVehicle(v1);

//                                vehicleManager.addNewVehicleFiLe(enterType,enterMake, enterModel, milesPerKwH,
//                                        registration,costPerMile,year, month, day,
//                                        mileage, latitude, longitude, numSeats);
                            }

                            System.out.println("*-----------------------------*");
                            System.out.println("*   New vehicle is added      *");
                            System.out.println("*-----------------------------*\n");

                            keyboard.nextLine();

                        } catch (Exception e) {
                            keyboard.nextLine();
                            System.out.println(INPUT_MIS_MATCH);

                        }


                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);
    }

    private void displayBookingMenu() {
        final String MENU_ITEMS =
                          "*------------------------------------------*\n"
                        + "*           *** BOOKING MENU ***           *\n"
                        + "*------------------------------------------*\n"
                        + "*   1. Show all Booking                    *\n"
                        + "*   2. Find Booking by ID                  *\n"
                        + "*   3. Add new booking                     *\n"
                        + "*   4. Edit booking by ID                  *\n"
                        + "*   5. Delete booking by ID                *\n"
                        + "*   6. Exit                                *\n"
                        + "*------------------------------------------*\n"
                        + "*             Enter Option [1,6]           *\n"
                        + "*------------------------------------------*";

        final int SHOW_ALL = 1;
        final int FIND_BY_ID = 2;
        final int ADD_NEW_BOOKING = 3;
        final int EDIT_BOOKING = 4;
        final int DELETE_BOOKING = 5;
        final int EXIT = 6;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case SHOW_ALL:
                        System.out.println("*-----------------------------*");
                        System.out.println("*    Display ALL Bookings     *");
                        System.out.println("*-----------------------------*");
                        bookingManager.displayAllBookings();

                        break;

                    case FIND_BY_ID:
                        System.out.println("*------------------------------*");
                        System.out.println("*    Find Booking by ID        *");
                        System.out.println("*------------------------------*");
                        System.out.println("Please enter booking ID : ");
                        int id = Integer.parseInt(keyboard.nextLine()) ;
                        Booking b = bookingManager.findBooking(id);
                        if (b == null)
                            System.out.println("No Booking matching the ID \"" + id + "\"");
                        else
                            System.out.println("Found Booking: \n" + b.toString());
                        break;

                    case ADD_NEW_BOOKING:
                        System.out.println("*---------------------------*");
                        System.out.println("*    Add new booking        *");
                        System.out.println("*---------------------------*");

                        bookingManager.displayAllBookings();
                        passengerStore.displayAllPassengers();
                        vehicleManager.displayAllVehicles();

                        System.out.println("Please enter passenger ID: ");
                        int passengerId = keyboard.nextInt();
                        System.out.println("Please enter vehicle ID: ");
                        int vehicleId = keyboard.nextInt();
                        System.out.println("Please enter year of the booking: ");
                        int yearOfbooking = keyboard.nextInt();
                        System.out.println("Please enter month of the booking: ");
                        int monthOfbooking = keyboard.nextInt();
                        System.out.println("Please enter day of the booking: ");
                        int dayOfbooking = keyboard.nextInt();
                        System.out.println("Please enter hour of the booking: ");
                        int hourOfbooking = keyboard.nextInt();
                        System.out.println("Please enter minute of the booking: ");
                        int minuteOfbooking = keyboard.nextInt();

                        //2021,2,1
                        LocalDateTime dateBooking = LocalDateTime.of(yearOfbooking, monthOfbooking, dayOfbooking,
                                hourOfbooking, minuteOfbooking);
                        System.out.println("Please enter start latitude of the booking: ");
                        double startLatitude = keyboard.nextDouble();
                        System.out.println("Please enter start longtitude of the booking: ");
                        double startLongtitude = keyboard.nextDouble();

                        LocationGPS startLocation = new LocationGPS(startLatitude,startLongtitude);
                        System.out.println("Please enter end latitude of the booking: ");
                        double endLatitude = keyboard.nextDouble();
                        System.out.println("Please enter end longtitude of the booking: ");
                        double endLongtitude = keyboard.nextDouble();

                        LocationGPS endLocation = new LocationGPS(endLatitude,endLongtitude);
                        System.out.println("Please enter end cost of the booking: ");
                        double cost = bookingManager.costBooking(startLatitude,startLongtitude,endLatitude,endLongtitude);
                        //Booking book1 = new Booking(101,115,dateBooking,startLocation,endLocation,314.34);

                        bookingManager.createBooking(passengerId,vehicleId,dateBooking,
                                startLocation,endLocation,cost);

                       // bookingManager.addBooking();

                        break;

                    case EDIT_BOOKING:
                        System.out.println("*------------------------------*");
                        System.out.println("*    Edit Booking by ID        *");
                        System.out.println("*------------------------------*\n");

                        //display All booking list
                        bookingManager.displayAllBookings();

                        System.out.println("\nPlease enter booking ID : ");
                        int bokngIDedit = keyboard.nextInt();
                        bookingManager.editBooking(bokngIDedit);

                        //display after delete
                        bookingManager.displayAllBookings();
                        break;
                    case DELETE_BOOKING:
                        System.out.println("*-----------------------------*");
                        System.out.println("*    Delete Booking by ID     *");
                        System.out.println("*-----------------------------*");
                        System.out.println("\nEnter booking ID: ");
                        //display All booking list
                        bookingManager.displayAllBookings();

                        int bokngIDdel = keyboard.nextInt();
                        bookingManager.deleteBooking(bokngIDdel);


                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);
    }
}
//
//        // create PassengerStore and load it with passenger records from text file
//        PassengerStore passengerStore = new PassengerStore("passengers.txt");
//        System.out.println("List of all passengers:");
//
//        //creating new passangers
//        passengerStore.addPassenger("Iggy Pop", "iggy@gmail.com", "0448691644", 34.3623, -23.2345);
//        passengerStore.addPassenger("Jony Rotten", "JohnRott@gmail.com", "04458691644", 54.3623, -13.2345);
//        //display all passengers
//        passengerStore.displayAllPassengers();
//        //TODO
//        //find passenger by name
//
//        //vehicle manager file reading
//        VehicleManager vehicleManager = new VehicleManager("vehicles.txt");
//        System.out.println("List of all Vehicles:");
//        //display all vehicles
//        vehicleManager.displayAllVehicles();
//
//
//        //find vehicle by registration number
//        System.out.println("\n****************Vechicle with regNumber 151D987105************ ");
//        String reg = "151D987105";
//        Vehicle v = vehicleManager.findvehicleByRegNumber(reg);
//        //vehicleManager.findvehicleByRegNumber(reg);
//
//        if (v != null) {
//            System.out.println(v);
//        } else {
//            System.out.println("Vehicle not found");
//        }
//        //todo
//        //display vehicle by Registration order - sort the array by reg - just  display no return
//        //display vehicle by Type - findByType- return array list
//
//        //creating new booking
//        BookingManager bookingManager = new BookingManager("bookings.txt");
//
//        //create new cars
//
//
//        Vehicle car = new Car("Car", "Mercedes", "E220", 4,
//                "171D23507", 7.00, 2017, 8, 2,
//                186000, 54.2543, -16.4444, 6);
//
//        Vehicle car1 = new Car("4x4", "Mercedes", "G550", 4,
//                "101D23507", 7.00, 2010, 3, 23,
//                245600, 54.2543, -16.4444, 7);
//
//        Vehicle van1 = new Van("VAN", "Mercedes", "Sprinter", 4,
//                "121D23507", 7.00, 2012, 3, 23,
//                345600, 24.2543, -6.4444, 700);
//
//        vehicleManager.addNewCar(car);
//        vehicleManager.addNewCar(car1);
//        vehicleManager.addNewCar(van1);
//        System.out.println("\n**********Added new Vehicle from User*****************");
//        vehicleManager.displayAllVehicles();
//
//        System.out.println("\n************Add new booking Bookings********************");
//        // Booking book1 = new Booking();
//
//        System.out.println("\n************List of all Bookings********************");
//        bookingManager.displayAllBookings();
//
//        System.out.println("\nProgram exiting... Goodbye");