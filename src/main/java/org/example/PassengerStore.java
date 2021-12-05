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

    @Override
    public String toString() {
        return "PassengerStore{" +
                "passengerList=" + passengerList +
                '}';
    }

    // TODO - see functional spec for details of code to add
   //Display all passenegers
    public void displayAllPassengers() {
        if (!passengerList.isEmpty()) {
            for (Passenger p : this.passengerList) {
                System.out.println(p.toString());
            }
        } else {
            System.out.println("\n ~~##   There is no passengers in the list!   ##~~");
        }

    }
    //Add new passenger
    public void addPassenger(String name, String email, String phone, double latitude, double longitude) throws IOException {
        Passenger passenger1 = new Passenger(name, email, phone, latitude, longitude);
        boolean found = false;
        for (Passenger p : passengerList) {

            if (p.equals(passenger1)) {
                found = true;
                System.out.println("There is a passenger with the same details!");
                break; //its stops the for loop here
            }
        }
        if (found == false) {

           passengerList.add(passenger1);

            System.out.println("*-----------------------------*");
            System.out.println("*   New passenger is added    *");
            System.out.println("*-----------------------------*\n");
        }

    }
    public static void write(Object s,File f)throws IOException{
        FileWriter writer1 = new FileWriter(f,true);
        writer1.write((char[]) s);
        writer1.close();
    }


    //ADD Passenger in the file
    public void addPassengerInFile(String name, String email, String phone, double latitude, double longitude) {

        Passenger passenger1 = new Passenger(name, email, phone, latitude, longitude);

        boolean found = false;
        for (Passenger p : passengerList) {

            if (p.equals(passenger1)) {
                found = true;
                System.out.println("The passenger already exist");
                break; //its stops the for loop here
            }
        }

        if (found == false) {
            try {
                // BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\teodo\\Desktop\\aYEAR2\\OOP\\Projects\\CA1-vechicleManager\\passangerNew.txt"));
                FileReader reader = new FileReader("C:\\Users\\teodo\\Desktop\\aYEAR2\\OOP\\Projects\\CA1-vechicleManager\\passangerNew.txt");

                FileWriter writer = new FileWriter("C:\\Users\\teodo\\Desktop\\aYEAR2\\OOP\\Projects\\CA1-vechicleManager\\passangerNew.txt");


               // writer.write(String.valueOf(passenger1));

                writer.append(String.valueOf(reader) + String.valueOf(passenger1));
                writer.close();
                passengerList.add(passenger1);
                System.out.println("*-----------------------------*");
                System.out.println("*   New passenger is added    *");
                System.out.println("*-----------------------------*\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void editPassenger(int id) {
        Scanner kb = new Scanner(System.in);
        boolean found = false;
        for (Passenger p : passengerList) {

            if (p.getId() == id) {
                 found = true;
                System.out.println("Please enter passenger new Name: ");
                String newName = kb.nextLine();
                System.out.println("Please enter passenger new Email: ");
                String newEmail = kb.nextLine();
                System.out.println("Please enter passenger new Phone number: ");
                String newPhone = kb.nextLine();
                System.out.println("Please enter passenger new latitude location: ");
                double newLatitude = kb.nextDouble();
                System.out.println("Please enter passenger new longitude location: ");
                double newLongitude = kb.nextDouble();

                Passenger editPassenger = new Passenger(newName, newEmail, newPhone, newLatitude, newLongitude);
                passengerList.set(p.getId(),editPassenger);
                System.out.println("\n~~##  The passenger with id " + id + " is edited.  ##~~");
                break;
            }

        }
        if (found == false) {
            System.out.println("\n~~##  There is no passenger with id " + id + " in the list!   ##~~");
        }
    }
    //FIND Passenger by name
    public Passenger findPassengerByName(String name) {

        for (Passenger p : passengerList) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
                //  System.out.println(v);
            }
        }
        return null;
    }

    //FIND Passenger by ID
    public Passenger findPassengerByID(int id) {

        for (Passenger p : passengerList) {
            if (p.getId() == id) {
                return p;
                //  System.out.println(v);
            }
        }
        return null;
    }
    //Delete passenger
    public void deletePassenger(int id) {
        for (Passenger p : passengerList) {

            if(p.getId() == id) {

                passengerList.remove(p);
                System.out.println("The passenger with id " + id + " is deleted.");
                break;
            }
        }
    }

} // end class
