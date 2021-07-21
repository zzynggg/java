package edu.monash.fit2099;

import edu.monash.fit2099.exceptions.BidException;

/**
 * An interface(contract) for autoShowroom
 * @author Yong Zi Ying
 * @version 1.0.0
 * @see AutoShowroom
 */
public interface IAutoShowroom {
    /**
     * Classes that implements IAutoShowroom class must implement this method to create Vehicle
     */
    void createCars();

    /**
     * Classes that implements IAutoShowroom class must implement this method to get the user input
     * @return must return a valid integer
     */
    default Integer inputMenu() {
        return null;
    }

    /**
     * Classes that implements IAutoShowroom class must implement this method to create sedan
     */
    void createSedan();

    /**
     * Classes that implements IAutoShowroom class must implement this method to create truck
     */
    void createTruck();

    /**
     * Classes that implements IAutoShowroom class must implement this method to create buyer
     */
    void createBuyer();
    
    /**
     * Classes that implements IAutoShowroom class must implement this method to create bid
     * @throws BidException if any error caught must raise the exception
     */
    void createBid() throws BidException;
}
