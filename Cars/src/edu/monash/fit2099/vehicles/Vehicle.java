package edu.monash.fit2099.vehicles;

import edu.monash.fit2099.bids.BidsManager;
import edu.monash.fit2099.exceptions.VehicleException;

import java.util.Random;

/**
 * The abstract Vehicle class is the parent class of Sedan and Truck
 *
 * @author Yong Zi Ying
 * @version 1.0.0
 * @see BidsManager
 * @see VehicleException
 */
//make it an abstract class
public abstract class Vehicle {
    /**
     * A String vehicle's maker, in the range of 3..15
     */
    private String maker;
    /**
     * A String vehicle's model, in the range of 3..15
     */
    private String model;
    /**
     * A String vehicle's vehicleID
     */
    private String vehicleID;       // another attribute represents its unique ID
    /**
     * A bidManager that allowed buyer to add bid to a specific vehicle
     */
    private BidsManager bidsManager;

    /**
     * A Vehicle class constructor with 2 parameters and the Vehicle ID is generated randomly
     *
     * @param maker A String vehicle's maker, in the range of 3..15
     * @param model A String vehicle's model, in the range of 3..15
     * @throws VehicleException When the setMaker or setModel return false catch the exception
     */
    public Vehicle(String maker, String model) throws VehicleException {
        // accepts the maker and model attributes and generates a random id
        // vehicleID didn't given
        if (!setMaker(maker)) {
            throw new VehicleException("Invalid maker, length of maker should be within 3-15");
        }
        if (!setModel(model)) {
            throw new VehicleException("Invalid model, length of model should be within 3-15");
        }
        if (setMaker(maker) && setModel(model)) {
            // generates a random id
            Random random = new Random();  // to generate random ID
            int randomID = random.nextInt(1000);
            vehicleID = "V" + randomID;
            this.bidsManager = new BidsManager();
        }

    }

    /**
     * A Vehicle class constructor with 3 parameters and the Vehicle ID is given by the user
     *
     * @param newVehicleID A String vehicleID given by user
     * @param maker        A String vehicle's maker, in the range of 3..15
     * @param model        A String vehicle's model, in the range of 3..15
     * @throws VehicleException When the setMaker or setModel return false catch the exception
     */
    public Vehicle(String newVehicleID, String maker, String model) throws VehicleException {
        // three-parameter constructor
        // vehicleID given
        if (!setMaker(maker)) {
            throw new VehicleException("Invalid maker, length of maker should be within 3-15");
        }
        if (!setModel(model)) {
            throw new VehicleException("Invalid model, length of model should be within 3-15");
        }
        if (setMaker(maker) && setModel(model)) {
            vehicleID = newVehicleID;
            this.bidsManager = new BidsManager();
        }
    }

    /**
     * A method for other classes to invoke it in order to get the vehicle's maker
     *
     * @return A String vehicle's maker
     */
    public String getMaker() {
        return maker;
    }

    /**
     * A method for other classes to invoke it in order to set the vehicle's maker
     *
     * @param maker A String vehicle's maker
     * @return A boolean flag, to make sure the data inputted is accurate
     */
    public boolean setMaker(String maker) {
        boolean flag = false;
        if (maker.length() >= 3 && maker.length() <= 15) {
            this.maker = maker;
            flag = true;
        }
        return flag;
    }

    /**
     * A method for other classes to invoke it in order to get the vehicle's model
     *
     * @return A String vehicle's model
     */
    public String getModel() {
        return model;
    }

    /**
     * A method for other classes to invoke it in order to set the vehicle's model
     *
     * @param model A String vehicle's model
     * @return A boolean flag, to make sure the data inputted is accurate
     */
    public boolean setModel(String model) {
        boolean flag = false;
        if (model.length() >= 3 && model.length() <= 15) {
            this.model = model;
            flag = true;
        }
        return flag;
    }

    /**
     * A method for other classes to invoke it in order to get the vehicle's vehicleID
     *
     * @return A String vehicleID
     */
    public String getVehicleID() {
        return vehicleID;
    }

    /**
     * A method for other classes to invoke it in order to set the vehicle's vehicleID
     *
     * @param vehicleID A String vehicleID
     */
    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    /**
     * A method for other classes to invoke it in order to get the vehicle's bidsManager
     *
     * @return An instance of BidsManager class
     */
    public BidsManager getBidsManager() {
        return bidsManager;
    }

    /**
     * A method for other classes to invoke it in order to set the vehicle's bidsManager
     *
     * @param bidsManager An instance of BidsManager class
     */
    public void setBidsManager(BidsManager bidsManager) {
        this.bidsManager = bidsManager;
    }

    /**
     * To output the description of the Vehicle
     *
     * @return A String output
     */
    public String description() {
        String output;
        output = "[" + vehicleID + ", " + getMaker() + ", " + getModel() + "]";
        return output;
    }
}
