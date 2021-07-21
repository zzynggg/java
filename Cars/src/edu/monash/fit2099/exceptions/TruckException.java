package edu.monash.fit2099.exceptions;

/**
 * The TruckException class is used for Truck class to catch any edu.monash.fit2099.exceptions
 *
 * @author Yong Zi Ying
 * @version 1.0.0
 * @see VehicleException
 */
public class TruckException extends VehicleException {
    /**
     * A TruckException class constructor to initialise the super class instance variable
     *
     * @param message A String message
     */
    public TruckException(String message) {
        super(message);
    }
}
