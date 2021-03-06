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


        // create VehicleManager, and load all vehicles from text file
        vehicleManager = new VehicleManager("vehicles.txt");
        // create BookingManager, and load all bookings from text file
         bookingManager = new BookingManager("bookings.txt",vehicleManager,passengerStore);



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
                        passengerStore.addPassengerInFile();
                        vehicleManager.addToFIle();
                        bookingManager.addBookingInFile();

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
                + "*  4. Edit Passenger          *\n"
                + "*  5. Delete Passenger        *\n"
                + "*  6. Exit                    *\n"
                + "*-----------------------------*\n"
                + "*     Enter Option [1,6]      *\n"
                + "*-----------------------------*";

        final int SHOW_ALL = 1;
        final int FIND_BY_NAME = 2;
        final int ADD_NEW_PASSENGER = 3;
        final int EDIT_PASSENGER = 4;
        final int DELETE_PASSENGER = 5;
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


                        boolean isNum = false;
                        while (isNum != true) {
                            try {
                                String name = keyboard.nextLine();
                                isNum = true;
                               Passenger p =  passengerStore.findPassengerByName(name);
                                System.out.println(p);

                            } catch (InputMismatchException e) {
                                keyboard.nextLine();
                                System.out.println("Please enter a letters for Name!!!");
                            }
                        }

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
                        boolean isDouble = false;
                        while (isDouble != true) {
                            try {
                                System.out.println("\nEnter passenger latitude coordinate: ");
                                double platitude = Double.parseDouble(keyboard.nextLine());

                                System.out.println("\nEnter passenger longitude coordinate: ");
                                double plongitude = Double.parseDouble(keyboard.nextLine());
                                isDouble = true;
                                Passenger p1 = new Passenger(pName, pEmail, pPhone, platitude, plongitude);

                                passengerStore.addNewPassenger(p1);

                            } catch (Exception e) {

                                System.out.println(INPUT_MIS_MATCH);

                            }
                        }
                        break;

                    case EDIT_PASSENGER:
                        System.out.println("*-----------------------------*");
                        System.out.println("*      Edit passenger         *");
                        System.out.println("*-----------------------------*");
                        //Display all passengers
                        passengerStore.displayAllPassengers();

                        System.out.println("\nEnter passenger ID to edit: ");
                        boolean isNum1 = false;
                        while (isNum1 != true) {
                            try {
                                int passID = keyboard.nextInt();
                                isNum1 = true;
                                passengerStore.editPassenger(passID);
                                break;
                            } catch (InputMismatchException e) {
                                keyboard.nextLine();
                                System.out.println("Please enter a number for ID!!!");
                            }
                        }
                        break;


                    case DELETE_PASSENGER:
                        System.out.println("*-----------------------------*");
                        System.out.println("*    Delete Passenger by ID   *");
                        System.out.println("*-----------------------------*");

                        //Display all passengers
                        passengerStore.displayAllPassengers();
                        System.out.println("\nEnter passenger ID to delete: ");
                        boolean isID = false;
                        while (isID != true) {
                            try {
                                int passIDdel = keyboard.nextInt();
                                isID = true;
                                //Delete passenger by ID - passing ID
                                passengerStore.deletePassenger(passIDdel);

                               // break;
                            } catch (InputMismatchException e) {
                                 keyboard.nextLine();
                                System.out.println("Please enter a number for ID!!!");
                            }
                        }

                        break;
                    case EXIT:

                        vehicleManager.addToFIle();
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException | IOException e) {
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
                        + "*   6. Delete Vehicle                        *\n"
                        + "*   7. Exit                                  *\n"
                        + "*--------------------------------------------*\n"
                        + "*             Enter Option [1,7]             *\n"
                        + "*--------------------------------------------*";

        final int SHOW_ALL = 1;
        final int FIND_BY_REG = 2;
        final int FIND_BY_TYPE = 3;
        final int FIND_BY_NUMSEATS = 4;
        final int ADD_NEW_VEHICLE = 5;
        final int DELETE_VEHICLE = 6;
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
                        System.out.println("*    Display ALL Vehicles     *");
                        System.out.println("*-----------------------------*");
                        vehicleManager.displayAllVehicles();

                        break;
                    case FIND_BY_REG:
                        System.out.println("*--------------------------------------------*");
                        System.out.println("*    Find Vehicle by Registration number     *");
                        System.out.println("*--------------------------------------------*");
                        System.out.println("Please enter Registration number of the vehicle: ");
                        String regNumber = keyboard.nextLine();
                        Vehicle v = vehicleManager.findvehicleByRegNumber(regNumber);
                        if (v == null)
                            System.out.println("No Vehicle matching the registration number \"" + regNumber + "\"");
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
                            System.out.println("No Vehicle matching the type \"" + type + "\"");
                        else
                            for (Vehicle vtype : listType) {
                                System.out.println(vtype);
                            }
                        break;
                    case FIND_BY_NUMSEATS:
                        System.out.println("*----------------------------------------*");
                        System.out.println("*    Find Vehicle by Number of Seats     *");
                        System.out.println("*----------------------------------------*");
                        System.out.println("Please enter Number of Seats: ");
                       // int numOfSeats = Integer.parseInt(keyboard.nextLine());
                        boolean isNumSeat = false;
                        while (isNumSeat != true) {
                            try {
                                int numOfSeats = keyboard.nextInt();
                                isNumSeat = true;
                                ArrayList<Vehicle> listNumSeat = vehicleManager.findvehicleByNumSeat(numOfSeats);
                                if (listNumSeat.isEmpty())
                                    System.out.println("No Vehicle with \"" + numOfSeats + "\"  seats!!");
                                else
                                    for (Vehicle vNumSeat : listNumSeat) {
                                        System.out.println(vNumSeat);
                                    }
                            } catch (InputMismatchException e) {
                                keyboard.nextLine();
                                System.out.println("Enter number for Number of seats!!!");
                            }
                        }
                        break;
                    case ADD_NEW_VEHICLE:
                        System.out.println("*-----------------------------*");
                        System.out.println("*      Add new Vehicle        *");
                        System.out.println("*-----------------------------*");
                        System.out.println("\nEnter vehicle type(Car, 4x4, Van or Truck): ");

                        String enterType = keyboard.nextLine();
                        System.out.println("\nEnter vehicle make: ");
                        String enterMake = keyboard.nextLine();
                        System.out.println("\nEnter vehicle model: ");
                        String enterModel = keyboard.nextLine();
                    //    try {
                            System.out.println("\nEnter vehicle milesPerKwH: ");
                            int milesPerKwH = Integer.parseInt(keyboard.nextLine());
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
                                double loadSpace = Double.parseDouble(keyboard.nextLine());
                                Vehicle v1 = new Van(enterType,enterMake, enterModel, milesPerKwH,
                                        registration,costPerMile,year, month, day,
                                        mileage, latitude, longitude, loadSpace);

                                //add New VAN || Truck
                                vehicleManager.addNewVehicle(v1);


                            }
                            if(enterType.equalsIgnoreCase("Car") || enterType.equalsIgnoreCase("4x4")) {

                                System.out.println("\nEnter vehicle seat number: ");
                                int numSeats = Integer.parseInt(keyboard.nextLine());
                                Vehicle v2 = new Car(enterType,enterMake, enterModel, milesPerKwH,
                                        registration,costPerMile,year, month, day,
                                        mileage, latitude, longitude, numSeats);
                                //ADD new Car || 4x4
                                vehicleManager.addNewVehicle(v2);

                            }


                       // } catch (IOException e) {
                           // keyboard.nextLine();
                          //  System.out.println(INPUT_MIS_MATCH);

                      //  }
                        break;
                    case DELETE_VEHICLE:
                        System.out.println("*-----------------------------*");
                        System.out.println("*    Delete Vehicle  by ID     *");
                        System.out.println("*-----------------------------*");

                        //Display all passengers
                        vehicleManager.displayAllVehicles();
                        System.out.println("\nEnter passenger ID to delete: ");
                        boolean isID = false;
                        while (isID != true) {
                            try {

                                int passIDdel = keyboard.nextInt();
                                isID = true;
                                //Delete passenger by ID - passing ID
                                vehicleManager.deleteVehicle(passIDdel);

                                // break;
                            } catch (InputMismatchException e) {
                                keyboard.nextLine();
                                System.out.println("Please enter a number for ID!!!");
                            }
                        }

                        break;
                    case EXIT:

                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException | IOException e) {
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
                        + "*   2. Show all current Bookings           *\n"
                        + "*   3. Find Booking by ID                  *\n"
                        + "*   4. Find Booking by Passenger Name      *\n"
                        + "*   5. Add new booking                     *\n"
                        + "*   6. Edit booking by ID                  *\n"
                        + "*   7. Delete booking by ID                *\n"
                        + "*   8. Exit                                *\n"
                        + "*------------------------------------------*\n"
                        + "*             Enter Option [1,8]           *\n"
                        + "*------------------------------------------*";

        final int SHOW_ALL = 1;
        final int SHOW_ALL_CURRENT = 2;
        final int FIND_BY_ID = 3;
        final int FIND_BY_PASSNAME = 4;
        final int ADD_NEW_BOOKING = 5;
        final int EDIT_BOOKING = 6;
        final int DELETE_BOOKING = 7;
        final int EXIT = 8;

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
                    case SHOW_ALL_CURRENT:
                        System.out.println("*-------------------------------------*");
                        System.out.println("*    Display ALL Current Bookings     *");
                        System.out.println("*-------------------------------------*");
                        bookingManager.displayCurrentBookings();

                        break;

                    case FIND_BY_ID:
                        System.out.println("*------------------------------*");
                        System.out.println("*    Find Booking by ID        *");
                        System.out.println("*------------------------------*");
                        System.out.println("Please enter booking ID : ");

                        // int id = keyboard.nextInt() ;
                        boolean isID = false;
                        while (isID != true) {
                            try {

                                int passID = keyboard.nextInt();
                                isID = true;
                                Booking b = bookingManager.findBooking(passID);
                                System.out.println(b);

                                keyboard.nextLine();
                            } catch (InputMismatchException e) {
                                keyboard.nextLine();
                                System.out.println("Please enter a number for ID!!!");
                            }
                        }

                        break;

                    case FIND_BY_PASSNAME:
                        System.out.println("*---------------------------------------*");
                        System.out.println("*    Find Booking by Passenger Name     *");
                        System.out.println("*---------------------------------------*");
                        System.out.println("Please enter passenger Name : ");

                        // int id = keyboard.nextInt() ;
                        boolean isName = false;
                        while (isName != true) {
                            try {

                                String passName = keyboard.nextLine();
                                isName = true;
                                Booking b = bookingManager.findBookingByName(passName);
                                System.out.println(b);

                                // break;
                            } catch (InputMismatchException e) {
                                keyboard.nextLine();
                                System.out.println("Please enter a number for ID!!!");
                            }
                        }

                        break;

                    case ADD_NEW_BOOKING:
                        System.out.println("*---------------------------*");
                        System.out.println("*    Add new booking        *");
                        System.out.println("*---------------------------*");

                        bookingManager.displayAllBookings();
                        passengerStore.displayAllPassengers();
                        vehicleManager.displayAllVehicles();

                        System.out.println("Please choose passenger ID: ");
                        int passengerId = Integer.parseInt(keyboard.nextLine()) ;
                        System.out.println("Please choose vehicle ID: ");
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

                        LocalDateTime dateBooking = LocalDateTime.of(yearOfbooking, monthOfbooking, dayOfbooking,
                                hourOfbooking, minuteOfbooking);
                        System.out.println("Please enter start latitude of the booking: ");
                        double startLatitude = Double.parseDouble(keyboard.nextLine());
                        System.out.println("Please enter start longttude of the booking: ");
                        double startLongtitude = Double.parseDouble(keyboard.nextLine());

                        LocationGPS startLocation = new LocationGPS(startLatitude,startLongtitude);
                        System.out.println("Please enter end latitude of the booking: ");
                        double endLatitude = Double.parseDouble(keyboard.nextLine());
                        System.out.println("Please enter end longitude of the booking: ");
                        double endLongtitude = Double.parseDouble(keyboard.nextLine());

                        LocationGPS endLocation = new LocationGPS(endLatitude,endLongtitude);


                        LocationGPS vehDepotLocation = vehicleManager.getLocation(vehicleId);
                        double costPerMile = vehicleManager.getCostPerMIle(vehicleId);


                        double cost = bookingManager.costBooking(startLatitude,startLongtitude,endLatitude,endLongtitude,
                                vehDepotLocation,costPerMile);


                        Booking b1 = new Booking(passengerId,vehicleId,dateBooking,
                                startLocation,endLocation,cost);

                        if (passengerStore.findPassengerByID(passengerId) != null) {

                            if (vehicleManager.findVehicleByID(vehicleId) != null) {
                                bookingManager.addNewBooking(b1);
                            }else {

                                System.out.println("There is no such passenger in the file");
                            }
                        }
                        break;


                    case EDIT_BOOKING:
                        System.out.println("*------------------------------*");
                        System.out.println("*    Edit Booking by ID        *");
                        System.out.println("*------------------------------*\n");

                        //display All booking list
                        bookingManager.displayAllBookings();

                        System.out.println("\nPlease enter booking ID : ");
                        int bokngIDedit = Integer.parseInt(keyboard.nextLine());
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
                        System.out.println("\nPlease enter booking ID : ");
                        boolean isIDdel = false;
                        while (isIDdel != true) {
                            try {
                                int bokngIDdel = keyboard.nextInt();

                                //Delete booking by ID - passing ID
                                bookingManager.deleteBooking(bokngIDdel);
                                keyboard.nextLine();
                                isIDdel = true;
                                // break;
                            } catch (InputMismatchException e) {
                                keyboard.nextLine();
                                System.out.println("Please enter a number for ID!!!");
                            }
                        }

                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException | IOException e) {
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