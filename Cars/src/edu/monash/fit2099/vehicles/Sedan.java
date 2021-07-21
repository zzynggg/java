package edu.monash.fit2099.vehicles;

import edu.monash.fit2099.exceptions.SedanException;
import edu.monash.fit2099.exceptions.VehicleException;

/**
 * The Sedan class is for user to add new Sedan based on Sedan's maker, model and seats
 *
 * @author Yong Zi Ying
 * @version 1.0.0
 * @see Vehicle
 * @see SedanException
 */
public class Sedan extends Vehicle {
    /**
     * An Integer seats for the Vehicle(Sedan), in the range of 4..5
     */
    private int seats;

    /**
     * A Sedan class constructor with 3 parameters and the Vehicle ID is generated randomly in super class
     *
     * @param maker A String Sedan's maker, in the range of 3..15
     * @param model A String Sedan's model, in the range of 3..15
     * @param seats An Integer Sedan's seats, in the range of 4..5
     * @throws VehicleException      When the setSeats return false catch the exception
     * @throws NumberFormatException When user input String datatype for seats catch the exception
     */
    public Sedan(String maker, String model, int seats) throws VehicleException, NumberFormatException {
        super(maker, model);
        if (!setSeats(seats))
            throw new SedanException("Invalid number of seats, either 4 or 5 seats are acceptable only");
    }

    /**
     * A Sedan class constructor with 4 parameters and the Vehicle ID is given by the user
     *
     * @param newVehicleID A String vehicleID given by user
     * @param maker        A String Sedan's maker, in the range of 3..15
     * @param model        A String Sedan's model, in the range of 3..15
     * @param seats        An Integer Sedan's seats, in the range of 4..5
     * @throws VehicleException      When the setSeats return false catch the exception
     * @throws NumberFormatException When user input String datatype for seats catch the exception
     */
    public Sedan(String newVehicleID, String maker, String model, int seats) throws VehicleException, NumberFormatException {
        super(newVehicleID, maker, model);
        if (!setSeats(seats))
            throw new SedanException("Invalid number of seats, either 4 or 5 seats are acceptable only");
    }

    /**
     * A method for other classes to invoke it in order to get the seats
     *
     * @return An Integer seats
     */
    public int getSeats() {
        return seats;
    }

    /**
     * A method for other classes to invoke it in order to set the seats
     *
     * @param seats An Integer seats
     * @return A boolean flag, to make sure the data inputted is accurate
     */
    public boolean setSeats(int seats) {
        boolean flag = false;
        if (seats == 4 || seats == 5) {
            this.seats = seats;
            flag = true;
        }
        return flag;
    }

}
