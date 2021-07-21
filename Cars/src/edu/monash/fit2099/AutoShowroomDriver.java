package edu.monash.fit2099;

/**
 * The AutoShowroomDriver is the main class of entire program
 *
 * @author Yong Zi Ying
 * @version 1.0.0
 * @see edu.monash.fit2099.AutoShowroom
 */
public class AutoShowroomDriver {

    /**
     * A static main method to execute the code
     *
     * @param args A String array
     */
    public static void main(String[] args) {
        edu.monash.fit2099.AutoShowroom autoShowroom = new edu.monash.fit2099.AutoShowroom();
        autoShowroom.printStatus();
    }
}
