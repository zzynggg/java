package edu.monash.fit2099.vehicles;

import edu.monash.fit2099.exceptions.TruckException;
import edu.monash.fit2099.exceptions.VehicleException;

/**
 * The Truck class is for user to add new Truck based on Truck's maker, model, capacity and wheels
 *
 * @author Yong Zi Ying
 * @version 1.0.0
 * @see TruckException
 * @see Vehicle
 */
public class Truck extends Vehicle {

    /**
     * An Integer truck's capacity in tons, in the range of 1..15
     */
    private int capacity;

    /**
     * An Integer truck's wheels, in the range of 4..16
     */
    private int wheels;

    /**
     * A truck class constructor with 4 parameters and the Vehicle ID is generated randomly in super class
     *
     * @param maker    A String truck's maker, in the range of 3..15
     * @param model    A String truck's model, in the range of 3..15
     * @param capacity An Integer truck's capacity, in the range of 1..15
     * @param wheels   An Integer truck's wheels, in the range of 4..16
     * @throws VehicleException      When the setCapacity or setWheels return false catch the exception
     * @throws NumberFormatException When user input String datatype for capacity or wheels catch the exception
     */
    public Truck(String maker, String model, int capacity, int wheels) throws VehicleException, NumberFormatException {
        super(maker, model);
        if (!setCapacity(capacity)) throw new TruckException("Invalid number of capacity within 1 - 15");
        if (!setWheels(wheels)) throw new TruckException("Invalid number of wheel within 4 - 16");

    }

    /**
     * A truck class constructor with 5 parameters and the Vehicle ID is given by the user
     *
     * @param newVehicleID A String vehicleID given by user
     * @param maker        A String truck's maker, in the range of 3..15
     * @param model        A String truck's model, in the range of 3..15
     * @param capacity     An Integer truck's capacity, in the range of 1..15
     * @param wheels       An Integer truck's wheels, in the range of 4..16
     * @throws VehicleException      When the setCapacity or setWheels return false catch the exception
     * @throws NumberFormatException When user input String datatype for capacity or wheels catch the exception
     */
    public Truck(String newVehicleID, String maker, String model, int capacity, int wheels) throws VehicleException, NumberFormatException {
        super(newVehicleID, maker, model);
        if (!setCapacity(capacity)) throw new TruckException("Invalid number of capacity within 1 - 15");
        if (!setWheels(wheels)) throw new TruckException("Invalid number of wheel within 4 - 16");

    }

    /**
     * A method for other classes to invoke it in order to set the truck's capacity
     *
     * @param capacity An Integer truck's capacity
     * @return A boolean flag, to make sure the data inputted is accurate
     */
    public boolean setCapacity(int capacity) {
        boolean flag = false;
        if (capacity >= 1 && capacity <= 15) {
            this.capacity = capacity;
            flag = true;
        }
        return flag;
    }

    /**
     * A method for other classes to invoke it in order to set the truck's wheels
     *
     * @param wheels An Integer truck's wheels
     * @return A boolean flag, to make sure the data inputted is accurate
     */
    public boolean setWheels(int wheels) {
        boolean flag = false;
        if (wheels >= 4 && wheels <= 16) {
            this.wheels = wheels;
            flag = true;
        }
        return flag;
    }
}
