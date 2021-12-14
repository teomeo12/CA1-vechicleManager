package org.example;

import java.io.*;
import java.util.*;

public class PassengerStore {

    private final ArrayList<Passenger> passengerList;

    public PassengerStore(String fileName) {
        this.passengerList = new ArrayList<>();
        loadPassengerDataFromFile(fileName);
    }

    public List<Passenger> getAllPassengers() {
        return this.passengerList;
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

    // TODO - see functional spec for details of code to add
    //Display all passengers
    public void displayAllPassengers() {
        if (!passengerList.isEmpty()) {
            System.out.println("--------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-5s %-15s %-25s %-15s %-15s %-15s\n", "ID", " Name", " Email", " Phone", "Latitude", "Longtitude");
            System.out.println("--------------------------------------------------------------------------------------------------------------");
            for (Passenger p : this.passengerList) {
                System.out.printf("%-5d %-15s %-25s %-15s %-15s %-15s \n", p.getId(), p.getName(), p.getEmail(), p.getPhone(), p.getLocation().getLatitude(), p.getLocation().getLongitude());
                // System.out.println(p.toString());
            }
        } else {
            System.out.println("\n ~~##   There is no passengers in the list!   ##~~");
        }

    }

    //Add new passenger
    public Passenger addNewPassenger(Passenger p) throws IOException {
        boolean found = false;
        for (Passenger p1 : passengerList) {
            if (p1.equals(p)) {
                found = true;
                System.out.println("There is a passenger with the same details!");
                break; //its stops the for loop here
            }
        }
        if (found == false) {

            passengerList.add(p);

            System.out.println("*-----------------------------*");
            System.out.println("*   New passenger is added    *");
            System.out.println("*-----------------------------*\n");
        }
        return null;
    }

    //ADD Passenger in the file   C:\Users\teodo\Desktop\aYEAR2\OOP\Projects\CA1-vechicleManager\
    public void addPassengerInFile() throws IOException {
        FileWriter writer = new FileWriter("passengers.txt");
        for (Passenger p : passengerList) {
            String data = p.getId() + "," + p.getName() + "," + p.getEmail() + "," + p.getPhone() + "," + p.getLocation().getLatitude() + "," + p.getLocation().getLongitude();
            writer.append(data + "\n");

        }
        writer.close();
        System.out.println("The data in the passenger file is updated and saved!!!");

    }

    //Edit Passenger
    public void editPassenger(int id) {

        Passenger p = findPassengerByID(id);

        Scanner kb = new Scanner(System.in);
        System.out.println("Please enter passenger new Name: ");
        String newName = kb.nextLine();
        System.out.println("Please enter passenger new Email: ");
        String newEmail = kb.nextLine();
        System.out.println("Please enter passenger new Phone number: ");
        String newPhone = kb.nextLine();
        System.out.println("Please enter passenger new latitude location: ");
        double newLatitude = Double.parseDouble(kb.nextLine());
        System.out.println("Please enter passenger new longitude location: ");
        double newLongitude = Double.parseDouble(kb.nextLine());

        p.setName(newName);
        p.setEmail(newEmail);
        p.setPhone(newPhone);
        p.setLocation(newLatitude, newLongitude);

        System.out.println("*--------------------------------------------------*");
        System.out.println("*      The passenger with ID " + id + " is edited.     *");
        System.out.println("*--------------------------------------------------*");
    }

    //Delete passenger
    public void deletePassenger(int id) {
        for (Passenger p : passengerList) {

            if (p.getId() == id) {

                passengerList.remove(p);
                System.out.println("The passenger with id " + id + " is deleted.");
                break;
            }
        }
    }

    //FIND Passenger by name
    public Passenger findPassengerByName(String name) {

        for (Passenger p : passengerList) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        System.out.println("\n~~##  There is no passenger with name " + name + " in the list!   ##~~");
        return null;
    }

    //FIND Passenger by ID
    public Passenger findPassengerByID(int id) {

        for (Passenger p : passengerList) {
            if (p.getId() == id) {
                return p;
            }
        }
        System.out.println("\n~~##  There is no passenger with id " + id + " in the list!   ##~~");
        return null;
    }

    @Override
    public String toString() {
        return "PassengerStore{" +
                "passengerList=" + passengerList +
                '}';
    }

} // end class


