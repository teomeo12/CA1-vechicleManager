package org.example;

public class Car extends Vehicle{
    private int numSeats;



    public Car(String type, String make, String model, double milesPerKwH,
               String registration, double costPerMile, int year, int month, int day,
               int mileage, double latitude, double longitude,
               int seats) {
        super(type, make, model, milesPerKwH,
                registration, costPerMile, year, month, day,
                mileage, latitude, longitude);
        this.numSeats = seats;
    }

    // Constructor version to be used to recreate a Car that was read from file.
    public Car(int id, String type, String make, String model, double milesPerKwH,
               String registration, double costPerMile, int year, int month, int day,
               int mileage, double latitude, double longitude,
               int seats) {
        super(id, type, make, model, milesPerKwH,
                registration, costPerMile, year, month, day,
                mileage, latitude, longitude);
        this.numSeats = seats;
    }
    public int getNumSeats() {
        return numSeats;
    }

    public void setNumSeats(int numSeats) {
        this.numSeats = numSeats;
    }

    @Override
    public String toString() {
        return "Car{" +
                "seats=" + numSeats +
                '}' + super.toString();
    }
}
