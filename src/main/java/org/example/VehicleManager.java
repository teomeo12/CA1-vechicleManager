package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class VehicleManager {
    private final ArrayList<Vehicle> vehicleList;  // for Car and Van objects
    private Object FileWriter;

    //constructor
    public VehicleManager(String fileName) {
        this.vehicleList = new ArrayList<>();
        loadVehiclesFromFile(fileName);
    }
    //Reading file method
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
                    double loadSpace = sc.nextDouble();
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
    //Passing and Add new vehicle to the Vehicle constructor
    public Vehicle addNewVehicle(Vehicle e) throws IOException {

        if(e instanceof Car ){
            vehicleList.add(e);
            System.out.println("*-----------------------------*");
            System.out.println("*   New Car is added          *");
            System.out.println("*-----------------------------*\n");
        }else {
            vehicleList.add(e);
            System.out.println("*-----------------------------*");
            System.out.println("*   New Vehicle is added      *");
            System.out.println("*-----------------------------*\n");
        }
        return null;
    }

    //Add the vehicles to the file "vehicle.txt"
    public void addToFIle() throws IOException{

        FileWriter writer = new FileWriter("C:\\Users\\teodo\\Desktop\\aYEAR2\\OOP\\Projects\\CA1-vechicleManager\\vehicles.txt");
        for (Vehicle v : vehicleList) {

            if (v instanceof Car) {
               String data = v.getId()+","+v.getType() +","+ v.getMake() +","+ v.getModel() +","+ v.getMilesPerKm() +","+
                v.getRegistration() +"," + v.getCostPerMile() +","+ v.getLastServicedDate().getYear()+","+v.getLastServicedDate().getMonthValue()+","+v.getLastServicedDate().getDayOfMonth() +","+ v.getMileage() +","+
                       v.getDepotGPSLocation().getLatitude() +","+ v.getDepotGPSLocation().getLongitude() +","+ ((Car) v).getNumSeats();

                writer.append(data +"\n");

            }if (v instanceof Van){
                String data = v.getId()+","+v.getType() +","+ v.getMake() +","+ v.getModel() +","+ v.getMilesPerKm() +","+
                        v.getRegistration() +"," + v.getCostPerMile() +","+ v.getLastServicedDate().getYear()+","+v.getLastServicedDate().getMonthValue()+","+v.getLastServicedDate().getDayOfMonth() +","+ v.getMileage() +","+
                        v.getDepotGPSLocation().getLatitude() +","+ v.getDepotGPSLocation().getLongitude() +","+ ((Van) v).getLoadSpace();

                writer.append(data +"\n");
            }

        }
        writer.close();
        System.out.println("The data in the vehicle file is updated and saved!!!");
    }
    //Delete vehicle
    public void deleteVehicle(int id) {
        for (Vehicle v : vehicleList) {

            if(v.getId() == id) {

                vehicleList.remove(v);
                System.out.println("The vehicle with id " + id + " is deleted.");
                break;
            }
        }
    }
    //Display all vehicles method
    public void displayAllVehicles() {
        if (!vehicleList.isEmpty()) {
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-5s %-6s %-10s %-15s %-15s %-15s %-10s %-13s %-10s %-10s %-15s \n","ID" ," Type"," Make"," Model" ,"Miles/KwH","Registration","Cost/mile","Last service","Milage","Latitude","Longtitude");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
            for (Vehicle v : this.vehicleList) {
                System.out.printf("%-5d %-6s %-10s %-15s %-15s %-15s %-10s %-13s %-10s %-10s %-15.4f  \n",v.getId() , v.getType(), v.getMake(),v.getModel(),v.getMilesPerKm(),v.getRegistration(),v.getCostPerMile(),
                        v.getLastServicedDate(),v.getMileage(),v.getDepotGPSLocation().getLatitude(),v.getDepotGPSLocation().getLongitude());

            }
        } else {
            System.out.println("\n ~~##   There is no passengers in the list!   ##~~");
        }
    }
    //Get Vehicle location
    public LocationGPS getLocation(int id){
        for (Vehicle v : vehicleList) {
            if(v.getId() == id){
                return  v.getDepotGPSLocation();
            }

        }
        return null;
    }
    //Get Vehicle Cost per mile
    public double getCostPerMIle(int id){
        for (Vehicle v : vehicleList) {
            if(v.getId() == id){
                return  v.getCostPerMile();
            }
        }
        return 0;
    }

    //FIND Vehicle by ID
    public Vehicle findVehicleByID(int id) {

        for (Vehicle v : vehicleList) {
            if (v.getId() == id) {
                return v;
            }
        }
        System.out.println("\n~~##  There is no Vehicle with ID " + id + " in the list!   ##~~");

        return null;
    }
    //Find all vehicles by registration number method
    public Vehicle findvehicleByRegNumber(String reg) {

        for (Vehicle v : vehicleList) {
            if (v.getRegistration().equalsIgnoreCase(reg)) {
                return v;
            }
        }
        System.out.println("\n~~##  There is no Vehicle with Registration Number:  " + reg + " in the list!   ##~~");

        return null;
    }
    //Find all vehicles by Type of vehicle e.g. VAN,4x4  method
    public ArrayList findvehicleByType(String type) {
        ArrayList<Vehicle> newList = new ArrayList<>();
        for (Vehicle v : vehicleList) {
            if (v.getType().equalsIgnoreCase(type)) {
                newList.add(v);
            }
        }
        return newList;
    }
    //Find vehicle by number of seats
    public ArrayList findvehicleByNumSeat(int numSeats) {

        ArrayList<Vehicle> newList1 = new ArrayList<>();
        for (Vehicle v : vehicleList) {
            if (v instanceof Car) {
                if(((Car) v).getNumSeats() ==numSeats)
                    newList1.add(v);

            }
        }
        return newList1;
    }


    @Override
    public String toString() {
        return "VehicleManager{" +
                "vehicleList=" + vehicleList +
                '}';
    }


}

