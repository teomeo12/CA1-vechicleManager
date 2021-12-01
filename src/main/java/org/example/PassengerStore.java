package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PassengerStore {

    private final ArrayList<Passenger> passengerList;

    public PassengerStore(String fileName) {
        this.passengerList = new ArrayList<>();
        loadPassengerDataFromFile(fileName);
    }

    public List<Passenger> getAllPassengers() {
        return this.passengerList;
    }

    public void displayAllPassengers() {
        for (Passenger p : this.passengerList) {
            System.out.println(p.toString());
        }
    }

    /**
     * Read Passenger records from a text file and create and add Passenger
     * objects to the PassengerStore.
     */
    private void loadPassengerDataFromFile(String filename) {

        try {
            Scanner sc = new Scanner(new File(filename));
//           Delimiter: set the delimiter to be a comma character ","
//                    or a carriage-return '\r', or a newline '\n'
            sc.useDelimiter("[,\r\n]+");

            while (sc.hasNext()) {
                int id = sc.nextInt();
                String name = sc.next();
                String email = sc.next();
                String phone = sc.next();
                double latitude = sc.nextDouble();
                double longitude = sc.nextDouble();

                // construct a Passenger object and add it to the passenger list
                passengerList.add(new Passenger(id, name, email, phone, latitude, longitude));
            }
            sc.close();

        } catch (IOException e) {
            System.out.println("Exception thrown. " + e);
        }
    }

    @Override
    public String toString() {
        return "PassengerStore{" +
                "passengerList=" + passengerList +
                '}';
    }

    // TODO - see functional spec for details of code to add
    public void addPassenger(String name, String email, String phone, double latitude, double longitude) {
        Passenger passenger1 = new Passenger(name, email, phone, latitude, longitude);
        boolean found = false;
        for (Passenger p : passengerList) {

            if (p.equals(passenger1)) {
                found = true;
                break; //its stops the for loop here
            }
        }
        if (found == false) {
            passengerList.add(passenger1);
        }

    }
    public Passenger findPassengerByName(String name) {

        for (Passenger p : passengerList) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
                //  System.out.println(v);
            }
        }
        return null;
    }
    public void addPassengerInFile(String name, String email, String phone, double latitude, double longitude) {
//        Passenger passenger1 = new Passenger(name, email, phone, latitude, longitude);
//        try {
//           BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\teodo\\Desktop\\aYEAR2\\OOP\\Projects\\CA1-vechicleManager\\output.txt"));
//            bw.write(String.valueOf(new Passenger(name, email, phone, latitude, longitude)));
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Passenger passenger1 = new Passenger(name, email, phone, latitude, longitude);

        try {
            FileWriter writer = new FileWriter("passangerNew.txt");

            writer.write(String.valueOf(passenger1));

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//
//        boolean found = false;
//        for (Passenger p : passengerList) {
//
//            if (p.equals(passenger1)) {
//                found = true;
//                break; //its stops the for loop here
//            }
//        }
//        if (found == false) {
//            passengerList.add(passenger1);
//        }


    }

} // end class
