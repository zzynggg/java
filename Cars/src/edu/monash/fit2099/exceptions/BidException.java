package edu.monash.fit2099.exceptions;

/**
 * The BidException class is used for Bid class to catch any edu.monash.fit2099.exceptions
 * @author Yong Zi Ying
 * @version 1.0.0
 * @see Exception
 */
public class BidException extends Exception {

    /**
     * A BidException class constructor to initialise the super class instance variable
     *
     * @param message A String message
     */
    public BidException(String message) {
        super(message);
    }
}
