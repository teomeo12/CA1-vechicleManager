package org.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class VehicleManager {
    private final ArrayList<Vehicle> vehicleList;  // for Car and Van objects
    //constructor
    public VehicleManager(String fileName) {
        this.vehicleList = new ArrayList<>();
        loadVehiclesFromFile(fileName);
    }

    public void displayAllVehicles() {
        for (Vehicle v : vehicleList)
            System.out.println(v.toString());
    }

    public void loadVehiclesFromFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
//           Delimiter: set the delimiter to be a comma character ","
//                    or a carriage-return '\r', or a newline '\n'
            sc.useDelimiter("[,\r\n]+");

            while (sc.hasNext()) {
                int id = sc.nextInt();
                String type = sc.next();  // vehicle type
                String make = sc.next();
                String model = sc.next();
                double milesPerKwH = sc.nextDouble();
                String registration = sc.next();
                double costPerMile = sc.nextDouble();
                int year = sc.nextInt();   // last service date
                int month = sc.nextInt();
                int day = sc.nextInt();
                int mileage = sc.nextInt();
                double latitude = sc.nextDouble();  // Depot GPS location
                double longitude = sc.nextDouble();



                if (type.equalsIgnoreCase("Van") ||
                        type.equalsIgnoreCase("Truck") ) {
                    int loadSpace = sc.nextInt();
                    // construct a Van object and add it to the passenger list
                    vehicleList.add(new Van(id, type, make, model, milesPerKwH,
                            registration, costPerMile,
                            year, month, day,
                            mileage, latitude, longitude,
                            loadSpace));

                }
                if(type.equalsIgnoreCase("Car") || type.equalsIgnoreCase("4x4")){
                    int numSeats = sc.nextInt();
                    vehicleList.add(new Car( type, make, model, milesPerKwH,
                            registration, costPerMile,
                            year, month, day,
                            mileage, latitude, longitude, numSeats));
                }
            }
            sc.close();

        } catch (IOException e) {
            System.out.println("Exception thrown. " + e);
        }
    }

    //TODO add more functionality as per spec.
    public Vehicle findvehicleByRegNumber(String reg) {

        for (Vehicle v : vehicleList) {
            if (v.getRegistration().equalsIgnoreCase(reg)) {
                return v;
                //  System.out.println(v);
            }
        }
        return null;
    }


    public Vehicle addNewCar(Vehicle e){
        vehicleList.add(e);
        return null;
    }

    public Vehicle deleteVehicle(Vehicle e){
        vehicleList.remove(e);
        return null;
    }


}
