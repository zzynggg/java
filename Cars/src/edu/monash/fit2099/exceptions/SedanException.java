package edu.monash.fit2099.exceptions;

/**
 * The SedanException class is used for Sedan class to catch any edu.monash.fit2099.exceptions
 *
 * @author Yong Zi Ying
 * @version 1.0.0
 * @see VehicleException
 */
public class SedanException extends VehicleException {

    /**
     * A SedanException class constructor to initialise the super class instance variable
     *
     * @param message A String message
     */
    public SedanException(String message) {
        super(message);
    }
}
