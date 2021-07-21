package edu.monash.fit2099.exceptions;

/**
 * The VehicleException class is the parent class of TruckException and SedanException
 *
 * @author Yong Zi Ying
 * @version 1.0.0
 * @see Exception
 */
public class VehicleException extends Exception {
    /**
     * A VehicleException class constructor to initialise the super class instance variable
     *
     * @param message A String message
     */
    public VehicleException(String message) {
        super(message);
    }
}
