package edu.monash.fit2099.buyers;

import java.util.Random;

/**
 * The Buyer class is used to create a new Buyer based on the buyerID, buyer's given name and last name
 *
 * @author Yong Zi Ying
 * @version 1.0.0
 * @see Random
 */
public class Buyer {

    // private attributes
    /**
     * A String buyerID
     */
    private String buyerId;

    /**
     * A String buyer's given/first name, in the range of 2..15
     * using given and family name is to ensure all
     * client's input are appropriate.
     * some client can't differentiate between first name
     * and last name(including me as well). Ended up the
     * input will mess up due to inappropriate input from client.
     */
    private String givenName;

    /**
     * A String buyer's family/last name, in the range of 2..15
     */
    private String familyName;

    /**
     * A zero parameter Buyer constructor
     */
    private Buyer() {
    }

    /**
     * A Buyer constructor that initialise the buyerID
     *
     * @param newBuyerId A String buyerID
     */
    private Buyer(String newBuyerId) {
        buyerId = newBuyerId;
    }

    /**
     * A Buyer constructor with 3 parameters and initialises all 3 parameters
     *
     * @param newBuyerId    A String buyerID
     * @param newGivenName  A String buyer's given/first name, in the range of 2..15
     * @param newFamilyName A String buyer's family/last name, in the range of 2..15
     */
    private Buyer(String newBuyerId, String newGivenName, String newFamilyName) {
        buyerId = newBuyerId;
        givenName = newGivenName;
        familyName = newFamilyName;
    }

    /**
     * A static method that restrict the developer to get and set the value from Buyer class easily
     *
     * @param givenName  A String buyer's given/first name, in the range of 2..15
     * @param familyName A String buyer's family/last name, in the range of 2..15
     * @return An instance of Buyer class
     */
    public static Buyer getInstance(String givenName, String familyName) {
        Buyer buyer = new Buyer();
        Random random = new Random();  // to generate random ID
        int randomID = random.nextInt(1000);  // range 0 to 999
        buyer.setBuyerId("B" + randomID);
        if (!buyer.setGivenName(givenName) || !buyer.setFamilyName(familyName)) {
            buyer = null;
        }
        return buyer;

    }

    /**
     * A static method that restrict the developer to get and set the value from Buyer class easily
     *
     * @param buyerId A String buyerID
     * @return An instance of Buyer class
     */
    public static Buyer getInstanceID(String buyerId) {
        Buyer buyer = new Buyer();
        buyer.setBuyerId(buyerId);
        return buyer;
    }

    /**
     * A method for other classes to invoke it in order to get the buyerID
     *
     * @return A String buyerID
     */
    public String getBuyerId() {
        return buyerId;
    }

    /**
     * A method for other classes to invoke it in order to set the buyerID
     *
     * @param buyerId A String buyerID
     */
    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    /**
     * A method for other classes to invoke it in order to get the buyer's given name
     *
     * @return A String givenName
     */
    public String getGivenName() {
        return givenName;
    }

    /**
     * A method for other classes to invoke it in order to get the buyer's family name
     *
     * @return A String familyName
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * A method for other classes to invoke it in order to set the buyer's given name
     *
     * @param givenName A String givenName
     * @return A boolean flag, to make sure the data inputted is accurate
     */
    public boolean setGivenName(String givenName) {
        boolean flag = false;
        if (givenName.length() >= 2 && givenName.length() <= 15) {
            this.givenName = givenName;
            flag = true;
        }
        return flag;
    }

    /**
     * A method for other classes to invoke it in order to set the buyer's family name
     *
     * @param familyName A String familyName
     * @return A boolean flag, to make sure the data inputted is accurate
     */
    public boolean setFamilyName(String familyName) {
        boolean flag = false;
        if (familyName.length() >= 2 && familyName.length() <= 15) {
            this.familyName = familyName;
            flag = true;
        }
        return flag;
    }

    /**
     * To output the description of the Buyer
     *
     * @return A String output
     */
    public String description() {
        String output = "[" + buyerId + ", " + getGivenName() + ", " + getFamilyName() + "]";
        return output;
    }

}
